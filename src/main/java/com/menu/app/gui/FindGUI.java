package com.menu.app;

/*
 *
 *
 *
 *
 *
 * */

import javax.swing.*;
import java.awt.event.*;

public class FindGUI implements GUIImplementation {


        FindGUI(String s, String name) {

		String list = "";
		JFrame f = new JFrame();
		System.out.println("in GUI find " + s + "**" + name);
		//populate lists for user choice
		switch(s) {
			case "r" :
				list = ObjectMngmt.getInstance().getRestaurantList();
				s = "Restaurant";
				break;
			case "m" :
				list = ObjectMngmt.getInstance().getMenuList(s);
				s = "Menu Item";
				break;
			case "a" :
				list = ObjectMngmt.getInstance().getAllergyList();
				s = "Allergy";
				break;
			case "i" :
				list = ObjectMngmt.getInstance().getIngredientList();
				s = "Ingredient";
				break;
			default :
				break;
		}
		//because I need to use the string as final for the actionListener
		final String ss = s;
		String[] rs = new String[0];
		if (list != "") {
			rs = list.split("\n");
		}
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

		int yAxis = 80;
                int xAxis = 70;

        	JFrame ff = new JFrame(s + " not found");
       		ff.setJMenuBar(mb);
		JTextArea l1 = new JTextArea("Please enter from option below or home to go back to main menu");
		l1.setBounds(50, 10, 400, 60);
		l1.setLineWrap(true);
		ff.add(l1);
                for (int i = 0; i < rs.length; i++) {
   	                JButton bb = new JButton(rs[i]);
                        bb.setBounds(xAxis, yAxis, 100, 30);
                        bb.addActionListener(new ActionListener() {
                             	public void actionPerformed(ActionEvent e3) {
                                   	Main.setOption(bb.getText());
                                }
                    	});
                        yAxis += 50;
                        if ((i%3 == 0) && (i > 3)) {
                           	xAxis += 40;
                        }
                        ff.add(bb);
		}
		JButton b = new JButton("To Home");
                b.setBounds(250, 400, 100, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                GUIFactory.getGUI("ix", "");
                                ff.dispose();
                        }
                });
		ff.add(b);


		ff.setSize(600, 530);
		ff.setLayout(null);
		ff.setVisible(true);
		ff.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
