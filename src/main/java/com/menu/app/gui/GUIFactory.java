package com.menu.app;

/*
 *
 *
 *
 *
 *
 */

public class GUIFactory {

	public static GUIImplementation getGUI(String s, String name) {


		switch(s) {
			
			case "ix" :
				return new IndexGUI();
			case "m" :
				return new MenuItemGUI(name);
			case "a" :
				return new AllergyGUI(name);
			case "r" :
				return new RestaurantGUI(name);
			case "i" :
				return new IngredientGUI(name);
			case "s" :
				return new StudyGUI(name);
			case "mc" :
				return new MICollisionGUI(name);
			case "ms" :
				return new MessageGUI(name);
			default :
				return new IndexGUI();
		}
		
	}

	public static GUIImplementation getGUI(String s, String type, String name) {
		switch(s) {
			case "f" :
				return new FindGUI(type, name);
			default :
				return new IndexGUI();
		}
	}
}
