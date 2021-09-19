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

public class NewDataPanalUI extends JPanel {
	
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
		phoneNumbeText.setBounds(7, 139, 227, 30);
		phoneNumbeText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(phoneNumbeText);
		phoneNumbeText.setColumns(10);
		
		insertBtn = new JButton("Add");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		insertBtn.setBounds(7, 202, 227, 37);
		insertBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		insertBtn.setBackground(Color.WHITE);
		insertBtn.setForeground(SystemColor.textHighlight);
		insertBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		add(insertBtn);

	}
	
	public JTextField getNameText() {
		return this.nameText;
	}

	public JTextField getPhoneNumbeText() {
		return this.phoneNumbeText;
	}
	
	public void setNameText(String newValue) {
		this.nameText.setText(newValue);
	}
	
	public void setPhoneNumbeText(String newValue) {
		this.phoneNumbeText.setText(newValue);
	}
	
	public void setActionListnerToInsertBtn(ActionListener GetDataNameAndPhonNumber) {
		this.insertBtn.addActionListener(GetDataNameAndPhonNumber);
	}
	
	private JTextField nameText;
	private JTextField phoneNumbeText;
	private JButton insertBtn;

}
