package com.phonebook.manager.model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBModel {

	public DBModel() {

		initDB();
		dataTabel = new HashMap<>();
		readData();
	}

	private Connection getConnection() {

		conn = null;
		try {

			conn = DriverManager.getConnection(DATABASE_URL);

			if (conn != null) {

				System.out.println("A new connection is established.");
			} else {
				System.out.println("The connection isn't ready.");
			}

		} catch (Exception e) {
			System.err.println("Error: can't connect to the database.");
		}

		return conn;
	}

	private void initDB() {

		File file = new File(DATABASE_PATH);

		if (!file.exists()) {

			System.out.println("The structure isn't ready.");
			System.out.println("Creating a new structure...");

			File dirData = new File("data");
			File dataFile = new File("data/pm_data.db");
			try {

				dirData.mkdir();
				dataFile.createNewFile();

				// Init the database with the table
				Connection tempConn = getConnection();

				try {
					Statement stat = tempConn.createStatement();

					final String query = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
							+ "name TEXT NOT NULL, " + "phone_number TEXT NOT NULL UNIQUE, addition_date TEXT)";

					stat.executeUpdate(query);

					System.out.println("The db structure is ready.");
					conn.close();
				} catch (SQLException e) {

					System.err.println("Error: Create statement isn't ready.");
				}

			} catch (IOException e) {

				System.err.println("Error: a failure in the file creation");
			}

		} else {
			System.out.println("The structure is ready.");
		}
	}

	public void writeData(String name, String phoneNumber) {

		Connection tempConn = getConnection();

		try {

			final String query = "INSERT INTO " + TABLE_NAME+ " (name, phone_number) VALUES (?, ?)";
			PreparedStatement preStat = tempConn.prepareStatement(query);

			preStat.setString(1, name);
			preStat.setString(2, phoneNumber);

			preStat.executeUpdate();

			tempConn.close();

		} catch (Exception e) {
			System.out.println("Can't write data");
			System.err.println(e);
		}
	}

	public void readData() {

		Connection tempConn = getConnection();

		try {

			final String query = "SELECT name, phone_number FROM " + TABLE_NAME ;

			Statement stat = tempConn.createStatement();

			ResultSet reSet = stat.executeQuery(query);

			// Add the data to the hashtable
			while(reSet.next()) {

				dataTabel.put(reSet.getString("phone_number"), reSet.getString("name"));
			}

			tempConn.close();

		} catch (Exception e) {
			System.out.println("Cann't load data");
			System.err.println(e);
		}
	}

	public boolean isPhoneExists(String phone) {

		Connection tempConn = getConnection();

		boolean result=false;

		try {

			String query = "SELECT phone_number FROM " + TABLE_NAME + " WHERE phone_number = ?";

			PreparedStatement preStat = tempConn.prepareStatement(query);

			preStat.setString(1, phone);

			ResultSet reSet = preStat.executeQuery();

			result = reSet.next();

			conn.close();

		} catch (Exception e) {
			System.out.println("Can't read phone number data ");
			System.err.println(e);
		}

		return result;
	}

	public void updateData(String name, String phoneNumber, String oldPhoneNumber) {

		Connection tempConn = getConnection();

		try {

			String query = "UPDATE " + TABLE_NAME + " SET name = ?, phone_number = ? WHERE phone_number = ?";

			PreparedStatement preStat = tempConn.prepareStatement(query);

			preStat.setString(1, name);
			preStat.setString(2, phoneNumber);
			preStat.setString(3, oldPhoneNumber);

			preStat.executeUpdate();

			conn.close();

		} catch (Exception e) {
			System.out.println("Can't update data");
			System.err.println(e);
		}
	}
	
	public void deleteData(String name, String phoneNumber) {

		Connection tempConn = getConnection();

		try {

			String query = "DELETE FROM " + TABLE_NAME + " WHERE phone_number = ?";

			PreparedStatement preStat = tempConn.prepareStatement(query);

			preStat.setString(1, phoneNumber);

			preStat.executeUpdate();

			conn.close();

		} catch (Exception e) {
			System.out.println("Can't delete data");
			System.err.println(e);
		}
	}

	public HashMap<String, String> getDataAsKeyValue() {
		return dataTabel;
	}

	public void clearHashTable() {

		dataTabel.clear();
	}

	private Connection conn;
	private static final String DATABASE_PATH = "data/pm_data.db";
	private static final String DATABASE_URL = "jdbc:sqlite:data/pm_data.db";
	private static final String TABLE_NAME = "phonebook";
	private HashMap<String, String> dataTabel;
}
