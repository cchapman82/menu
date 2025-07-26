package com.menu.app.objects;

/*		Menu Program : Ingredient
 *
 *		instance variables
 *  			String name
 * 			String description
 * 			Double cost
 *
 * 		menthods
 * 			setters/getters
 * 			ingredientToString()
 * 			*toSQLString()
 *
 *
 *
 * 	Author: Christopher Chapman
 * 	Version: 1.0
 *
 * */

public class Ingredient implements ObjectMaker {

	// class variables
	private String name;
	private String description;
	private Double cost;

	// constructors
	public Ingredient() {}

	public Ingredient(String name, String description, Double cost) {
  		setName(name);
  		setDescription(description);
  		setCost(cost);
	}

	//setters
	private void setName(String name) {
		this.name = name;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	private void setCost(Double cost) {
		this.cost = cost;
	}

	//getters
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Double getCost() {
		return cost;
	}

	//other functions
	@Override
	public String toString() {
		return name  + "," + getDescription() + "," + getCost();
	}
}
