package test;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UnsupportedLookAndFeelException;


public class Test extends JFrame{

	public Test() {
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		 this.setResizable(false);
		 this.setLayout(null);
		JLabel lbl_image = new JLabel();
		lbl_image.setBounds(0, 0, 800, 600);
		lbl_image.setIcon(new ImageIcon("src/Image/四月是你的谎言.jpg"));
		add(lbl_image);
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Test test = new Test();
	}
}
