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
import java.util.Map;
import java.util.HashMap;



public class StudyMenuGUI implements GUIImplementation {

        StudyMenuGUI(String name) {

		Map<String, Integer> catNums = Study.getInstance().getCatItemNums();


                JFrame f = new JFrame("Study Restaurant " + name + " Home");


                JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });

                JLabel t1 = new JLabel("<html>You are studying for " +
                                 name + ".  You will be asked about this in 15 sec.<html>");
                t1.setBounds(10, 10, 300, 30);
                f.add(t1);

                JLabel t2 = new JLabel(name + " has these menu " +
                                " categories");
                t2.setBounds(10, 40, 300, 30);
		f.add(t2);

		JLabel t3 = new JLabel("<html>" + catNums.keySet()  + "<html>");
		t3.setBounds(20, 70, 300, 200);
	       f.add(t3);

		JLabel t4 = new JLabel("<html>" + getCatNums(catNums) + "<html");
		t4.setBounds(20, 270, 300, 200);
		f.add(t4);
		Timer timer = new Timer(15000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				GUIFactory.getAnswerGUI("m", name);
			}	
		});
		timer.setRepeats(false);
		timer.start();	


                JButton b = new JButton("To Home");
                b.setBounds(200, 475, 100, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                                Main.getItem("r", name);
                                f.dispose();
				timer.stop();
                        }
                });
                f.add(b);
                file.add(exit);
                mb.add(file);
                f.setJMenuBar(mb);


                f.setSize(500, 700);
                f.setLayout(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }

	private String getCatNums(Map<String,Integer> catNums) {
		String result = "The categories have this many items each:<br>";
		for(Map.Entry<String, Integer> e : catNums.entrySet()) {
			result+= e.getKey() + "		" + e.getValue() + "<br>";
		}
		return result.substring(0, result.length());
	}

}

