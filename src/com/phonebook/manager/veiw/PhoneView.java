package com.phonebook.manager.veiw;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class PhoneView extends JFrame {
	/**
	 * Create the frame.
	 */
	public PhoneView() {
		setTitle("Phone Manager v1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 688);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone Manager");
		lblNewLabel.setBounds(38, 73, 136, 24);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		addGUI = new NewDataPanalUI();
		addGUI.setBounds(38, 125, 242, 261);
		contentPane.add(addGUI);
		
		tableInfoUI = new TableDataUI();
		tableInfoUI.setBounds(307, 125, 436, 516);
		contentPane.add(tableInfoUI);
		
		setVisible(true);
	}
	
	public NewDataPanalUI getAddGUI() {
		return addGUI;
	}
	
	public TableDataUI getTableGUI() {
		return tableInfoUI;
	}

	private JPanel contentPane;
	private NewDataPanalUI addGUI;
	private TableDataUI tableInfoUI;
}
