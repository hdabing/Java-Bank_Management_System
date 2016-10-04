package view_ATM;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import pojo.DespositType;

public class Desposit_Main extends JDialog {

	private Double amount = null;

	private JComboBox typeBox = new JComboBox();

	private JLabel tishi1Label = new JLabel("输入存款金额：");
	private JLabel tishi2Label = new JLabel("请选择存款类型：");

	private JTextField jeField = new JTextField();

	private JRadioButton hqButton = new JRadioButton("活期");
	private JRadioButton dqButton = new JRadioButton("定期");

	private JButton giveupButton = new JButton("放弃交易");
	private JButton yesButton = new JButton("确认存款");

	@SuppressWarnings("unchecked")
	public Desposit_Main(final String account) {

		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		final DecimalFormat df = new DecimalFormat("0.00");
		typeBox.setBounds(380, 130, 110, 30);
		add(typeBox);

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
		yesButton.setBounds(410, 200, 90, 35);
		add(yesButton);

		typeBox.setEnabled(false);

		// 连数据库把定期的类型弄进去下拉列表框
		try {Class.forName("com.mysql.jdbc.Driver");
			Connection connection2 = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
			String sql2 = "select typename from DespositType";
			PreparedStatement ps2 = connection2.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				DespositType despositType = new DespositType();
				despositType.setTypename(rs2.getString(1));
				typeBox.addItem(despositType);

			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		dqButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if (dqButton.isSelected()) {
					typeBox.setEnabled(true);
				}

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		hqButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if (hqButton.isSelected()) {
					typeBox.setEnabled(false);
				}

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		// 先连数据库，把钱读出来
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance from currentAccount where userAccount = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

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

				if (jeField.getText() == null || jeField.getText() == "") {
					JOptionPane.showMessageDialog(null, "输入金额不能为空！");
					return;
				}

				int n = 0;
				if (hqButton.isSelected()) {
					if (jeField.getText() == null || "".equals(jeField.getText())) {
						JOptionPane.showMessageDialog(null, "输入金额不能为空！");
						return;
					}
					int jine1 = Integer.parseInt(jeField.getText());
					if (jine1 % 100 != 0) {
						JOptionPane.showMessageDialog(null, "存入金额只能为100的倍数！");
						jeField.setText("");
						return;
					}
					double zongqian = amount + Double.parseDouble(jine1+"");
					try {
						// 连数据库，存入钱
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, zongqian);
						ps.setObject(2, account);
						n = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (n > 0) {
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "存款成功！");
						JOptionPane.showMessageDialog(null, "您当前账户总余额为："
								+ (df.format(amount + jine1)));

						MainView_User mainView_User = new MainView_User(account);
						Desposit_Main.this.dispose();
					} else {
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "存款失败！");
						return;
					}

				}
				if (dqButton.isSelected()) {

					if (jeField.getText() == null || jeField.getText() == "") {
						JOptionPane.showMessageDialog(null, "输入金额不能为空！");
						return;
					}

					int jine2 = Integer.parseInt(jeField.getText());
					if (jine2 % 100 != 0) {
						JOptionPane.showMessageDialog(null, "存款金额只能为100的倍数！");
						jeField.setText("");
						return;
					}
					String s = typeBox.getSelectedItem().toString();
					if ("三个月定期".equals(s)) {
						// 连数据库判断是否已经存在该账户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"该账户已存在定期存款，无法再次存入！");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// 时间
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*0.25*0.016;
						int y=0;
						// 连数据库，把时间也写进去 新建存款用户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection5 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "添加定期存款成功！");
								JOptionPane.showMessageDialog(null, "三个月后您将获得的利息为："+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("半年定期".equals(s)) {
						// 连数据库判断是否已经存在该账户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"该账户已存在定期存款，无法再次存入！");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// 时间
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*0.5*0.018;
						int y=0;
						// 连数据库，把时间也写进去 新建存款用户
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection5 = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "添加定期存款成功！");
								JOptionPane.showMessageDialog(null, "三个月后您将获得的利息为："+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("一年定期".equals(s)) {
						// 连数据库判断是否已经存在该账户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"该账户已存在定期存款，无法再次存入！");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// 时间
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*1*0.020;
						int y=0;
						// 连数据库，把时间也写进去 新建存款用户
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection5 = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "添加定期存款成功！");
								JOptionPane.showMessageDialog(null, "三个月后您将获得的利息为："+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("两年定期".equals(s)) {
						// 连数据库判断是否已经存在该账户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"该账户已存在定期存款，无法再次存入！");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// 时间
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*2*0.026;
						int y=0;
						// 连数据库，把时间也写进去 新建存款用户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection5 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "添加定期存款成功！");
								JOptionPane.showMessageDialog(null, "三个月后您将获得的利息为："+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("三年定期".equals(s)) {
						// 连数据库判断是否已经存在该账户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"该账户已存在定期存款，无法再次存入！");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// 时间
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*3*0.0325;
						int y=0;
						// 连数据库，把时间也写进去 新建存款用户
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection5 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "添加定期存款成功！");
								JOptionPane.showMessageDialog(null, "三个月后您将获得的利息为："+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 

				}

			}

		});

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Desposit_Main.this.dispose();
				MainView_User mainView_User = new MainView_User(account);

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

	public static void main(String[] args) {
		Desposit_Main desposit_Main = new Desposit_Main("350964661");
	}
}
