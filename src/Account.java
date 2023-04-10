import java.util.ArrayList;

public class Account {

    // Name of the account.
    private String name;

    // The account ID number;
    private String uuid;

    private User holder;

    private ArrayList<Transaction> transactions;

    /**
     *  Create a new Account
     * @param name      the name of the account
     * @param holder    the User obj. that holds this account
     * @param theBank   the bank that issues this account
     */
    public Account(String name, User holder, Bank theBank) {
        this.name = name;
        this.holder = holder;

        // get a new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // init transactions
        this.transactions = new ArrayList<Transaction>();
        
    }

    public String getUUID() {
        return this.uuid;
    }


    /**
     *  Get summary line for the account
     * @return  the string summary
     */
    public String getSummaryLine() {

        // get the account's ballance
        double balance = this.getBalance();

        // format the summary line, deppending on the whether the balance is negative
        if(balance >= 0){
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;
        for( Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }


    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size()-1; t >=0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {

        // create new transaction obj and add to our list
        Transaction newTrans = new Transaction(amount, memo,this);
        this.transactions.add(newTrans);
    }
}
