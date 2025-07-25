package com.menu.app.database;

/*		Menu Program : Database Controller
 *               
 *               Singleton paradigm
 *              lists of objects on next boot up,it to will deal with a postresql database 
 *
 *
 *		Instance Variables
 			DatabaseController dbCon
			String url
			String user
			String password
			Connection conn
			String[] lists
			ObjectMngmt objMngmt
			Statement stmt		

		Methods
			Create
				getInstance()
				Connection connect()
				createTable()
				addItem(String targetDatabase, String item) 
			Read
				populateLists(String [] lists)
					-> objMngmt.makeList(type, result)
			Update
				updatItem(String targetDatabase,String itemName,String fieldName,
					String newInfo)
			Delete
				deleteItem(String targetDatabase, String item)

 *
 *      Author: Christopher Chapman
 *      Version: 1.0
 *
 * */

import java.sql.*;
import java.util.Map;
import java.util.Set;

import com.menu.app.objects.ObjectMngmt;

import java.util.Properties;
import java.io.InputStream;

public class DatabaseController {

	//instance variables
	private static DatabaseController dbCon;
	private Connection conn;
	private final String[] lists = {"m", "r", "i", "a"};
	private ObjectMngmt objMngmt = ObjectMngmt.getInstance();
	private Statement stmt;
	private PreparedStatement prepdstmt;
	private String sql;
	private Properties prop = new Properties();


	//constructors with initial methods
	private DatabaseController() {
		conn = connect();
		createTable();
		populateLists();
	}


	public static DatabaseController getInstance() {

		if (dbCon == null) {
			return dbCon = new DatabaseController();
		} else {
			return dbCon;
		}
	}


	//initial methods
	private Connection connect() {
		try {
			InputStream dbprops = DatabaseController.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(dbprops);
			conn = DriverManager.getConnection(prop.getProperty("URL"),
					prop.getProperty("CONNECTION_USERNAME"),
					prop.getProperty("CONNECTION_PASSWORD"));
			//conn = DriverManager.getConnection("jdbc:sqlite:menu.db");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection to database unsuccesfull");
		}
		return conn;
	}

