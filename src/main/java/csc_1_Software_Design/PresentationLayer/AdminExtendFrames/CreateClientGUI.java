package csc_1_Software_Design.PresentationLayer.AdminExtendFrames;

import csc_1_Software_Design.BusinessLayer.AdminOp;
import csc_1_Software_Design.DataLayer.Client;
import csc_1_Software_Design.PresentationLayer.AdminGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateClientGUI extends JFrame {
    public JPanel createClientPanel;
    private JButton createClientButton;
    private JTextField cnpField;
    private JTextField cniField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField addressField;
    private JButton backButton;

    public CreateClientGUI(){
        super();
        setSize(400, 500);
        setTitle("Create new Client");
        add(createClientPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(createClientButton);

        final AdminGUI adminGUI = new AdminGUI();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminGUI.setVisible(true);
                setVisible(false);
            }
            });

        createClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminOp adminOp = new AdminOp();

                try {
                    if(!adminOp.existsClientByCNP(cnpField.getText())){

                        Client client = new Client();
                        client.setCnp(cnpField.getText());
                        client.setCni(cniField.getText());
                        client.setLast_name(lastNameField.getText());
                        client.setFirst_name(firstNameField.getText());
                        client.setAddress(addressField.getText());
                        client.setCnpAdmin(cnpField.getText());
                        adminOp.insertClient(client);
                        JOptionPane.showMessageDialog(null, "Client created successfully!");
                        adminGUI.setVisible(true);
                        setVisible(false);

                    }else
                        JOptionPane.showMessageDialog(null, "This client already exists!");
                        adminGUI.setVisible(true);
                        setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
