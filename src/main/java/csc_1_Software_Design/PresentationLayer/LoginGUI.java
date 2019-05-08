package csc_1_Software_Design.PresentationLayer;

import csc_1_Software_Design.BusinessLayer.DBConnection;
import csc_1_Software_Design.BusinessLayer.UserOp;
import csc_1_Software_Design.DataLayer.LoginOP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginGUI extends JFrame{

    public DBConnection dbConnection = DBConnection.getConnection();
    private JPanel loginPanel;
    private JTextField USERNAMETextField;
    private JPasswordField PASSWORDPasswordField;
    private JButton Login;
    private JButton createAccountButton;
    private JButton createAdminAccountButton;
    private JPanel spacer;


    public LoginGUI() {
        super();

        setTitle("Login");;
        setSize(500,800);

        add(loginPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getRootPane().setDefaultButton(Login);

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOP loginOP = new LoginOP();

                try {

                    if(loginOP.loginOK(dbConnection.connection, USERNAMETextField.getText(), PASSWORDPasswordField.getText())){
                        if(loginOP.isAdmin(dbConnection.connection, USERNAMETextField.getText())){
                            AdminGUI adminGUI = new AdminGUI();
                            setVisible(false);
                            adminGUI.setVisible(true);
                        }else{

                            UserGUI userGUI = new UserGUI(loginOP.getCNPfromLogin(dbConnection.connection, USERNAMETextField.getText()));
                            setVisible(false);
                            userGUI.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Wrong username or password!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserAccountGUI createUserAccountGUI = new CreateUserAccountGUI();
                setVisible(false);
                createUserAccountGUI.setVisible(true);
            }
        });

        createAdminAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAdminLoginGUI createAdminLoginGUI = new CreateAdminLoginGUI();
                setVisible(false);
                createAdminLoginGUI.setVisible(true);
            }
        });


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
