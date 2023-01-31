package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {


    public static void main(String args[]){
        Dotenv dotenv = Dotenv.configure().load();

        JFrame frame = new JFrame(String.format(dotenv.get("PROJECT_NAME")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        // widgets here
        //Creating the MenuBar and adding components
        /*JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        */

        //Creating the panel at bottom and adding components

        JPanel authPanel = new JPanel(); // the panel is not visible in output

        JLabel email = new JLabel("Email");
        JTextField emailField = new JTextField(20); // accepts upto 10 characters

        JLabel password = new JLabel("Password");
        JTextField passwordField = new JPasswordField(20); // accepts upto 10 characters

        JButton send = new JButton("Sign in");
        //JButton reset = new JButton("Reset");

        authPanel.add(email); // Components Added using Flow Layout
        authPanel.add(emailField);

        authPanel.add(password); // Components Added using Flow Layout
        authPanel.add(passwordField);

        authPanel.add(send);
        //panel.add(reset);

        // Text Area at the Center
        //JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, authPanel);
        //frame.getContentPane().add(BorderLayout.NORTH, mb);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);




        frame.setVisible(true);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // this makes sure the button you are pressing is the button variable
                if(e.getSource() == send) {
                    System.out.println(emailField.getText());
                    System.out.println(passwordField.getText());

                    crud crud = new crud();
                    crud.dbconnect();

                    crud.userConnect(emailField.getText(), passwordField.getText());
                }
            }
        });
    }
}
