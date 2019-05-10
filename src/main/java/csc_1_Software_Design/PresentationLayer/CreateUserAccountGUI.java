package csc_1_Software_Design.PresentationLayer;

import csc_1_Software_Design.DataLayer.LoginOP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUserAccountGUI extends JFrame{
    public JPanel createUserLoginPanel;
    private JTextField usernameField;
    private JButton createAccountButton;
    private JTextField cnpField;
    private JPasswordField passwordField;
    private JButton backButton;


    public CreateUserAccountGUI(){
        super();

        setTitle("Create User Login");
        setSize(400,800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(createAccountButton);

        add(createUserLoginPanel);

        final LoginGUI loginGUI = new LoginGUI();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginGUI.setVisible(true);
                setVisible(false);
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOP loginOP = new LoginOP();

                try {
                    if(loginOP.usernameExists(loginGUI.dbConnection.connection, usernameField.getText())) {
                        JOptionPane.showMessageDialog(null, "Username already exists!");
                    }else if(loginOP.existsLoginByCNP(loginGUI.dbConnection.connection, cnpField.getText())){
                        JOptionPane.showMessageDialog(null,"You are already registered");
                    }else{
                        loginOP.createUserLogin(loginGUI.dbConnection.connection, cnpField.getText(),usernameField.getText(),passwordField.getText());
                        JOptionPane.showMessageDialog(null, "Login Created with\n" + "Username: " +usernameField.getText() +"\n" +
                                "CNP: " + cnpField.getText() + "\n 0 errors");
                    }
                        setVisible(false);
                        loginGUI.setVisible(true);


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });





    }

}
