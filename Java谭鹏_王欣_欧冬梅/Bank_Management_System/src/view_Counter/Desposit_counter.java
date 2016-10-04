package view_Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import view_ATM.Desposit_Main;
import view_ATM.MainView_User;
import view_Admin.MainView_Admin;


public class Desposit_counter extends JDialog {

private Double amount = null;
	
	private JLabel tishi1Label = new JLabel("输入存款金额：");
	private JLabel tishi2Label = new JLabel("请选择存款类型：");

	private JTextField jeField = new JTextField();

	private JRadioButton hqButton = new JRadioButton("活期");
	private JRadioButton dqButton = new JRadioButton("定期");

	private JButton giveupButton = new JButton("放弃交易");
	private JButton yesButton = new JButton("确认存款");

	public Desposit_counter(final String account_admin, final String account) {

		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		tishi1Label.setBounds(80, 60, 120, 30);
		add(tishi1Label);
		tishi2Label.setBounds(80, 130, 120, 30);
		add(tishi2Label);

		jeField.setBounds(220, 60, 160, 30);
		add(jeField);

		hqButton.setBounds(220, 130, 70, 30);
		dqButton.setBounds(305, 130, 70, 30);

		ButtonGroup group = new ButtonGroup();
		group.add(hqButton);
		group.add(dqButton);
		add(hqButton);
		add(dqButton);

		hqButton.setSelected(true);

		giveupButton.setBounds(60, 200, 90, 35);
		add(giveupButton);
		yesButton.setBounds(380, 200, 90, 35);
		add(yesButton);

		//先连数据库，把钱读出来
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance from currentAccount where userAccount = ?";
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setObject(1, account);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				amount = rs.getDouble(1);
				
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		yesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n=0;
				if (hqButton.isSelected()) {
					int jine = Integer.parseInt(jeField.getText());
					
					try {
						//连数据库，存入钱
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, amount+jine);
						ps.setObject(2, account);
						n = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(n>0){
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "存款成功！");
						JOptionPane.showMessageDialog(null, "您当前账户总余额为："+(amount+jine));
						Desposit_counter.this.dispose();
						MainView_Admin mainView_User = new MainView_Admin(account_admin);
					}else{
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "存款失败！");
						return;
					}
							

				}
				if (dqButton.isSelected()) {
					System.out.println("定期");
				}

			}

		});

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Desposit_counter.this.dispose();
				MainView_Admin mainView_User = new MainView_Admin(account);

			}

		});

		// 渲染器
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);

		this.setTitle("存款");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(550, 330);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}
	
	
	
	
}
