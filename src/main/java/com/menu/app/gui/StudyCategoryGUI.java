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
import java.util.List;



public class StudyCategoryGUI implements GUIImplementation {


        StudyCategoryGUI(String name) {


        	List<String> items = Study.getInstance().getCatItems(name);

                JFrame f = new JFrame("Study Category " + name + " Home");

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
                t1.setBounds(10, 10, 480, 60);
                f.add(t1);

                JLabel t2 = new JLabel(name + " has these menu " +
                                " items");
                t2.setBounds(10, 110, 480, 30);
                f.add(t2);

		JLabel t3 = new JLabel(makeItemList(items));
		t3.setBounds(20, 160, 300, 450);
		JScrollPane sp = new JScrollPane(t3);
		t3.setVerticalAlignment(SwingConstants.TOP);
		f.add(sp);
		f.add(t3);


		Timer timer = new Timer(15000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
				f.dispose();
				String itemAnswer = JOptionPane.showInputDialog(
						"How many items are in " + name + "?");
                                GUIFactory.getAnswerGUI("c", name + "." + itemAnswer);
                        }
                });
                timer.setRepeats(false);
                timer.start();


                JButton b = new JButton("To Restaurant");
                b.setBounds(170, 475, 150, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
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

	private String makeItemList(List<String> items) {
		String result = "<html>";
		for(String s : items) {
			result += s + "<br>";
		}
		return result.substring(0, result.lastIndexOf("<br>")) + "<html>";
	}

}
