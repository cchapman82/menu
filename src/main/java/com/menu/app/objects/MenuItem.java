package com.menu.app;

/*
 * 	Menu Program : MenuItem
 *
 *		instance variables
 *			String name
 *			String restaurant
 *			String menuCategory
 *			String description
 *			Double price
 *			String[] ingredients
 *			String preparationStyle
 *			String size
 *			String[] allergies
 *
 *		methods
 *			setters/getters
 *			menuItemToString()
 *			*toSQLString()
 *
 *
 *	Author: Christopher Chapman
 *	Version 1.0
 *
 *
 * */


public class MenuItem implements ObjectMaker {

	
	private String name;
	private String restaurant;
	private String menuCategory;
	private String description;
	private Double price;
	private String[] ingredients = new String[0];
	private String preparationStyle;
	private String size;
	private String[] allergies = new String[0];
	
	//constructors	
	public MenuItem() {}

	public MenuItem(String name, String restaurant, String menuCategory, String description,
			Double price, String ingredients, String preparationStyle, String size,
			String allergies) {
		setName(name);
		setRestaurant(restaurant);
		setMenuCategory(menuCategory);
		setDescription(description);
		setPrice(price);
		setIngredients(ingredients);
		setPreparationStyle(preparationStyle);
		setSize(size);
		setAllergies(allergies);
	}
	
	//getters
	public String getName() {
		return name;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public String getMenuCategory() {
		return menuCategory;
	}
	public String getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	public String getIngredients() {
		String result = "";
		for (int i = 0; i < ingredients.length; i++) {
			if (i == ingredients.length - 1) {
				result += ingredients[i];
			} else {
				result += ingredients[i] + ",";
			}
		}
		return result;
	}
	public String getAllergies() {
		String result = "";
		for (int i = 0; i < allergies.length; i++) {
			if (i == allergies.length - 1) {
				result += allergies[i];
			} else {
				result += allergies[i] + ",";
			}
		}
		return result;
	}
	public String getPreparationStyle() {
		return preparationStyle;
	}
	public String getSize() {
		return size;
	}

	//setters 
	public void setName(String name) {
		this.name = name;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	public void setMenuCategory(String menuCategory) {
		this.menuCategory = menuCategory;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setIngredients(String ingredients) {
		String[] result = ingredients.split(",");
		for (int i = 0; i < result.length; i++) {
			if (result[i].startsWith("_")) {
				result[i] = result[i].substring(1);
			}
			result[i] = result[i].replace("_", " ");
		}
		this.ingredients = result;
	}
	public void setAllergies(String allergies) {
		String[] result = allergies.split(",");
		for (int i = 0; i < result.length; i++) {
			if (result[i].startsWith("_")) {
				result[i] = result[i].substring(1);
			}
			result[i] = result[i].replace("_", " ");
		}
		this.allergies = result;
	}
	
	public void setPreparationStyle(String preparationStyle) {
		this.preparationStyle = preparationStyle;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	//other class methods
	@Override
	public String toString() {
		return getName() + "," + getRestaurant() + "," + getMenuCategory()
			+ "," + getDescription() + "," + getPrice() 
			+ "," + getIngredients() + "," + getPreparationStyle() 
			+ "," + getSize() + "," + getAllergies();
	}
	/*
	@Override
	public String toSQLString() {
		return "'" + getName() + "','" + getRestaurant() + "','" + this.getMenuCategory()
			+ "','" + getDescription() + "'," + getPrice() 
			+ ",'" + getIngredients() + "','" + getPreparationStyle() 
			+ "','" + getSize() + "','" + getAllergies() + "'";
	}*/
}
