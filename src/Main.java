import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Transaction> transactionList = new ArrayList<>();
    static TransactionList transactions = new TransactionList(transactionList, 0);
    static ArrayList<Coin> coinList = new ArrayList<>();
    static CoinList coins = new CoinList(coinList, 0);

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Cryptofolio app = new Cryptofolio();
        app.menuPrincipal(scan);
    }
}
