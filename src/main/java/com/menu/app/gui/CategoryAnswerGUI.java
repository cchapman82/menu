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

import com.menu.app.objects.Study;

import java.awt.event.*;
import java.awt.*;
import java.util.List;


public class CategoryAnswerGUI implements GUIImplementation {


	CategoryAnswerGUI(String name) {

		int itemsAnswer = Integer.parseInt(name.substring(name.indexOf(".") + 1, name.length()));
		String cName = name.substring(0, name.indexOf("."));
		List<String> items = Study.getInstance().getCatItems(cName);
		int itemsExpected = items.size();
        	JTextField[] answerFields = new JTextField[itemsAnswer];
		JFrame f = new JFrame("Category answers for  " + cName );


                JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });

                JLabel l1 = new JLabel("Please answer the questions below...");
                l1.setBounds(10, 20, 480, 30);
                f.add(l1);

                JLabel l2 = new JLabel("You have stated that " + cName +
			       " has " + itemsAnswer);
                l2.setBounds(20, 60, 480, 30);
                f.add(l2);


                JLabel l3 = new JLabel("What items are in " + cName + "?");
        	l3.setBounds(20, 110, 480, 30);
	        f.add(l3);

		JPanel jp = new JPanel();
		jp.setBounds(30, 150, 440, 280);
		jp.setLayout(new GridLayout(itemsAnswer, 1));
	
               	for (int i = 0; i < itemsAnswer; i++) {
       			JTextField t = new JTextField(" ");
                       	t.setPreferredSize(new Dimension(300, 30));
			jp.add(t);
               	        answerFields[i] = t;
	       	}
		JScrollPane s = new JScrollPane(jp);
		s.setBounds(30, 150, 440, 280);
		f.getContentPane().add(s);
 		JButton ab = new JButton("Answer");
                ab.setBounds(200, 435, 100, 30);
                ab.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
				String[] answers = new String[answerFields.length];
                                for(int j = 0; j < answerFields.length; j++) {
                                        answers[j] = answerFields[j].getText();
                                }
                                JOptionPane.showMessageDialog(f, makeAnswerMessage(items, answers, 
							itemsAnswer, itemsExpected, cName));
                       }


                });
                f.add(ab);

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

        private String makeAnswerMessage(List<String> items, String[] answers,
			Integer itemsAnswer, Integer itemsExpected, String name) {
                String result = "Your answer of " + itemsAnswer + " for how many items in " +
		       name +	":\n";
		if(itemsAnswer < itemsExpected) {
                        result += "incorrect -- too little items\n";

		} else if(itemsAnswer == itemsExpected) {
                        result += "CORRECT!\n";
                } else {
                        result += "incorrect -- too many items\n";
                }

                result += "Following are the results of the items in this category question:\n";

                for(int i = 0; i < answers.length; i++) {
                        result += answers[i] + "      ";
                        if (items.contains(answers[i])) {
                                result += "CORRECT!\n";
                        } else {
                                result += "incorrect\n";
			}
                }
                return result;
        }
}


