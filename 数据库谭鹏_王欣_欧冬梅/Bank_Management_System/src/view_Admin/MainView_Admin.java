package view_Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import view_ATM.MainView_User.Shijian;
import view_Counter.Draw_main_counter;
import view_Counter.GetAccount_Desposit;
import view_Counter.GetAccount_Draw;
import view_Counter.GetAccount_Inquiry;
import view_Counter.Recharge_pay_counter;
import view_Counter.Register_counter;
import view_Counter.Revise_First_counter;

public class MainView_Admin extends JFrame implements Runnable {

	JMenuBar bar = new JMenuBar();

	JMenu depMenu = new JMenu("部门管理");
	JMenuItem getdepMenu = new JMenuItem("查看部门");
	JMenuItem adddepMenu = new JMenuItem("添加部门");
	JMenuItem updatedepMenu = new JMenuItem("修改信息");
	JMenuItem deldepMenu = new JMenuItem("删除部门");

	JMenu empMenu = new JMenu("员工管理");
	JMenuItem getempMenu = new JMenuItem("查看员工");
	JMenuItem addempMenu = new JMenuItem("添加员工");
	JMenuItem updateempMenu = new JMenuItem("修改信息");
	JMenuItem delempMenu = new JMenuItem("删除员工");

	private JLabel timeLabel = new JLabel("当前系统时间为：");
	private JLabel time1Label = new JLabel();

	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("请选择所需业务");

	private JButton zhuanzhangButton = new JButton("转账业务");
	private JButton qukuanButton = new JButton("取款");
	private JButton chongzhiButton = new JButton("充值缴费");
	private JButton cunkuanButton = new JButton("实时存款");
	private JButton licaiButton = new JButton("新用户注册");
	private JButton xiugaimimaButton = new JButton("修改用户信息");
	private JButton tuichuButton = new JButton("退出");
	private JButton chaxunyewuButton = new JButton("查询业务");

	public class Shijian implements Runnable {

		public void run() {
			// 线程，显示当前时间
			while (true) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String time = sdf.format(date);
				time1Label.setForeground(Color.red);
				time1Label.setText(time);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public MainView_Admin(final String account_admin) {
		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Shijian shijian = new Shijian();
		Thread thread = new Thread(shijian);
		thread.start();

		timeLabel.setBounds(530, 500, 120, 20);
		timeLabel.setForeground(Color.red);
		add(timeLabel);
		time1Label.setBounds(680, 500, 120, 20);
		add(time1Label);

		etishiLabel.setBounds(190, 40, 500, 55);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 43));
		etishiLabel.setForeground(Color.red);

		ctishiLabel.setBounds(320, 90, 350, 55);
		ctishiLabel.setForeground(Color.red);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("行楷", Font.PLAIN, 26));

		zhuanzhangButton.setBounds(130, 160, 180, 50);
		add(zhuanzhangButton);
		chongzhiButton.setBounds(130, 240, 180, 50);
		add(chongzhiButton);
		licaiButton.setBounds(130, 320, 180, 50);
		add(licaiButton);
		tuichuButton.setBounds(130, 400, 180, 50);
		add(tuichuButton);

		zhuanzhangButton.setForeground(Color.red);
		chongzhiButton.setForeground(Color.red);
		licaiButton.setForeground(Color.red);
		tuichuButton.setForeground(Color.red);

		zhuanzhangButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		chongzhiButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		licaiButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		tuichuButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));

		qukuanButton.setBounds(560, 160, 180, 50);
		add(qukuanButton);
		cunkuanButton.setBounds(560, 240, 180, 50);
		add(cunkuanButton);
		xiugaimimaButton.setBounds(560, 320, 180, 50);
		add(xiugaimimaButton);
		chaxunyewuButton.setBounds(560, 400, 180, 50);
		add(chaxunyewuButton);

		qukuanButton.setForeground(Color.red);
		cunkuanButton.setForeground(Color.red);
		xiugaimimaButton.setForeground(Color.red);
		chaxunyewuButton.setForeground(Color.red);

		qukuanButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		cunkuanButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		xiugaimimaButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		chaxunyewuButton.setFont(new Font("微软雅黑", Font.PLAIN, 24));

		depMenu.add(getdepMenu);
		depMenu.add(adddepMenu);
		depMenu.add(updatedepMenu);
		depMenu.add(deldepMenu);

		empMenu.add(getempMenu);
		empMenu.add(addempMenu);
		empMenu.add(updateempMenu);
		empMenu.add(delempMenu);

		bar.add(depMenu);
		bar.add(empMenu);

		bar.setBounds(0, 0, 850, 20);
		add(bar);

		depMenu.setEnabled(false);
		empMenu.setEnabled(false);

		if (account_admin.equals("6217002940103996053")) {
			depMenu.setEnabled(true);
			empMenu.setEnabled(true);
		}

		JLabel lbl_image = new JLabel();
		lbl_image.setBounds(0, 0, 850, 600);
		lbl_image.setIcon(new ImageIcon("src/Image/管理员界面背景.jpg"));
		add(lbl_image);
		// 修改用户信息
		xiugaimimaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				Revise_First_counter revise_First_counter = new Revise_First_counter(
						account_admin);
			}
		});
		// 新用户注册
		licaiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				JOptionPane.showMessageDialog(null,
						"注册新用户需要交费50，其中手续费加卡费共20，剩余金额存入卡内！");
				Register_counter register_counter = new Register_counter(
						account_admin);
			}
		});
		// 转账
		zhuanzhangButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 先连到一个界面，输入账号密码，得到卡号信息，再转到最开始的转账界面
				MainView_Admin.this.dispose();
				GetAccount_main getAccount_main = new GetAccount_main(
						account_admin);
			}
		});
		// 取款
		qukuanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				GetAccount_Draw getAccount_main = new GetAccount_Draw(
						account_admin);
			}
		});
		// 充值缴费
		chongzhiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				Recharge_pay_counter recharge_pay_counter = new Recharge_pay_counter(
						account_admin);
			}
		});
		// 存款
		cunkuanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				GetAccount_Desposit getAccount_main = new GetAccount_Desposit(
						account_admin);
			}
		});
		// 查询业务
		chaxunyewuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				GetAccount_Inquiry getAccount_main = new GetAccount_Inquiry(
						account_admin);
			}
		});
		// 退出
		tuichuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定退出？", "提示",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "退出成功！祝您工作顺利！");
					System.exit(0);
				} else {
					return;
				}
			}
		});
		// 查看部门
		getdepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAllDep showAllDep = new ShowAllDep();
			}
		});
		// 添加部门
		adddepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDepView addDep = new AddDepView();
			}
		});
		// 修改部门
		updatedepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateDepView updateDepView = new UpdateDepView();
			}
		});
		// 删除部门
		deldepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DelDepView delDepView = new DelDepView();
			}
		});
		// 查看员工
		getempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAllEmp showAllEmp = new ShowAllEmp(account_admin);
			}
		});
		// 添加员工
		addempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEmpView addEmpView = new AddEmpView();
			}
		});
		// 修改信息
		updateempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateEmpView updateEmpView = new UpdateEmpView();
			}
		});
		// 删除信息
		delempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DelEmpView delEmpView = new DelEmpView();
			}
		});
		// 添加监听事件，关闭窗口
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认退出？", "提示",
						JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					System.exit(0);
				} else {
					// 添加其他操作
					return;
				}
			}

		});
		this.setTitle("主界面");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(850, 600);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化

	}

	public void run() {

		// 线程，显示当前时间
		while (true) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			time1Label.setForeground(Color.red);
			time1Label.setText(time);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {

		MainView_Admin mainView_Admin = new MainView_Admin("613258200");
		Thread thread = new Thread(mainView_Admin);
		thread.start();
	}

}
