package org.istore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.github.cdimascio.dotenv.Dotenv;

public class GUI {
    Dotenv dotenv = Dotenv.configure().load();
    JFrame authFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Authentication");
    JFrame homeFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Home");

    public void authentication() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }


                authFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                authFrame.setSize(800,600);
                authFrame.add(new SignIn());
                //authFrame.pack();
                authFrame.setLocationRelativeTo(null);
                authFrame.setVisible(true);


            }
        });
    }

    public class SignIn extends JPanel {

        public SignIn() {
            JTextField emailField = new JTextField(20);
            JTextField passwordField = new JPasswordField(20);
            JButton signInButton = new JButton("Sign in");
            JButton signUpButton = new JButton("New user");

            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;

            add(new JLabel("Email"), gbc);
            gbc.gridy++;
            add(new JLabel("Password "), gbc);

            gbc.gridy = 0;
            gbc.gridx = 1;
            add(new JLabel("<html><h4>Please, sign in</h4></html>"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            add(emailField, gbc);
            gbc.gridy++;
            add(passwordField, gbc);

            gbc.gridy = 3;
            gbc.gridx = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(signInButton, gbc);

            gbc.gridy = 3;
            gbc.gridx = 2;
            add(signUpButton, gbc);

            signInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // this makes sure the button you are pressing is the button variable
                    if(e.getSource() == signInButton) {
                        System.out.println(emailField.getText());
                        System.out.println(passwordField.getText());

                        User crud = new User();

                        DBManager database = new DBManager();
                        database.dbconnect();

                        boolean connect = crud.userConnect(emailField.getText(), passwordField.getText());
                        System.out.println(connect);

                        if (connect) {
                            // connected user
                            System.out.println("connected");
                            authFrame.setVisible(false);

                            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            homeFrame.setSize(800,600);
                            homeFrame.add(new Home());
                            homeFrame.setLocationRelativeTo(null);
                            homeFrame.setVisible(true);
                        } else {
                            System.out.println("not connected");
                            // print error message
                            gbc.gridy = 5;
                            gbc.gridx = 1;
                            add(new JLabel("<html><b style='color: red;'>Wrong email or password!</b></html>"), gbc);
                            authFrame.setVisible(true);
                        }
                    }
                }
            });
        }

        public class Home extends JPanel {

            public Home() {
                // home page here
                System.out.println("HOME PAGE DISPLAY");
            }
        }

    }
}
