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


public class MessageGUI implements GUIimplementation {

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
		
		/*
		System.out.println("MSG -- " + message);
		String[] ms = message.split(":");
		String [] rs = ms[1].split("\n");
		String type = "";
		if (message.contains(",")) {
			type = message.substring(0,message.indexOf(","));
			ms[1] = message.substring(message.indexOf(",") + 1, message.length());
		}

		final String fType = type;
	/*	JTextArea t = new JTextArea(message);
		t.setBounds(50, 50, 400, 60);
		f.add(t);
		int yAxis = 130;
		int xAxis = 70;
		System.out.println(ms[0]);
		if ((rs.length == 0) && (ms[0].equals("m") || ms[0].equals("r")
				       	|| ms[0].equals("a") || ms[0].equals("i"))) {
			JTextField tf = new JTextField();
			tf.setBounds(xAxis, yAxis, 100, 30);
			f.add(tf);
			JButton sb = new JButton("Submit");
			sb.setBounds(100, 400, 100, 30);
			sb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e4) {
					final String item = tf.getText();
					if (ObjectMngmt.getInstance().checkIfExists(fType, item)) {
						String input = JOptionPane.showInputDialog(tf.getText()  +
								" is already entered into the program" +
								" please enter the name of the " +
								"restaurant to append to the item");
						Main.setOption(fType + ":"/* + tf.getText() + input 
							       	+ item);
					}
				}
			});
		        f.add(sb);	

		} else {
			for (int i = 0; i < rs.length; i++) {
				JButton bb = new JButton(rs[i]);
				bb.setBounds(xAxis, yAxis, 100, 30);
				bb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e3) {
		//				ObjectMngmt.getInstance().getRestaurantStudy(bb.getText());
						Main.setOption(bb.getText());
					/*	Study.getInstance().studyRestaurant(
								ObjectMngmt.getInstance().
								getRestaurant(bb.getText()));
					//	GUIFactory.getGUI("s", bb.getText());
						f.dispose();
					}
				});
				yAxis += 50;
				if ((i%3 == 0) && (i > 3)) {
					xAxis += 40;
				}
				f.add(bb);
			}
		}
*/
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
