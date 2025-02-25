package com.menu.app;

/*
 *
 * 	Menu Program : ObjectMngmt 
 *
 * 		Singleton
 * 		Holds lists for further actions in program, adds to lists and connects with database
 * 		to add objects to database and lists
 *
 * 		instance variables
 * 			ArrayList<Restaurant> restaurantList
 * 			ArrayList<Ingredient> ingredientList
 * 			ArrayList<Allergy> allergyList
 * 			ArrayList<MenuItem> menuList
 * 			ObjectFactory of
 * 			ObjectMngmt objMngmt		---singleton instance
 *
 *
 * 		methods
 * 			addItem(type)
 * 				-> of.getObject(type)
 *			makeList(String type, String[] ss)
 *				-> pushToArray(type, of.getItem(type, stringItem))
 *			pushToArray(type, object)
 *				-> objArrayList<Object>.add(object)
 *			pushToDatabase(String type, String stringIem)
 *				-> DatabaseController.getInstance().addItem(type, stringItem)
 *			makeRestaurants()
 *				-> makeMenus(r)
 *			makeMenus(Restaurant r)
 *			updateRestaurant(ObjectMaker obj)
 *			addItemToRestaurant(String item, String rest)
 *			getRestaurantStudy(String name)
 *			getRestaurant(String name)
 *			getMenuItem(String name)
 *			checkIfExists(String type. String name)
 *			getMenuList(Restaurant r)
 *			getRestaurantList()
 *
 *
 * 	Author: Christopher Chapman
 * 	Version: 1.0
 *
 *
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class ObjectMngmt {

	//ArrayLists needed for program
	private static ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	private static ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private static ArrayList<Allergy> allergyList = new ArrayList<Allergy>();
	private static ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

	//trying to make the factory pattern to get object, then it can be added to the list
	private ObjectFactory of = new ObjectFactory();

	//Singleton Instance
	private static ObjectMngmt objMngmt = null;

	//default constructor
	private  ObjectMngmt() {}

	//get Singleton Instance
	public static ObjectMngmt getInstance() {
		if (objMngmt == null) {
			return objMngmt = new ObjectMngmt();
		} else {
			return objMngmt;
		}
	}

	// add object info based on type
	public void addItem(String type) {
		ObjectMaker om = of.getObject(type);
	}

	//make list based on databse string
	public void makeList(String type, String[] ss) {
		for (int i = 0; i < ss.length; i++) {
			pushToArray(type, of.getObjectString(type, ss[i]));
		}
	}

	//add to program array list for future actions based on object from ObjectFactory
	public void pushToArray(String type, ObjectMaker obj) {
		switch(type) {
			case "m" :
				MenuItem mi = (MenuItem) obj;
				menuList.add(mi);
				break;
				
			case "r" :
				Restaurant ri = (Restaurant) obj;
				restaurantList.add(ri);
				break;
			case "i" :
				Ingredient iI = (Ingredient) obj;
				ingredientList.add(iI);
				break;
			case "a" :
				Allergy ai = (Allergy) obj;
				allergyList.add(ai);
				break;
			default :
				System.out.println("Type not recognized");
		}
	}

	//add item to database
	public void pushToDatabase(String type, String s) {
		DatabaseController.getInstance().addItem(type, s.toLowerCase());
	}

	//loop through restaurants
	public void makeRestaurants() {
		for (Restaurant r : restaurantList) {
			makeMenus(r);
		}
	}

	//make menu list for restaurant by looping through all menuItems
	private void makeMenus(Restaurant r) {
		String result = "";
		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getRestaurant().equals(r.getName())) {
				result += menuList.get(i).getName() + ",";
			}
		}
		// if there is items, do not take last comma
		if (result != "") {
			r.setMenu(result.substring(0, result.lastIndexOf(",")));
		}
	}

	public void updateRestaurant(ObjectMaker obj) {
		MenuItem mi = (MenuItem) obj;
		addItemToRestaurant(mi.getName(), mi.getRestaurant());
	}

	//  add item to restaurantMenuItemList if not already included
	private void addItemToRestaurant(String item, String rest) {
		Restaurant r = getRestaurant(rest);
		r.setNewItem(item);
	}

	// get restaurant if added, if not, throw to calling function
	public void getRestaurantStudy(String r) {
		Restaurant res = getRestaurant(r);
		try {
			Study.getInstance().studyRestaurant(res);
			
		} catch (NullPointerException e) {
			GUIFactory.getGUI("ms", "No restaurant selected, return to main menu");
		}
	}

	public Restaurant getRestaurant(String r) {
		Restaurant result = new Restaurant();
		int control = 0;
		for (Restaurant res : restaurantList) {
			if (res.getName().equals(r)) {
				result = res;
				control = 1;
			}
		}
		
		// if restaurant is not present 
		if (control == 0) {
		       if (restaurantList.size() == 0) {
				result.setDescription("There are not any restaurants entered, please" +
						" enter to continue");
		       } else {
			       String message="\t\tRestaurant not recognized" +
				       "\n\t\tPlease enter from list bellow:";
			// give restaurant list
		       for (Restaurant res : restaurantList) {
					message += res.getName() +"\n";
			}
			result.setDescription(message);
		       }
		}
		return result;
	}

	//study methods
	//
	//get menu Item to study
	public MenuItem getMenuItem(String name) {
		MenuItem m = new MenuItem();
		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getName().equals(name)) {
				m = menuList.get(i);
			}
		}
		return m;
	}

	//helper methods
	//
	// check if item exisits in list based on type of item and name of item
	public static Boolean checkIfExists(String type, String name) {
		Boolean result = false;
		switch(type) {
			case "m" :
				for (MenuItem mi : menuList) {
					if (mi.getName().equals(name)) {
						result = true;
					}
				}
				break;
			case "r" :
				for (Restaurant ri : restaurantList) {
					if (ri.getName().equals(name)) {
						result = true;
					}
				}
				break;
			case "i" :
				for (Ingredient ii : ingredientList) {
					if (ii.getName().equals(name)) {
						result = true;
					}
				}
				break;
			case "a" :
				for (Allergy ai : allergyList) {
					if (ai.getName().equals(name)) {
						result = true;
					}
				}
				break;
		}
		return result;
	}

	// get restaurant from list
	public String getRestaurantList() {
		String result = "";
		for (Restaurant r : restaurantList) {
			result += r.getName() + "\n";
		}
		result = result.substring(0, result.lastIndexOf("\n"));
		return result;
	}

	// get Menu Item based on restaurant associated with
	public String getMenuList(String restaurant) {
		String result = "";
		for (MenuItem m : menuList) {
			if (m.getRestaurant().equals(restaurant)) { 
				result += m.getName() + "\n";
			}
		}
		result = result.substring(0, result.lastIndexOf("\n"));
		return result;
	}
	// get Menu Item based on restaurant associated with
	public String getIngredientList() {
		String result = "";
		for (Ingredient i : ingredientList) {
			result += i.getName() + "\n";
		}
		result = result.substring(0, result.lastIndexOf("\n"));
		return result;
	}
	// get Menu Item based on restaurant associated with
	public String getAllergyList() {
		String result = "";
		for (Allergy a : allergyList) {
			result += a.getName() + "\n";
		}
		result = result.substring(0, result.lastIndexOf("\n"));
		return result;
	}
	public String getObjectString(String type, String name) {
		String result = "";
		switch(type) {
			case "m" :
				for(MenuItem m : menuList) {
					if(m.getName().equals(name)) {
						result += (m.menuItemToString());
					}
				}
			
			case "a" :
				for(Allergy a : allergyList) {
					
					if(a.getName().equals(name)) {
						result += a.allergyToString();
					}
				}
			case "r" :
				for(Restaurant r : restaurantList) {
					
					if(r.getName().equals(name)) {
						result += r.restaurantToString();
					}
				}
			case "i" :
				for(Ingredient i : ingredientList) {
					
					if(i.getName().equals(name)) {
						result += i.ingredientToString();
					}
				}
		}
		return result;
	}
}

