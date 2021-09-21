package com.phonebook.manager.controller;

import com.phonebook.manager.model.DBModel;
import com.phonebook.manager.veiw.PhoneView;

public class Run {

	public static void main(String[] args) {

		PhoneView theGUI = new PhoneView();
		DBModel dbModel = new DBModel();


		new PhoneController(theGUI, dbModel);

	}
}
