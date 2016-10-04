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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class UpdateEmpView extends JDialog {

	// 部门选择
	private JComboBox depBox = new JComboBox();
	// JTextField empnumField = new JTextField();
	private JComboBox empnumBox = new JComboBox();
	// 时间选择
	private JComboBox yearBox = new JComboBox();
	private JComboBox monthBox = new JComboBox();
	private JComboBox dayBox = new JComboBox();

	private JComboBox yearBox1 = new JComboBox();
	private JComboBox monthBox1 = new JComboBox();
	private JComboBox dayBox1 = new JComboBox();

	private JRadioButton mButton = new JRadioButton("男");
	private JRadioButton fButton = new JRadioButton("女");

	JLabel tishiLabel = new JLabel("请输入下列信息：");
	JLabel empnumLabel = new JLabel("员工编号");
	JLabel empnameLabel = new JLabel("员工姓名");
	JLabel empsexLabel = new JLabel("员工性别");
	JLabel empbornLabel = new JLabel("出生日期");
	JLabel emptelLabel = new JLabel("电话号码");
	JLabel emphiredayLabel = new JLabel("入职日期");
	JLabel depLabel = new JLabel("选择部门");

	JTextField empnameField = new JTextField();
	JTextField emptelField = new JTextField();
	JTextField emphiredayField = new JTextField();

	private JButton updateButton = new JButton("修改");
	private JButton clearButton = new JButton("清除");

	public UpdateEmpView() {

		this.setTitle("修改员工信息");
		this.setSize(600, 480);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

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
			String sql2 = "select empnum from emp";
			PreparedStatement ps1 = connection.prepareStatement(sql2);
			ResultSet result2 = ps1.executeQuery();
			while (result2.next()) {
				Emp emp = new Emp();
				emp.setEmpnum(result2.getInt(1));
				empnumBox.addItem(emp);
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 渲染器
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);

		tishiLabel.setBounds(90, 45, 160, 20);
		add(tishiLabel);

		empnumLabel.setBounds(40, 100, 69, 25);
		empnumBox.setBounds(116, 100, 100, 25);
		add(empnumLabel);
		add(empnumBox);

		empnameLabel.setBounds(270, 100, 69, 25);
		empnameField.setBounds(356, 100, 120, 25);
		add(empnameLabel);
		add(empnameField);

		empsexLabel.setBounds(40, 150, 69, 25);
		add(empsexLabel);
		mButton.setBounds(160, 150, 40, 20);
		fButton.setBounds(240, 150, 40, 20);
		mButton.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(mButton);
		group.add(fButton);
		add(mButton);
		add(fButton);

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

		updateButton.setBounds(320, 370, 60, 25);
		add(updateButton);
		clearButton.setBounds(420, 370, 60, 25);
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

		// 修改按钮
		updateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// int depnum = ((Dep) depBox.getSelectedItem()).getDepnum();
				int empnum = ((Emp) empnumBox.getSelectedItem()).getEmpnum();
				String userAccount = "";
				String empname = empnameField.getText();
				String empsex = "男";
				int empsex_ = 1;
				String emptel = emptelField.getText();
				if (fButton.isSelected()) {
					empsex = "女";
					empsex_ = 0;
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

				int depnum = ((Dep) depBox.getSelectedItem()).getDepnum();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection2 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
					String sql2 = "select userAccount from emp where empnum = ?";
					PreparedStatement ps2 = connection2.prepareStatement(sql2);
					ps2.setObject(1, empnum);
					ResultSet rs2 = null;
					rs2 = ps2.executeQuery();
					while(rs2.next()){
						userAccount = (String) rs2.getObject(1);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				int n = 0;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
					String sql = "update emp set depnum=?,empborn=?,hireday=? where empnum = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, depnum);
					ps.setObject(2, empborn);
					ps.setObject(3, hireday);
					ps.setObject(4, empnum);
					n = ps.executeUpdate();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int n2 = 0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection3 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
					String sql3 = "update userinfo set userName=?,userSex=?,userTel=? where userAccount = ?";
					PreparedStatement ps3 = connection3.prepareStatement(sql3);
					ps3.setObject(1, empname);
					ps3.setObject(2, empsex_);
					ps3.setObject(3, emptel);
					ps3.setObject(4, userAccount);
					n2 = ps3.executeUpdate();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				if (n > 0 && n2>0) {
					JOptionPane.showMessageDialog(null, "修改成功！");
					UpdateEmpView.this.disable();
					ShowAllEmp showAllEmp = new ShowAllEmp(userAccount);

				} else {
					JOptionPane.showMessageDialog(null, "修改失败！");

				}

			}

		});

		// 清除按钮
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				empnumBox.setSelectedItem(null);
				empnameField.setText("");
				emptelField.setText("");
				mButton.setSelected(true);
				monthBox.setSelectedItem(1);
				dayBox.setSelectedItem(1);
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
		UpdateEmpView updateEmpView = new UpdateEmpView();
	}

}
