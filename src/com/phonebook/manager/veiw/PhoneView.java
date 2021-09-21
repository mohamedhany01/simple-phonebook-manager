package com.phonebook.manager.veiw;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PhoneView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String APP_VER = "v1.1";

	public PhoneView() {
		setTitle("Phone Manager " + APP_VER);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 638);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Phone Manager");
		lblNewLabel.setBounds(38, 37, 136, 24);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		addGUI = new NewDataPanalUI();
		addGUI.setBounds(38, 89, 242, 426);
		contentPane.add(addGUI);

		tableInfoUI = new TableDataUI();
		tableInfoUI.setBounds(307, 154, 436, 426);
		contentPane.add(tableInfoUI);

		searchUI = new SearchBarUI();
		searchUI.setBounds(305, 89, 436, 51);
		contentPane.add(searchUI);

		setVisible(true);
	}

	public NewDataPanalUI getAddGUI() {
		return addGUI;
	}

	public TableDataUI getTableGUI() {
		return tableInfoUI;
	}

	public SearchBarUI getSearchGUI() {
		return searchUI;
	}

	public void showMessage(String title, String message, int type) {
		JOptionPane.showMessageDialog(null, message, title, type);
	}

	public void addMouseListenerFram(MouseListener listener) {
		addMouseListener(listener);
	}

	private JPanel contentPane;
	private NewDataPanalUI addGUI;
	private TableDataUI tableInfoUI;
	private SearchBarUI searchUI;
}
