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
                                 name + ".  You will be asked about this in 15 sec.<br><br><html>");
                t1.setBounds(10, 10, 480, 40);
                f.add(t1);

                JLabel t2 = new JLabel(name + " has these menu " +
                                " categories");
                t2.setBounds(10, 50, 480, 30);
		t2.setVerticalAlignment(SwingConstants.TOP);
		f.add(t2);

		JLabel t3 = new JLabel("<html>" + getCategories(catNums)  + "<html>");
		t3.setBounds(20, 90, 300, 200);
		t3.setVerticalAlignment(SwingConstants.TOP);
		JScrollPane sp1 = new JScrollPane(t3);
	       f.add(t3);
	       f.add(sp1);

		JLabel t4 = new JLabel("<html>" + getCatNums(catNums) + "<html");
		t4.setBounds(20, 290, 300, 185);
		t4.setVerticalAlignment(SwingConstants.TOP);
		JScrollPane sp2 = new JScrollPane(t4);
		f.add(sp2);
		f.add(t4);
		Timer timer = new Timer(15000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				f.dispose();
				String answer = JOptionPane.showInputDialog(
						"How Many categories are in this menu?");
				GUIFactory.getAnswerGUI("m", name + "." + answer);
			}	
		});
		timer.setRepeats(false);
		timer.start();	


                JButton b = new JButton("To Restaurant");
                b.setBounds(175, 475, 150, 30);
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
		String result = "The categories have this many items each:<br><br><br>";
		for(Map.Entry<String, Integer> e : catNums.entrySet()) {
			result+= e.getKey() + "		" + e.getValue() + "<br>";
		}
		return result.substring(0, result.lastIndexOf("<br>")) + "<html>";
	}

	private String getCategories(Map<String, Integer> catNums) {
		String result = "<html><br><br><br>";
		for(String s : catNums.keySet()) {
			result += s + "<br>";
		}
		return result.substring(0, result.lastIndexOf("<br>")) + "<html>";
	}

}

