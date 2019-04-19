package csc_1_Software_Design.PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

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

        //table setup
        Font font = new Font("", 1, 20);
        resultsTable.setFont(font);
        String[] columns = {"name", "age", "address"};
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columns);
        resultsTable.setModel(defaultTableModel);
        resultsTable.setRowHeight(30);
        resultsTable.setBackground(Color.gray);
        resultsTable.setForeground(Color.white);

    }
}
