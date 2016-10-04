package view_Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import method.DepTableModal;


public class DelDepView extends JDialog {

	private JButton delButton = new JButton("删除");

	private JTable table = new JTable() {

		// 重写方法
		public boolean isCellEditable(int row, int column) { // 行和列
			return false;
		}
	};
	private JScrollPane pane = new JScrollPane();

	public DelDepView() {

		this.setTitle("删除部门");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		// 用方法实现，写表格的表模型
		DefaultTableModel defaultTableModel = DepTableModal.getDepTableModal();
		table.setModel(defaultTableModel);

		pane.setBounds(20, 60, 450, 240);
		pane.getViewport().add(table);
		add(pane);

		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);


		delButton.setBounds(350, 340, 80, 30);
		add(delButton);

		delButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int row = table.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "请选中行！");
					return;
				}

				int n = JOptionPane.showConfirmDialog(null, "确认删除？", "提示",
						JOptionPane.YES_NO_OPTION);
				if (n != 0) {
					return;
				}
				Object object = table.getValueAt(row, 0);
				int i = Integer.parseInt((String) object);
				int result = 0;

				try {Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql = "delete from dep where depnum = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, i);
					result = ps.executeUpdate();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (result == 0) {
					JOptionPane.showMessageDialog(null, "删除失败");
				} else {
					JOptionPane.showMessageDialog(null, "删除成功");
					DefaultTableModel depTableModal = DepTableModal
							.getDepTableModal();
					table.setModel(depTableModal);
				}

			}

		});

		this.setModal(true);
		this.setVisible(true);
	}

}
