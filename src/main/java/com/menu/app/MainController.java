package com.menu.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.menu.app.database.DatabaseController;
import com.menu.app.gui.GUIFactory;
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
	private static List<String> strings;
	private String fileName = "src\\main\\resources\\config.properties";

	// default constructory
	private MainController() {
	}

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
		DatabaseController.getInstance();
		objMngmt.makeRestaurants();
		Main.setOption(".");
	}

	// function to get the first option -- add menu item
	public void addMainItem(String type) {
		objMngmt.addItem(type);
	}

	// send to menu to study items of restaurant
	public void studyRestaurant(String r) {
		objMngmt.getRestaurantStudy(r);
	}

	// kill app -- add printing/logging actions
	public void closeApp() {
	}
}
