package view_Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import util.MD5Util;

public class AddEmpView extends JDialog {

	// 部门选择
	private JComboBox depBox = new JComboBox();

	// 时间选择
	private JComboBox yearBox = new JComboBox();
	private JComboBox monthBox = new JComboBox();
	private JComboBox dayBox = new JComboBox();

	private JComboBox yearBox1 = new JComboBox();
	private JComboBox monthBox1 = new JComboBox();
	private JComboBox dayBox1 = new JComboBox();

	private JRadioButton mButton = new JRadioButton("男");
	private JRadioButton fButton = new JRadioButton("女");

	JLabel userIDLable = new JLabel("请输入身份证号：");
	JTextField userIDField = new JTextField();
	JLabel tishiLabel = new JLabel("请输入下列信息：");
	JLabel id = new JLabel("您的卡号为：");
	JLabel id2 = new JLabel();
	JLabel empnumLabel = new JLabel("员工编号");
	JLabel empnameLabel = new JLabel("员工姓名");
	JLabel empsexLabel = new JLabel("员工性别");
	JLabel empbornLabel = new JLabel("出生日期");
	JLabel emptelLabel = new JLabel("电话号码");
	JLabel emphiredayLabel = new JLabel("入职日期");
	JLabel depLabel = new JLabel("选择部门");
	JLabel password = new JLabel("请输入你的密码");
	JLabel password2 = new JLabel("请再次输入你的密码");

	JLabel empnumField = new JLabel();
	JTextField empnameField = new JTextField();
	JTextField emptelField = new JTextField();
	JTextField emphiredayField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JPasswordField passwordField2 = new JPasswordField();

	private JButton addButton = new JButton("添加");
	private JButton clearButton = new JButton("清除");

