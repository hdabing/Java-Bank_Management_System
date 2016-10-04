package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import method.WindowListener;


public class AddSpecialDraw extends JDialog {

	double shijian = 0;
	private double ninterest = 0;
	private double balance = 0;
	private double balance_ = 0;
	
	private JLabel cue1Label = new JLabel("请设置新的特约取款信息");
	private JLabel cue2Label = new JLabel("特约取款ID号：");
	private JLabel cue3Label = new JLabel("特约取款金额：");
	private JLabel cue4Label = new JLabel("特约取款密码：");
	private JLabel cue5Label = new JLabel("提示：ID号为16位");

	private JButton clearButton = new JButton("清空");
	private JButton gzButton = new JButton("更正");
	private JButton fhButton = new JButton("返回");
	private JButton qrButton = new JButton("确认");

	private JTextField idField = new JTextField();
	private JTextField amountField = new JTextField();
	private JTextField passwordField = new JPasswordField();

	public AddSpecialDraw(final String account_) {
		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		gzButton.setBounds(15, 170, 70, 35);
		add(gzButton);
		clearButton.setBounds(450, 167, 70, 35);
		add(clearButton);
		fhButton.setBounds(15, 230, 70, 35);
		add(fhButton);
		qrButton.setBounds(450, 227, 70, 35);
		add(qrButton);

		idField.setBounds(275, 85, 150, 30);
		add(idField);
		amountField.setBounds(275, 140, 150, 30);
		add(amountField);
		passwordField.setBounds(275, 197, 150, 30);
		add(passwordField);

		cue1Label.setBounds(125, 20, 290, 40);
		cue1Label.setFont(new Font("", Font.PLAIN, 26));
		add(cue1Label);

		cue2Label.setBounds(105, 80, 160, 40);
		cue2Label.setFont(new Font("", Font.PLAIN, 20));
		add(cue2Label);

		cue3Label.setBounds(105, 136, 160, 40);
		cue3Label.setFont(new Font("", Font.PLAIN, 20));
		add(cue3Label);

		cue4Label.setBounds(105, 190, 160, 40);
		cue4Label.setFont(new Font("", Font.PLAIN, 20));
		add(cue4Label);

		cue5Label.setBounds(300, 270, 110, 20);
		add(cue5Label);

		try {Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance,interest,operateTime from currentAccount where userAccount = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account_);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				balance = resultSet.getDouble(1);
				double interest = resultSet.getDouble(2);
				Date time = resultSet.getDate(3);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = new java.util.Date();
				calendar.setTime(now);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(calendar.DAY_OF_MONTH);
				calendar.setTime(time);
				int oyear = calendar.get(Calendar.YEAR);
				int omonth = calendar.get(Calendar.MONTH) + 1;
				int oday = calendar.get(Calendar.DAY_OF_MONTH);

				int yshijian = year - oyear;
				int dshijian = 0;

				int dshijian1 = month * 30 + day;
				int dshijian2 = omonth * 30 + oday;

				if (dshijian1 - dshijian2 < 0) {
					dshijian = dshijian2 - dshijian1;
					yshijian = yshijian - 1;
				} else {
					dshijian = dshijian1 - dshijian2;
				}
				shijian = ((double) yshijian * 365 + (double) dshijian) / 365.0;

				ninterest = balance * shijian * 0.0035;

				balance_ = balance + ninterest;
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 更正按钮
		idField.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

				gzButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						idField.setText("");

					}

				});

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

		amountField.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

				gzButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						amountField.setText("");

					}

				});

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

		passwordField.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

				gzButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						passwordField.setText("");

					}

				});

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

		// 清空按钮
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				idField.setText("");
				amountField.setText("");
				passwordField.setText("");
				return;

			}

		});
		// 返回按钮
		fhButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				AddSpecialDraw.this.dispose();
				MainView_User mainView_User = new MainView_User(account_);

			}

		});

		qrButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int n = 0;
				if (idField.getText() == null || "".equals(idField.getText())) {
					JOptionPane.showMessageDialog(null, "输入ID号不能为空");
					return;
				}
				if (amountField.getText() == null
						|| "".equals(amountField.getText())) {
					JOptionPane.showMessageDialog(null, "输入取款金额不能为空！");
					return;
				}
				if (passwordField.getText() == null
						|| "".equals(passwordField.getText())) {
					JOptionPane.showMessageDialog(null, "输入密码不能为空！");
					return;
				}

				String sid = idField.getText();
				String spassword = passwordField.getText();
				int samount = Integer.parseInt(amountField.getText());

				try {Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql = "insert into specialDraw(sID,sPassword,sAmount) values(?,?,?)";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, sid);
					ps.setObject(2, spassword);
					ps.setObject(3, samount);

					n = ps.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(n==0){
					JOptionPane.showMessageDialog(null, "设置新的特约取款失败");
					AddSpecialDraw.this.dispose();
					return;
				}else{
					
					int q = 0;
					int s = Integer.parseInt(amountField.getText());

					if (balance_ - s < 0) {
						JOptionPane.showMessageDialog(null, "金额不足");
						return;
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date1 = new java.util.Date();

					Date date = null;
					date = new Date(date1.getTime());

					try {Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
						PreparedStatement ps1 = connection1.prepareStatement(sql1);
						// 计算利息 然后把时间改变，金额改变，利息改变。

						ps1.setObject(1, balance_ - s);
						ps1.setObject(2, date);
						ps1.setObject(3, ninterest);
						ps1.setObject(4, account_);

						n = ps1.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (n > 0) {
						JOptionPane.showMessageDialog(null, "添加特约取款成功！");
						AddSpecialDraw.this.dispose();
						MainView_User mainView_User = new MainView_User(account_);
						return;
					} else {
						JOptionPane.showMessageDialog(null, "添加特约取款失败！");
						return;
					}

					
					
				}
				
				
			}

		});

		this.setTitle("设置新的特约取款");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(550, 340);
		this.setLocationRelativeTo(null); // 居中
		this.addWindowListener(new WindowListener());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}

}
