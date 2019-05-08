package csc_1_Software_Design.PresentationLayer.UserExtendFrames;

import csc_1_Software_Design.BusinessLayer.AdminOp;
import csc_1_Software_Design.BusinessLayer.UserOp;
import csc_1_Software_Design.PresentationLayer.AdminGUI;
import csc_1_Software_Design.PresentationLayer.CreateAdminLoginGUI;
import csc_1_Software_Design.PresentationLayer.UserGUI;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateMoneyAccountUserGUI extends JFrame{


    String cnp;
    private JButton createAccountButton;
    private JTextField typeField;
    private JButton backButton;
    private JLabel cnpLabel;
    private JTextField initialBalanceField;
    private JPanel createAccountPanelUser;

    public CreateMoneyAccountUserGUI(final String cnp) throws SQLException {
        super();
        this.cnp = cnp;

        setSize(300,500);
        setTitle("Create Money Account");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(createAccountButton);

        add(createAccountPanelUser);

        cnpLabel.setText(cnp);

        final UserGUI userGUI = new UserGUI(cnp);
        final UserOp userOp = new UserOp();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userGUI.setVisible(true);
                setVisible(false);
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                        if (userOp.numberOfMoneyAccountsOnCLIENT(cnp) < 5) {
                            userOp.createAccount(cnp, typeField.getText(), Double.parseDouble(initialBalanceField.getText()));
                            JOptionPane.showMessageDialog(null, "Account created successfully!");
                             UserGUI userGUIUpdated = new UserGUI(cnp);

                            userGUIUpdated.setVisible(true);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "This client reached the limit of OPENED ACCOUNTS!");
                            userGUI.setVisible(true);
                            setVisible(false);
                        }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


    }
}
