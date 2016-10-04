package view_Counter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MD5Util;
import view_ATM.Transfer_accounts_Main;
import view_Admin.GetAccount_main;
import view_Admin.MainView_Admin;
import view_Counter.Transfer_accounts_Main_counter;


public class GetAccount_Draw  extends JDialog{
	private JLabel account_numLabel = new JLabel("账号");
	private JTextField account_numField = new JTextField();

	private JLabel passwordLabel = new JLabel("密码");
	private JTextField passwordField = new JPasswordField();

	private JButton button1 = new JButton("确认");
	private JButton button2 = new JButton("返回");

	private int s = 0;
	
	
	public  GetAccount_Draw( final String account_admin) {
		// 在构造方法里面进行布局
		final int n = 0;
		this.setLayout(null); // 绝对布局

		account_numLabel.setBounds(40, 25, 40, 20);
		add(account_numLabel);

		account_numField.setBounds(120, 25, 140, 20);
		add(account_numField);

		account_numField.setText("6217002940103996053");
		
		passwordLabel.setBounds(40, 60, 40, 20);
		add(passwordLabel);

		passwordField.setBounds(120, 60, 140, 20);
		add(passwordField);

		button1.setBounds(140, 100, 70, 25);
		add(button1);

		button2.setBounds(230, 100, 70, 25);
		add(button2);

		button1.addActionListener(new ActionListener() { // 添加监听事件

					public void actionPerformed(ActionEvent w) {
						// TODO Auto-generated method stub
						// 从文本框中获取输入值
						String account_num = account_numField.getText();
						String password = passwordField.getText();

						if ("".equals(account_num) || account_num == null) {
							JOptionPane.showMessageDialog(null, "输入账号不能为空！\n\t"
									+ "请重新操作！");
							account_numField.setText("");
							passwordField.setText("");
							return;
						}
						if ("".equals(password) || password == null) {
							JOptionPane.showMessageDialog(null, "输入密码不能为空！\n\t"
									+ "请重新操作！");
							account_numField.setText("");
							passwordField.setText("");
							return;
						}

						try {
							password = MD5Util.getMD5(MD5Util.getMD5(password));
							// 连数据库
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql = "select userAccount,userPassword from userinfo where userAccount = ? and userPassword = ? ";
							PreparedStatement ps = connection
									.prepareStatement(sql);
							ps.setObject(1, account_num);
							ps.setObject(2, password);

							ResultSet rs = ps.executeQuery();
							if (rs.next()) {

								GetAccount_Draw.this.dispose(); // 关闭该界面，下面进入到主界面
								String account = rs.getString(1);
								Draw_counter draw_main_counter = new Draw_counter(account,account_admin);

							} else {
								JOptionPane.showMessageDialog(null, "登陆失败！");
								account_numField.setText("");
								passwordField.setText("");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		button2.addActionListener(new ActionListener() {

			

			public void actionPerformed(ActionEvent e) {
				
				GetAccount_Draw.this.dispose();
				
				MainView_Admin mainView_Admin = new MainView_Admin(account_admin);

			}

		});
		// 添加监听事件 窗体监听事件 关闭窗口
		this.addWindowListener(new WindowAdapter() { // WindowAdapter
														// 是抽象类，不可实例化，这类是调用匿名内部类
					// 重写 override
					public void windowClosing(WindowEvent e) {
						int n = JOptionPane.showConfirmDialog(null, "真的要退出吗？",
								"提示", JOptionPane.YES_NO_OPTION);

						if (n == 0) {
							System.exit(0);
						} else {
							// 添加其他操作
							return;
						}
					}
				});
		this.setTitle("欢迎来到银行管理系统");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(360, 200);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}
}
