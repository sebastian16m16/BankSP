package csc_1_Software_Design.PresentationLayer;

import javax.swing.*;

public class AdminGUI extends JFrame{

    private JTable resultsTable;
    private JTextArea searchArea;
    private JButton modifyButton;
    private JButton searchButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JPanel adminPanel;

    public AdminGUI(){
        setTitle("Logged in as Admin");
        setSize(800, 800);
        add(adminPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}
