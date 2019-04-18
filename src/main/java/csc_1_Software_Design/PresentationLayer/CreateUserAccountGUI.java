package csc_1_Software_Design.PresentationLayer;

import csc_1_Software_Design.BusinessLayer.DBConnection;
import csc_1_Software_Design.DataLayer.LoginOP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUserAccountGUI extends JFrame{
    private JPanel createUserLoginPanel;
    private JTextField usernameField;
    private JButton createAccountButton;
    private JTextField cnpField;
    private JPasswordField passwordField;
    DBConnection connection = DBConnection.getConnection();

    public CreateUserAccountGUI(){

        add(createUserLoginPanel);
        setTitle("Create User Login");
        setSize(400,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginGUI loginGUI = new LoginGUI();

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOP loginOP = new LoginOP();

                try {
                    if(loginOP.usernameExists(connection.connection, usernameField.getText())) {
                        JOptionPane.showMessageDialog(null, "Username already exists!");
                    }else if(loginOP.cnpExists(connection.connection, cnpField.getText())){
                        JOptionPane.showMessageDialog(null,"You are already registered");
                    }else{
                        loginOP.createUserLogin(connection.connection, cnpField.getText(),usernameField.getText(),passwordField.getText());
                        JOptionPane.showMessageDialog(null, "Login Created with\n" + "Username: " +usernameField.getText() +"\n" +
                                "CNP: " + cnpField.getText() + "\n 0 errors");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });





    }

}
