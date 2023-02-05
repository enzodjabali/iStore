package org.istore.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import io.github.cdimascio.dotenv.Dotenv;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

import io.github.cdimascio.dotenv.Dotenv;
import org.istore.Listing;


public class GUI {
    Dotenv dotenv = Dotenv.configure().load();
    JFrame authFrame = new JFrame(String.format(dotenv.get("PROJECT_NAME")) + " - Authentication");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void Authentication() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                authFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                authFrame.setSize(800, 600);
                authFrame.add(new SignIn(authFrame));
                authFrame.setLocationRelativeTo(null);
                authFrame.setVisible(true);

            }
        });
    }
}
