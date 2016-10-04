import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;

//文件选择器和菜单
public class MyFrame9 extends JFrame implements ActionListener{
	private MyPanel oneJPanel;
	
	private JMenuBar mainJMenuBar;
	
	private JMenu fileJMenu;
	private JMenu editJMenu;
	private JMenu formatJMenu;
	
	private JMenu newFileJMenuItem;
	private JMenuItem saveFileJMenuItem;
	private JMenuItem openFileJMenuItem;
	
	private JMenuItem newOneFileJMenuItem;
	private JMenuItem newProgramJMenuItem;
	
	private JTextArea fileJTextArea;
	
	private JFileChooser fileJFileChooser;
	
	public MyFrame9(){
		init();
		Calendar c = Calendar.getInstance();
		System.out.println (c);
		Date d = c.getTime();
		java.text.SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println (f.format(d));
		
		this.setVisible(true);
	}
	
	private void init(){
		this.setSize(400,400);
		this.setLocationRelativeTo(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		
		fileJTextArea = new JTextArea();
		fileJTextArea.setLineWrap(true);
		c.add(fileJTextArea);
		
		oneJPanel = new MyPanel();
		oneJPanel.setLayout(null);
		//c.add(oneJPanel);
		
		mainJMenuBar = new JMenuBar();
		this.setJMenuBar(mainJMenuBar);
		
		fileJMenu = new JMenu("文件");
		mainJMenuBar.add(fileJMenu);
		
		newFileJMenuItem = new JMenu("新建(A)");
	//	newFileJMenuItem.addActionListener(this);
		fileJMenu.add(newFileJMenuItem);
		
		newOneFileJMenuItem = new JMenuItem("文件");
		newFileJMenuItem.add(newOneFileJMenuItem);
		
		newProgramJMenuItem = new JMenuItem("工程");
		newFileJMenuItem.add(newProgramJMenuItem);
		
		saveFileJMenuItem = new JMenuItem("保存(A)",KeyEvent.VK_A);
		fileJMenu.add(saveFileJMenuItem);
		saveFileJMenuItem.addActionListener(this);
		fileJMenu.addSeparator();
		
		openFileJMenuItem = new JMenuItem("打开(A)",KeyEvent.VK_A);
		fileJMenu.add(openFileJMenuItem);
		
		editJMenu = new JMenu("编辑");
		mainJMenuBar.add(editJMenu);
		
		formatJMenu = new JMenu("格式");
		mainJMenuBar.add(formatJMenu);
		
		fileJFileChooser = new JFileChooser("f:");
	}
	
	public void actionPerformed(ActionEvent e){
		int result = fileJFileChooser.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION){
		//	fileJTextArea.show(false);
			
			
			File file = fileJFileChooser.getSelectedFile();
			
			try{
				//保存的 
				String str = fileJTextArea.getText();
				String[] strs = str.split("\r\n");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
//				for (int i = 0; i<strs.length; i++){
//					bw.write(str);
//					bw.flush();
//				}
				bw.write(str);
				
				bw.close();
				fw.close();
				
				//打开的
//				FileReader fr = new FileReader(file);
//				BufferedReader br = new BufferedReader(fr);
//				StringBuffer sb = new StringBuffer();
//				String str = "";
//				while((str = br.readLine())!=null){
//					fileJTextArea.append(str+"\r\n");
//				}
//				//fileJTextArea.setText(sb.toString())	;
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}