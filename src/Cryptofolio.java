import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cryptofolio {
    public void menuPrincipal(Scanner scan) {
        System.out.println("\n\n\n\n");
        System.out.println(" ██████ ██████  ██    ██ ██████  ████████  ██████  ███████  ██████  ██      ██  ██████  \n" +
                "██      ██   ██  ██  ██  ██   ██    ██    ██    ██ ██      ██    ██ ██      ██ ██    ██ \n" +
                "██      ██████    ████   ██████     ██    ██    ██ █████   ██    ██ ██      ██ ██    ██ \n" +
                "██      ██   ██    ██    ██         ██    ██    ██ ██      ██    ██ ██      ██ ██    ██ \n" +
                " ██████ ██   ██    ██    ██         ██     ██████  ██       ██████  ███████ ██  ██████  \n");
        System.out.print("----------------------------------------------------------------------------------------------------\n");


        int choice = 0;
        do {
            System.out.println("Welcome to Cryptofolio!\n\n1) Add coin\n2) Add transaction\n3) Transaction history\n4) Coins\n5) Save data\n6) Load data\n7) Exit\n");
            try {
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        addCoinMenu(scan);
                        break;
                    case 2:
                        addTransactionMenu(scan);
                        break;
                    case 3:
                        Main.transactions.printAllTransactions();
                        break;
                    case 4:
                        coinsMenu(scan);
                        break;
                    case 5:
                        saveData();
                        break;
                    case 6:
                        System.out.println("This could take a moment...");
                        loadData();
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scan.nextLine();
            }
        } while (choice != 7);

    }

    public void coinsMenu(Scanner scan) {
        Main.coins.showListCoins();
        System.out.println("\nPress ENTER to continue...");
        scan.nextLine();
        scan.nextLine();
    }

    public void addCoinMenu(Scanner scan){
        scan.nextLine();
        String url = null;
        String coinName = null;
        try {
            System.out.println("Coin Name: \n");
            coinName = scan.nextLine();
            do {
                System.out.println("LiveCoinWatch URL: \n");
                url = scan.nextLine();
            } while (!url.startsWith("https://www.livecoinwatch.com/price/"));
        } catch (Exception e) {
            e.printStackTrace();
            scan.nextLine();
        }
        System.out.println("This could take a moment...");
        Coin coin = new Coin(coinName, url);
        Main.coins.addCoin(coin);
    }

    public void addTransactionMenu(Scanner scan) {
        boolean validInput = false;
        if (Main.coins.numCoins == 0) {
            System.out.println("You have to add coins in the first place");
        }else {
            Main.coins.showListCoins();
            scan.nextLine();
            while (!validInput) {
                try {
                    System.out.println("Select coin: \n");
                    int coinInt = scan.nextInt();
                    Coin coin = Main.coins.coins.get(coinInt - 1);
                    System.out.println("Investment ($): \n");
                    double investment = scan.nextDouble();
                    System.out.println("Price bought ($): \n");
                    double priceBought = scan.nextDouble();
                    Transaction transaction = new Transaction(coin, investment, priceBought);
                    Main.transactions.addTransaction(transaction);
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    validInput = false;
                    scan.nextLine();
                }
            }
        }
        System.out.println("Confirmed transaction. Press ENTER to continue...");
        scan.nextLine();
        scan.nextLine();
    }

    public void saveData() {
        try {
            DataOutputStream write = new DataOutputStream(new FileOutputStream("coin.dat"));
            DataOutputStream write2 = new DataOutputStream(new FileOutputStream("transaction.dat"));
            for (int i = 0; i < Main.coins.numCoins; i++){
                write.writeUTF(Main.coins.coins.get(i).coinName);
                write.writeUTF(Main.coins.coins.get(i).coinURL);
            }
            write.close();
            for (int i = 0; i < Main.transactions.transactionCount; i++){
                write2.writeUTF(Main.transactions.getTransaction().get(i).coin.coinName);
                write2.writeDouble(Main.transactions.getTransaction().get(i).getInvestment());
                write2.writeDouble(Main.transactions.getTransaction().get(i).getPriceBought());
            }
            write2.close();
        }catch(IOException e){
            System.out.println("Writting error");
        }
    }

    public void loadData() {

        try {
            DataInputStream read = new DataInputStream(new FileInputStream("coin.dat"));
            for (int i = 0; i < read.available(); i++){
                String coinName = read.readUTF();
                String url = read.readUTF();
                Coin coin = new Coin(coinName, url);
                Main.coins.coins.add(coin);
                Main.coins.numCoins++;
            }
            read.close();
            DataInputStream read2 = new DataInputStream(new FileInputStream("transaction.dat"));
            for (int i = 0; i < read2.available(); i++){
                String name = read2.readUTF();
                double investment = read2.readDouble();
                double priceBought = read2.readDouble();
                Transaction transaction = new Transaction(Main.coins.findCoin(name), investment, priceBought);
                Main.transactions.addTransaction(transaction);
            }
        }catch (Exception e){
            System.out.println("Reading error");
        }
    }
}
