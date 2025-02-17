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


public class StudyGUI implements GUIimplementation {

	StudyGUI(String name) {

		String menuItems = ObjectMngmt.getInstance().getMenuList(name);
		String menuCategories = Study.getInstance().getCategories();

        	JFrame f = new JFrame("Study Restaurant " + name + " Home");


	        JMenuBar mb = new JMenuBar();
        	JMenu file = new JMenu("File");
	        JMenuItem exit = new JMenuItem("Exit");
        	exit.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e2) {
                        	System.exit(1);
                	}
        	});

		JTextField t1 = new JTextField("You are studying for " +
				 name);
		t1.setBounds(10, 10, 300, 30);
		f.add(t1);

		JTextField t2 = new JTextField(name + " has thes menu " + 
				" categories");
		t2.setBounds(10, 40, 300, 30);
		f.add(t2);
		System.out.println("---" + menuCategories);

		JTextArea tA1 = new JTextArea(menuCategories);
		tA1.setBounds(10, 80, 300, 30);
		JScrollPane scrollA = new JScrollPane(tA1);
		scrollA.setBounds(10, 80, 300, 30);
		f.getContentPane().add(scrollA);	

		JTextField t3 = new JTextField(name + " has these  " +
				"menu items  : ");
		t3.setBounds(10, 120, 300, 30);
		f.add(t3);	
		JTextArea tA2 = new JTextArea();
		tA2.setLineWrap(true);
		tA2.setBounds(10, 160, 480, 180);
		tA2.setText(menuItems);
		JScrollPane scroll = new JScrollPane(tA2);
		scroll.setBounds(10, 160, 480, 180);
		f.getContentPane().add(scroll);
		


		JButton b = new JButton("To Home");
		b.setBounds(250, 400, 100, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIFactory.getGUI("f", name);
				f.dispose();
			}
		});
		f.add(b);
		file.add(exit);
		mb.add(file);
		f.setJMenuBar(mb);


		f.setSize(500, 530);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
