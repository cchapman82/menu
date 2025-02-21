package com.menu.app;

/*
 *
 *
 *
 *
 *
 */

public class GUIFactory {

	public static GUIimplementation getGUI(String s, String name) {

	//	GUIimplementation result = null;

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
			/*
			case "fr" :
				return new FindGUI("r");
			case "fm" :
				return new FindGUI("m:" + name);
			case "fa" :
				return new FindGUI("a");
			case "fi" :
				return new FindGUI("i");
			*/
			case "ms" :
				return new MessageGUI(name);
			default :
				return new IndexGUI();
		}
		
	}

	public static GUIimplementation getGUI(String s, String type, String name) {
		System.out.println("In overloaded factory");
		switch(s) {
			case "f" :
				System.out.println(s + "****************" + type);
				return new FindGUI(type, name);
			default :
				return new IndexGUI();
		}
	}
}
