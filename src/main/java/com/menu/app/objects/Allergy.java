package com.menu.app;


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
	private String description;
	private String tx;

	// constructors
	public Allergy() {}

	public Allergy(String name, String description, String tx) {
  		setName(name);
  		setDescription(description);
		setTx(tx);
	}

	//setters
	private void setName(String name) {
		this.name = name;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	private void setTx(String tx) {
		this.tx = tx;
	}

	//getters
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getTx() {
		return tx;
	}
	
	//object functions
	@Override
	public String toString() {
		return getName() + "," + getDescription() + "," + getTx();
	}
/*
	@Override
	public String toSQLString() {
		return "'" + getName() + "','" + getDescription() + "','" + getTx() + "'";
	}*/
}
