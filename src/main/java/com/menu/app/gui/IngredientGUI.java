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

public class IngredientGUI implements GUIImplementation {

	private ObjectMngmt objMngmt = ObjectMngmt.getInstance();

	public IngredientGUI(String s) {

		JFrame f = new JFrame("Entering the Ingredient " + s);
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
			+ "Ingredient");
	       l1.setBounds(50, 10, 400, 30);
                f.add(l1);

                JLabel l2 = new JLabel("Name :");
                l2.setBounds(50, 40, 100, 30);
                f.add(l2);
                JTextField t1 = new JTextField(s);
		t1.setEditable(false);
                t1.setBounds(200, 40, 250, 30);
                f.add(t1);
                JLabel l3 = new JLabel("Description : ");
                l3.setBounds(50, 70, 100, 30);
                f.add(l3);
                JTextField t2 = new JTextField();
                t2.setBounds(200, 70, 250, 60);
                f.add(t2);
                JLabel l4 = new JLabel("Cost :");
                l4.setBounds(50, 130, 400, 30);
                f.add(l4);
                JTextField t3 = new JTextField();
                t3.setBounds(200, 130, 250, 30);
                f.add(t3);
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
                                        Ingredient ii  = new Ingredient(
							t1.getText().toLowerCase().replace(" ", "_"), 
							t2.getText().toLowerCase().replace(" ", "_"),
     							Double.parseDouble(t3.getText()));
                                        objMngmt.pushToArray("i", ii);
                                        objMngmt.pushToDatabase("i", ii.toSQLString());

                                        f.dispose();

                               } catch (Exception ec) {
                                          JOptionPane.showMessageDialog(f,"Ingredient not entered, please try again");
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
