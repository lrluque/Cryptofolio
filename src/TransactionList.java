import java.util.List;
import java.util.Scanner;

public class TransactionList {
    private List<Transaction> transactions;
    public int transactionCount;

    public TransactionList(List<Transaction> transactions, int transactionCount) {
        this.transactions = transactions;
        this.transactionCount = transactionCount;
    }

    public List<Transaction> getTransaction() {return transactions;}


    public void printAllTransactions() {
        System.out.printf("\n%-22s %-22s %-22s %-22s", "COIN NAME", "CURRENT PRICE", "AMOUNT", "BALANCE($)");
        for (int i = 0; i < transactionCount; i++){
            Transaction transaction = transactions.get(i);
            System.out.printf("\n%-22s %-22.2f %-22.2f %-22.2f", transaction.coin.coinName, transaction.coin.coinPrice, transaction.getCoinAmount(), transaction.getBalance());
        }
        System.out.println("\n\nPress ENTER to continue...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        transactionCount++;
    }


}
