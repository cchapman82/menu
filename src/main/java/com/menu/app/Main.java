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
		setOption(".");
	}
	
	// separated to insure upgrades and consistent formatting
	public static void setOption(String type) {
		String item = "";
		type = type.toLowerCase();
		//based on type of item
		switch(type) {
			//back to index ui
			case "." :
				GUIFactory.getGUI("ix", "Home");
				break;
			// do the same thing for all of these options
			case "a" :
			case "i" :
			case "r" :
			case "m" : 
				//get item name
				item = JOptionPane.showInputDialog("Please enter name of item");
				if(item == "" || item == null) {
					setOption(".");
				} else if(ObjectMngmt.getInstance().checkIfExists(type, item)) {
					if(type.equals("m")) {
						setOption("mc", item);
					} else {
						//if entered, find item or rename item
						JOptionPane.showMessageDialog(new JFrame(), item + " already entered");
					} 
				} else {
					//if not entered, continue to adding
					setOption(type, item);
				}
				setOption(".");
				break;
			default :
				setOption(".");
		}
	}
	
	public static void setOption(String type, String item) {
		GUIFactory.getGUI(type, item);
	}

	public static void updateItem(String type, String item) {
		if(ObjectMngmt.getInstance().checkIfExists(type, item)) {
                        //if entered, find item or rename item
                	GUIFactory.getGUI("u", type, item);
			setOption(".");
                } else {
			JOptionPane.showMessageDialog(new JFrame(), item.toUpperCase() 
				+ "  not entered, enter item into system.");
			setOption(".");
		}

	}
	public static void getItem(String type, String item) {
		if(ObjectMngmt.getInstance().checkIfExists(type, item.toLowerCase())) {
			switch(type) {
				case "m" :
				case "i" :
				case "a" :
				       GUIFactory.getGUI("d", type, item);
				       break;
				case "r" :
				       ObjectMngmt.getInstance().getRestaurantStudy(item);
				       GUIFactory.getGUI("s", item);
				       break;
				default :
				       	setOption(".");
					break;
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame("Item Missing"), item + 
					" not entered, enter item into system.");
			setOption(".");
		}

	}
	//list of items by button for study or display
	public static void findItems(String type, String item) {
		GUIFactory.getGUI("f", type, item);
	}

	public static void studyItems(String type, String item) {
		GUIFactory.getGUI("s", type, item);
	}

}
