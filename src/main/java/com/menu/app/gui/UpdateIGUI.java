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

public class UpdateIGUI implements GUIImplementation {

        UpdateIGUI(String type, String name) {

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

		JTextArea ta = new JTextArea("Please enter the information you would like to update"
			      + " for " + name.toUpperCase());
                ta.setBounds(50, 80, 400, 30);
                ta.setLineWrap(true);
                f.add(ta);

		 String[] objectInfo = ObjectMngmt.getInstance().getObjectString(type, name).split(",");

           	JLabel ja1 = new JLabel("Name");
            	ja1.setBounds(60, 150, 100, 30);
    		f.add(ja1);
             	JTextArea ta1 = new JTextArea(objectInfo[0]);
        	ta1.setBounds(180, 150, 260, 30);
          	f.add(ta1);

            	JLabel ja2 = new JLabel("Description");
               	ja2.setBounds(60, 190, 100, 30);
                f.add(ja2);
   		JTextArea ta2 = new JTextArea(objectInfo[1]);
        	ta2.setBounds(180, 190, 260, 30);
              	f.add(ta2);

    	        JLabel ja3 = new JLabel("Cost");
         	ja3.setBounds(60, 230, 100, 30);
         	f.add(ja3);
		JTextArea ta3 = new JTextArea(objectInfo[2]);
           	ta3.setBounds(180, 230, 260, 30);
         	f.add(ta3);


                JButton b = new JButton("To Home");
                b.setBounds(100, 330, 100, 30);
                b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                GUIFactory.getGUI("f", "");
                                f.dispose();
                        }
                });
                f.add(b);

		JButton b2 = new JButton("Submit");
		b2.setBounds(200, 330, 100, 30);
		b.addActionListener(new ActionListener() {
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

