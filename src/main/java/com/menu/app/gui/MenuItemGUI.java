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

public class MenuItemGUI implements GUIImplementation {

	ObjectMngmt objMngmt = ObjectMngmt.getInstance();

	public MenuItemGUI(String s) {

		JFrame f = new JFrame("Enering " + s );

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

		JLabel jl = new JLabel("Please enter the information for the new"
			+ " Menu Item");
		jl.setBounds(50, 10, 400, 30);
		f.add(jl);

		JLabel jl1 = new JLabel("Name :");
		jl1.setBounds(50, 40, 100, 30);
		f.add(jl1);
		JTextField ta1 = new JTextField(s);
		ta1.setEditable(false);
		ta1.setBounds(200, 40, 250, 30);
		f.add(ta1);

		JLabel jl2 = new JLabel("Restaurant : ");
		jl2.setBounds(50, 70, 100, 30);
		f.add(jl2);
		JTextField ta2 = new JTextField();
		ta2.setBounds(200, 70, 250, 30);
		f.add(ta2);

		JLabel jl3 = new JLabel("Menu Category :");
		jl3.setBounds(50, 100, 400, 30);
		f.add(jl3);
		JTextField ta3 = new JTextField();
		ta3.setBounds(200, 100, 250, 30);
		f.add(ta3);

		JLabel jl4 = new JLabel("Description : ");
		jl4.setBounds(50, 130, 400, 30);
		f.add(jl4);
		JTextArea ta4 = new JTextArea();
		ta4.setBounds(200, 130, 250, 60);
		ta4.setLineWrap(true);
		f.add(ta4);

		JLabel jl5 = new JLabel("Price : ");
		jl5.setBounds(50, 190, 400, 30);
		f.add(jl5);
		JTextField ta5 = new JTextField();
		ta5.setBounds(200, 190, 250, 30);
		f.add(ta5);

		JLabel jl6 = new JLabel("Ingredients : ");
		jl6.setBounds(50, 220, 400, 30);
		f.add(jl6);
		JTextArea ta6 = new JTextArea();
		ta6.setBounds(200, 220, 250, 90);
		ta6.setLineWrap(true);
		f.add(ta6);

		JLabel jl7 = new JLabel("Preparation Style : ");
		jl7.setBounds(50, 310, 400, 30);
		f.add(jl7);
		JTextField ta7 = new JTextField();
		ta7.setBounds(200, 310, 250, 30);
		f.add(ta7);

		JLabel jl8 = new JLabel("Size : ");
		jl8.setBounds(50, 340, 400, 30);
		f.add(jl8);
		JTextField ta8 = new JTextField();
		ta8.setBounds(200, 340, 250, 30);
		f.add(ta8);

		JLabel jl9 = new JLabel("Allergies : ");
		jl9.setBounds(50, 370, 400, 30);
		f.add(jl9);
		JTextField ta9 = new JTextField();
		ta9.setBounds(200, 370, 250, 30);
		f.add(ta9);

		JLabel jl10 = new JLabel("Current(y/n) : ");
		jl10.setBounds(50, 400, 400, 30);
		f.add(jl10);
		JTextField ta10 = new JTextField("y");
		ta10.setBounds(200, 400, 250, 30);
		f.add(ta10);

		JButton bb = new JButton("To Home");
                bb.setBounds(100, 430, 100, 30);
                bb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
				Main.setOption(".");	
                                f.dispose();
                        }
                });

		//if item is incorrect
		JLabel jl11 = new JLabel();
		jl11.setBounds(200, 470, 200, 30);
		f.add(jl11);


		//add item
		JButton b = new JButton("Submit");
		b.setBounds(220, 430, 100, 30);
		b.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
				//check to make sure information is correct
				int option = JOptionPane.showConfirmDialog(f, "Is this information" +
				      " correct?\n\t" + jl1.getText() + ta1.getText() + "\n\t" +
				      jl2.getText() + ta2.getText() + "\n\t" + 
				      jl3.getText() + ta3.getText() + "\n\t" + 
				      jl4.getText() + ta4.getText() + "\n\t" +
				      jl5.getText() + ta5.getText() + "\n\t" +
				      jl6.getText() + ta6.getText() + "\n\t" +
				      jl7.getText() + ta7.getText() + "\n\t" +
				      jl8.getText() + ta8.getText() + "\n\t" +
				      jl9.getText() + ta9.getText() + "\n\t" +
				      jl10.getText() + ta10.getText());
				if(option == 0) {
					//correct information
                        		try {
						MenuItem mi = new MenuItem(
							ta1.getText().toLowerCase().replace(",", "*"), 
							ta2.getText().toLowerCase().replace(",", "*"),
							ta3.getText().toLowerCase().replace(",","*"),
							ta4.getText().toLowerCase().replace(",","*"), 
							Double.parseDouble(ta5.getText()),
							ta6.getText().toLowerCase().replace(",","*"), 
							ta7.getText().toLowerCase().replace(",","*"), 
							ta8.getText().toLowerCase().replace(",","*"), 
							ta9.getText().toLowerCase().replace(",","*"),
							ta10.getText().toLowerCase().replace(",","*"));
                        	        	objMngmt.pushToArray("m", mi);
                	        	        objMngmt.pushToDatabase("m", mi.toString());
        	        	                objMngmt.updateRestaurant(mi);
	
	         	               	} catch (Exception ec) {
 	                                	JOptionPane.showMessageDialog(f, 
							"Menu Item not entered, please try again");
			       		}
				//incorrect informaiton
				} else if(option == 1) {
					jl11.setText("Plese re-enter the information");
				//cancel??????????????????????????????????????????
				} else if(option == 2) {
					Main.setOption(".");
				}
				f.dispose();
			}
		});

		f.add(b);
		f.add(bb);
		f.setSize(550, 530);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
