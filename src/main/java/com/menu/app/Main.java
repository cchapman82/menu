package com.menu.app;

/*
 *
 *	Menu Program: Main User Input
 *
 *		global variables
 *			MainController mc
 *
 *		methods
 *			mc.addMainItem(userInput)
 *			mc.closeApp()
 *			mc.studyRestaurant()
 *			GUIfactory.getGUI(GUIType, name)
 *				-> index
 *				-> message
 *				-> study
 *
 *		 
 *	--- change to be part of another aspect based on what the user wants to do
 *		greet user and get info from the user
 *			restaurant
 *			people
 *			menuItem
 *			ingredient
 *			allergy
 *
 * 	Author: Christopher John Chapman
 * 	version: 1.0
 * 
 * */

import javax.swing.*;
import java.awt.event.*;

public class Main {

	private static MainController mc = MainController.getInstance();
	//start up activities and get initial ui
	public static void main( String[] args) {
		mc.openApp();
		GUIFactory.getGUI("ix", "Home");
	}
	
	// separated to insure upgrades and consistent formatting
	public static void setOption(String s) {
		String item = "";
		Boolean update = false;
		s = s.toLowerCase();
		s.replace(" ", "_");
		//separate the type(s) and item, and if to update item(u)
		if (s.contains(":")) {
			item = s.substring(s.indexOf(":") + 1, s.length());
			s = s.substring(0, s.indexOf(":"));
			if (s.contains("u")) {
				update = true;
				s = s.substring(0, s.indexOf("u"));
			}
		}
		//based on type of item
		switch(s) {
			//back to index ui
			case "." :
				break;
			// do the same thing for all of these options
			case "a" :
			case "i" :
			case "r" :
			case "m" : 
				//if no item specified, get item name
				if (item.equals("")) {
					item = JOptionPane.showInputDialog(
						"Please enter name of item");
				}
				//if item needs to update
				if (update) {
					if(ObjectMngmt.getInstance().checkIfExists(
                                                                s, item)) {
                                                //if entered, find item or rename item
						System.out.println(s + "****************");
                                                GUIFactory.getGUI("u", s, item);//****
                                        } else {
						GUIFactory.getGUI("ms", item.toUpperCase() 
								+ "  not found not entered");
					}
				} else {
					//check if item is already entered
					if(ObjectMngmt.getInstance().checkIfExists(
								s, item)) {
						if(s.equals("m")) {
							GUIFactory.getGUI("mc", item);
						} else {
							//if entered, find item or rename item
							GUIFactory.getGUI("f", s, item);
						}
					} else {
						//if not entered, continue to adding
						GUIFactory.getGUI(s, item);
								}
				}
				GUIFactory.getGUI(".", item);
				break;
			case "f" :
				//find item throuth ui
				GUIFactory.getGUI(s, "r", item);
				break;
			default :
				//default action is to study
		//		mc.studyRestaurant(s);
				GUIFactory.getGUI(".", s);
				break;
		}
	}
}
