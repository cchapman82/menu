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
import com.menu.app.database.DatabaseController;
import com.menu.app.objects.ObjectMngmt;

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

           	JLabel jl1 = new JLabel("Name : ");
            	jl1.setBounds(60, 150, 100, 30);
    		f.add(jl1);
             	JTextArea ta1 = new JTextArea(objectInfo[0]);
        	ta1.setBounds(180, 150, 260, 30);
          	f.add(ta1);

            	JLabel jl2 = new JLabel("Description : ");
               	jl2.setBounds(60, 190, 100, 30);
                f.add(jl2);
   		JTextArea ta2 = new JTextArea(objectInfo[1]);
        	ta2.setBounds(180, 190, 260, 30);
              	f.add(ta2);

    	        JLabel jl3 = new JLabel("Cost : ");
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

		JLabel jl4 =  new JLabel();
		jl4.setBounds(200, 370, 200, 30);
		f.add(jl4);

		JButton b2 = new JButton("Submit");
		b2.setBounds(300, 330, 100, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(f, "Is this information " +
						"correct?\n\t" + jl1.getText() + ta1.getText()
						+ "\n\t" + jl2.getText() + ta2.getText()
						+ "\n\t" + jl3.getText() + ta3.getText());
				if(option == 0) {
					if(!ta1.getText().equals(objectInfo[0])) {
                                	        map.put(jl1.getText().toLowerCase().replace(":", ""), ta1.getText().toLowerCase().replace(",","~"));
	                                }
        	                        if(!ta2.getText().equals(objectInfo[1])) {
                	                        map.put(jl2.getText().toLowerCase().replace(":", ""), ta2.getText().toLowerCase().replace(",","~"));
                        	        }
                                	if(!ta3.getText().equals(objectInfo[2])) {
                                        	map.put(jl3.getText().toLowerCase().replace(":", ""), ta3.getText().toLowerCase().replace(",","~"));
                                	}
					DatabaseController.getInstance().updateInfo(type, name, map);
				} else if(option == 1) {
					jl4.setText("Please re-enter information");
				} else if(option == 2) {
					Main.setOption(".");
				}
				f.dispose();
			}
		});
		f.add(b2);


                f.setSize(500, 530);
                f.setLayout(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        }
}

