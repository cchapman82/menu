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
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class MenuAnswerGUI implements GUIImplementation {

		private Map<String, Integer> catNums = Study.getInstance().getCatItemNums();
		private int catExpected = catNums.keySet().size();
		private JTextField[] answerFields = new JTextField[catNums.keySet().size()];
		private JLabel[] answerLabels = new JLabel[catNums.keySet().size()];
		private Map<String, Integer> answers = new HashMap<String, Integer>();

        MenuAnswerGUI(String name) {

                //String menuCategories = Study.getInstance().getCategories();
                //String[] strings = menuCategories.split("<br>");

		JFrame f = new JFrame("Menu answers for  " + name );


                JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });

		JLabel l1 = new JLabel("Please answer the questions below...");
		l1.setBounds(10, 20, 300, 30);
		f.add(l1);

		JLabel l2 = new JLabel("How many categories does this menu have?");
		l2.setBounds(20, 60, 250, 30);
		f.add(l2);

		JTextField t2 = new JTextField();
		t2.setBounds(280, 60, 25, 30);
		f.add(t2);

		JLabel l3 = new JLabel("How many items are in each category?");
		l3.setBounds(20, 100, 300, 30);
		f.add(l3);
		int i = 0;
		int yAxis = 150;
                for (Map.Entry<String, Integer> e : catNums.entrySet()) {
                        JLabel l4  = new JLabel(e.getKey());
                       // l4.setText(strings[i]);
                        l4.setBounds(30, yAxis, 200, 30);
                        f.add(l4);
                        JTextField t3 = new JTextField();
                        t3.setBounds(240, yAxis, 50, 30);
                        f.add(t3);
			answerFields[i] = t3;
			answerLabels[i] = l4;
			i++;
                        yAxis += 40;
		}
                JButton ab = new JButton("Answer");
                ab.setBounds(200, yAxis, 100, 30);
                ab.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {	
				int catAnswer = Integer.parseInt(t2.getText());
				for(int j = 0; j < answerFields.length; j++) {
					answers.put(answerLabels[j].getText(), Integer.parseInt(answerFields[j].getText()));
				}
				JOptionPane.showMessageDialog(f, makeAnswerMessage(catAnswer));
  			}


                });
                f.add(ab);

 		JButton b = new JButton("To Home");
                b.setBounds(200, 475, 100, 30);
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

	private String makeAnswerMessage(Integer catAnswer) {
		String result = "Your answer of " + catAnswer+ " for how many categories on the menu is :\n";
		if(catAnswer == catNums.keySet().size()) {
			result += "CORRECT!\n";
		} else {
			result += "inorrect\n";
		}
		
		result += "Following are the results of the amount of items in each category question:\n";

		for(Map.Entry<String, Integer> e : answers.entrySet()) {
			result += e.getKey() + "       " + e.getValue() + "      ";
			if (e.getValue()  == catNums.get(e.getKey())) {
				result += "CORRECT!\n";
			} else {
				result += "incorrect\n";
			}
		}
		return result;

	}
}

