package view_ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Transfer_accounts_Main extends JDialog {

	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("请选择所需业务");

	private JButton b1 = new JButton("活期转账");
	private JButton b2 = new JButton("定期转账");
	private JButton b3 = new JButton("退出");
	private JButton b4 = new JButton("返回主菜单");

	public Transfer_accounts_Main(final String account) {

		this.setLayout(null); // 布局方式：绝对布局
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		etishiLabel.setBounds(140, 20, 620, 45);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 29));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(250, 60, 350, 45);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("行楷", Font.PLAIN, 18));

		b1.setBounds(120, 140, 140, 50);
		add(b1);
		b2.setBounds(370, 140, 140, 50);
		add(b2);
		b3.setBounds(120, 220, 140, 50);
		add(b3);
		b4.setBounds(370, 220, 140, 50);
		add(b4);

		b1.setForeground(Color.red);
		b2.setForeground(Color.red);
		b3.setForeground(Color.red);
		b4.setForeground(Color.red);

		b1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		b2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		b3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		b4.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		// 活期转账
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Transfer_accounts_Main.this.dispose();
				CurrentTransfer_Main currentTransfer_Main = new CurrentTransfer_Main(
						account);

			}

		});

		// 定期转账
		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Transfer_accounts_Main.this.dispose();
				FixedTransfer_Main fixedTransfer_Main = new FixedTransfer_Main(account);

			}

		});

		b3.addActionListener(new ActionListener() {

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

		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Transfer_accounts_Main.this.dispose();
				MainView_User mainView_User = new MainView_User(account);

			}

		});

		this.setTitle("转账业务");
		this.setResizable(false); // 不可拖动或最大化
		this.setSize(650, 400);
		this.setLocationRelativeTo(null); // 居中
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 关闭，做什么都不关
		this.setVisible(true); // 界面可视化

	}

	public static void main(String[] args) {

		Transfer_accounts_Main transfer_accounts_Main = new Transfer_accounts_Main(
				"350964661");

	}
}
