package Main.Finance;

public class Transaction {
    double amount;
    long duration;
    TRs type;

    public Transaction(double amount, long duration, TRs type){
        this.amount = amount;
        this.duration = duration;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public long getDuration() {
        return duration;
    }

    public TRs getType() {
        return type;
    }
}