	//check if database has tables, create if not
	private void createTable() {
		try {
			sql = "CREATE TABLE IF NOT EXISTS ingredient (id serial primary key, " +
			       "name varchar, description varchar, cost float not null)";
			stmt.executeUpdate(sql);
			sql= "CREATE TABLE IF NOT EXISTS restaurant (id serial primary key, " +
				"name varchar, location varchar, description varchar)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS allergy (id serial primary key, " +
					"name varchar, items varchar)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS menuItem (id serial primary key, " +
					"name varchar, restaurant varchar, category varchar, description" +
			     " varchar, price float, ingredients varchar, " +
			     "preparation varchar, size varchar, allergies varchar, current char(1) DEFAULT 'y')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Tables not created or accessable");
			e.printStackTrace();
		}
	}




	// Add/insert from item type and string from object
	public void addItem(String type, String item) {
		try {
			String[] itemFields = item.split(",");
			switch(type) {
				case "m" :
					System.out.println(item);
					sql = "INSERT INTO menuitem (name, restaurant, category, description, price, ingredients, preparation, size, allergies, current) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemFields[0]);
					prepdstmt.setString(2, itemFields[1]);
					prepdstmt.setString(3, itemFields[2]);
					prepdstmt.setString(4, itemFields[3]);
					prepdstmt.setDouble(5, Double.parseDouble(itemFields[4]));
					prepdstmt.setString(6, itemFields[5]);
					prepdstmt.setString(7, itemFields[6]);
					prepdstmt.setString(8, itemFields[7]);
					prepdstmt.setString(9, itemFields[8]);
					prepdstmt.setString(10, itemFields[9]);
					prepdstmt.executeUpdate();
					break;
				case "i" :
					sql = "INSERT INTO ingredient (name, description, cost) values (?, ?, ?)";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemFields[0]);
					prepdstmt.setString(2, itemFields[1]);
					prepdstmt.setDouble(3, Double.parseDouble(itemFields[2]));
					prepdstmt.executeUpdate();
					break;
				case "r" :
					sql = "INSERT INTO restaurant (name, location, description) values (?, ?, ?)";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemFields[0]);
					prepdstmt.setString(2, itemFields[1]);
					prepdstmt.setString(3, itemFields[2]);
					prepdstmt.executeUpdate();
					break;
				case "a" :
					sql= "INSERT INTO allergy (name, items) values (?, ?)";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemFields[0]);
					prepdstmt.setString(2, itemFields[1]);
					prepdstmt.executeUpdate();
					break;
			}
		} catch (Exception e) {
			System.out.println("The item was not entered, if you have another" +
					" item from another restaurant with the same name, " +
					"please enter an identifier to the name and enter the " +
					"information again.");
			e.printStackTrace();
			objMngmt.addItem(type);
		}
	}
	
	//  Query tables in psql databases to populate the array list in Object Management
	public void populateLists() {	
		ResultSet rs;
		int index = 0;
		String[] result;
		try {
                        for (int i = 0; i < lists.length; i++) {
                               String type = lists[i];
				switch (type) {
					case "m" :
						index = 0;
						sql = "SELECT name, restaurant, category, description, price, ingredients, preparation, size, allergies, current FROM menuItem";
						rs = stmt.executeQuery(sql);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("restaurant") + "," +
								rs.getString("category") + "," +
								rs.getString("description") + "," +
								rs.getDouble("price") + "," +
								rs.getString("ingredients") + "," +
								rs.getString("preparation")+"," +
								rs.getString("size") + "," +
								rs.getString("allergies") + "," +
								rs.getString("current");
							index++;		
						}
						objMngmt.makeList(type, result);
						break;
					case "i" :
						index = 0;
						sql =  "SELECT name, description, cost FROM ingredient";
						rs = stmt.executeQuery(sql);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("description") + "," +
								rs.getDouble("cost");
							index++;
						}
						objMngmt.makeList(type, result);
						break;
					case "r" :
						index = 0;
						sql = "SELECT name, location, description FROM restaurant";
						rs = stmt.executeQuery(sql);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("location") + "," +
								rs.getString("description");
							index++;
						}
						objMngmt.makeList(type, result);
						break;
					case "a" :
						index = 0;
						sql = "SELECT name, items FROM allergy";
						rs = stmt.executeQuery(sql);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("items");
							index++;
						}
						objMngmt.makeList(type, result);
						break;
				}
			}
		} catch (SQLException e) {
			System.out.println("Could not read items from database insert into object");
			e.printStackTrace();
		}
	}

	private String getItem(String type, String itemName) {
		String result = "";
		ResultSet rs;
		try {
			switch(type) {
				case "m" : 
					sql = "SELECT * FROM menuitem WHERE NAME = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					rs = prepdstmt.executeQuery();
					rs.first();
					result = rs.getString("name") + "," +
                                                rs.getString("restaurant") + "," +
                                              	rs.getString("category") + "," +
                            	                rs.getString("description") + "," +
       		                                rs.getDouble("price") + "," + 
 						rs.getString("ingredients") + "," +
    	                                 	rs.getString("preparation")+"," +
                                     		rs.getString("size") + "," +
                                       		rs.getString("allergies") + "," +
						rs.getString("current");
					break;
				case "a" : 
					sql = "SELECT name, items FROM allergy WHERE NAME = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					rs = prepdstmt.executeQuery();
					rs.first();
					result = rs.getString("name") + "," +
                            	                rs.getString("items");
					break;
				case "i" : 
					sql = "SELECT * FROM ingredient WHERE NAME = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					rs = prepdstmt.executeQuery();
					rs.first();
					result = rs.getString("name") + "," +
                            	                rs.getString("description") + "," +
						rs.getDouble("cost");
					break;
				case "r" : 
					sql = "SELECT name, location, description FROM restaurant WHERE NAME = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					rs = prepdstmt.executeQuery();
					rs.first();
					result = rs.getString("name") + "," +
						rs.getString("location") + "," +
                            	                rs.getString("description");
					break;

			}
			
		} catch (SQLException e)  {
			e.printStackTrace();
		}
		return result;
	}

	// update item already in database from ojbect string
	public void updateItem(String type, String itemName, String fieldName, String newInfo) {
		try {
			System.out.println(itemName + " " + fieldName + " " + newInfo);
			switch(type) {
				case "m" :
					sql = "UPDATE menuitem set " + fieldName + " = ? where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					if (fieldName.equals("price")) {
						Double price = Double.parseDouble(newInfo);
						prepdstmt.setDouble(1, price);
						prepdstmt.setString(2, itemName);
					} else {
						prepdstmt.setString(1, newInfo);
						prepdstmt.setString(2, itemName);
					}
					prepdstmt.executeUpdate();
					break;
				case "i" :
					sql = "UPDATE ingredient set " + fieldName + " = ? where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					if (fieldName.equals("cost")) {
						Double cost = Double.parseDouble(newInfo);
						prepdstmt.setDouble(1, cost);
						prepdstmt.setString(2, itemName);
					} else {
						prepdstmt.setString(1, newInfo);
						prepdstmt.setString(2, itemName);
					}
					prepdstmt.executeUpdate();
					break;
				case "r" :
					sql = "UPDATE restaurant set " + fieldName + "  = ?  where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, newInfo);
					prepdstmt.setString(2, itemName);
					prepdstmt.executeUpdate();
					break;
				case "a" :
					sql = "allergy set " + fieldName + " = ? where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, newInfo);
					prepdstmt.setString(2, itemName);
					prepdstmt.executeUpdate();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete item from database
	public void deleteItem(String type, String itemName) {
		try {	
			switch(type) {
				case "m" :
					sql = "DELETE FROM menuitem where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					prepdstmt.executeUpdate();
					break;
				case "i" :
					sql = "DELETE FROM ingredient where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					prepdstmt.executeUpdate();
					break;
				case "r" :
					sql = "DELETE FROM restaurant where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					prepdstmt.executeUpdate();
					break;
				case "a" :
					sql = "DELETE FROM allergy where name = ?";
					prepdstmt = conn.prepareStatement(sql);
					prepdstmt.setString(1, itemName);
					prepdstmt.executeUpdate();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//receive update information
	public void updateInfo(String type, String itemName, Map<String, String> map) {
		String info = "";
		Set<String> keys = map.keySet();
		for(String k : keys) {
			if (k.equals("name")) {
				info = getItem(type, itemName.replace(","," "));
				String newInfo = String.valueOf(map.get(k)).replace(","," ");
				newInfo += info.substring(info.indexOf(","), info.length());
				deleteItem(type, itemName.replace(","," "));
				String[] nInfo = newInfo.split(",");
				String nnInfo ="'";
				for(int i = 0; i < nInfo.length; i++) {
					switch(type) {
						case "m" :
							if(i == 0) {
								nnInfo += nInfo[i] + "','";
							} else if(i == 3) {
								nnInfo += nInfo[i] + "',";	
							}else if(i == 4) {
								nnInfo +=  nInfo[i] + ",'";
							} else if(i == nInfo.length - 1) {
								nnInfo += nInfo[i] + "'"; 
							} else {
								nnInfo +=  nInfo[i] + "','";
							}
							break;
						case "a" :
						case "r" :
							if(i == 0) {
								nnInfo += nInfo[i] + "','";
							} else if(i == nInfo.length - 1) {
								nnInfo += nInfo[i] + "'"; 
							} else {
								nnInfo +=  nInfo[i] + "','";	
							}
							break;
						case "i" :
							if(i == 0) {
								nnInfo += nInfo[i] + "','";
							} else if(i == 1) {
								nnInfo += nInfo[i] + "',";
							} else {
								nnInfo += nInfo[i];
							}
							break;
					}
				}
				addItem(type,nnInfo);
			} else {
				updateItem(type,itemName, k, 
						String.valueOf(map.get(k)));
			}	
		}
		ObjectMngmt.getInstance().updateLists();

	}


}
