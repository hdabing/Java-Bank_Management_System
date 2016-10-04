package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import method.WindowListener;


public class SpecialDraw extends JDialog {

	private JLabel cue1Label = new JLabel("请输入特约取款凭据");
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

	public SpecialDraw() {

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

		cue1Label.setBounds(145, 20, 260, 40);
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

				SpecialDraw.this.dispose();
				FirstView_User firstView_User = new FirstView_User();

			}

		});

		qrButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
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

					String sql = "select sAmount,sPassword from specialDraw where sID = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, sid);
					ResultSet resultSet = ps.executeQuery();
					while (resultSet.next()) {

						int amount = resultSet.getInt(1);
						String password = resultSet.getString(2);
						if (password.equals(spassword)) {
							if (amount == samount) {
								
								Class.forName("com.mysql.jdbc.Driver");
								Connection connection1 = DriverManager.getConnection(
										"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

								String sql1 = "delete from specialDraw where sID = ?";
								PreparedStatement ps1 = connection1.prepareStatement(sql1);
								ps1.setObject(1, sid);
								int resultSet1 = ps1.executeUpdate();

								if(resultSet1>0){
									JOptionPane.showMessageDialog(null, "特约取款成功！");
									SpecialDraw.this.dispose();
									return;
								}else{
									JOptionPane.showMessageDialog(null, "特约取款失败！");
									SpecialDraw.this.dispose();
									FirstView_User firstView_User = new FirstView_User();
								}
								
							} else {
								JOptionPane.showMessageDialog(null,
										"输入金额与实际金额不符");
								amountField.setText("");
								return;
							}
						} else {

							JOptionPane.showMessageDialog(null, "输入密码错误");
							passwordField.setText("");
							return;

						}

					}
					JOptionPane.showMessageDialog(null, "输入ID号错误");
					idField.setText("");
					amountField.setText("");
					passwordField.setText("");
					return;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		this.setTitle("特约取款");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(550, 340);
		this.setLocationRelativeTo(null); // 居中
		this.addWindowListener(new WindowListener());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}

	public static void main(String[] args) {
		SpecialDraw specialDraw = new SpecialDraw();
	}

}
