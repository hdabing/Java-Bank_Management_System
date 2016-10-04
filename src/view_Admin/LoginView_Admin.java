package view_Admin;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MD5Util;
import view_ATM.LoginView_User;



//这里是管理员登陆界面
public class LoginView_Admin extends JFrame{
	
	private JLabel account_numLabel = new JLabel("账号");
	private JTextField account_numField = new JTextField();
	
	private JLabel passwordLabel = new JLabel("密码");
	private JTextField passwordField = new JPasswordField();
	
	private JButton button1 = new JButton("登陆");
    private JButton button2 = new JButton("取消"); 
    
	public LoginView_Admin(){
		
		this.setLayout(null);  //绝对布局
		this.setTitle("欢迎来到银行管理系统");
		this.setResizable(false);
		this.setSize(360, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		account_numLabel.setBounds(40, 25, 40, 20);
		add(account_numLabel);
		
		account_numField.setBounds(120, 25, 140, 20);
		add(account_numField);
		
		passwordLabel.setBounds(40, 60, 40, 20);
		add(passwordLabel);
		
		passwordField.setBounds(120, 60, 140, 20);
		add(passwordField);
		
		button1.setBounds(160,100,60, 25);
		add(button1);
		button2.setBounds(240, 100, 60, 25);
		add(button2);
		
		account_numField.setText("6217002940103996053");
		passwordField.setText("tanpeng");
		//登陆界面
		button1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				String account_num = account_numField.getText();
				String password  = passwordField.getText();
				password = MD5Util.getMD5(MD5Util.getMD5(password));
				
				//输入判空
				if("".equals(account_num)||account_num==null){
					JOptionPane.showMessageDialog(null, "输入账号不能为空");
					account_numField.setText("");
					passwordField.setText("");
					return;
				}
				if("".equals(password)||password==null){
					JOptionPane.showMessageDialog(null, "输入密码不能为空");
					account_numField.setText("");
					passwordField.setText("");
					return ;
				}
				
				//JDBC  
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql = "select userAccount,userPassword from userinfo where userAccount = ? and userPassword = ? and ifadmin = 1";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, account_num);
					ps.setObject(2, password);
					
					ResultSet rs = ps.executeQuery();  //执行Sql语句
					
					if(rs.next()){
						String account = account_num;
						LoginView_Admin.this.dispose();   //关闭该页面
						MainView_Admin mainView = new MainView_Admin(account);
						
					}else{
						JOptionPane.showMessageDialog(null, "账号或密码错误");
						account_numField.setText("");
						passwordField.setText("");
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		});
		

		button2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				LoginView_Admin.this.dispose();
				System.exit(0);	
				
				
			}
			
		});
		//
		
		//添加监听事件，关闭窗口
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int n = JOptionPane.showConfirmDialog(null, "真的要退出吗？","提示", JOptionPane.YES_NO_OPTION);
			
			if(n==0){
				System.exit(0);				
			}else{
				//添加其他操作
				return;
			}
			}
			
		});
		this.setVisible(true);
		
	}

}
