package csc_1_Software_Design.PresentationLayer;

import csc_1_Software_Design.BusinessLayer.UserOp;
import csc_1_Software_Design.DataLayer.Account;
import csc_1_Software_Design.DataLayer.Client;
import csc_1_Software_Design.PresentationLayer.UserExtendFrames.CreateMoneyAccountUserGUI;
import csc_1_Software_Design.PresentationLayer.UserExtendFrames.UpdateClientUserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserGUI extends JFrame {
    public JPanel userPanel;
    private JButton updatePersonalInformationButton;
    private JButton updateLoginInformationButton;
    private JLabel acc1TypeField;
    private JLabel acc2TypeField;
    private JLabel acc3TypeField;
    private JLabel acc4TypeField;
    private JLabel acc5TypeField;
    private JLabel balance1Field;
    private JLabel balance2Field;
    private JLabel balance3Field;
    private JLabel balance4Field;
    private JLabel balance5Field;
    private JLabel cniInfoField;
    private JLabel cnpInfoField;
    private JLabel lastNameField;
    private JLabel firstNameField;
    private JLabel addressField;
    private JButton topUPButton;
    private JButton transferButton;
    private JButton createAccountButton;
    private JButton deleteAccountButton;
    private JButton refreshButton;
    private JButton backToLoginButton;
    private JLabel id1;
    private JLabel id5;
    private JLabel id4;
    private JLabel id3;
    private JLabel id2;

    String cnp;

    public String getCnp() {
        return cnp;
    }

    public UserGUI(final String cnp) throws SQLException {
        super();
        this.cnp = cnp;


        add(userPanel);
        final UserOp userOp = new UserOp();

        setTitle("Logged in as: "+ userOp.getFullClientName(cnp));
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        ArrayList<Account> availableAccount = userOp.getClientAccount(cnp);
        ArrayList<JLabel> accountTypes = new ArrayList<>();
        accountTypes.add(acc1TypeField);
        accountTypes.add(acc2TypeField);
        accountTypes.add(acc3TypeField);
        accountTypes.add(acc4TypeField);
        accountTypes.add(acc5TypeField);

        ArrayList<JLabel> accountsBalances = new ArrayList<>();
        accountsBalances.add(balance1Field);
        accountsBalances.add(balance2Field);
        accountsBalances.add(balance3Field);
        accountsBalances.add(balance4Field);
        accountsBalances.add(balance5Field);

        ArrayList<JLabel> accountIDs = new ArrayList<>();
        accountIDs.add(id1);
        accountIDs.add(id2);
        accountIDs.add(id3);
        accountIDs.add(id4);
        accountIDs.add(id5);

        for(int i=0; i< 5; i++){
            accountsBalances.get(i).setText("");
            accountTypes.get(i).setText("");
            accountIDs.get(i).setText("");
        }

        for(int i=0; i<availableAccount.size(); i++){
            accountTypes.get(i).setText(availableAccount.get(i).getType());
            accountsBalances.get(i).setText(Double.toString(availableAccount.get(i).getBalance()));
            accountIDs.get(i).setText(Integer.toString(availableAccount.get(i).getAccount_id()));

        }

        final Client thisClient = userOp.getClientByCNP(cnp);

        cnpInfoField.setText(thisClient.getCnp());
        cniInfoField.setText(thisClient.getCni());
        lastNameField.setText(thisClient.getLast_name());
        firstNameField.setText(thisClient.getFirst_name());
        addressField.setText(thisClient.getAddress());


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    CreateMoneyAccountUserGUI createMoneyAccountUserGUI = new CreateMoneyAccountUserGUI(cnp);
                    createMoneyAccountUserGUI.setVisible(true);
                    setVisible(false);
                }catch (SQLException ex){
                    ex.printStackTrace();
                }


            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Object response = JOptionPane.showInputDialog(null,
                            "Which account do you want to delete?", "DELETE process!",
                            JOptionPane.QUESTION_MESSAGE, null, userOp.getTypesOfAccountsFromClient(cnp, ""),
                            userOp.getTypesOfAccountsFromClient(cnp, "")[0]);
                    if(userOp.numberOfMoneyAccountsOnCLIENT(cnp) > 1) {
                        if (userOp.getBalanceOfAccountByTypeAndCNP(response.toString(), cnp) > 0) {
                            Object response1 = JOptionPane.showInputDialog(null,
                                    "This account is not empty!\n In which account do you want the rest of the money?", "DELETE ALERT!",
                                    JOptionPane.QUESTION_MESSAGE, null, userOp.getTypesOfAccountsFromClient(cnp, response.toString()),
                                    userOp.getTypesOfAccountsFromClient(cnp, response.toString())[0]);

                            userOp.transferAllFromOneTypeToAnother(response.toString(), response1.toString());
                            userOp.deleteAccount(userOp.getAccountIDbyType(response.toString(), cnp));


                            JOptionPane.showMessageDialog(null, "Account deleted!");
                            UserGUI update = new UserGUI(cnp);
                            update.setVisible(true);
                            setVisible(false);
                        } else if (userOp.getBalanceOfAccountByTypeAndCNP(response.toString(), cnp) == 0) {
                            userOp.deleteAccount(userOp.getAccountIDbyType(response.toString(), cnp));

                            JOptionPane.showMessageDialog(null, "Account deleted!");
                            UserGUI update = new UserGUI(cnp);
                            update.setVisible(true);
                            setVisible(false);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "This is the last account!\n Only an administrator can delete the last Account!");

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserGUI update = new UserGUI(cnp);
                    update.setVisible(true);
                    setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        topUPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object response = JOptionPane.showInputDialog(null,
                            "Which account do you want to TopUp?", "TopUp process!",
                            JOptionPane.QUESTION_MESSAGE, null, userOp.getTypesOfAccountsFromClient(cnp, ""),
                            userOp.getTypesOfAccountsFromClient(cnp, "")[0]);
                    if(response != null) {
                        double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "How much money do you want to TopUp?"));
                        userOp.topUp(userOp.getAccountIDbyType(response.toString(), cnp), amount);
                        JOptionPane.showMessageDialog(null, "Account updated!");

                        UserGUI update = new UserGUI(cnp);
                        update.setVisible(true);
                        setVisible(false);

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        updateLoginInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object response = JOptionPane.showInputDialog(null,
                            "What do you want to update?", "Login Update!",
                            JOptionPane.QUESTION_MESSAGE, null, new String[]{"Username", "Password", "Both"},
                            "Username");
                    if (response == "Username") {
                        String newUsername = JOptionPane.showInputDialog(null, "Insert new Username!");
                        userOp.updateUsernameByCNP(newUsername, cnp);
                        JOptionPane.showMessageDialog(null, "Username updated!");
                    }else if(response == "Password"){
                        String newPassword = JOptionPane.showInputDialog(null, "Insert new Password!");
                        userOp.updatePasswordByCNP(newPassword, cnp);
                        JOptionPane.showMessageDialog(null, "Password Updated!");
                    }else if(response == "Both"){
                        String newUsername = JOptionPane.showInputDialog(null, "Insert new Username!");
                        userOp.updateUsernameByCNP(newUsername, cnp);
                        String newPassword = JOptionPane.showInputDialog(null, "Insert new Password!");
                        userOp.updatePasswordByCNP(newPassword, cnp);
                        JOptionPane.showMessageDialog(null, "Username and Password updated!");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

        updatePersonalInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateClientUserGUI updateClientUserGUI = new UpdateClientUserGUI(thisClient);
                updateClientUserGUI.setVisible(true);
                setVisible(false);
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

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sender_id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the SENDER account!"));
                    double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the amount you want to transfer!"));
                    int receiver_id = Integer.parseInt(JOptionPane.showInputDialog(null, "Where do you want to send the money?\n" +
                            "Enter the ID:"));
                    if (userOp.existsAccountByID(receiver_id)) {
                        //Intreaba daca acesta e receiver-ul
                        Client receiver = userOp.getClientByCNP(userOp.getAccountCNPFromAccountID(receiver_id));
                        Object response = JOptionPane.showInputDialog(null, "Is this the name of the Receiver?\n\n Last Name: " +
                                receiver.getLast_name() + "\n First Name: " + receiver.getFirst_name(), "Confirmation",JOptionPane.QUESTION_MESSAGE, null,
                                new String[]{"Yes", "No"}, "No");
                        if(response == "Yes"){
                            userOp.transferMoney(sender_id, receiver_id, amount);
                            JOptionPane.showMessageDialog(null, "Transfer was successful!");
                            UserGUI update = new UserGUI(cnp);
                            update.setVisible(true);
                            setVisible(false);
                        }

                    }else
                        JOptionPane.showMessageDialog(null, "The RECEIVER doesn't exist!");
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }
}
