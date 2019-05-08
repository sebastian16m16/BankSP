package csc_1_Software_Design.PresentationLayer;

import com.sun.xml.internal.bind.v2.model.core.ID;
import csc_1_Software_Design.BusinessLayer.AdminOp;
import csc_1_Software_Design.DataLayer.Account;
import csc_1_Software_Design.DataLayer.Client;
import csc_1_Software_Design.DataLayer.Login;
import csc_1_Software_Design.PresentationLayer.AdminExtendFrames.CreateClientGUI;
import csc_1_Software_Design.PresentationLayer.AdminExtendFrames.CreateMoneyAccountGUI;
import csc_1_Software_Design.PresentationLayer.AdminExtendFrames.UpdateClientGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminGUI extends JFrame{

    private JTable resultsTable;
    private JButton insertClientBtn;
    private JButton searchButton;
    private JButton deleteButton;
    private JPanel adminPanel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JRadioButton IDRadioButton;
    private JRadioButton CNPRadioButton;
    private JButton createAccountButton;
    private JButton updateBtn;
    private JButton backToLoginButton;

    public AdminGUI(){
        super();
        setTitle("Logged in as Admin");
        setSize(1300, 800);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(searchButton);
        textField1.requestFocus();

        add(adminPanel);

        //table setup
        final Font font = new Font("", 1, 20);
        final Object[] columns = {"name", "age", "address"};

        final DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);
        resultsTable.setModel(tableModel);
        resultsTable.setRowHeight(30);
        resultsTable.setBackground(Color.gray);
        resultsTable.setForeground(Color.green);
        resultsTable.setFont(font);
        IDRadioButton.setSelected(true);

        IDRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDRadioButton.setSelected(true);
                CNPRadioButton.setSelected(false);
            }
        });

        CNPRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CNPRadioButton.setSelected(true);
                IDRadioButton.setSelected(false);
            }
        });

        final AdminOp adminOp = new AdminOp();


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMoneyAccountGUI createMoneyAccountGUI = new CreateMoneyAccountGUI();
                createMoneyAccountGUI.setVisible(true);
                setVisible(false);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Search clients
                if(comboBox1.getSelectedIndex()==0 && IDRadioButton.isSelected()){


                    Object[] clientInfo = {"ID", "CNP", "CNI", "Last Name", "First Name", "Address"};
                    Object[] row = new Object[6];
                    tableModel.setRowCount(0);


                    try {
                        if (adminOp.existsClientByID(Integer.parseInt(textField1.getText()))) {
                            try {

                                Client client = adminOp.getClientByID(Integer.parseInt(textField1.getText()));

                                row[0] = client.getClient_id();
                                row[1] = client.getCnp();
                                row[2] = client.getCni();
                                row[3] = client.getLast_name();
                                row[4] = client.getFirst_name();
                                row[5] = client.getAddress();

                                tableModel.setColumnIdentifiers(clientInfo);
                                tableModel.addRow(row);
                                resultsTable.setModel(tableModel);


                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }else
                            JOptionPane.showMessageDialog(null, "Client not found!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(comboBox1.getSelectedIndex()==0 && CNPRadioButton.isSelected()){


                    Object[] clientInfo = {"ID", "CNP", "CNI", "Last Name", "First Name", "Address"};
                    Object[] row = new Object[6];
                    tableModel.setRowCount(0);


                    try {
                        if (adminOp.existsClientByCNP(textField1.getText())) {
                            try {

                                Client client = adminOp.getClientByCNP(textField1.getText());

                                row[0] = client.getClient_id();
                                row[1] = client.getCnp();
                                row[2] = client.getCni();
                                row[3] = client.getLast_name();
                                row[4] = client.getFirst_name();
                                row[5] = client.getAddress();

                                tableModel.setColumnIdentifiers(clientInfo);
                                tableModel.addRow(row);
                                resultsTable.setModel(tableModel);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }else
                            JOptionPane.showMessageDialog(null, "Client not found!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                //Search accounts
                else if(comboBox1.getSelectedIndex() == 1 && IDRadioButton.isSelected()){ // Bank accounts search

                    Object[] accountInfo = {"Account_id", "CNP", "Balance", "Type", "Created Date"};
                    Object[] row = new Object[5];
                    tableModel.setRowCount(0);


                    try {
                        if(adminOp.existsAccountByID(Integer.parseInt(textField1.getText()))){

                            Account foundAccount = adminOp.getAccountByID(Integer.parseInt(textField1.getText()));
                            row[0] = foundAccount.getAccount_id();
                            row[1] = foundAccount.getCnpAdmin();
                            row[2] = foundAccount.getBalance();
                            row[3] = foundAccount.getType();
                            row[4] = foundAccount.getCreatedDate();

                            tableModel.setColumnIdentifiers(accountInfo);
                            tableModel.addRow(row);
                            resultsTable.setModel(tableModel);

                        }else
                            JOptionPane.showMessageDialog(null, "Account doesn't exist!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }
                else if(comboBox1.getSelectedIndex() == 1 && CNPRadioButton.isSelected()){
                    Object[] accountInfo = {"Account_id", "CNP", "Balance", "Type", "Created Date"};
                    Object[] row = new Object[5];
                    tableModel.setColumnIdentifiers(accountInfo);
                    tableModel.setRowCount(0);
                    resultsTable.setModel(tableModel);

                    try {
                        if(adminOp.existsAccountByCNP(textField1.getText())){

                                ArrayList<Account> foundAccounts = adminOp.getAccountByCNP(textField1.getText());
                                for(int i=0; i < foundAccounts.size(); i++) {
                                    row[0] = foundAccounts.get(i).getAccount_id();
                                    row[1] = foundAccounts.get(i).getCnpAdmin();
                                    row[2] = foundAccounts.get(i).getBalance();
                                    row[3] = foundAccounts.get(i).getType();
                                    row[4] = foundAccounts.get(i).getCreatedDate();

                                    tableModel.addRow(row);

                                }



                        }else
                            JOptionPane.showMessageDialog(null, "This client doesn't have any Accounts!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                //Search Logins
                else if(comboBox1.getSelectedIndex() == 2 && IDRadioButton.isSelected()){

                    Object[] loginInfo = {"Login_ID", "Username", "Password", "Administrator", "CNP"};
                    Object[] row = new Object[5];
                    tableModel.setRowCount(0);
                    tableModel.setColumnIdentifiers(loginInfo);
                    resultsTable.setModel(tableModel);

                    try {
                        if(adminOp.existsLoginByID(Integer.parseInt(textField1.getText()))){
                            Login login = adminOp.getLoginByID(Integer.parseInt(textField1.getText()));

                            row[0] = login.getLogin_id();
                            row[1] = login.getUsername();
                            row[2] = login.getPassword();
                            row[3] = login.getAdministrator();
                            row[4] = login.getCnp();

                            tableModel.addRow(row);

                        }else
                            JOptionPane.showMessageDialog(null, "Login not found!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(comboBox1.getSelectedIndex() == 2 && CNPRadioButton.isSelected()){

                    try {

                        Object[] loginInfo = {"Login_ID", "Username", "Password", "Administrator", "CNP"};
                        Object[] row = new Object[5];
                        tableModel.setRowCount(0);
                        tableModel.setColumnIdentifiers(loginInfo);
                        resultsTable.setModel(tableModel);


                        if(adminOp.existsLoginByCNP(textField1.getText())){

                          Login login = adminOp.getLoginByCNP(textField1.getText());

                            row[0] = login.getLogin_id();
                            row[1] = login.getUsername();
                            row[2] = login.getPassword();
                            row[3] = login.getAdministrator();
                            row[4] = login.getCnp();

                            tableModel.addRow(row);

                        }else
                            JOptionPane.showMessageDialog(null, "Login not found!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        insertClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOPTIONPANE time    SAU     NEW FRAME
                CreateClientGUI createClientGUI = new CreateClientGUI();
                createClientGUI.setVisible(true);
                setVisible(false);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedIndex()==1){
                    CNPRadioButton.setSelected(false);
                    IDRadioButton.setSelected(true);
                }

                if(comboBox1.getSelectedIndex() == 0){
                    try {
                        String cnp = JOptionPane.showInputDialog(null, "Insert the CNP of the client you want to DELETE!");
                        if(adminOp.existsClientByCNP(cnp)){

                            Object response = JOptionPane.showInputDialog(null,
                                    "Are you sure?\n This will delete everything about the client!", "DELETE ALERT!",
                                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "NO" },
                                    "NO");
                            if(response == "Yes"){
                                adminOp.deleteClient(cnp);
                                JOptionPane.showMessageDialog(null, "Client deleted alongside the Accounts and the Login!");
                            }

                        }else
                            JOptionPane.showMessageDialog(null, "This client does not exist!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

                if(comboBox1.getSelectedIndex() == 1){
                    int accountID = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the ID of the Account you want to DELETE!"));


                    try {
                        if(adminOp.existsAccountByID(accountID)){
                            if(adminOp.numberOfMoneyAccountsOnCLIENT(adminOp.getAccountCNPbyID(accountID))==1){
                                Object response = JOptionPane.showInputDialog(null,
                                        "Are you sure? \n This is the last account from this client!", "DELETE ALERT!",
                                        JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "NO"},
                                        "NO");
                                if (response == "Yes" && adminOp.getBalanceFromAccountByID(accountID) > 0){
                                    Object response2 = JOptionPane.showInputDialog(null,
                                            "There are still money on this account!\n Are you sure?", "DELETE ALERT!",
                                            JOptionPane.QUESTION_MESSAGE,null, new String[]{"Yes", "NO"},
                                            "NO");
                                    if(response2 == "Yes"){
                                        adminOp.deleteAccountByID(accountID);
                                        JOptionPane.showMessageDialog(null, "Account deleted!");
                                    }

                                }else if(response == "Yes" && adminOp.getBalanceFromAccountByID(accountID) == 0) {
                                    adminOp.deleteAccountByID(accountID);
                                    JOptionPane.showMessageDialog(null, "Account deleted!");
                                }

                            }
                            else {

                                    Object response3 = JOptionPane.showInputDialog(null,
                                        "Are you sure? \n ", "DELETE ALERT!",
                                        JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "NO"},
                                        "NO");

                                    if (response3 == "Yes" && adminOp.getBalanceFromAccountByID(accountID) > 0) {
                                        Object response2 = JOptionPane.showInputDialog(null,
                                                "Where do you want to transfer the remaining money from this account?", "DELETE ALERT!",
                                                JOptionPane.QUESTION_MESSAGE, null, adminOp.getTypesOfAccountsFromClient(adminOp.getAccountCNPbyID(accountID), adminOp.getAccountTypeByID(accountID)),
                                                adminOp.getTypesOfAccountsFromClient(adminOp.getAccountCNPbyID(accountID), adminOp.getAccountTypeByID(accountID))[0]);

                                        adminOp.transferAllFromOneTypeToAnother(adminOp.getAccountTypeByID(accountID), response2.toString());
                                        adminOp.deleteAccountByID(accountID);
                                        JOptionPane.showMessageDialog(null, "Account deleted!");

                                    }else if(response3 == "Yes" && adminOp.getBalanceFromAccountByID(accountID) == 0){
                                        adminOp.deleteAccountByID(accountID);
                                        JOptionPane.showMessageDialog(null, "Account Deleted!");
                                    }
                            }
                        }else
                            JOptionPane.showMessageDialog(null, "No such account!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (IDRadioButton.isSelected() && comboBox1.getSelectedIndex()==0) {
                        if (adminOp.existsClientByID(Integer.parseInt(textField1.getText()))) {
                            UpdateClientGUI updateClientGUI = new UpdateClientGUI(Integer.parseInt(textField1.getText()));
                            updateClientGUI.setVisible(true);
                            setVisible(false);
                        } else
                            JOptionPane.showMessageDialog(null, "The client does not EXIST!");

                    } else if (CNPRadioButton.isSelected() && comboBox1.getSelectedIndex() == 0) {
                        if (adminOp.existsClientByCNP(textField1.getText())) {
                            UpdateClientGUI updateClientGUI = new UpdateClientGUI(textField1.getText());
                            updateClientGUI.setVisible(true);
                            setVisible(false);
                        } else
                            JOptionPane.showMessageDialog(null, "The client does not EXIST!");

                    } else if (textField1.getText() == null && comboBox1.getSelectedIndex() == 0)
                        JOptionPane.showMessageDialog(null, "Enter an ID or a CNP in the text area!");
                    //Modify accounts
                    else if(comboBox1.getSelectedIndex()==1){
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the account you want to update!"));
                        adminOp.updateAccountType(id, JOptionPane.showInputDialog("Enter the new type for the account!"));
                    }
                    else if(comboBox1.getSelectedIndex() == 2 && IDRadioButton.isSelected()){
                       int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the login you want to update!"));
                       if(adminOp.existsLoginByID(id)) {
                           Object response1 = JOptionPane.showInputDialog(null,
                                   "What do you want to update?", "Update ALERT!",
                                   JOptionPane.QUESTION_MESSAGE, null, new String[]{"Username", "Password", "Both"},
                                   "Username");
                           if (response1 == "Username") {
                               adminOp.updateUsernameByID(JOptionPane.showInputDialog("Enter new Username!"), id);
                               JOptionPane.showMessageDialog(null, "Username Updated!");
                           } else if (response1 == "Password") {
                               adminOp.updatePasswordByID(JOptionPane.showInputDialog("Enter new Password!"), id);
                               JOptionPane.showMessageDialog(null, "Password Updated!");
                           } else if (response1 == "Both") {
                               adminOp.updateUsernameByID(JOptionPane.showInputDialog("Enter new Username!"), id);
                               adminOp.updatePasswordByID(JOptionPane.showInputDialog("Enter new Password!"), id);
                               JOptionPane.showMessageDialog(null, "Username and password Updated!");
                           }
                       }else
                           JOptionPane.showMessageDialog(null, "The login with id: " + id + " does't exist!");
                    }else if(comboBox1.getSelectedIndex() == 2 && CNPRadioButton.isSelected()){
                        String cnp = (JOptionPane.showInputDialog(null, "Enter the CNP of the login you want to update!"));
                        if(adminOp.existsLoginByCNP(cnp)){
                            Object response1 = JOptionPane.showInputDialog(null,
                                    "What do you want to update?", "Update ALERT!",
                                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"Username", "Password", "Both"},
                                    "Username");
                            if (response1 == "Username") {
                                adminOp.updateUsernameByCNP(JOptionPane.showInputDialog("Enter new Username!"), cnp);
                                JOptionPane.showMessageDialog(null, "Username Updated!");
                            } else if (response1 == "Password") {
                                adminOp.updatePasswordByCNP(JOptionPane.showInputDialog("Enter new Password!"), cnp);
                                JOptionPane.showMessageDialog(null, "Password Updated!");
                            } else if (response1 == "Both") {
                                adminOp.updateUsernameByCNP(JOptionPane.showInputDialog("Enter new Username!"), cnp);
                                adminOp.updatePasswordByCNP(JOptionPane.showInputDialog("Enter new Password!"), cnp);
                                JOptionPane.showMessageDialog(null, "Username and password Updated!");
                            }
                        }else
                            JOptionPane.showMessageDialog(null, "The login with CNP: " + cnp + " doesn't exist!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
                setVisible(false);
            }
        });



    }


}
