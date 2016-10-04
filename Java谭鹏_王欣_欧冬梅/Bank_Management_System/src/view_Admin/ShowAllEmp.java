package view_Admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import pojo.Dep;
import util.DateChooser;

import method.EmpTableModal;
import method.WindowListener;

public class ShowAllEmp extends JDialog {

	private JScrollPane pane = new JScrollPane();
	private JTable table = new JTable() {
		// 重写方法
		public boolean isCellEditable(int row, int column) { // 行和列
			return false;
		}
	};

	private JLabel tishiLabel = new JLabel("入职日期：");
	private JLabel tishi1Label = new JLabel("至");
	private JLabel mingziLabel = new JLabel("名字：");
	private JLabel bumenLabel = new JLabel("部门：");

	private JTextField nameField = new JTextField();
	private JButton button = new JButton("搜索");
	private JButton button1 = new JButton("返回");

	DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
	DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
	JLabel showDate1 = new JLabel("单击选择日期");
	JLabel showDate2 = new JLabel("单击选择日期");

	@SuppressWarnings("unchecked")
	public ShowAllEmp(String account_admin) {

		setSize(530, 520);
		setLocationRelativeTo(null);
		setLayout(null);
		dateChooser1.register(showDate1);
		dateChooser2.register(showDate2);
		dateChooser1.setBounds(60, 316, 120, 30);
		dateChooser2.setBounds(260, 316, 120, 30);
		this.add(dateChooser1, BorderLayout.SOUTH);
		this.add(dateChooser2, BorderLayout.NORTH);

		tishiLabel.setBounds(40, 280, 100, 25);
		add(tishiLabel);
		tishi1Label.setBounds(226, 316, 30, 25);
		add(tishi1Label);

		mingziLabel.setBounds(40, 380, 100, 30);
		add(mingziLabel);
		bumenLabel.setBounds(260, 380, 100, 30);
		add(bumenLabel);

		nameField.setBounds(95, 380, 110, 30);
		add(nameField);

		button.setBounds(430, 430, 60, 25);
		add(button);
		button1.setBounds(40, 430, 60, 25);
		add(button1);

		pane.setBounds(20, 20, 480, 240);

		table.setModel(EmpTableModal.getEmpTableModal());

		pane.getViewport().add(table);

		// 设置列的宽度
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(40);
		table.getColumnModel().getColumn(3).setMaxWidth(40);
		table.getColumnModel().getColumn(4).setMaxWidth(210);
		table.getColumnModel().getColumn(5).setMaxWidth(140);
		table.getColumnModel().getColumn(6).setMaxWidth(130);
		add(pane);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cellRenderer);

		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ShowAllEmp.this.dispose();

			}

		});

		final JComboBox depBox = new JComboBox();
		depBox.addItem(null);

		depBox.setBounds(340, 380, 100, 25);
		add(depBox);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root",
					"admin");
			String sql = "select depnum , depname from dep";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Dep dep = new Dep();
				dep.setDepnum(result.getInt(1));
				dep.setDepname(result.getString(2));
				depBox.addItem(dep);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int flag = 0;

				String bdate = showDate1.getText();
				String edate = showDate2.getText();
				// System.out.println(bdate);
				// System.out.println(edate);
				if ("单击选择日期".equals(bdate))
					bdate = null;
				if ("单击选择日期".equals(edate))
					edate = null;

				String name = nameField.getText();

				String sql = "select empnum, hireday ,  depnum ,userAccount from emp where 1=1 ";
				// select empnum , empname , hireday , empsex , depnum
				StringBuffer sb3 = new StringBuffer();
				List list = new ArrayList();

				/*
				 * if (name != null && name.trim().length() > 0) {
				 * sb3.append(" and empname like ? "); list.add("%" + name +
				 * "%"); }
				 */

				if (bdate != null) {
					sb3.append(" and hireday >= ? ");
					list.add(bdate);
				}
				if (edate != null) {
					sb3.append(" and hireday <= ? ");
					list.add(edate);
				}

				Object object = depBox.getSelectedItem();
				int depid;

				Dep dep = (Dep) object;
				if (dep != null) {
					depid = dep.getDepnum();
					sb3.append(" and depnum = ? ");
					list.add(depid);
				}

				sql = sql + sb3.toString();

				// 给表格设置表模型 --> 表头和表数据
				Vector<String> thVector = new Vector<String>();
				thVector.add("编号");
				thVector.add("姓名");
				thVector.add("性别");
				thVector.add("入职日期");
				thVector.add("所在部门");

				// 表数据
				Vector<Vector<String>> dataVector = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System",
							"root", "admin");
					PreparedStatement ps = connection.prepareStatement(sql);

					// 给问号赋值
					// System.out.println(list.size());
					for (int i = 0; i < list.size(); i++) {
						ps.setObject(i + 1, list.get(i));
						// System.out.println(list.get(i));
					}

					ResultSet rs = ps.executeQuery();

					dataVector = new Vector<Vector<String>>();

					while (rs.next()) {
						flag = 0;
						// System.out.println("有");
						Vector<String> vector = new Vector<String>();
						vector.add(rs.getInt(1) + "");
						String hireday = rs.getString(2);
						String depnum = rs.getString(3);
						String userAccount = rs.getString(4);

						Connection connection4 = DriverManager
								.getConnection(
										"jdbc:mysql://127.0.0.1/Bank_Management_System",
										"root", "admin");
						String username = nameField.getText();
						if (username == null)
							username = "";
						String sql4 = "select userName,userSex  from userinfo where userAccount = ? and userName like '%"
								+ username + "%'";
						PreparedStatement ps4 = connection4
								.prepareStatement(sql4);
						ResultSet rs4 = null;
						ps4.setObject(1, userAccount);
						rs4 = ps4.executeQuery();

						while (rs4.next()) {
							flag = 1;
							vector.add(rs4.getString(1));
							String sex_ = rs4.getString(2);
							if ("1".equals(sex_))
								vector.add("男");
							else
								vector.add("女");
						}

						vector.add(hireday);

						Connection connection22 = DriverManager
								.getConnection(
										"jdbc:mysql://127.0.0.1/Bank_Management_System",
										"root", "admin");
						String sql22 = "select depname  from dep where depnum = ?  ";
						PreparedStatement ps2 = connection22
								.prepareStatement(sql22);
						ps2.setObject(1, depnum);
						ResultSet rs2 = ps2.executeQuery();
						rs2.next();
						vector.add(rs2.getString(1));
						if (flag == 1)
							dataVector.add(vector);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DefaultTableModel defaultTableModel = new DefaultTableModel(
						dataVector, thVector);

				table.setModel(defaultTableModel);

			}
		});

		setModal(true);
		setVisible(true);

		// 关闭
		this.addWindowListener(new WindowListener());

	}

	public static void main(String[] args) {
		ShowAllEmp showAllEmp = new ShowAllEmp("6217002940103996052");
	}
}
