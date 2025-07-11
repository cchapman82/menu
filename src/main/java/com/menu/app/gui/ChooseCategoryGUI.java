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



public class ChooseCategoryGUI implements GUIImplementation {

                private Map<String, Integer> catNums = Study.getInstance().getCatItemNums();

        ChooseCategoryGUI(String name) {



                JFrame f = new JFrame("Study Restaurant " + name + " Categories Home");
 
		JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });

                JLabel t1 = new JLabel("<html>You are studying for " +
                                 name + ".<html>");
                t1.setBounds(10, 10, 300, 30);
                f.add(t1);

                JLabel t2 = new JLabel(name + " has these menu " +
                                " categories, please choose one.");
                t2.setBounds(10, 40, 300, 30);
                f.add(t2);
		int xAxis = 20;
		int yAxis = 90;

		for(Map.Entry<String,Integer> e : catNums.entrySet()) {
			JButton cb = new JButton(e.getKey() + "/" + e.getValue());
			cb.setBounds(xAxis, yAxis, 100, 30);
			cb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					System.out.println(e.getKey());
					Main.studyItems("cc", e.getKey());
				}
			});
			f.add(cb);
			if((xAxis + 120) <= 500) {
				xAxis += 120;
			} else {
				xAxis = 20;
				yAxis += 50;
			}

		}

                JButton b = new JButton("To Home");
                b.setBounds(200, 475, 100, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                                Main.getItem("r", name);
                                f.dispose();
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
}
