package view_ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Inquiry_main extends JDialog {

	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("请选择所需业务");

	private JLabel yeLabel = new JLabel("您的账户余额为：");
	private JLabel eyeLabel = new JLabel("Account Balance");

	private JLabel ayeLabel = new JLabel("可用余额为：");
	private JLabel aeyeLabel = new JLabel("Available Balance");

	private JTextField yeField = new JTextField();
	private JTextField ayeField = new JTextField();

	private JButton duobizhongButton = new JButton("多币种余额");
	private JButton tuichuButton = new JButton("退出");
	private JButton chaxunjiluButton = new JButton("查询汇率");
	private JButton fanhuiButton = new JButton("返回主菜单");

	public Inquiry_main(final String account_) {

		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		duobizhongButton.setBounds(90, 310, 110, 40);
		add(duobizhongButton);
		tuichuButton.setBounds(90, 370, 110, 40);
		add(tuichuButton);

		chaxunjiluButton.setBounds(480, 310, 110, 40);
		add(chaxunjiluButton);
		fanhuiButton.setBounds(480, 370, 110, 40);
		add(fanhuiButton);

		yeLabel.setBounds(170, 120, 170, 40);
		add(yeLabel);
		yeLabel.setFont(new Font("", Font.PLAIN, 20));

		eyeLabel.setBounds(180, 145, 120, 40);
		add(eyeLabel);
		eyeLabel.setFont(new Font("", Font.PLAIN, 16));

		yeField.setBounds(350, 130, 140, 40);
		add(yeField);
		yeField.setFont(new Font("", Font.PLAIN, 20));

		ayeLabel.setBounds(170, 200, 170, 40);
		add(ayeLabel);
		ayeLabel.setFont(new Font("", Font.PLAIN, 20));

		aeyeLabel.setBounds(180, 230, 130, 40);
		add(aeyeLabel);
		aeyeLabel.setFont(new Font("", Font.PLAIN, 16));

		ayeField.setBounds(350, 210, 140, 40);
		add(ayeField);
		ayeField.setFont(new Font("", Font.PLAIN, 20));

		etishiLabel.setBounds(190, 20, 500, 45);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 33));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(300, 60, 350, 40);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("行楷", Font.PLAIN, 22));

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root",
					"admin");

			String sql = "select balance from currentAccount where userAccount=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account_);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				double yue = rs.getDouble(1);
				System.out.println(yue);
				String Syue = yue + "";
				DecimalFormat aa = new DecimalFormat(".##");
				String yue2 = aa.format(yue);
				yeField.setText(yue2);
				ayeField.setText(yue2);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		chaxunjiluButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Inquiry_end inquiry_end = new Inquiry_end(account_);

			}

		});

		duobizhongButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Inquiry_main.this.dispose();
				MoneyTypes_Main moneyTypes_Main = new MoneyTypes_Main(account_);

			}

		});

		tuichuButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int n = JOptionPane.showConfirmDialog(null, "确定退出？", "提示",
						JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					JOptionPane.showMessageDialog(null, "退出成功！祝您工作顺利！");
					System.exit(0);
				} else {
					return;
				}

			}

		});

		fanhuiButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Inquiry_main.this.dispose();
				MainView_User mainView_User = new MainView_User(account_);

			}

		});

		this.setTitle("查询业务");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(700, 500);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}

	public static void main(String[] args) {
		Inquiry_main inquiry_main = new Inquiry_main("350964661");
	}
}
