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


public class DisplayGUI implements GUIImplementation {

	DisplayGUI(String type, String name) {
	
        	JFrame f = new JFrame("Item Display");


	        JMenuBar mb = new JMenuBar();
        	JMenu file = new JMenu("File");
	        JMenuItem exit = new JMenuItem("Exit");
        	exit.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e2) {
                        	System.exit(1);
                	}
        	});
		String item = ObjectMngmt.getInstance().getObjectString(type, name);
		JLabel l = new JLabel("<html>" + item + "<html>");
		l.setBounds(10, 10, 200, 300);
		f.add(l);
		
		JButton b = new JButton("To Home");
		b.setBounds(250, 400, 100, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				Main.setOption(".");
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
