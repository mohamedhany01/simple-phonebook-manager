package com.phonebook.manager.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class DBFileModel {

	public DBFileModel() {

		// Check the structure is exist
		initStructureFile();
		dbTable = new HashMap<>();
		readData();
	}

	private void initStructureFile() {

		File file = new File(DATA_PATH);

		if(!file.exists())
		{
			System.out.println("The structure isn't ready.");
			System.out.println("Creating a new structure...");

			File dirData = new File("data");
			File dataFile = new File("data/pm_data.txt");
			try {

				dirData.mkdir();
				dataFile.createNewFile();
				System.out.println("The structure is ready.");
			} catch (IOException e) {

				System.err.println("Error: a failure in the file creation");
			}

		}
		else {
			System.out.println("The structure is ready.");
		}
	}

	public void writeData(String name, String phoneNumber) {

		File file = new File(DATA_PATH);

		BufferedWriter writer = null;
		try {
			if (dbTable.containsKey(phoneNumber)) {
				JOptionPane.showMessageDialog(null, "This " + phoneNumber + " is already exist!");
			}
			else {
				dbTable.put(phoneNumber, name);
				writer = new BufferedWriter(new FileWriter(file, true));
				writer.write(name+DELIMITER+phoneNumber);
				writer.newLine();
				writer.flush();
				writer.close();
				System.out.println("A new row is added.");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void readData() {

		File file = new File(DATA_PATH);

		// Do this only if the structure exist
		if(file.exists()) {

			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));

				try {
					String temp = null;
					while((temp = reader.readLine()) != null) {
						parseDataAndAddToDBTable(temp);
					}
					reader.close();
				} catch (IOException e) {
					System.err.println("Error: reading data in the file.");
				}
			} catch (FileNotFoundException e) {
				System.err.println("Error: opening the file.");
			}

			System.out.println(dbTable);

		}
	}

	private void parseDataAndAddToDBTable(String dataRow) {

		StringBuilder name = new StringBuilder();
		StringBuilder ph = new StringBuilder();

		char[] chars = dataRow.toCharArray();
		int inx = 0;

		while(chars[inx] != DELIMITER)
		{
			name.append(chars[inx++]);
		}

		int startPos = dataRow.indexOf(DELIMITER) + 1;
		int endtPos =  dataRow.length();

		for (int i = startPos; i < endtPos; i++) {
			ph.append(dataRow.charAt(i));
		}

		if(!dbTable.containsKey(ph.toString())) {
			dbTable.put(ph.toString(), name.toString());
		}
	}

	public static String getDataPath() {
		return DATA_PATH;
	}

	public Map<String, String> getDbTable() {
		return dbTable;
	}

	private Map<String, String> dbTable;
	private static final String DATA_PATH = "data/pm_data.txt";
	private static final char DELIMITER = ',';
}