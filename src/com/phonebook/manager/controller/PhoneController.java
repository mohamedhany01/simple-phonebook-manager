package com.phonebook.manager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.phonebook.manager.model.DBModel;
import com.phonebook.manager.veiw.NewDataPanalUI;
import com.phonebook.manager.veiw.PhoneView;

public class PhoneController {

	private final static int NAME = 0, PHONE_NUMBER = 1;

	public PhoneController(PhoneView view, DBModel dbModel) {

		this.view = view;
		this.dbModel = dbModel;

		insertBtn = this.view.getAddGUI().getInsertBtn();
		updateBtn = this.view.getAddGUI().getUpdateBtn();
		deleteBtn = this.view.getAddGUI().getDeleteBtn();
		guiTable = this.view.getTableGUI().getJtable();
		nameField = this.view.getAddGUI().getNameField();
		phoneNumberField = this.view.getAddGUI().getPhoneNumberField();
		searchField = this.view.getSearchGUI().getSearchField();

		DBTable = dbModel.getDataAsKeyValue();

		loadDataFromFileToDBTable();

		insertListener = new InsertDataToTableListener();
		view.getAddGUI().addActionListnerBtn(insertListener, insertBtn);
		view.getTableGUI().setActionListnerToTable(new GetRowDataFromTableListener());
		view.getSearchGUI().addKeyListnerSearchField(new FilterTableListener(), searchField);
		view.getAddGUI().addActionListnerBtn(new UpdateRowDataFromTableListener(), updateBtn);
		view.getAddGUI().addActionListnerBtn(new DeleteRowDataFromTableListener(), deleteBtn);
		view.addMouseListenerFram(new ResetGUI());

	}

	private void loadDataFromFileToDBTable() {

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel = (DefaultTableModel) guiTable.getModel();

		for (Entry<String, String> mapEntiry : DBTable.entrySet()) {
			String[] keyValueTemp = { mapEntiry.getValue().toString(), mapEntiry.getKey().toString() };
			tableModel.addRow(keyValueTemp);
		}
	}

	private void refreshTableGUI() {

		// Clear the the hashTabel
		dbModel.clearHashTable();

		// Load the new data from the db
		dbModel.readData();

		// Clear all data in the GUI table
		view.getTableGUI().clearTableGUI();

		// Load the new data
		loadDataFromFileToDBTable();
	}

	public boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// Action listeners implementation
	private class ResetGUI implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			JButton[] btns = { updateBtn, deleteBtn };

			oldPhoneNumber = null;

			view.getAddGUI().lockBtns(btns);

			view.getAddGUI().resetFields();

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class InsertDataToTableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String name = nameField.getText().strip();
			String pn = phoneNumberField.getText().strip();

			if (name.equals("") || pn.equals("")) {

				view.showMessage("Empty Fields", "Both field msut be full", JOptionPane.ERROR_MESSAGE);
			} else if (dbModel.isPhoneExists(pn)) {
				view.showMessage("Duplication", "This phone number already exists, no duplicates are allowed",
						JOptionPane.ERROR_MESSAGE);
			} else if (!isInteger(pn)) {
				view.showMessage("Invalid Phone Number", "The phone must be numbers", JOptionPane.ERROR_MESSAGE);
			} else {

				// Write data to the file
				dbModel.writeData(name, pn);

				// Refresh table with the new data
				refreshTableGUI();

				view.getAddGUI().resetFields();
			}
		}
	}

	private class UpdateRowDataFromTableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (oldPhoneNumber != null && !phoneNumberField.getText().equals("")) {

				String nameData = nameField.getText().strip();
				String phData = phoneNumberField.getText().strip();

				dbModel.updateData(nameData, phData, oldPhoneNumber);

				view.showMessage("Update Data", "Data have updated successfully", JOptionPane.INFORMATION_MESSAGE);

				JButton[] btns = { updateBtn, deleteBtn };
				view.getAddGUI().lockBtns(btns);

				view.getAddGUI().resetFields();

				refreshTableGUI();
			}

		}

	}

	private class DeleteRowDataFromTableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			final int YES_CONFIRMTION = 0;

			int toDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want delete these data?",
					"Deletion Confirmtion", JOptionPane.WARNING_MESSAGE);

			if (toDelete == YES_CONFIRMTION) {

				String nameData = nameField.getText().strip();
				String phData = phoneNumberField.getText().strip();

				dbModel.deleteData(nameData, phData);

				view.showMessage("Delete Data", "Data have deleted successfully", JOptionPane.INFORMATION_MESSAGE);

				JButton[] btns = { updateBtn, deleteBtn };
				view.getAddGUI().lockBtns(btns);

				view.getAddGUI().resetFields();

				refreshTableGUI();
			}
		}

	}

	private class GetRowDataFromTableListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			final NewDataPanalUI addNewGUI = view.getAddGUI();

			DefaultTableModel tableModel = (DefaultTableModel) guiTable.getModel();
			int slectedRowIndex = guiTable.getSelectedRow();

			String nameData = tableModel.getValueAt(slectedRowIndex, NAME).toString();
			String phData = tableModel.getValueAt(slectedRowIndex, PHONE_NUMBER).toString();

			oldPhoneNumber = phData;

			// Unlock btns
			JButton[] btns = { updateBtn, deleteBtn };
			addNewGUI.unlockBtns(btns);

			// Load row data into both fields
			nameField.setText(nameData);
			phoneNumberField.setText(phData);
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

	private class FilterTableListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {

			// Names column is 0, phone numbers is 1
			final int TABLE_COLUMN_TO_SEARCH = 0;

			// Case-insensitive
			final String IGNORE_SENSITIVITY = "(?i)";

			String personName = searchField.getText().trim();

			DefaultTableModel model = (DefaultTableModel) guiTable.getModel();
			TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
			guiTable.setRowSorter(rowSorter);
			rowSorter.setRowFilter(RowFilter.regexFilter(IGNORE_SENSITIVITY + personName, TABLE_COLUMN_TO_SEARCH));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// GUI references
	private PhoneView view;
	private DBModel dbModel;

	// Event listeners
	private InsertDataToTableListener insertListener;

	// GUI Components
	private JButton insertBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JTable guiTable;
	private JTextField nameField;
	private JTextField phoneNumberField;
	private JTextField searchField;

	// Instance variables
	private HashMap<String, String> DBTable;
	private String oldPhoneNumber; // Keep tracking the old phone number in case of updating process

}
