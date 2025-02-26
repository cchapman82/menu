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


public class UpdateIGUI implements GUIImplementation {

	private Map<String, String> map = new HashMap<String, String>();
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

           	JLabel jl1 = new JLabel("Name");
            	jl1.setBounds(60, 150, 100, 30);
    		f.add(jl1);
             	JTextArea ta1 = new JTextArea(objectInfo[0]);
        	ta1.setBounds(180, 150, 260, 30);
          	f.add(ta1);

            	JLabel jl2 = new JLabel("Description");
               	jl2.setBounds(60, 190, 100, 30);
                f.add(jl2);
   		JTextArea ta2 = new JTextArea(objectInfo[1]);
        	ta2.setBounds(180, 190, 260, 30);
              	f.add(ta2);

    	        JLabel jl3 = new JLabel("Cost");
         	jl3.setBounds(60, 230, 100, 30);
         	f.add(jl3);
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
				if(!ta1.getText().equals(objectInfo[0])) {
                                        map.put(jl1.getText().toLowerCase(), ta1.getText());
                                }
                                if(!ta2.getText().equals(objectInfo[1])) {
                                        map.put(jl2.getText().toLowerCase(), ta2.getText());
                                }
                                if(!ta3.getText().equals(objectInfo[2])) {
                                        map.put(jl3.getText().toLowerCase(), ta3.getText());
                                }
				DatabaseController.getInstance().updateInfo(type, name, map);
			}
		});
		f.add(b2);


                f.setSize(500, 530);
                f.setLayout(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        }
}

