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
import javax.swing.JTextPane;

import util.MD5Util;

import method.WindowListener;


//修改密码
public class RevisePassword extends JDialog{
	
	private JLabel tishiLabel = new JLabel("请输入您的原密码后按确认键");
	private JTextField oldpasswordField = new JPasswordField();
	private JButton yesButton = new JButton("确认");
	private JButton exitButton = new JButton("返回");
	private JButton clearButton = new JButton("清除");
	
	public RevisePassword( final String account_num) {
		
		 
		    this.setTitle("修改密码");
			this.setSize(500, 350);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			this.setLayout(null);
		
			tishiLabel.setBounds(50, 30, 400, 40);
			add(tishiLabel);
			tishiLabel.setFont(new Font("微软雅黑",Font.PLAIN,26));
			
			oldpasswordField.setBounds(150, 100, 200, 50);
			add(oldpasswordField);
			oldpasswordField.setFont(new Font("微软雅黑",Font.PLAIN,20));
			
			exitButton.setBounds(40, 240, 95, 40);
			add(exitButton);
			yesButton.setBounds(310, 180, 95, 40);
			add(yesButton);
			clearButton.setBounds(310, 240, 95, 40);
			add(clearButton);
			
			//修改密码部分 按钮
			yesButton.addActionListener(new ActionListener(){


				String  account = account_num;
				//String oldPassword = oldpasswordField.getText();
				public void actionPerformed(ActionEvent e) {
					//得到输入的旧密码
					String oldPassword = oldpasswordField.getText();
					oldPassword = MD5Util.getMD5(MD5Util.getMD5(oldPassword));
					//连数据库，找旧密码。对了就再写个类似的界面
					try {Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "select userPassword from userinfo where userAccount = ? ";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, account);
						//ps.setObject(2, oldPassword);
						ResultSet result = ps.executeQuery();
						while(result.next()){
							String s = result.getString(1);
							if(s.equals(oldPassword)){
								RevisePassword.this.dispose();
								RevisePassword_ revisePassword_ = new RevisePassword_(account);
							}else{
								JOptionPane.showMessageDialog(null, "旧密码错误！");
								oldpasswordField.setText("");
								return;
							}
							
						}
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			});
		
			
			//退出按钮
			exitButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					RevisePassword.this.dispose();
					MainView_User mainView_User = new MainView_User(account_num);
					Thread thread = new Thread(mainView_User);
					thread.start();
				}
				
			});
			
			
			//清除按钮
			clearButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					
					oldpasswordField.setText("");
					
				}
				
				
				
			});
			
			this.setVisible(true);
	}
	
	public static void main(String[] args) {
		RevisePassword revisePassword = new RevisePassword("350964661");
	}


}
