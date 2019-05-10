package csc_1_Software_Design.PresentationLayer.AdminExtendFrames;

import csc_1_Software_Design.BusinessLayer.AdminOp;
import csc_1_Software_Design.PresentationLayer.AdminGUI;
import csc_1_Software_Design.PresentationLayer.CreateAdminLoginGUI;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateMoneyAccountGUI extends JFrame{
    public JPanel createAccountPanel;
    private JTextField cnpField;
    private JTextField typeField;
    private JTextField initialBalanceField;
    private JButton createAccountButton;
    private JButton backButton;

    public CreateMoneyAccountGUI(){

        super();
        setSize(300,500);
        setTitle("Create Money Account");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(createAccountButton);

        add(createAccountPanel);
        final AdminGUI adminGUI = new AdminGUI();
        final AdminOp adminOp = new AdminOp();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminGUI.setVisible(true);
                setVisible(false);
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(adminOp.existsClientByCNP(cnpField.getText())) {
                        if (adminOp.numberOfMoneyAccountsOnCLIENT(cnpField.getText()) < 5) {
                            adminOp.createAccount(cnpField.getText(), typeField.getText(), Double.parseDouble(initialBalanceField.getText()));
                            JOptionPane.showMessageDialog(null, "Account created successfully!");
                            adminGUI.setVisible(true);
                            setVisible(false);
                        } else
                            JOptionPane.showMessageDialog(null, "This client reached the limit of OPENED ACCOUNTS!");
                    }else
                        JOptionPane.showMessageDialog(null, "There is no Client with this CNP!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


    }
}
