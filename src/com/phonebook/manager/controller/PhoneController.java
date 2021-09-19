package com.phonebook.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.phonebook.manager.model.DBFileModel;
import com.phonebook.manager.veiw.PhoneView;

public class PhoneController {
	
	public PhoneController(PhoneView pmView, DBFileModel fController) {
		
		this.pmView = pmView;
		this.fController = fController;
		
		this.DBTable = fController.getDbTable();
		loadDataFromFileToDBTable();
		
		this.pmView.getAddGUI().setActionListnerToInsertBtn(new InsertDataToTable());
		this.pmView.getTableGUI().setActionListnerToInsertBtn(new GetRowDataFromTable());
		
	}
	
	private void loadDataFromFileToDBTable() {
		
		JTable tableUI = pmView.getTableGUI().getJtable();
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel)tableUI.getModel();
		
		for (Entry<String, String> mapEntiry : DBTable.entrySet()) {
			String[] keyValueTemp = {mapEntiry.getValue().toString(), mapEntiry.getKey().toString()};
			tableModel.addRow(keyValueTemp);
		}
	}
	
	// Action listeners implementation
	private class InsertDataToTable implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			final JTextField nameField = pmView.getAddGUI().getNameText();
			final JTextField pnField = pmView.getAddGUI().getPhoneNumbeText();

			String name = nameField.getText();
			String pn = pnField.getText();
			
			if (name.equals("") || pn.equals("")) {
				JOptionPane.showMessageDialog(null, "Set data please.");
			}
			else {
				
				// Write data to the file
				fController.writeData(name, pn);
				
				// Refresh table with the new data
				refrshGUIWithNewData();

				
				pmView.getAddGUI().setNameText(null);
				pmView.getAddGUI().setPhoneNumbeText(null);
			}
						
		}

		private void refrshGUIWithNewData() {
			
			final JTable tableUI = pmView.getTableGUI().getJtable();
			
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel = (DefaultTableModel)tableUI.getModel();
			
			int rowCount = tableModel.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				tableModel.removeRow(i);
			}
			
			loadDataFromFileToDBTable();
		}	
	}
	
	private class GetRowDataFromTable implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			final JTable tableInfo = pmView.getTableGUI().getJtable();
			final JTextField nameField = pmView.getAddGUI().getNameText();
			final JTextField pnField = pmView.getAddGUI().getPhoneNumbeText();
			
			DefaultTableModel tableModel = (DefaultTableModel)tableInfo.getModel();
			int slectedRowIndex = tableInfo.getSelectedRow();
			
			String nameData = tableModel.getValueAt(slectedRowIndex, NAME).toString();
			String phData = tableModel.getValueAt(slectedRowIndex, PHONE_NUMBER).toString();
			
			nameField.setText(nameData);
			pnField.setText(phData);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	
	}
	
	private final static int NAME = 0, PHONE_NUMBER = 1;
	
	private PhoneView pmView;
	private DBFileModel fController;
	private Map<String, String> DBTable;
}
