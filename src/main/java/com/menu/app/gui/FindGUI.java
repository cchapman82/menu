package com.menu.app.gui;

/*
 *
 *
 *
 *
 *
 * */

import javax.swing.*;

import com.menu.app.Main;
import com.menu.app.objects.ObjectMngmt;

import java.awt.event.*;

public class FindGUI implements GUIImplementation {


        FindGUI(String type, String name) {

		String list = "";
		JFrame f = new JFrame();
		//populate lists for user choice
		switch(type) {
			case "r" :
				list = ObjectMngmt.getInstance().getRestaurantList();
				break;
			case "a" :
				list = ObjectMngmt.getInstance().getAllergyList();
				break;
			case "i" :
				list = ObjectMngmt.getInstance().getIngredientList();
				break;
			default :
				break;
		}
		//because I need to use the string as final for the actionListener
		final String ftype = type;
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

        //	JFrame ff = new JFrame(name + " Already entered");
       		f.setJMenuBar(mb);
		JLabel l1 = new JLabel("<html>" + name + " not entered Please choose from options below or home to go back to main menu<html>");
		l1.setBounds(50, 10, 400, 60);
		f.add(l1);
                for (int i = 0; i < rs.length; i++) {
   	                JButton bb = new JButton(rs[i]);
                        bb.setBounds(xAxis, yAxis, 100, 30);
                        bb.addActionListener(new ActionListener() {
                             	public void actionPerformed(ActionEvent e3) {
                                   	Main.getItem(ftype, bb.getText());
					f.dispose();
                                }
                    	});
                        yAxis += 50;
                        if ((i%3 == 0) && (i > 3)) {
                           	xAxis += 40;
                        }
                        f.add(bb);
		}
		JButton b = new JButton("To Home");
                b.setBounds(250, 400, 100, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Main.setOption(".");
                                f.dispose();
                        }
                });
		f.add(b);


		f.setSize(600, 530);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
