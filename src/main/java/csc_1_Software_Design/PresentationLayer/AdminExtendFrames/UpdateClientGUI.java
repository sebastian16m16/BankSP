package csc_1_Software_Design.PresentationLayer.AdminExtendFrames;

import csc_1_Software_Design.BusinessLayer.AdminOp;
import csc_1_Software_Design.PresentationLayer.AdminGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateClientGUI extends JFrame {


    private JLabel cnpLabel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField addressField;
    private JTextField cniField;
    private JButton backButton;
    private JButton UPDATEModifyButton;
    private JPanel updatePanel;
    String cnp;
    int id;

    public UpdateClientGUI(final String cnp){
        super();
        this.cnp = cnp;


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Update Client");
        setLocationRelativeTo(null);

        add(updatePanel);
        getRootPane().setDefaultButton(UPDATEModifyButton);

        final AdminGUI adminGUI = new AdminGUI();
        final AdminOp adminOp = new AdminOp();
        cnpLabel.setText(cnp);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminGUI.setVisible(true);
                setVisible(false);
            }
        });

        try {
            lastNameField.setText(adminOp.getClientByCNP(cnp).getLast_name());
            firstNameField.setText(adminOp.getClientByCNP(cnp).getFirst_name());
            cniField.setText(adminOp.getClientByCNP(cnp).getCni());
            addressField.setText(adminOp.getClientByCNP(cnp).getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UPDATEModifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object response = JOptionPane.showInputDialog(null,
                        "Are you sure? \n ", "Update ALERT!",
                        JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "NO"},
                        "NO");

                if(response == "Yes"){
                    try {
                        adminOp.updateClient(cnp, firstNameField.getText(), lastNameField.getText(), cniField.getText(), addressField.getText());
                        JOptionPane.showMessageDialog(null, "Client Updated!");
                        adminGUI.setVisible(true);
                        setVisible(false);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


    }

    public UpdateClientGUI(final int id){

        this.id = id;
        getRootPane().setDefaultButton(UPDATEModifyButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Update Client");
        setLocationRelativeTo(null);

        add(updatePanel);

        final AdminGUI adminGUI = new AdminGUI();
        final AdminOp adminOp = new AdminOp();
        try {
            cnpLabel.setText(adminOp.getClientCNPByID(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminGUI.setVisible(true);
                setVisible(false);
            }
        });

        try {
            lastNameField.setText(adminOp.getClientByID(id).getLast_name());
            firstNameField.setText(adminOp.getClientByID(id).getFirst_name());
            cniField.setText(adminOp.getClientByID(id).getCni());
            addressField.setText(adminOp.getClientByID(id).getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UPDATEModifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object response = JOptionPane.showInputDialog(null,
                        "Are you sure? \n ", "Update ALERT!",
                        JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "NO"},
                        "NO");

                if(response == "Yes"){
                    try {
                        adminOp.updateClient(adminOp.getClientCNPByID(id), firstNameField.getText(), lastNameField.getText(), cniField.getText(), addressField.getText());
                        JOptionPane.showMessageDialog(null, "Client Updated!");
                        adminGUI.setVisible(true);
                        setVisible(false);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
