package org.istore.GUI;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.istore.User;
import static org.istore.GUI.GUI.isNumeric;

public class SignUp extends JPanel {
    public SignUp() {
        Dotenv dotenv = Dotenv.configure().load();

        JFrame SignUpFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Sign up");
        SignUpFrame.setSize(350, 200);
        SignUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SignUpFrame.setLocationRelativeTo(null);
        SignUpFrame.setVisible(true);

        JTextField pseudoField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);

        JButton SignUpButton = new JButton("Sign up");

        SignUpFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;

        SignUpFrame.add(new JLabel("Pseudo"), gbc);
        gbc.gridy++;
        SignUpFrame.add(new JLabel("Email"), gbc);
        gbc.gridy++;
        SignUpFrame.add(new JLabel("Password "), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        SignUpFrame.add(new JLabel("<html><h4>New user</h4></html>"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        SignUpFrame.add(pseudoField, gbc);
        gbc.gridy++;
        SignUpFrame.add(emailField, gbc);
        gbc.gridy++;
        SignUpFrame.add(passwordField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        SignUpFrame.add(SignUpButton, gbc);


        SignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == SignUpButton) {
                    String pseudo = pseudoField.getText();
                    String email = emailField.getText();
                    String password = emailField.getText();

                    gbc.gridy = 5;
                    gbc.gridx = 1;

                    if (!pseudo.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                        if (new User().userRegister(pseudo, email, password)) {
                            SignUpFrame.dispatchEvent(new WindowEvent(SignUpFrame, WindowEvent.WINDOW_CLOSING));
                        }

                    } else {
                        JLabel errorMessage = new JLabel("<html><b style='color: red;'>Please, fill all the fields!</b></html>");
                        SignUpFrame.add(errorMessage, gbc);
                        SignUpFrame.setVisible(true);
                    }
                }
            }
        });
    }
}
