package view_ATM;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Inquiry_end extends JDialog{
	
	private JTextArea tishiArea = new JTextArea();
	
	public Inquiry_end(String account ) {
		
		this.setLayout(null);  //绝对布局
		
		tishiArea.setBounds(30, 30, 300, 180);
		add(tishiArea);
		
		tishiArea.setText("1英镑 = 9.7027人民币"+"\n"+"1美元 = 6.2097人民币\n"+"1港币 = 0.801人民币");
		
		
		this.setTitle("汇率查询");
		this.setResizable(false);   //不可拖动或最大化
		this.setSize(360, 260);
		this.setLocationRelativeTo(null);   //居中
		this.setVisible(true);   //界面可视化
	}
	public static void main(String[] args) {
		Inquiry_end inquiry_end = new Inquiry_end("350964661");
	}

}
