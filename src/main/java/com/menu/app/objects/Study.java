
package com.menu.app.objects;
/*
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Study {
	
	private static Study study = null;
	private static ObjectMngmt objMngmt = ObjectMngmt.getInstance();
	private Scanner inStudy = new Scanner(System.in);
	private Restaurant res = null;
	private Set<String> menuCategories = new HashSet<String>();
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	private HashMap<String, ArrayList<String>> categoryItems =
			new HashMap<String, ArrayList<String>>();
	private int menuNum = menuItems.size();
	private int catNum = menuCategories.size();

	private Study() {
		inStudy.useDelimiter("\n");
	}

	public static Study getInstance() {
		if (study == null) {
			return study = new Study();
		} else {
			return study;
		}
	}

	public int getMenuNum() { return menuNum;}
	public int getCatNum() { return catNum;}

	public String getCategories() {
		String result = "";
		for (String s : menuCategories) {
			result += s + "<br>";
		}
		return result.substring(0, result.length());
	}

	//return number of Items in categories
	public Map<String, Integer> getCatItemNums() {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for(Map.Entry<String, ArrayList<String>> e : categoryItems.entrySet()) {
			result.put(e.getKey(), e.getValue().size());
		}
		return result;
	}
	public Boolean checkRestaurant() {
		return res == null;
	}

	// sets up lists to study
	public void studyRestaurant(Restaurant r) {
		res = r;
		String[] menuItemStrings = new String[0];
		String menu = res.getMenu();
		if (menu != "") {
			menuItemStrings = menu.split(",");
		}
		for (int i = 0; i < menuItemStrings.length; i++) {
			MenuItem m = objMngmt.getMenuItem(menuItemStrings[i]);
			if (!m.getName().equals(null) && m.getCurrent() == 'y') {
				menuItems.add(m);
				menuCategories.add(m.getMenuCategory());
				categoryItems.putIfAbsent(m.getMenuCategory(), new ArrayList<String>());
				categoryItems.get(m.getMenuCategory()).add(m.getName());
			}
		}
	}

	public ArrayList<String> getCatItems(String category) {
		return categoryItems.get(category);
	}
}
