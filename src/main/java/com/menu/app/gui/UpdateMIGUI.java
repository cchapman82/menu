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

public class UpdateMIGUI implements GUIImplementation {

	UpdateMIGUI(String type, String name) {

		JFrame f = new JFrame("Updating " + name);
		JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                                System.exit(1);
                        }
                });
                file.add(exit);
                mb.add(file);
                f.setJMenuBar(mb);

		JTextArea ta = new JTextArea("Please enter the new information you would like to update"
				+ " for " + name.toUpperCase());
		ta.setBounds(50, 10, 400, 30);
		ta.setLineWrap(true);
		f.add(ta);

		String[] objectInfo = ObjectMngmt.getInstance().getObjectString(type, name).split(",");

		JLabel jl1 = new JLabel("Name");
		jl1.setBounds(60, 50, 100, 30);
		f.add(jl1);
		JTextArea ta1 = new JTextArea(objectInfo[0]);
		ta1.setBounds(180, 50, 260, 30);
		f.add(ta1);

		JLabel jl2 = new JLabel("Restaurant");
		jl2.setBounds(60, 90, 100, 30);
		f.add(jl2);
		JTextArea ta2 = new JTextArea(objectInfo[1]);
		ta2.setBounds(180, 90, 260, 30);
		f.add(ta2);

		JLabel jl3 = new JLabel("Category");
		jl3.setBounds(60, 130, 100, 30);
		f.add(jl3);
		JTextArea ta3 = new JTextArea(objectInfo[2]);
		ta3.setBounds(180, 130, 260, 30);
		f.add(ta3);

		JLabel jl4 = new JLabel("Description");
		jl4.setBounds(60, 170, 100, 30);
		f.add(jl4);
		JTextArea ta4 = new JTextArea(objectInfo[3]);
		ta4.setBounds(180, 170, 260, 30);
		f.add(ta4);

		JLabel jl5 = new JLabel("Price");
		jl5.setBounds(60, 210, 100, 30);
		f.add(jl5);
		JTextArea ta5 = new JTextArea(objectInfo[4]);
		ta5.setBounds(180, 210, 260, 30);
		f.add(ta5);

		JLabel jl6 = new JLabel("Ingredients");
		jl6.setBounds(60, 250, 100, 30);
		f.add(jl6);
		JTextArea ta6 = new JTextArea(objectInfo[5]);
		ta6.setBounds(180, 250, 260, 30);
		f.add(ta6);

		JLabel jl7 = new JLabel("Preparation");
		jl7.setBounds(60, 290, 100, 30);
		f.add(jl7);
		JTextArea ta7 = new JTextArea(objectInfo[6]);
		ta7.setBounds(180, 290, 260, 30);
		f.add(ta7);

		JLabel jl8 = new JLabel("Size");
		jl8.setBounds(60, 330, 100, 30);
		f.add(jl8);
		JTextArea ta8 = new JTextArea(objectInfo[7]);
		ta8.setBounds(180, 330, 260, 30);
		f.add(ta8);

		JLabel jl9 = new JLabel("Allergies");
		jl9.setBounds(60, 370, 100, 30);
		f.add(jl9);
		JTextArea ta9 = new JTextArea(objectInfo[8]);
		ta9.setBounds(180, 370, 260, 30);
		f.add(ta9);
		
		JButton b = new JButton("To Home");
                b.setBounds(100, 410, 100, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                GUIFactory.getGUI("f", "");
                                f.dispose();
                        }
                });
                f.add(b);

		JButton b2 = new JButton("Submit");
		b2.setBounds(300, 410, 100, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Submitting");
			}
		});
		f.add(b2);


                f.setSize(500, 530);
                f.setLayout(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
}
