
package com.menu.app;
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
	private Boolean endCont = false;
	private Restaurant res = new Restaurant();
	private Set<String> menuCategories = new HashSet<String>();
//	private ArrayList<String> menuCategories = new ArrayList<String>();
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
	public Integer[] getCatItemNums() {
		Integer[] result = new Integer[menuCategories.size()];
		int i = 0;
		for(Map.Entry<String, ArrayList<String>> e : categoryItems.entrySet()) {
			result[i] = e.getValue().size();
			i++;
		}
		return result;
	}

	// sets up lists to study
	public void studyRestaurant(Restaurant r) {
		res = r;
		String[] menuItemStrings = new String[0];
		String menu = res.getMenu();
		if (menu != "") {
			menuItemStrings = menu.split(",");
		}
		//menuNum = menuItemStrings.length;
		for (int i = 0; i < menuItemStrings.length; i++) {
			MenuItem m = objMngmt.getMenuItem(menuItemStrings[i]);
			if (!m.getName().equals(null)) {
				menuItems.add(m);
		
		//	if (!menuCategories.contains(m.getMenuCategory())) {
				menuCategories.add(m.getMenuCategory());
				categoryItems.putIfAbsent(m.getMenuCategory(), new ArrayList<String>());
				categoryItems.get(m.getMenuCategory()).add(m.getName());
		/*	} else {
				categoryItems.get(m.getMenuCategory()).add(m.getName());	*/
			}
		}

		
/*		Boolean cont = true;
		while (cont) {
			endCont = false;
			System.out.println("Enter the number for what you would like to study?" +
				"\n 1  Whole Menu\n 2  Category\n 3  Menu Item\n stop End");
			switch(inStudy.next()) {
				case "1":
					studyMenu(r.getName());
					break;
				case "2":
					System.out.println("Please enter the category to study");
					showCategories();
					studyCategory(inStudy.next());
					break;
				case "3":
					System.out.println("Please enter the number for " +
							"the item you would like to study from " +
							"the list above");
				       studyItem(menuItems.get(Integer.parseInt(inStudy.next()) - 1));
					break;
				case "stop":
					cont = false;
					break;
			}
		}*/
	}

/*	private void studyMenu(String r) {
		System.out.println("This menu contains these categories");
		showCategories();
		System.out.println("Menu Categories  for  " + r);
		// study num of iems in category
		while (!numQuestion(menuCategories.size(), "menu")) {
		}
		if (endCont == false) {
			while (!nameQuestion(menuCategories, "menu")) {}
		}
		
	}*/

	private void studyCategory(String category) {
		ArrayList<String> categoryItemStrings = categoryItems.get(category);
		Boolean cont = true;
		System.out.println("Category  :  " + category);
		for (String s : categoryItemStrings) {
			System.out.println(s);
		}
		// study num of iems in category
		while (!numQuestion(categoryItemStrings.size(), "category")) {
		}
		if (endCont == false) {
			while (!nameQuestion(categoryItemStrings, "category")) {}
		}
	}


	private void studyItem(MenuItem m) {
		System.out.println(m.getName());
		System.out.println(m.getIngredients());
		String[] ingredients = m.getIngredients().split("\\. ");
		ArrayList<String> arrLIngredients = new ArrayList<String>();
		for (int i = 0; i < ingredients.length; i++) {
			System.out.println(ingredients[i]);
			arrLIngredients.add(ingredients[i]);
		}
		while(!numQuestion(ingredients.length, m.getName())) {}
		if (endCont == false) {
			while (!nameQuestion(arrLIngredients, m.getName())) {}
		}
		
		System.out.println(m.getAllergies());
		String[] allergies = m.getAllergies().split("\\. ");
		ArrayList<String> arrLAllergies = new ArrayList<String>();
		for (int i = 0; i < allergies.length; i++) {
			System.out.println(allergies[i]);
			arrLAllergies.add(allergies[i]);
		}
		while(!numQuestion(allergies.length, "allergies for " + m.getName())) {}
		if (endCont == false) {
			while (!nameQuestion(arrLAllergies, m.getName())) {}
		}
	}


	//   Questions
	//   	num of items in individual
	private Boolean numQuestion(int num, String item) {
		Boolean result = false;
		System.out.println("How many items in this " + item + "?" +
					"\n Or you can enter stop to quit");
		int intAnswer = getNumInput();
		if (intAnswer == num) {
			result = true;
			System.out.println("Correct!!!");
		} else if (intAnswer == 9999) {
			result = true;
			endCont = true;
			System.out.println("Thank you for studying!");	
		} else {
			System.out.println("Incorrect");
		}
		return result;
	}
	//	name of items in individual
	private Boolean nameQuestion(ArrayList<String> list, String item) {
		Boolean correct = false;
		System.out.println("Please enter the items in " + item + ",\n" + 
				"Enter items separated by commas" + 
				"\nAt any time you can enter stop to quit");
		String strAnswer = inStudy.next();
		int numCorrect = 0;
		if (strAnswer.contains("stop")) {
			System.out.println("Thank you for studying");
			correct = true;
		} else {
			String[] arrStrAnswer = strAnswer.split(",");
			String[] answerCategoryStrings = new String[arrStrAnswer.length];
			for (int i = 0; i < arrStrAnswer.length; i++) {
				if (arrStrAnswer[i].startsWith(" ")) {
					arrStrAnswer[i] = arrStrAnswer[i].substring(1);
				}
				for (int k = 0; k < list.size(); k++) {
					if (arrStrAnswer[i].contains(list.get(k))) {
						answerCategoryStrings[numCorrect] = arrStrAnswer[i]; 
						numCorrect++;
					}
				}
			}
			if (numCorrect == list.size()) {
				System.out.println("Correct!!!");
				correct = true;
			} else {
				System.out.println("Incorrect, please try again!" + 
					"\n YOU GOT " + arrStrAnswer.length + "!!!!!!!");
				for (int i = 0; i < arrStrAnswer.length; i++) {
					if (arrStrAnswer[i] != null) {
						System.out.println(arrStrAnswer[i]);
					}
				}
			}
		}
		return correct;	
	}

	//helper functions
	private int getNumInput() {
		String s = inStudy.next();
		int result = 0;
		try {
			if (s.equals("stop")) {
				result = 9999;
			} else {
				result = Integer.parseInt(s);
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number");
			getNumInput();
		}
		return result;
	}
}
