package view_Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import pojo.DbzTableModal;
import view_ATM.MainView_User;
import view_ATM.MoneyTypes_Main;
import view_Admin.MainView_Admin;

import method.EmpTableModal;


public class MoneyTypes_Main_counter extends JDialog{
	
	private JLabel etishiLabel = new JLabel("Transaction  Detail");
	private JLabel ctishiLabel = new JLabel("多币种明细信息");
	
	private JButton tuichuButton = new JButton("退出");
	private JButton jixuButton = new JButton("继续交易");

	private JScrollPane pane = new JScrollPane();
	private JTable table = new JTable() {

		// 重写方法
		public boolean isCellEditable(int row, int column) { // 行和列
			return false;
		}

	};

	public MoneyTypes_Main_counter(final String account_admin, String account) {
		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		tuichuButton.setBounds(60, 360, 100, 45);
		add(tuichuButton);
		jixuButton.setBounds(480, 360, 100, 45);
		add(jixuButton);
		
		pane.setBounds(160, 120, 340, 200);

		table.setModel(DbzTableModal.getDbzTableModal(account));

		pane.getViewport().add(table);
		
		//设置列的宽度
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(140);
		table.getColumnModel().getColumn(2).setMaxWidth(140);
		
		add(pane);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, cellRenderer);
		
		etishiLabel.setBounds(190, 20, 500, 45);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 33));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(260, 60, 350, 40);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("行楷", Font.PLAIN, 22));

		tuichuButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				int n = JOptionPane.showConfirmDialog(null, "确定退出？","提示",JOptionPane.YES_NO_OPTION);
				
				if(n==0){
					JOptionPane.showMessageDialog(null, "退出成功！祝您工作顺利！");
					System.exit(0);
				}else{
					return;
				}
				
				
			}
			
			
			
		});
		
		jixuButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				MoneyTypes_Main_counter.this.dispose();
				MainView_Admin mainView_User = new MainView_Admin(account_admin);
				
			}
			
			
			
		});
		
		this.setTitle("查询业务");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(660, 470);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化
	}
	

}
