package com.menu.app.gui;

/*
 *
 *
 *
 *
 *
 *
 * */

import javax.swing.*;

import com.menu.app.Main;
import com.menu.app.objects.ObjectMngmt;
import com.menu.app.objects.Study;

import java.awt.event.*;


public class StudyGUI implements GUIImplementation {

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

		JLabel t1 = new JLabel("You are studying for " +
				 name);
		t1.setBounds(10, 10, 300, 30);
		f.add(t1);

		JLabel t2 = new JLabel(name + " has thes menu " + 
				" categories");
		t2.setBounds(10, 40, 300, 30);
		f.add(t2);

		JLabel tA1 = new JLabel("<html>" + menuCategories + "<html>");
		tA1.setBounds(10, 80, 300, 30);
		JScrollPane scrollA = new JScrollPane(tA1);
		scrollA.setBounds(10, 80, 300, 30);
		f.getContentPane().add(scrollA);	

		JLabel t3 = new JLabel(name + " has these  " +
				"menu items  : ");
		t3.setBounds(10, 120, 300, 30);
		f.add(t3);	
		JLabel tA2 = new JLabel();
		tA2.setBounds(10, 160, 480, 180);
		tA2.setText("<html>" + menuItems.replace("\n", "<br>") + "<html>");
		tA2.setVerticalAlignment(SwingConstants.TOP);
		JScrollPane scroll = new JScrollPane(tA2);
		scroll.setBounds(10, 160, 480, 180);
		f.getContentPane().add(scroll);
	
		JLabel sl = new JLabel("What to Study ...");
		sl.setBounds(25, 350, 300, 30);
		f.add(sl);	

		JButton sb1 = new JButton("Menu");
		sb1.setBounds(31, 400, 125, 30);
		sb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.studyItems("m", name);
				f.dispose();
			}
		});
		JButton sb2 = new JButton("Categories");
		sb2.setBounds(187, 400, 125, 30);
		sb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.studyItems("cc", name);
				f.dispose();
			}
		});
		JButton sb3 = new JButton("Item");
		sb3.setBounds(343, 400, 125, 30);
		sb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.studyItems("ci", name);
				f.dispose();
			}
		});

		JButton b = new JButton("To Home");
		b.setBounds(175, 475, 150, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setOption(".");
				f.dispose();
			}
		});
		f.add(b);
		f.add(sb1);
		f.add(sb2);
		f.add(sb3);
		file.add(exit);
		mb.add(file);
		f.setJMenuBar(mb);


		f.setSize(500, 700);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
