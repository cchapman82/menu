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
import com.menu.app.objects.ObjectMngmt;

import java.awt.event.*;
import java.awt.*;

public class ChooseItemGUI implements GUIImplementation {


        ChooseItemGUI(String name) {
        	String[] menuItems = ObjectMngmt.getInstance().getMenuList(name).split("\n");

                JFrame f = new JFrame("Study Restaurant " + name + " Menu Items Home");

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
                                " items, please choose one.");
                t2.setBounds(10, 40, 480, 30);
                f.add(t2);

		JPanel jp = new JPanel();
		jp.setBounds(20, 90, 440,380); 
		int rows = (menuItems.length + 3)/3;
		jp.setLayout(new GridLayout(rows, 3));

		for(int i = 0; i < menuItems.length; i++) {
			JButton b = new JButton(menuItems[i]);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Main.studyItems("i", b.getText());
				}
			});
			jp.add(b);
		}

		JScrollPane sp = new JScrollPane(jp);
		sp.setBounds(20, 90, 440, 380);
		f.getContentPane().add(sp);

		JButton b = new JButton("To Restaurant");
                b.setBounds(175, 475, 150, 30);
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

