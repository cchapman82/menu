package com.menu.app;

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
import java.util.HashMap;
import java.util.Set;

public class DatabaseController {

	//instance variables
	private static DatabaseController dbCon;
	private final String url = "jdbc:postgresql://localhost:5432/menu";
	private final String user = "";
	private final String password = "";
	private Connection conn;
	private final String[] lists = {"m", "r", "i", "a"};
	private ObjectMngmt objMngmt = ObjectMngmt.getInstance();
	private Statement stmt;


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
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

		} catch (Exception e) {
			System.out.println("Connection to database unsuccesfull");
		}
		return conn;
	}

	//check if database has tables, create if not
	private void createTable() {
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			// ingredient table
			ResultSet ingredientTable = metaData.getTables(null,null, "ingredient", null);
			if (ingredientTable.next()) {
			} else {
				String sql = "CREATE TABLE ingredient (name varchar primary key," +
				      "description varchar, cost float not null)";
				stmt.executeUpdate(sql);
			}
			// restaurant table
			ResultSet restaurantTable = metaData.getTables(null,null, "restaurant",null);
			if (restaurantTable.next()) {
			} else {
				String sql= "CREATE TABLE restaurant (name varchar primary key," +
				        " location varchar, description varchar)";
				stmt.executeUpdate(sql);
			}	
			// allergy table
			ResultSet allergyTable = metaData.getTables(null, null, "allergy", null);
			if (allergyTable.next()) {
			} else {
				String sql = "CREATE TABLE allergy (name varchar primary key," +
				      " description varchar, tx varchar)";
				stmt.executeUpdate(sql);
			}
			// menuItem table
			ResultSet menuItemTable = metaData.getTables(null, null, "menuitem", null);
			if (menuItemTable.next()) {
			} else {
				String sql = "CREATE TABLE menuItem (name varchar primary key," +
				      "restaurant varchar, menuCategory varchar, description" +
				     " varchar, price float, ingredients varchar, " +
				     "preparationStyle varchar, size varchar, allergies varchar)";
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			System.out.println("Tables not created or accessable");
		}
	}




	// Add/insert from item type and string from object
	public void addItem(String targetDatabase, String item) {
		String sqlBase = "INSERT INTO ";
		String sqlPlus = sqlBase;
		try {
			switch(targetDatabase) {
				case "m" :
					System.out.println(item);
					sqlPlus += "menuitem values (" + item + ")";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "i" :
					sqlPlus += "ingredient values (" + item + ")";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "r" :
					sqlPlus += "restaurant values (" + item + ")";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "a" :
					sqlPlus += "allergy values (" + item + ")";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
			}
		} catch (Exception e) {
			System.out.println("The item was not entered, if you have another" +
					" item from another restaurant with the same name, " +
					"please enter an identifier to the name and enter the " +
					"information again.");
			objMngmt.addItem(targetDatabase);
		}
	}
	
	//  Query tables in psql databases to populate the array list in Object Management
	public void populateLists() {	
		String sqlBase = "SELECT * FROM ";
		String sqlPlus = sqlBase;
		ResultSet rs;
		int index = 0;
		String[] result;
		try {
                        for (int i = 0; i < lists.length; i++) {
                               String type = lists[i];
				switch (type) {
					case "m" :
						index = 0;
						sqlPlus += " menuItem";
						rs = stmt.executeQuery(sqlPlus);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("restaurant") + "," +
								rs.getString("menuCategory") + "," +
								rs.getString("description") + "," +
								rs.getDouble("price") + "," +
								rs.getString("ingredients") + "," +
								rs.getString("preparationStyle")+"," +
								rs.getString("size") + "," +
								rs.getString("allergies");
							index++;		
						}
						sqlPlus = sqlBase;
						objMngmt.makeList(type, result);
						break;
					case "i" :
						index = 0;
						sqlPlus += " ingredient";
						rs = stmt.executeQuery(sqlPlus);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("description") + "," +
								rs.getDouble("cost");
							index++;
						}
						sqlPlus = sqlBase;
						objMngmt.makeList(type, result);
						break;
					case "r" :
						index = 0;
						sqlPlus += " restaurant";
						rs = stmt.executeQuery(sqlPlus);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("location") + "," +
								rs.getString("description");
							index++;
						}
						sqlPlus = sqlBase;
						objMngmt.makeList(type, result);
						break;
					case "a" :
						index = 0;
						sqlPlus += " allergy";
						rs = stmt.executeQuery(sqlPlus);
						rs.last();
						result = new String[rs.getRow()];
						rs.beforeFirst();
						while(rs.next()) {
							result[index] = rs.getString("name") + "," +
								rs.getString("description") + "," +
								rs.getString("tx");
							index++;
						}
						sqlPlus = sqlBase;
						objMngmt.makeList(type, result);
						break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getItem(String type, String name) {
		String result = "";
		ResultSet rs;
		String sqlBase = "SELECT * FROM ";
		try {
			switch(type) {
				case "m" : 
					sqlBase += "menuitem WHERE NAME = '" + name + "'";
					rs = stmt.executeQuery(sqlBase);
					rs.first();
					result = rs.getString("name") + "," +
                                                rs.getString("restaurant") + "," +
                                              	rs.getString("menuCategory") + "," +
                            	                rs.getString("description") + "," +
       		                                rs.getDouble("price") + "," + 
 						rs.getString("ingredients") + "," +
    	                                 	rs.getString("preparationStyle")+"," +
                                     		rs.getString("size") + "," +
                                       		rs.getString("allergies");
					break;
				case "a" : 
					sqlBase += "allergy WHERE NAME = '" + name + "'";
					rs = stmt.executeQuery(sqlBase);
					rs.first();
					result = rs.getString("name") + "," +
                            	                rs.getString("description") + "," +
						rs.getString("tx");
					break;
				case "i" : 
					sqlBase += "ingredient WHERE NAME = '" + name + "'";
					rs = stmt.executeQuery(sqlBase);
					rs.first();
					result = rs.getString("name") + "," +
                            	                rs.getString("description") + "," +
						rs.getDouble("cost");
					break;
				case "r" : 
					sqlBase += "restaurant WHERE NAME = '" + name + "'";
					rs = stmt.executeQuery(sqlBase);
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
	public void updateItem(String targetDatabase,String itemName,String fieldName,String newInfo) {
		String sqlBase = "UPDATE ";
		String sqlPlus = sqlBase;
		try {
			switch(targetDatabase) {
				case "m" :
					sqlPlus += "menuitem set " + fieldName + "='" + newInfo + 
						"' where name='" + itemName + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "i" :
					sqlPlus += "ingredient set " + fieldName + "='" + newInfo + 
						"' where name='" + itemName + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "r" :
					sqlPlus += "restaurant set " + fieldName + "='" + newInfo + 
						"' where name='" + itemName + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "a" :
					sqlPlus += "allergy set " + fieldName + "='" + newInfo + 
						"' where name='" + itemName + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete item from database
	public void deleteItem(String targetDatabase, String item) {
		String sqlBase = "DELETE FROM ";
		String sqlPlus = sqlBase;
		try {	
			switch(targetDatabase) {
				case "m" :
					sqlPlus += "menuitem where name='" + item + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "i" :
					sqlPlus += "ingredient where name='" + item + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "r" :
					sqlPlus += "restaurant where name='" + item + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
				case "a" :
					sqlPlus += "allergy where name='" + item + "'";
					stmt.executeUpdate(sqlPlus);
					sqlPlus = sqlBase;
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//receive update information
	public void updateInfo(String type, String name, Map map) {
		String info = "";
		Set<String> keys = map.keySet();
		for(String k : keys) {
			if (k.equals("name")) {
				info = getItem(type,name.replace(","," ").replace(" ", "_"));
				String newInfo = String.valueOf(map.get(k)).replace(","," ").replace(" ","_");
				newInfo += info.substring(info.indexOf(","), info.length());
				deleteItem(type,name.replace(","," ").replace(" ", "_"));
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
				updateItem(type,name.replace(","," ").replace(" ", "_"), k, 
						String.valueOf(map.get(k)).replace(","," ").replace(" ", "_"));
			}	
		}
		ObjectMngmt.getInstance().updateLists();

	}


}
