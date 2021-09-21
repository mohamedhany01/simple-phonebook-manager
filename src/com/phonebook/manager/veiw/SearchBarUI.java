package com.phonebook.manager.veiw;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBarUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public SearchBarUI() {
		setBackground(SystemColor.activeCaptionBorder);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setBounds(6, 13, 58, 18);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		add(lblNewLabel);

		searchField = new JTextField();
		searchField.setBorder(null);
		searchField.setBounds(76, 6, 362, 33);
		add(searchField);
		searchField.setColumns(15);
	}

	public void addKeyListnerSearchField(KeyListener listener, JTextField field ) {

		this.searchField.addKeyListener(listener);
	}

	public JTextField getSearchField() {

		return this.searchField;
	}

	private JTextField searchField;

}
