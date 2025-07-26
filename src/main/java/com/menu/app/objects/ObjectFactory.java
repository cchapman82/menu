package com.menu.app;

/*
 *		This is the factory of the factory pattern.  It will generate an object
 *		dynamically based on the input to its function.
 *		
 *		methods
 *			getObject(type)
 *			getObjectString(type, string)
 *
 *
 *	Author: Christopher Chapman
 *	Version: 1.0
 *
 *
 * */

public class ObjectFactory {

	public static ObjectMngmt objMngmt = ObjectMngmt.getInstance();

	public ObjectFactory() {}
	
	//return unpopulated object
	public ObjectMaker getObject(String s) {

		switch(s) {
			case "m" :
				MenuItem mi = new MenuItem();
				return mi;
			case "r" :
				Restaurant r = new Restaurant();
			case "i" :
				Ingredient i = new Ingredient();
			case "a" :
				Allergy a = new Allergy();
			default :
				return new MenuItem();
		}
	}

	// on start up, get string from database and return populated object
	public ObjectMaker getObjectString(String s, String item) {
		item = item.replace("_", " ");
		String[] ss = item.split(",");
		switch(s) {
			case "m" :
				MenuItem mi = new MenuItem(ss[0], ss[1], ss[2], ss[3],
						Double.parseDouble(ss[4]), ss[5], ss[6], ss[7],ss[8], ss[9]);
				return mi;
			case "r" :
				Restaurant r = new Restaurant(ss[0], ss[1], ss[2]);
				return r;
			case "i" :
				Ingredient i = new Ingredient(ss[0], ss[1],Double.parseDouble(ss[2]));
				return i;
			case "a" :
				Allergy a = new Allergy(ss[0], ss[1]);
				return a;
			default :
				return new MenuItem();
		}
	}
}
