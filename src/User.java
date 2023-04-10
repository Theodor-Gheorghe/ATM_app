import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;


public class User {

    private String firstName;
    private String lastName;

    // Universally Unique IDentifier (the id number of the user).
    private String uuid;

    // The MD5 hash of the user's pin number.
    private byte pinHash[];

    // A list of accounts for the users.
    private ArrayList<Account> accounts;


    /**
     * Create a new user
     *
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param pin       user's account pin number
     * @param theBnak   the Bank obj. that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBnak) {
        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's MD5 hash, rather than the original value, for security reasons.
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new uuid for the user
        this.uuid = theBnak.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);

    }

    // add an account for the user
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    public String getUUID() {
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the true User pin
     *
     * @param aPin the pin to check
     * @return wheter the pin is valid or not
     */

    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;

    }

    public String getFirstName() {
        return this.firstName;
    }

    // Print summaries for the accounts of this user
    public void printAccountsSUmmary() {

        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++){
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();

    }

    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account.
     * @param acctIdx   the index of the account to use
     */
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acttIdx   the index of the account to use
     * @return          the UUID of the account
     */
    public String getAcctUUID(int acttIdx){
        return this.accounts.get(acttIdx).getUUID();
    }

    /**
     * Add a transaction to a particular account
     * @param acctIdx   the index of the account
     * @param amount    the amount of the transaction
     * @param memo      the memo of the transaction
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
