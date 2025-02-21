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

public class MICollisionGUI implements GUIImplementation {

	MICollisionGUI(String name) {
		JFrame ff = new JFrame(name + " exists already");
        
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
		ff.setJMenuBar(mb);
	        JTextArea l1 = new JTextArea();
                //        if (ss.equals("Menu Item")) {
                                l1 = new JTextArea(name  + " is already entered into the program" +
                                          " please enter the name of the restaurant to append" +
                                         " to the item");
                  //      } else {
                    //            l1 = new JTextArea(name + " already entere into the program.");
                     //   }
          	 l1.setBounds(50, 10, 400, 60);
                 l1.setLineWrap(true);
                 ff.add(l1);
                 JTextField tf = new JTextField();
                 tf.setBounds(80, 80, 100, 30);
		 ff.add(tf);
                 final String itemOne = name;
                 JButton sb = new JButton("Submit");
                 sb.setBounds(100, 400, 100, 30);
                 sb.addActionListener(new ActionListener() {
                 	public void actionPerformed(ActionEvent e4) {
                                     //   final String itemOne = item;
                                       // if (/*ObjectMngmt.getInstance().checkIfExists(ss, item)*/) {
                                    /*            String input = JOptionPane.showInputDialog(item  +
" is already entered into the program" +
                                                                " please enter the name of the " +
                                                                "restaurant to append to the item");*/
                             	Main.setOption("m" + ":" + itemOne + "_" + tf.getText());
                                ff.dispose();
                                       // }
                   	}
       		});
                ff.add(sb);
                JButton b = new JButton("To Home");
                b.setBounds(250, 400, 100, 30);
                b.addActionListener(new ActionListener() {
                     	public void actionPerformed(ActionEvent e) {
                          	GUIFactory.getGUI("ix", "Home");
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
