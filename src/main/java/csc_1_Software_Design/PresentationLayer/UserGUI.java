package csc_1_Software_Design.PresentationLayer;

import csc_1_Software_Design.BusinessLayer.UserOp;

import javax.swing.*;
import java.sql.SQLException;

public class UserGUI extends JFrame {
    private JPanel userPanel;
    private JButton updatePersonalInformationButton;
    private JButton updateLoginInformationButton;

    String cnp;

    public UserGUI(String cnp) throws SQLException {
        this.cnp = cnp;
        UserOp userOp = new UserOp(cnp);

        setTitle("Logged in as: "+ userOp.getFullClientName(cnp));
        setSize(1000, 800);
    }
}
