package com.menu.app;

/*
 *
 *
 *
 *
 *
 *
 * */

import javax.swing.*;
import java.awt.event.*;

public class MenuItemGUI implements GUIimplementation {

	ObjectMngmt objMngmt = ObjectMngmt.getInstance();

	public MenuItemGUI(String s) {

		JFrame f = new JFrame("Enering " + s );

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

		JLabel l1 = new JLabel("Please enter the information for the new"
			+ " Menu Item");
		l1.setBounds(50, 10, 400, 30);
		f.add(l1);

		JLabel l2 = new JLabel("Name :");
		l2.setBounds(50, 40, 100, 30);
		f.add(l2);
		JTextField t1 = new JTextField(s);
		t1.setEditable(false);
		t1.setBounds(200, 40, 250, 30);
		f.add(t1);
		JLabel l3 = new JLabel("Restaurant : ");
		l3.setBounds(50, 70, 100, 30);
		f.add(l3);
		JTextField t2 = new JTextField();
		t2.setBounds(200, 70, 250, 30);
		f.add(t2);
		JLabel l4 = new JLabel("Menu Category :");
		l4.setBounds(50, 100, 400, 30);
		f.add(l4);
		JTextField t3 = new JTextField();
		t3.setBounds(200, 100, 250, 30);
		f.add(t3);
		JLabel l5 = new JLabel("Description : ");
		l5.setBounds(50, 130, 400, 30);
		f.add(l5);
		JTextField t4 = new JTextField();
		t4.setBounds(200, 130, 250, 60);
		f.add(t4);
		JLabel l6 = new JLabel("Price : ");
		l6.setBounds(50, 190, 400, 30);
		f.add(l6);
		JTextField t5 = new JTextField();
		t5.setBounds(200, 190, 250, 30);
		f.add(t5);
		JLabel l7 = new JLabel("Ingredients : ");
		l7.setBounds(50, 220, 400, 30);
		f.add(l7);
		JTextField t6 = new JTextField();
		t6.setBounds(200, 220, 250, 90);
		f.add(t6);
		JLabel l8 = new JLabel("Preparation Style : ");
		l8.setBounds(50, 310, 400, 30);
		f.add(l8);
		JTextField t7 = new JTextField();
		t7.setBounds(200, 310, 250, 30);
		f.add(t7);
		JLabel l9 = new JLabel("Size : ");
		l9.setBounds(50, 340, 400, 30);
		f.add(l9);
		JTextField t8 = new JTextField();
		t8.setBounds(200, 340, 250, 30);
		f.add(t8);
		JLabel l10 = new JLabel("Allergies : ");
		l10.setBounds(50, 370, 400, 30);
		f.add(l10);
		JTextField t9 = new JTextField();
		t9.setBounds(200, 370, 250, 30);
		f.add(t9);
		JButton bb = new JButton("To Home");
                bb.setBounds(100, 430, 100, 30);
                bb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                GUIFactory.getGUI(".", "");
                                f.dispose();
                        }
                });

		JButton b = new JButton("Submit");
		b.setBounds(220, 430, 100, 30);
		b.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                        	try {
					MenuItem mi = new MenuItem(
							t1.getText().toLowerCase().replace(" ", "_"), 
							t2.getText().toLowerCase().replace(" ", "_"),
							t3.getText().toLowerCase().replace(" ", "_"),
							t4.getText().toLowerCase().replace(" ", "_"),
							Double.parseDouble(t5.getText()), 
							t6.getText().toLowerCase().replace(" ", "_"), 
							t7.getText().toLowerCase().replace(" ", "_"), 
							t8.getText().toLowerCase().replace(" ", "_"), 
							t9.getText().toLowerCase().replace(" ", "_"));
                                	objMngmt.pushToArray("m", mi);
                        	        objMngmt.pushToDatabase("m", mi.toSQLString());
                	                objMngmt.updateRestaurant(mi);
		
                              	 	f.dispose();

         	               } catch (Exception ec) {
 	                                  JOptionPane.showMessageDialog(f, "Menu Item not entered, please try again");
			       }
			}
		});

		f.add(b);
		f.add(bb);
		f.setSize(500, 530);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
