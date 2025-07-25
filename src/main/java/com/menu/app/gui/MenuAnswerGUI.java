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
import com.menu.app.objects.Study;

import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;


public class MenuAnswerGUI implements GUIImplementation {

		private Map<String, Integer> catNums = Study.getInstance().getCatItemNums();
		private JTextField[] answerFields = new JTextField[catNums.keySet().size()];
		private JLabel[] answerLabels = new JLabel[catNums.keySet().size()];
		private Map<String, Integer> answers = new HashMap<String, Integer>();

        MenuAnswerGUI(String name) {

		int catAnswer = Integer.parseInt(name.substring(name.indexOf(".") + 1, name.length()));
		String cName = name.substring(0, name.indexOf("."));
		JFrame f = new JFrame("Menu answers for  " + cName );


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

		JLabel l2 = new JLabel("How answered that there are " +
				       catAnswer + " categories in this menu.");
		l2.setBounds(20, 60, 480, 30);
		f.add(l2);


		JLabel l3 = new JLabel("How many items are in each category?");
		l3.setBounds(20, 100, 300, 30);
		f.add(l3);
		int i = 0;
		int yAxis = 150;
                for (Map.Entry<String, Integer> e : catNums.entrySet()) {
                        JLabel l4  = new JLabel(e.getKey());
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
				for(int j = 0; j < answerFields.length; j++) {
					answers.put(answerLabels[j].getText(), Integer.parseInt(answerFields[j].getText()));
				}
				JOptionPane.showMessageDialog(f, makeAnswerMessage(catAnswer));
  			}


                });
                f.add(ab);

 		JButton b = new JButton("To Restaurant");
                b.setBounds(175, 475, 150, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
			       Main.getItem("r", cName);	
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

