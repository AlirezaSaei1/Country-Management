package Main.Finance;

import java.util.ArrayList;

public class Bank {
    ArrayList<BankAccount> accounts;
    public String bankName;
    double allMoney;
    int activeAccounts;
    public Bank(String bankName){
        this.bankName = bankName;
        accounts = new ArrayList<>();
        allMoney = 0;
        activeAccounts = 0;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public double getAllMoney() {
        return allMoney;
    }

    public int getActiveAccounts() {
        return activeAccounts;
    }

    public void setAllMoney(double allMoney) {
        this.allMoney = allMoney;
    }

    public void setActiveAccounts(int activeAccounts) {
        this.activeAccounts = activeAccounts;
    }
}
