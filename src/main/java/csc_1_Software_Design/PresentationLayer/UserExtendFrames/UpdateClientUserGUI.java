package csc_1_Software_Design.PresentationLayer.UserExtendFrames;

import csc_1_Software_Design.BusinessLayer.UserOp;
import csc_1_Software_Design.DataLayer.Client;
import csc_1_Software_Design.PresentationLayer.UserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateClientUserGUI extends JFrame{
    public JPanel updateClientPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField addressField;
    private JLabel cnpLabel;
    private JLabel cniLabel;
    private JButton updateButton;
    private JButton backButton;
    String cnp;
    String cni;

    public UpdateClientUserGUI(Client thisClient){
        super();
        this.cnp = thisClient.getCnp();
        this.cni = thisClient.getCni();

        add(updateClientPanel);
        setTitle("Update Personal Information");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(updateButton);

        cnpLabel.setText(cnp);
        cniLabel.setText(cni);


        lastNameField.setText(thisClient.getLast_name());
        firstNameField.setText(thisClient.getFirst_name());
        addressField.setText(thisClient.getAddress());

        final UserOp userOp = new UserOp();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    UserGUI userGUI = new UserGUI(cnp);
                    userGUI.setVisible(true);
                    setVisible(false);
                }catch (SQLException e1){
                    e1.printStackTrace();
                }

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!lastNameField.getText().equals("") && !firstNameField.getText().equals("") && !addressField.getText().equals("")) {
                    Object response = JOptionPane.showInputDialog(null,
                            "Are you sure? \n ", "Update ALERT!",
                            JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "NO"},
                            "NO");

                    if (response == "Yes") {
                        try {
                            userOp.updateClient(cnp, firstNameField.getText(), lastNameField.getText(), cni, addressField.getText());
                            JOptionPane.showMessageDialog(null, "Personal Information Updated!");
                            UserGUI userGUI = new UserGUI(cnp);
                            userGUI.setVisible(true);
                            setVisible(false);

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Please fill all blank fields!");
            }
        });

    }
}
