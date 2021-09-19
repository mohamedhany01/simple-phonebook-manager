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
	 * Create the panel.
	 */
	public TableDataUI() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		add(scrollPane);

		infoTable = new JTable();
		infoTable.setFont(new Font("Tahoma", Font.BOLD, 15));
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

	public void setActionListnerToInsertBtn(MouseListener GetDataNameAndPhonNumber) {
		this.infoTable.addMouseListener(GetDataNameAndPhonNumber);
	}

	private JTable infoTable;
}
