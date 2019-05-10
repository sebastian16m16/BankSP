package csc_1_Software_Design.PresentationLayer;

import csc_1_Software_Design.BusinessLayer.DBConnection;
import csc_1_Software_Design.DataLayer.LoginOP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class CreateAdminLoginGUI extends JFrame{
    private JTextField usernameField;
    private JButton createAccountButton;
    private JPasswordField passwordField1;
    public JPanel createAdminLoginPanel;
    private JButton backButton;
    final LoginGUI loginGUI = new LoginGUI();

    public CreateAdminLoginGUI(){
        super();
        setTitle("Create Admin Login");
        setSize(400, 800);
        final LoginGUI loginGUI = new LoginGUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(createAdminLoginPanel);
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
                    if(loginOP.usernameExists(loginGUI.dbConnection.connection, usernameField.getText())){
                        JOptionPane.showMessageDialog(null, "Username already exists!");
                    }else{
                        loginOP.createAdminLogin(loginGUI.dbConnection.connection, usernameField.getText(), passwordField1.getText());
                        JOptionPane.showMessageDialog(null, "Account Created!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


}
