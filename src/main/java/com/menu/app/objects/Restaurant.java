package com.menu.app;

/*
 * 	Menu Program : Restaurant
 *
 * 		instance variables
 * 			String name
 * 			String location
 * 			String description
 * 			String[] menu;
 *
 * 		methods
 * 			setters/getters
 * 			restaurantToString()
 * 			*toSQLstring() 			
 *
 * 	Author: Christopher Chapman
 * 	Version: 1.0
 *
 * */


public class Restaurant implements ObjectMaker {

	// class variables
	private String name;
	private String location;
	private String description;
	private String[] menu = new String[0];

	// constructors
	public Restaurant() {}
	
	public Restaurant(String name, String location, String description) {
  		setName(name);
  	 	setLocation(location);
  		setDescription(description);
	}

	//setters
	private void setName(String name) {
		this.name = name;
	}
	private void setLocation(String location) {
		this.location = location;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setMenu(String menu) {
		String[] result = menu.split(",");
		for (int i = 0; i < result.length; i++) {
			if (result[i].startsWith("_")) {
				result[i] = result[i].substring(1);
			}
			result[i] = result[i].replace("_", " ");
		}
		this.menu = result;
	}
	public void setNewItem(String item) {
		String newMenu = "";
		for (int i = 0; i < menu.length; i++) {
			newMenu += menu[i] + ",";
		}
		newMenu += item;
		this.setMenu(newMenu);
	}

	//getters
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public String getDescription() {
		return description;
	}
	public String getMenu() {
		String result = "";
		if (menu.length > 0) {
			for(int i = 0; i < menu.length; i++) {
				if(i == menu.length - 1) {
					result += menu[i];
				} else {
					result += menu[i] + ",";
				}
			}
		}
		return result;
	}


	//other functions
	@Override
	public String toString() {
		return getName() + "," + getLocation() + "," + getDescription();
	}
/*
	@Override
	public String toSQLString() {
		return "'" + getName() + "','" + getLocation() + "','" + getDescription() + "'";
	}*/
}
