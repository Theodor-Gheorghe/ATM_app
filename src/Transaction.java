import java.util.Date;

public class Transaction {

    private double amount;

    // The time and date of this transaction.
    private Date timeStamp;

    // A memeo for this transaction.
    private String memo;

    // The account in wich the transaction was performed.
    private Account inAccount;


    /**
     * Create a new transaction
     * @param amount    the amount transacted
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timeStamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction
     * @param amount    the amount transacted
     * @param memo      the memo for the transaction
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount) {
       // call the two-arg const. first
        this(amount, inAccount);
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string summarizing the transaction
     * @return  the summary string
     */
    public String getSummaryLine(){
        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s", this.timeStamp.toString(), this.amount, this.memo);
        }else {
            return String.format("%s : $(%.02f) : %s", this.timeStamp.toString(), this.amount, this.memo);
        }
    }
}
