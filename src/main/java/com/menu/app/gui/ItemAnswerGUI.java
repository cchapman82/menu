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

public class ItemAnswerGUI implements GUIImplementation {


        ItemAnswerGUI(String name) {
		String iName = name.substring(0, name.indexOf("."));
		String[] answers = name.substring(name.indexOf(".") + 1, name.length()).split("~");
		JTextField[] ingredientAnswers = new JTextField[Integer.parseInt(answers[0])];
		JTextField[] allergyAnswers = new JTextField[Integer.parseInt(answers[1])];
		String[] item = ObjectMngmt.getInstance().getObjectString("m", iName).split(",");
                String[] ingredients = item[5].split("~");
                String[] allergies = item[8].split("~");
                JFrame f = new JFrame("Study " + item[1] + " " + iName + " Menu Item Home");
                JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });

                JLabel l1 = new JLabel("<html>You are studying " +
                                 iName + ".<html>");
                l1.setBounds(10, 10, 300, 30);
                f.add(l1);

		JLabel l2 = new JLabel("<html>You stated " + iName + " has " + answers[0] + 
				" ingredients and " + answers[1] + " allergies associated <html>");
		l2.setBounds(10, 50, 400, 30);
		f.add(l2);

		JLabel l3 = new JLabel("How is " + iName + " prepared?");
		l3.setBounds(10, 90, 250, 30);
		f.add(l3);
		JTextField t3 = new JTextField();
		t3.setBounds(260, 90, 150, 30);
		f.add(t3);
		JLabel l4 = new JLabel("What size is " + iName + "?");
		l4.setBounds(10, 130, 250, 30);
		f.add(l4);
		JTextField t4 = new JTextField();
		t4.setBounds(260, 130, 150, 30);
		f.add(t4);
		JLabel l5 = new JLabel("What are the ingredients in " + iName + "?");
		l5.setBounds(10, 170, 300, 30);
		f.add(l5);
		int yAxis = 200;
		for(int i = 0; i < Integer.parseInt(answers[0]); i++) {
			if(i%2 != 0) {
				JTextField t = new JTextField();
				t.setBounds(190, yAxis, 150, 30);
				ingredientAnswers[i] = t;
				f.add(t);
				yAxis += 30;
			} else {
				JTextField t = new JTextField();
				t.setBounds(40, yAxis, 150, 30);
				ingredientAnswers[i] = t;
				f.add(t);
				if(i == Integer.parseInt(answers[0]) - 1) {
					yAxis += 40;
				}
			}
		}
		JLabel l6 = new JLabel("What are the allergies associated with " + iName + "?");
		l6.setBounds(10, yAxis, 400, 30);
		f.add(l6);
		yAxis += 40;
		for(int i = 0; i < Integer.parseInt(answers[1]); i++) {
			if(i%2 != 0) {
				JTextField t = new JTextField();
				t.setBounds(190, yAxis, 150, 30);
				allergyAnswers[i] = t;
				f.add(t);
				yAxis += 30;
			} else {
				JTextField t = new JTextField();
				t.setBounds(40, yAxis, 150, 30);
				allergyAnswers[i] = t;
				f.add(t);
				if(i == Integer.parseInt(answers[0]) - 1) {
					yAxis += 40;
				}
			}
		}

                JButton ab = new JButton("Answer");
                ab.setBounds(70, 475, 150, 30);
                ab.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) { 
				String prepAnswer = t3.getText();
				Boolean prepResult = prepAnswer.equals(item[6]);
				String sizeAnswer = t4.getText();
				Boolean sizeResult = sizeAnswer.equals(item[7]);
				
				String message = "Your preparation answer of " + prepAnswer + " is \n";
				if(prepResult) {
					message += " correct.\n";
				} else {
					message += " incorrect.\n";
				}
				message += "Your size answer of " + sizeAnswer + " is \n";
				if(sizeResult) {
					message += " correct.\n";
				} else {
					message += " incorrect.\n";
				}
				message += "Your answer of " + answers[0] + " ingredients was \n";
				if(Integer.parseInt(answers[0]) == ingredients.length) {
					message += "correct.\n";
				} else if(Integer.parseInt(answers[0]) < ingredients.length) {
					message += "incorrect, too little.\n";
				} else {
					message += "incorrect, too many.\n";
				}
				message += "Your answers for ingredients are : \n";
				for(int i = 0; i < ingredientAnswers.length; i++) {
					String result = "incorrect.\n";
					message += ingredientAnswers[i].getText() + " : ";
					for(int j = 0; j < ingredients.length; j++) {
						if(ingredientAnswers[i].getText().toLowerCase().equals(ingredients[j])) {
								result = "correct.\n";
								}
					}
					message += result;
				}
				message += "Your answer of " + answers[1] + " allergies was \n";
				if(Integer.parseInt(answers[1]) == allergies.length) {
					message += "correct.\n";
				} else if(Integer.parseInt(answers[1]) < allergies.length) {
					message += "incorrect, too little.\n";
				} else {
					message += "incorrect, too many.\n";
				}
				message += "Your answers for allergies are : \n";
				for(int i = 0; i < allergyAnswers.length; i++) {
					String result = "incorrect.\n";
					message += allergyAnswers[i].getText() + " : ";
					for(int j = 0; j < allergies.length; j++) {
						if(allergyAnswers[i].getText().toLowerCase().equals(allergies[j])) {
								result = "correct.\n";
								}
					}
					message += result;
				}

				JOptionPane.showMessageDialog(f,message); 
                                f.dispose();
                        }
                });
		f.add(ab);
                JButton b = new JButton("Back");
                b.setBounds(280, 475, 150, 30);
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


