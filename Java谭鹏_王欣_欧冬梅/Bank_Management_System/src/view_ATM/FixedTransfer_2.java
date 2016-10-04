package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FixedTransfer_2 extends JDialog{
	
	private JLabel kahaoLabel = new JLabel("请输入转账金额：");
	private JLabel tishiLabel = new JLabel("请输入您的密码：");

	private JTextField kahaoField = new JTextField();
	private JTextField tishiField = new JPasswordField();

	private JButton giveupButton = new JButton("放弃交易");
	private JButton continueButton = new JButton("确定");

	private Double amount_ = null;
	private Double amount_2 = null;
	private String password = null;

	public FixedTransfer_2(final String jaccount, final String paccount) {

		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		kahaoLabel.setBounds(80, 60, 190, 30);
		add(kahaoLabel);
		kahaoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		kahaoField.setBounds(270, 60, 190, 30);
		add(kahaoField);

		tishiLabel.setBounds(80, 130, 190, 30);
		add(tishiLabel);
		tishiLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		tishiField.setBounds(270, 130, 190, 30);
		add(tishiField);

		giveupButton.setBounds(80, 230, 90, 50);
		add(giveupButton);
		continueButton.setBounds(420, 230, 90, 50);
		add(continueButton);

		// double amount = Double.parseDouble(kahaoField.getText());

		// String password = tishiField.getText();

		try {Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance,userPassword from currentAccount where userAccount = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, jaccount);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				amount_ = rs.getDouble(1);
				password = rs.getString(2);

			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql1 = "select balance from currentAccount where userAccount = ? ";
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setObject(1, paccount);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {

				amount_2 = rs.getDouble(1);

			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				FixedTransfer_2.this.dispose();
				MainView_User mainView_User = new MainView_User(jaccount);

			}

		});

		continueButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// 判断金额是否超出，再判断密码

				double amount = Double.parseDouble(kahaoField.getText());
				String password_ = tishiField.getText();
				if (amount > amount_) {
					JOptionPane.showMessageDialog(null, "账户余额不足，无法转出");
					kahaoField.setText("");
					return;
				}
				if (password.equals(password_)) {
					// 一边加一边减

					int n = 0;
					int n1 = 0;

					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "update fixedAccount set balance = ? where userAccount = ?";
						PreparedStatement ps2 = connection
								.prepareStatement(sql);
						ps2.setObject(1, amount_ - amount);
						ps2.setObject(2, jaccount);
						n = ps2.executeUpdate();

						String sql2 = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps3 = connection
								.prepareStatement(sql2);
						ps3.setObject(1, amount_2 + amount);
						ps3.setObject(2, paccount);

						n1 = ps3.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (n > 0 && n1 > 0) {
						JOptionPane.showMessageDialog(null, "转出成功！");
						FixedTransfer_2.this.dispose();
						JOptionPane.showMessageDialog(null, "您的当前余额为："+(amount_ - amount));
						MainView_User mainView_User = new MainView_User(jaccount);
					}

				} else {
					JOptionPane.showMessageDialog(null, "密码错误");
					tishiField.setText("");
					return;
				}

			}

		});

		this.setTitle("转账业务");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(550, 370);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化

	}
	public static void main(String[] args) {
		FixedTransfer_2 fixedTransfer_2 = new FixedTransfer_2("111", "22");
	}

}
