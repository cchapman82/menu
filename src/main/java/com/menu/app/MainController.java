package com.menu.app;

import com.menu.app.objects.ObjectMngmt;

/*
 * 	Menu Program: Main Controller
 * 		
 * 		takes input from main and sends instructions to other parts of the program
 * 		*****Might be superfluous, if more fuctionality makes it more important -> stay
 * 				might be good to keep to insure compatability
 *
 * 		instance varaiables
 * 			ObjectMngmt objMngmt
 * 			DatabaseController dc
 * 			MainController mc		-- singleton
 *
 * 		methods
 * 			getInstance()
 * 			openApp()
 * 				-> objMngmt.makeRestaurants()
 * 			addMainItem(type)
 * 				-> objMngmt.addItem(type)
 * 			studyRestaurant(r)
 *				-> objMngmt.getRestaurantStuy(r)
 * 			closeApp()
 *
 *
 *	Author: Christopher Chapman
 *	Version: 1.0
 *
 *
 * */

public class MainController {

	private ObjectMngmt objMngmt = ObjectMngmt.getInstance();
	private static MainController mc = null;

	// default constructory
	private MainController () {}

	// access singleton object
	public static MainController getInstance() {
		if (mc == null) {
			return new MainController();
		} else {
			return mc;
		}
	}
	
	// instantiate initial lists
	public void openApp() {
			objMngmt.makeRestaurants();
		}

	// function to get the first option --  add menu item
	public void addMainItem(String type) {
		objMngmt.addItem(type);
	}

	// send to menu to study items of restaurant
	public void studyRestaurant(String r) {
		objMngmt.getRestaurantStudy(r);
	}

	// kill app -- add printing/logging actions
	public void closeApp() {}
}
