package view_Counter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import util.MD5Util;
import view_Admin.MainView_Admin;

public class Register_counter extends JDialog {

	private JLabel l1 = new JLabel("卡号");
	private JLabel l2 = new JLabel("新密码");
	private JLabel l3 = new JLabel("再次输入密码");
	private JLabel l4 = new JLabel("性别");
	private JLabel l5 = new JLabel("姓名");
	private JLabel l6 = new JLabel("身份证号");
	private JLabel l7 = new JLabel("邮箱");
	private JLabel l8 = new JLabel();
	private JLabel l9 = new JLabel("电话");

	private JRadioButton mButton = new JRadioButton("男");
	private JRadioButton fButton = new JRadioButton("女");

	private JTextField mimaField = new JPasswordField();
	private JTextField mima2Field = new JPasswordField();

	private JTextField nameField = new JTextField();
	private JTextField idField = new JTextField();
	private JTextField mailField = new JTextField();
	private JTextField telField = new JTextField();

	private JButton qxButton = new JButton("取消");
	private JButton qdButton = new JButton("确定");
	int sex_;

	public Register_counter(final String account_admin) {

		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		l1.setBounds(80, 40, 80, 35);
		l2.setBounds(80, 95, 80, 35);
		l3.setBounds(80, 150, 80, 35);
		l4.setBounds(80, 205, 80, 35);
		l5.setBounds(80, 260, 80, 35);
		l6.setBounds(80, 315, 80, 35);
		l7.setBounds(80, 370, 80, 35);
		l9.setBounds(80, 425, 80, 35);

		l8.setBounds(200, 40, 200, 35);
		mimaField.setBounds(200, 95, 180, 30);
		mima2Field.setBounds(200, 150, 180, 30);

		mButton.setBounds(210, 205, 70, 30);
		fButton.setBounds(290, 205, 70, 30);

		ButtonGroup group = new ButtonGroup();
		group.add(mButton);
		group.add(fButton);
		add(mButton);
		add(fButton);

		nameField.setBounds(200, 260, 180, 30);
		idField.setBounds(200, 315, 180, 30);
		mailField.setBounds(200, 370, 180, 30);
		telField.setBounds(200, 425, 180, 35);

		qxButton.setBounds(60, 490, 80, 30);
		qdButton.setBounds(330, 490, 80, 30);

		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(l6);
		add(l7);
		add(l9);

		add(l8);
		add(mimaField);
		add(mima2Field);
		add(qxButton);
		add(qdButton);
		add(nameField);
		add(idField);
		add(mailField);
		add(telField);

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 19; i++) {
			// System.out.println(random.nextInt(10000));
			sb.append(random.nextInt(10));
		}
		final String account = sb.toString();

		l8.setText(sb.toString());

		qdButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (mimaField.getText() == null
						|| "".equals(mimaField.getText().trim())
						|| mima2Field.getText() == null
						|| "".equals(mima2Field.getText().trim())
						|| nameField.getText() == null
						|| "".equals(nameField.getText())
						|| idField.getText() == null
						|| "".equals(idField.getText())
						|| telField.getText() == null
						|| "".equals(telField.getText())
						|| mailField.getText() == null
						|| "".equals(mailField.getText())) {
					JOptionPane.showMessageDialog(null, "输入内容不能为空！");
					return;
				}

				// 判断两个框里面的密码是否相等
				String password1 = mimaField.getText();
				String password2 = mima2Field.getText();
				String tel = telField.getText();
				String name = nameField.getText();
				String id = idField.getText();
				String mail = mailField.getText();
				String sex = null;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				if (mButton.isSelected()) {
					sex = "男";
					sex_ = 1;
				} else if (fButton.isSelected()) {
					sex = "女";
					sex_ = 0;
				}

				if (password1.equals(password2)) {
					int n = 0;
					int c = 0;
					password1 = MD5Util.getMD5(MD5Util.getMD5(password1));
					// 连数据库，写进三个数据库
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager
								.getConnection(
										"jdbc:mysql://127.0.0.1/Bank_Management_System",
										"root", "admin");

						String sql = "insert into userinfo(userAccount,userPassword,userName,userId,userSex,userTel,userMail) values(?,?,?,?,?,?,?)";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, account);
						ps.setObject(2, password1);
						ps.setObject(3, name);
						ps.setObject(4, id);
						ps.setObject(5, sex_);
						ps.setObject(6, tel);
						ps.setObject(7, mail);

						n = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager
								.getConnection(
										"jdbc:mysql://127.0.0.1/Bank_Management_System",
										"root", "admin");

						String sql = "insert into currentAccount(userAccount,balance,interest,operateTime) values(?,?,?,?)";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, account);
						ps.setObject(2, 0);
						ps.setObject(3, 0);
						ps.setObject(4, date);

						c = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (n > 0 && c > 0) {
						JOptionPane.showMessageDialog(null, "用户注册成功！请记下您的卡号！");
						JOptionPane.showMessageDialog(null, "您的卡号为：" + account);
						Register_counter.this.dispose();
						MainView_Admin mainView_Admin = new MainView_Admin(
								account_admin);
					}

				} else {
					JOptionPane.showMessageDialog(null, "两次输入密码不一致，请重新操作！");
					mimaField.setText("");
					mima2Field.setText("");
					nameField.setText("");
					idField.setText("");
					mailField.setText("");
					return;
				}

			}

		});

		qxButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Register_counter.this.dispose();
				MainView_Admin mainView_Admin = new MainView_Admin(
						account_admin);

			}

		});

		this.setTitle("新用户注册");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(460, 580);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}

	public static void main(String[] args) {
		Register_counter register_counter = new Register_counter("613258200");
	}

}
