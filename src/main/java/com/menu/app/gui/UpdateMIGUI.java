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
import java.util.HashMap;
import java.util.Map;

public class UpdateMIGUI implements GUIImplementation {

	private Map<String,String> map = new HashMap<String,String>();

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
					
		JLabel jl10 = new JLabel();
		jl10.setBounds(200, 450, 200, 30);
		f.add(jl10);

		JButton b2 = new JButton("Submit");
		b2.setBounds(300, 410, 100, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(f, "Is this information" +
				      " correct?\n\t" + jl1.getText() + ": " + ta1.getText() + "\n\t" +
				      jl2.getText() + ": " + ta2.getText() + "\n\t" + 
				      jl3.getText() + ": " + ta3.getText() + "\n\t" + 
				      jl4.getText() + ": " + ta4.getText() + "\n\t" +
				      jl5.getText() + ": " + ta5.getText() + "\n\t" +
				      jl6.getText() + ": " + ta6.getText() + "\n\t" +
				      jl7.getText() + ": " + ta7.getText() + "\n\t" +
				      jl8.getText() + ": " + ta8.getText() + "\n\t" +
				      jl9.getText() + ": " + ta9.getText());
				System.out.println(option);
				if(option == 0) {
					if(!ta1.getText().equals(objectInfo[0])) {
						map.put(jl1.getText().toLowerCase(), ta1.getText());
					}
					if(!ta2.getText().equals(objectInfo[1])) {
						map.put(jl2.getText().toLowerCase(), ta2.getText());
					}
					if(!ta3.getText().equals(objectInfo[2])) {
						map.put(jl3.getText().toLowerCase(), ta3.getText());
					}
					if(!ta4.getText().equals(objectInfo[3])) {
						map.put(jl4.getText().toLowerCase(), ta4.getText());
					}
					if(!ta5.getText().equals(objectInfo[4])) {
						map.put(jl5.getText().toLowerCase(), ta5.getText());
					}
					if(!ta6.getText().equals(objectInfo[5])) {
						map.put(jl6.getText().toLowerCase(), ta6.getText());
					}
					if(!ta7.getText().equals(objectInfo[6])) {
						map.put(jl7.getText().toLowerCase(), ta7.getText());
					}
					if(!ta8.getText().equals(objectInfo[7])) {
						map.put(jl8.getText().toLowerCase(), ta8.getText());
					}
					if(!ta9.getText().equals(objectInfo[8])) {
						map.put(jl9.getText().toLowerCase(), ta9.getText());
					}
					DatabaseController.getInstance().updateInfo(type, name, map);
				} else if(option == 1) {
					jl10.setText("Please re-enter information");
				} else if(option == 2) {
					Main.setOption(".");
				}
				f.dispose();

			}
		});

		f.add(b2);


                f.setSize(500, 550);
                f.setLayout(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
}
