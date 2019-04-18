package csc_1_Software_Design.PresentationLayer;

import javax.swing.*;

public class CreateAdminLoginGUI extends JFrame{
    private JTextField textField1;
    private JButton createAccountButton;
    private JPasswordField passwordField1;
    private JPanel createAdminLoginPanel;

    public CreateAdminLoginGUI(){
        setTitle("Create Admin Login");
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
