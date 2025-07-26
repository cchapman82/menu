package com.menu.app.objects;


/*	Menu Program : Allergy
 *
 *	hold info for further actions with questioning for menu training
 *
 * 	instance variables
 * 		String name
 * 		String description
 * 		String tx
 *
 * 	methods
 * 		getters/setters
 * 		allergyToString()
 * 		*toSQLString()
 *
 *
 * 	Author: Christopher Chapman
 * 	Version: 1.0
 *
 * */


public class Allergy implements ObjectMaker {


	// class variables
	private String name;
	private String[] items;

	// constructors
	public Allergy() {}

	public Allergy(String name, String items) {
  		setName(name);
  		setItems(items);
	}

	//setters
	private void setName(String name) {
		this.name = name;
	}
	private void setItems(String items) {
		this.items = items.split("~");
	}

	//getters
	public String getName() {
		return name;
	}
	public String getItems() {
		String result = "";
		for(int i = 0; i < items.length; i++) {
			result += items[i].trim() + "~";
		}
		return result.substring(0, result.length()-1);
	}
	
	//object functions
	@Override
	public String toString() {
		return getName() + "," + getItems();
	}
}
