package com.menu.app;

/*
 *
 *
 *
 *
 *
 */

import javax.swing.*;
import java.awt.event.*;

public class IndexGUI implements GUIImplementation {

	private MainController mc = MainController.getInstance();
	private Boolean cont = true;
	public String GUIType = "ix";
	public static String name = "";

	IndexGUI() {

	JFrame f = new JFrame("Menu Program Home");

	final JLabel l3 = new JLabel();
	l3.setBounds(50, 370, 300, 30);
	f.add(l3);

	JMenuBar mb = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem exit = new JMenuItem("Exit");
	exit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e2) {
			System.exit(1);
		}
	});
	file.add(exit);
	mb.add(file);
	f.setJMenuBar(mb);

	JLabel l1 = new JLabel("Welcome to the Menu Program!!");
	l1.setBounds(100, 0, 300, 30);
	f.add(l1);


	JLabel l2 = new JLabel("Please select an option");
	l2.setBounds(40, 30, 200, 30);
	f.add(l2);

	JButton b1 = new JButton("Add an Item");
	b1.setBounds(50, 70, 200, 30);
	b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				String type = getType(f);
				f.dispose();
				Main.setOption(type);
			} catch (Exception ec) {
				l3.setText("Action not preformed, try again");
			}

		}
	});
	f.add(b1);

	JButton b2 = new JButton("Update Item");
	b2.setBounds(50, 130, 200, 30);
	b2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			try {
				String type = getType(f);
			       String item = JOptionPane.showInputDialog("Please enter name of item "
					       + "to update");
				f.dispose();
				Main.updateItem(type, item);
			} catch (Exception ec) {
				l3.setText("Action not preformed, try again");
			}

		}
        });

	f.add(b2);

	JButton b3 = new JButton("Study Menu");
	b3.setBounds(50, 190, 200, 30);
	b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
			try {
                        	name =
                                   JOptionPane.showInputDialog(f,
                                      "Please enter name of the restaurant to" +
				      " study");
				if (name.equals("") || name.equals( null)) {
					l3.setText("No restaurant entered " +
							"Please try again");
				} else if (ObjectMngmt.checkIfExists("r", name.
							toLowerCase())) {
					Main.getItem("r", name.toLowerCase());
					f.dispose();
				} else if (ObjectMngmt.checkIfExists("r", name.
							toLowerCase()).
							equals(false)) {
					Main.findItems("r", name);

					f.dispose();	
				}
			} catch (Exception ec) {
				ec.printStackTrace();
				l3.setText("Action not preformed, try again");
			}
                }
        });
	f.add(b3);

	JButton b4 = new JButton("Display Item");
	b4.setBounds(50, 250, 200, 30);
	b4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String type = getType(f);
			name = JOptionPane.showInputDialog(f,
                                      "Please enter item to display");

			Main.getItem(type, name);
			f.dispose();
		}
	});
	f.add(b4);
	JButton b5 = new JButton("Close");
	b5.setBounds(50, 310, 200, 30);
	b5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			System.exit(1);
                }
        });
	f.add(b5);
	f.setSize(500, 500);
	f.setLayout(null);
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private String getType(JFrame f) {
		String[] items = {"Menu Item", "Restaurant", "Ingredient", "Allergy"};
		String type =  (String) JOptionPane.showInputDialog(f, 
				      "Please enter type of item to enter--", "Items to Choose",
				      JOptionPane.PLAIN_MESSAGE,
				      null, items, items[0]);
		switch(type) {
			case "Menu Item" :
				type = "m";
				break;
			case "Restaurant" :
				type = "r";
				break;
			case "Ingredient" :
				type = "i";
				break;
			case "Allergy" :
				type = "a";
				break;
		}
		return type;
	}
}
