package Main.Finance;

import Main.AllTransaction;
import Main.BankController;
import Main.FMController;
import Main.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class BankAccount {
    public static ArrayList<String> a = new ArrayList<>();
    public static ListView<String> listView;
    public static ListView<String> transaction;
    Person client;
    ArrayList<Transaction> allTransactions;
    double balance;
    String username;
    String password;
    long timeRemaining;
    public Bank branch;

    public BankAccount(Person p, String user, String pass, double cash, Bank bank) {
        client = p;
        username = user;
        password = pass;
        balance = cash;
        allTransactions = new ArrayList<>();
        branch = bank;
        timeRemaining = 60000;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (timeRemaining > 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        timeRemaining -= 1000;
                    }
                    timeRemaining = 60000;
                    if(FMController.onScreen) {
                        a.add("Username: " + username + "    Interest Amount: " + (balance * 0.18) + "    City: " + client.city.cityName + "    Bank: " + branch.bankName);
                        listView.getItems().clear();
                        listView.getItems().addAll(a);
                    }
                    if(AllTransaction.tr) {
                        ArrayList<String> list = new ArrayList<>();
                        for (Transaction tr : BankController.bankAccount.getAllTransactions()) {
                            list.add("Amount: " + tr.getAmount() + "   Type: " + tr.getType().toString() + "   Duration: " + tr.getDuration());
                        }
                        ObservableList<String> ol  = FXCollections.observableArrayList(list);
                        transaction.setItems(ol);
                    }
                    allTransactions.add(new Transaction((balance * 0.18), 60000, TRs.Interest));
                    branch.setAllMoney(branch.getAllMoney() + (balance * 0.18));
                    balance += (balance * 0.18);
                }
            }
        }).start();
    }

    public Person getClient() {
        return client;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
