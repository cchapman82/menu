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


public class MessageGUI implements GUIImplementation {

	MessageGUI(String message) {
	
        	JFrame f = new JFrame("Restaurant Message");


	        JMenuBar mb = new JMenuBar();
        	JMenu file = new JMenu("File");
	        JMenuItem exit = new JMenuItem("Exit");
        	exit.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e2) {
                        	System.exit(1);
                	}
        	});
		JTextArea ta = new JTextArea(message);
		ta.setBounds(10, 10, 200, 30);
		f.add(ta);
		JTextField t = new JTextField();
		t.setBounds(50, 50, 100, 30);
		f.add(t);
		
		JButton b = new JButton("To Home");
		b.setBounds(250, 400, 100, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIFactory.getGUI("f", "");
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
