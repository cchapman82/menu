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
import com.menu.app.objects.Ingredient;
import com.menu.app.objects.ObjectMngmt;

import java.awt.event.*;

public class IngredientGUI implements GUIImplementation {

	private ObjectMngmt objMngmt = ObjectMngmt.getInstance();

	public IngredientGUI(String s) {

		JFrame f = new JFrame("Entering the Ingredient " + s);
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
			+ "Ingredient");
	       jl.setBounds(50, 10, 400, 30);
                f.add(jl);

                JLabel jl1 = new JLabel("Name :");
                jl1.setBounds(50, 40, 100, 30);
                f.add(jl1);
                JTextField ta1 = new JTextField(s);
		ta1.setEditable(false);
                ta1.setBounds(200, 40, 250, 30);
                f.add(ta1);

                JLabel jl2 = new JLabel("Description : ");
                jl2.setBounds(50, 70, 100, 30);
                f.add(jl2);
                JTextArea ta2 = new JTextArea();
                ta2.setBounds(200, 70, 250, 60);
		ta2.setLineWrap(true);
                f.add(ta2);

                JLabel jl3 = new JLabel("Cost :");
                jl3.setBounds(50, 130, 400, 30);
                f.add(jl3);
                JTextField ta3 = new JTextField();
                ta3.setBounds(200, 130, 250, 30);
                f.add(ta3);


		JButton bb = new JButton("To Home");
                bb.setBounds(100, 430, 100, 30);
                bb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                GUIFactory.getGUI(".", "");
                                f.dispose();
                        }
                });

		JLabel jl4 = new JLabel();
		jl4.setBounds(200, 470, 200, 30);
		f.add(jl4);

                JButton b = new JButton("Submit");
                b.setBounds(220, 430, 100, 30);
                b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(f, "Is this information" +
				      " correct?\n\t" + jl1.getText() + ta1.getText() + "\n\t" +
				      jl2.getText() + ta2.getText() + "\n\t" + 
				      jl3.getText() + ta3.getText());
			       if(option == 0) {	
        	                        try {
	                                        Ingredient ii  = new Ingredient(
											ta1.getText().toLowerCase().replace(",", "~"), 
											ta2.getText().toLowerCase().replace(",", "~"),
     										Double.parseDouble(ta3.getText()));
                	                        objMngmt.pushToArray("i", ii);
        	                                objMngmt.pushToDatabase("i", ii.toString());
											Main.setOption(".");
                	               } catch (Exception ec) {
        	                                  JOptionPane.showMessageDialog(f,"Ingredient not entered, please try again");
	                               }
			       } else if(option == 1) {
				       jl4.setText("Please re-enter the information");
			       } else if(option == 2) {
				       Main.setOption(".");
			       }
			       f.dispose();
			}
                });
		f.add(b);
		f.add(bb);
		f.setSize(500, 530);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
