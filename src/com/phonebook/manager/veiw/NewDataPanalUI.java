package com.phonebook.manager.veiw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class NewDataPanalUI extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public NewDataPanalUI() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(SystemColor.activeCaptionBorder);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Person Name:");
		lblNewLabel.setBounds(7, 7, 105, 51);
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		add(lblNewLabel);

		nameText = new JTextField();
		nameText.setBorder(null);
		nameText.setBounds(7, 50, 227, 30);
		nameText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(nameText);
		nameText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Person Phone Number:");
		lblNewLabel_1.setBounds(7, 111, 174, 18);
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		add(lblNewLabel_1);

		phoneNumbeText = new JTextField();
		phoneNumbeText.setBorder(null);
		phoneNumbeText.setBounds(7, 139, 227, 30);
		phoneNumbeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(phoneNumbeText);
		phoneNumbeText.setColumns(10);

		insertBtn = new JButton("Add");
		insertBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		insertBtn.setBounds(7, 202, 227, 37);
		insertBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		insertBtn.setBackground(new Color(255, 255, 255));
		insertBtn.setForeground(SystemColor.textHighlight);
		insertBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		add(insertBtn);

		updateBtn = new JButton("Update");
		updateBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		updateBtn.setBackground(new Color(255, 255, 255));
		updateBtn.setForeground(new Color(107, 142, 35));
		updateBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		updateBtn.setBounds(7, 261, 227, 37);
		add(updateBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(new Color(255, 255, 255));
		deleteBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		deleteBtn.setForeground(new Color(255, 0, 0));
		deleteBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		deleteBtn.setBounds(7, 323, 227, 37);
		add(deleteBtn);

		JButton[] btns = {updateBtn, deleteBtn};

		lockBtns(btns);

	}

	public JTextField getNameField() {
		return this.nameText;
	}

	public JTextField getPhoneNumberField() {
		return this.phoneNumbeText;
	}

	public JButton getInsertBtn() {
		return this.insertBtn;
	}

	public JButton getUpdateBtn() {
		return this.updateBtn;
	}

	public JButton getDeleteBtn() {
		return this.deleteBtn;
	}

	public void resetFields() {
		this.nameText.setText(null);
		this.phoneNumbeText.setText(null);
	}

	public void addActionListnerBtn(ActionListener actionListener, JButton btn) {
		btn.addActionListener(actionListener);
	}

	public void removeActionListnerBtn(ActionListener actionListener, JButton btn) {
		btn.removeActionListener(actionListener);
	}

	public void lockBtn(JButton button) {

		button.setEnabled(false);
	}

	public void lockBtns(JButton[] buttons) {

		for (JButton button : buttons) {
			button.setEnabled(false);
		}
	}

	public void unlockBtn(JButton button) {

		button.setEnabled(true);
	}

	public void unlockBtns(JButton[] buttons) {

		for (JButton button : buttons) {
			button.setEnabled(true);
		}
	}

	private JTextField nameText;
	private JTextField phoneNumbeText;
	private JButton insertBtn, updateBtn, deleteBtn;
}
