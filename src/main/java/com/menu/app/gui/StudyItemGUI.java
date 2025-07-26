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

import com.menu.app.objects.ObjectMngmt;

import java.awt.event.*;


public class StudyItemGUI implements GUIImplementation {


        StudyItemGUI(String name) {
                String[] item = ObjectMngmt.getInstance().getObjectString("m", name).split(",");
		String[] ingredients = item[5].split("~");
		String[] allergies = item[8].split("~");

                JFrame f = new JFrame("Study " + item[1] + " " + name + " Menu Item Home");
		JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });

                JLabel t1 = new JLabel("<html>You are studying " +
                                 name + ".<html>");
                t1.setBounds(10, 10, 300, 30);
                f.add(t1);

                JLabel t2 = new JLabel(name + " has these fields.");
                t2.setBounds(10, 40, 480, 30);
                f.add(t2);

		JLabel l1 = new JLabel("Menu Category : ");
		l1.setBounds(10, 70, 200, 30);
		f.add(l1);
		JLabel f1 = new JLabel(item[2]);
		f1.setBounds(220, 70, 200, 30);
		f.add(f1);

		JLabel l2 = new JLabel("Description : ");
		l2.setBounds(10, 100, 200, 30);
		f.add(l2);
		JLabel f2 = new JLabel("<html>" + item[3] + "<html>");
		f2.setBounds(220, 100, 250, 60);
		f.add(f2);


		JLabel l3 = new JLabel("Price : ");
		l3.setBounds(10, 160, 200, 30);
		f.add(l3);
		JLabel f3 = new JLabel(item[4]);
		f3.setBounds(220, 160, 100, 30);
		f.add(f3);


		JLabel l4 = new JLabel("Ingredients : ");
		l4.setBounds(10, 190, 200, 30);
		f.add(l4);
		int yAxis = 220;
		for(int i = 0; i < ingredients.length; i++) {
			if(i%2 != 0) {	
				JLabel ff = new JLabel(ingredients[i]);
				ff.setBounds(220, yAxis, 200, 30);
				f.add(ff);
				yAxis += 30;
			} else {
				JLabel ff = new JLabel(ingredients[i]);
				ff.setBounds(40, yAxis, 200, 30);
				f.add(ff);
				if(i == ingredients.length -1) {
					yAxis += 30;
				}
			}
		}

		JLabel l5 = new JLabel("Preparation : ");
		l5.setBounds(10, yAxis, 200, 30);
		f.add(l5);
		JLabel f5 = new JLabel(item[6]);
		f5.setBounds(220, yAxis, 100, 30);
		f.add(f5);

		yAxis += 30;
		JLabel l6 = new JLabel("Size : ");
		l6.setBounds(10, yAxis, 200, 30);
		f.add(l6);
		JLabel f6 = new JLabel(item[7]);
		f6.setBounds(220, yAxis, 100, 30);
		f.add(f6);

		yAxis += 30;
		JLabel l7 = new JLabel("Allergies : ");
		l7.setBounds(10, yAxis, 200, 30);
		f.add(l7);
		yAxis += 30;
		for(int i = 0; i < allergies.length; i++) {
			if(i%2 != 0) {	
				JLabel ff = new JLabel(allergies[i]);
				ff.setBounds(220, yAxis, 200, 30);
				f.add(ff);
				yAxis += 30;
			} else {
				JLabel ff = new JLabel(allergies[i]);
				ff.setBounds(40, yAxis, 200, 30);
				f.add(ff);
			}
		}
		Timer timer = new Timer(15000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                f.dispose();
                                String answer = JOptionPane.showInputDialog(
                                                "How Many ingredients are in " + name + "?");
				String answer2 = JOptionPane.showInputDialog(
						"How many allergies are associated with " + name + "?");
                                GUIFactory.getAnswerGUI("i", name + "." + answer + "~" + answer2);
                        }
                });
                timer.setRepeats(false);
                timer.start();

		JButton b = new JButton("Back");
                b.setBounds(175, 475, 150, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
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

