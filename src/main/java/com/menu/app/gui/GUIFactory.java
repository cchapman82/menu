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
			default :
				return new IndexGUI();
		}
		
	}

	public static GUIImplementation getGUI(String s, String type, String name) {
		switch(s) {
			case "f" :
				return new FindGUI(type, name);
			case "u" :
				switch(type) {
					case "m" :
						return new UpdateMIGUI(type, name);
					case "a" :
						return new UpdateAGUI(type, name);
					case "i" :
						return new UpdateIGUI(type, name);
					case "r" :
						return new UpdateRGUI(type, name);
				}
			case "d" :
				return new DisplayGUI(type, name);
			default :
				return new IndexGUI();
		}
	}
}