	public AddEmpView() {
		this.setTitle("添加员工");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		sb.append("62170029");
		for (int i = 0; i < 11; i++) {
			// System.out.println(random.nextInt(10000));
			sb.append(random.nextInt(10));
		}
		//System.out.println(sb);
		id2.setText(sb.toString());
		final String userAccount = sb.toString();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select depnum,depname from dep";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				view_Admin.Dep dep = new view_Admin.Dep();
				dep.setDepnum(result.getInt(1));
				dep.setDepname(result.getString(2));
				depBox.addItem(dep);

			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String maxnum=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection6 = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql6 = "select max(empnum) from emp";
			PreparedStatement ps6 = connection6.prepareStatement(sql6);
			ResultSet rs6 = null;
			rs6  = ps6.executeQuery();
			while(rs6.next()){
				maxnum = rs6.getString(1);
			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		int empnum = Integer.parseInt(maxnum)+1;
		empnumField.setText(empnum+"");
		
		
		// 渲染器
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);

		tishiLabel.setBounds(90, 45, 160, 20);
		add(tishiLabel);
		id.setBounds(250, 45, 100, 20);
		add(id);
		id2.setBounds(360, 45, 170, 20);
		add(id2);
		
		
		empnumLabel.setBounds(40, 100, 69, 25);
		empnumField.setBounds(145, 100, 100, 25);
		add(empnumLabel);
		add(empnumField);

		empnameLabel.setBounds(270, 100, 69, 25);
		empnameField.setBounds(356, 100, 120, 25);
		add(empnameLabel);
		add(empnameField);

		empsexLabel.setBounds(40, 150, 69, 25);
		add(empsexLabel);
		mButton.setBounds(140, 150, 40, 20);
		fButton.setBounds(210, 150, 40, 20);
		userIDLable.setBounds(270, 145, 170, 30);
		userIDField.setBounds(380, 145, 170, 30);
		password.setBounds(40, 350, 100, 30);
		add(password);
		passwordField.setBounds(150, 350, 130, 30);
		add(passwordField);
		password2.setBounds(300, 350, 130, 30);
		add(password2);
		passwordField2.setBounds(430, 350, 130, 30);
		add(passwordField2);
		mButton.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(mButton);
		group.add(fButton);
		add(mButton);
		add(fButton);
		add(userIDLable);
		add(userIDField);
		empbornLabel.setBounds(40, 200, 69, 25);
		yearBox.setBounds(150, 200, 80, 20);
		monthBox.setBounds(270, 200, 75, 20);
		dayBox.setBounds(375, 200, 75, 20);
		add(empbornLabel);
		add(yearBox);
		add(monthBox);
		add(dayBox);

		emptelLabel.setBounds(40, 250, 69, 25);
		emptelField.setBounds(150, 250, 120, 25);
		add(emptelLabel);
		add(emptelField);

		depLabel.setBounds(320, 250, 65, 25);
		depBox.setBounds(400, 250, 95, 25);
		add(depLabel);
		add(depBox);

		addButton.setBounds(320, 410, 60, 25);
		add(addButton);
		clearButton.setBounds(420, 410, 60, 25);
		add(clearButton);

		Calendar calendar = Calendar.getInstance();
		java.util.Date now = new java.util.Date();
		calendar.setTime(now);
		int year = calendar.get(Calendar.YEAR);

		for (int i = year; i >= 1900; i--) {
			yearBox.addItem(i);
		}

		for (int i = 1; i <= 12; i++) {
			monthBox.addItem(i);
		}

		for (int i = 1; i <= 31; i++) {
			dayBox.addItem(i);
		}
		// 月份改变的时候
		monthBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int month = (Integer) monthBox.getSelectedItem();

				if (month == 1 || month == 3 || month == 5 || month == 7
						|| month == 8 || month == 10 || month == 12) {

					dayBox.removeAllItems();

					for (int i = 1; i <= 31; i++) {
						dayBox.addItem(i);
					}

				}

				if (month == 4 || month == 6 || month == 9 || month == 11) {

					dayBox.removeAllItems();

					for (int i = 1; i <= 30; i++) {
						dayBox.addItem(i);
					}

				}

				if (month == 2) {
					int year = (Integer) yearBox.getSelectedItem();
					if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
						for (int i = 1; i <= 29; i++) {
							dayBox.addItem(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							dayBox.addItem(i);
						}

					}

				}

			}
		});

		// 年份改变的时候
		yearBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int month = (Integer) monthBox.getSelectedItem();

				if (month == 1 || month == 3 || month == 5 || month == 7
						|| month == 8 || month == 10 || month == 12) {

					dayBox.removeAllItems();

					for (int i = 1; i <= 31; i++) {
						dayBox.addItem(i);
					}

				}

				if (month == 4 || month == 6 || month == 9 || month == 11) {

					dayBox.removeAllItems();

					for (int i = 1; i <= 30; i++) {
						dayBox.addItem(i);
					}

				}

				if (month == 2) {
					int year = (Integer) yearBox.getSelectedItem();
					if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
						for (int i = 1; i <= 29; i++) {
							dayBox.addItem(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							dayBox.addItem(i);
						}

					}

				}

			}
		});

		emphiredayLabel.setBounds(40, 300, 69, 25);
		add(emphiredayLabel);

		yearBox1.setBounds(150, 300, 80, 20);
		monthBox1.setBounds(270, 300, 75, 20);
		dayBox1.setBounds(375, 300, 75, 20);
		add(yearBox1);
		add(monthBox1);
		add(dayBox1);

		// int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(calendar.DAY_OF_MONTH);

		for (int i = year; i <= 2300; i++) {
			yearBox1.addItem(i);
		}

		for (int i = month; i <= 12; i++) {
			monthBox1.addItem(i);
		}

		for (int i = day; i <= 31; i++) {
			dayBox1.addItem(i);
		}
		// 月份改变的时候
		monthBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int month = (Integer) monthBox1.getSelectedItem();

				if (month == 1 || month == 3 || month == 5 || month == 7
						|| month == 8 || month == 10 || month == 12) {

					dayBox1.removeAllItems();

					for (int i = 1; i <= 31; i++) {
						dayBox1.addItem(i);
					}

				}

				if (month == 4 || month == 6 || month == 9 || month == 11) {

					dayBox1.removeAllItems();

					for (int i = 1; i <= 30; i++) {
						dayBox1.addItem(i);
					}

				}

				if (month == 2) {
					int year = (Integer) yearBox1.getSelectedItem();
					if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
						for (int i = 1; i <= 29; i++) {
							dayBox1.addItem(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							dayBox1.addItem(i);
						}

					}

				}

			}
		});

		// 年份改变的时候
		yearBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int month = (Integer) monthBox1.getSelectedItem();
				int year1 = (Integer) yearBox1.getSelectedItem();
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = new java.util.Date();
				calendar.setTime(now);
				int month1 = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(calendar.DAY_OF_MONTH);
				int year = calendar.get(Calendar.YEAR);
				if (year == year1) {
					for (int i = month1; i <= 12; i++) {
						monthBox1.addItem(i);
					}

					for (int i = day; i <= 31; i++) {
						dayBox1.addItem(i);
					}
				}

				if (month == 1 || month == 3 || month == 5 || month == 7
						|| month == 8 || month == 10 || month == 12) {

					dayBox1.removeAllItems();

					for (int i = 1; i <= 31; i++) {
						dayBox1.addItem(i);
					}

				}

				if (month == 4 || month == 6 || month == 9 || month == 11) {

					dayBox1.removeAllItems();

					for (int i = 1; i <= 30; i++) {
						dayBox1.addItem(i);
					}

				}

				if (month == 2) {
					if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
						for (int i = 1; i <= 29; i++) {
							dayBox1.addItem(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							dayBox1.addItem(i);
						}

					}

				}

			}
		});

		// 添加按钮 JDBC技术 连数据库
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String empname = empnameField.getText();
				String sex = "男";
				int sex_ = 1;
				String emptel = emptelField.getText();
				if (fButton.isSelected()) {
					sex = "女";
					sex_ = 0;
				}

				String year = (Integer) yearBox.getSelectedItem() + "";
				String month = (Integer) monthBox.getSelectedItem() + "";
				String day = (Integer) dayBox.getSelectedItem() + "";

				String year1 = (Integer) yearBox1.getSelectedItem() + "";
				String month1 = (Integer) monthBox1.getSelectedItem() + "";
				String day1 = (Integer) dayBox1.getSelectedItem() + "";

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				java.sql.Date hireday = null;
				try {
					date = sdf.parse(year1 + "-" + month1 + "-" + day1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hireday = new java.sql.Date(date.getTime());

				java.sql.Date empborn = null;

				try {
					date = sdf.parse(year + "-" + month + "-" + day);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				empborn = new java.sql.Date(date.getTime());
				
				String userID = userIDField.getText();
				if(userID==null){
					JOptionPane.showMessageDialog(null, "身份证号不能为空！");
					return;
				}
				
				int depnum = ((Dep) depBox.getSelectedItem()).getDepnum();
				String password = passwordField.getText();
				String password2 = passwordField2.getText();
				if(password==null||password2==null){
					JOptionPane.showMessageDialog(null, "输入密码不能为空！");
					return;
				}
				if(!password2.equals(password)){
					JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！");
					passwordField.setText("");
					passwordField2.setText("");
					return;
				}
				password = MD5Util.getMD5(MD5Util.getMD5(password));
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection8 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
					String sql2 = "insert into userinfo(userAccount,userPassword,userTel,userID,userName,userSex,ifadmin) values(?,?,?,?,?,?,?)";
					PreparedStatement ps4 = connection8.prepareStatement(sql2);
					
					ps4.setObject(1, userAccount);
					ps4.setObject(2, password);
					ps4.setObject(3, emptel);
					ps4.setObject(4, userID);
					ps4.setObject(5, empname);
					ps4.setObject(6, sex_);
					ps4.setObject(7, 0);
					
					int n2 = ps4.executeUpdate(); 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				int n;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
					String sql = "insert into emp(depnum,empborn,hireday,userAccount) values(?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(sql);
					
					ps.setObject(1, depnum);
					ps.setObject(2, empborn);
					ps.setObject(3, hireday);
					ps.setObject(4, userAccount);
					
					n = ps.executeUpdate();
					if(n!=0){
						JOptionPane.showMessageDialog(null, "添加成功！");
						AddEmpView.this.dispose();
						MainView_Admin mainViewAdmin = new MainView_Admin(userAccount);
						/*empnumField.setText("");
						empnameField.setText("");
						emptelField.setText("");
						mButton.setSelected(true);
						monthBox.setSelectedItem(1);
						dayBox.setSelectedItem(1);
						passwordField.setText("");
						userIDField.setText("");
						passwordField2.setText("");
						Calendar calendar = Calendar.getInstance();
						java.util.Date now = new java.util.Date();
						calendar.setTime(now);
						int year3 = calendar.get(Calendar.YEAR);
						int month3 = calendar.get(Calendar.MONTH) + 1;
						int day3 = calendar.get(calendar.DAY_OF_MONTH);
						yearBox.setSelectedItem(year3);
						dayBox1.setSelectedItem(day3);
						monthBox1.setSelectedItem(month3);
						yearBox1.setSelectedItem(year3);*/
						
						
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		});

		// 清除按钮
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				empnameField.setText("");
				emptelField.setText("");
				mButton.setSelected(true);
				monthBox.setSelectedItem(1);
				dayBox.setSelectedItem(1);
				userIDField.setText("");
				passwordField.setText("");
				passwordField2.setText("");
				userIDField.setText("");
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = new java.util.Date();
				calendar.setTime(now);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(calendar.DAY_OF_MONTH);
				yearBox.setSelectedItem(year);
				dayBox1.setSelectedItem(day);
				monthBox1.setSelectedItem(month);
				yearBox1.setSelectedItem(year);
				return;

			}

		});

		this.setModal(true);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		AddEmpView addEmpView = new AddEmpView();
	}

}
