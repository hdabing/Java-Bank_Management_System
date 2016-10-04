package view_Counter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class Revise_First_counter extends JDialog{

	private JButton zhaomimaButton = new JButton("1.找回密码");
	private JButton xiugaixixiButton = new JButton("2.修改信息");
	
	
	public Revise_First_counter(final String account_admin) {
		
		this.setLayout(null);  //绝对布局
		this.setTitle("修改用户信息");
		this.setResizable(false);
		this.setSize(360, 250);
		this.setLocationRelativeTo(null);
		
		zhaomimaButton.setBounds(100, 60, 130, 30);
		add(zhaomimaButton);
		xiugaixixiButton.setBounds(100, 130, 130, 30);
		add(xiugaixixiButton);
		
		
		//1.找回密码
		zhaomimaButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				Revise_First_counter.this.dispose();
				ForgetPassword_counter forgetPassword_counter = new ForgetPassword_counter(account_admin);
				
			}
			
						
		});
		//2.修改信息
		xiugaixixiButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				Revise_First_counter.this.dispose();
				GetAccount_revise getAccount_revise = new GetAccount_revise(account_admin);
				
				
			}
			
			
		});
		
		
		this.setVisible(true);
	}
	
	
}
