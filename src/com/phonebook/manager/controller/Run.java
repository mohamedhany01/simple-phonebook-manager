package com.phonebook.manager.controller;

import com.phonebook.manager.model.DBFileModel;
import com.phonebook.manager.veiw.PhoneView;

public class Run {

	public static void main(String[] args) {
		
		PhoneView theGUI = new PhoneView();
		DBFileModel fileController = new DBFileModel();
		
		new PhoneController(theGUI, fileController);
		
	}

}
