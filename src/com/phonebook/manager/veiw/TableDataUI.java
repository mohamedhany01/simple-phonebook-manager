package com.phonebook.manager.veiw;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableDataUI extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TableDataUI() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		add(scrollPane);

		// Disable cell editing
		infoTable = new JTable(){
			
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		infoTable.setBorder(null);
		infoTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoTable.getTableHeader().setResizingAllowed(false);
		infoTable.getTableHeader().setReorderingAllowed(false);
		infoTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(infoTable);
		infoTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Phone Number" }));
		infoTable.getColumnModel().getColumn(1).setResizable(false);
		infoTable.getColumnModel().getColumn(1).setPreferredWidth(80);

	}

	public JTable getJtable() {

		return this.infoTable;
	}

	public void setInfoIntoTable(String rowData[]) {

		DefaultTableModel tableModel = (DefaultTableModel) infoTable.getModel();
		tableModel.addRow(rowData);
	}

	public void clearTableGUI() {

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel)infoTable.getModel();

		int rowCount = tableModel.getRowCount();

		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	public void setActionListnerToTable(MouseListener GetDataNameAndPhonNumber) {
		this.infoTable.addMouseListener(GetDataNameAndPhonNumber);
	}

	private JTable infoTable;
}
