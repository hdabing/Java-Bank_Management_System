package view_Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import method.DepTableModal;


public class UpdateDepView extends JDialog {

	private JLabel depnumLabel = new JLabel("部门编号");
	private JTextField depnumField = new JTextField();

	private JLabel depnameLabel = new JLabel("部门名称");
	private JTextField depnameField = new JTextField();

	private JLabel depmanagerLabel = new JLabel("部门主管");
	private JTextField depmanagerField = new JTextField();

	private JLabel deptelLabel = new JLabel("部门电话");
	private JTextField deptelField = new JTextField();

	private JButton updateButton = new JButton("修改");

	private JTable table = new JTable() {
		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}

	};
	private JScrollPane pane = new JScrollPane();

	public UpdateDepView() {

		this.setTitle("部门修改");
		this.setSize(600, 460);
		this.setLocationRelativeTo(null);

		DefaultTableModel defaultTableModel = DepTableModal.getDepTableModal();
		table.setModel(defaultTableModel);

		pane.setBounds(20, 20, 400, 200);
		pane.getViewport().add(table);// 先出去 viewport
		add(pane);

		depnumLabel.setBounds(46, 250, 69, 25);
		depnumField.setBounds(115, 250, 85, 25);
		add(depnumLabel);
		add(depnumField);

		depnameLabel.setBounds(225, 250, 69, 25);
		depnameField.setBounds(300, 250, 120, 25);
		add(depnameLabel);
		add(depnameField);

		depmanagerLabel.setBounds(46, 300, 69, 25);
		depmanagerField.setBounds(115, 300, 85, 25);
		add(depmanagerLabel);
		add(depmanagerField);

		deptelLabel.setBounds(225, 300, 69, 25);
		deptelField.setBounds(300, 300, 120, 25);
		add(deptelLabel);
		add(deptelField);

		updateButton.setBounds(360, 360, 80, 30);
		add(updateButton);

		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);

		// 渲染器
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cellRenderer);

		depnumField.setEditable(false);

		// 鼠标监听事件
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {

				int row = table.getSelectedRow();

				String depnum = (String) table.getValueAt(row, 0);
				String depname = (String) table.getValueAt(row, 1);
				String depmanager = (String) table.getValueAt(row, 3);
				String deptel = (String) table.getValueAt(row, 2);

				depnumField.setText(depnum);
				depnameField.setText(depname);
				depmanagerField.setText(depmanager);
				deptelField.setText(deptel);

			}

		});

		updateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String depnum = depnumField.getText();
				String depname = depnameField.getText();
				String depmanager = depmanagerField.getText();
				String deptel = deptelField.getText();
				int n = 0;

				try {Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql = "update dep set depname = ?,depmanager=?,deptel = ? where depnum = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(4, depnum);
					ps.setObject(1, depname);
					ps.setObject(2, depmanager);
					ps.setObject(3, deptel);

					n = ps.executeUpdate();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(n>0){
					JOptionPane.showMessageDialog(null, "修改成功！");
					table.setModel(DepTableModal.getDepTableModal());
					
				}else{
					JOptionPane.showMessageDialog(null, "修改失败！");
					
				}
				

			}

		});

		this.setLayout(null);
		this.setModal(true);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		UpdateDepView updateDepView = new UpdateDepView();
	}
}
