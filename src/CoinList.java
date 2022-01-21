import java.util.ArrayList;

public class CoinList {
    ArrayList<Coin> coins;
    int numCoins;

    public CoinList(ArrayList<Coin> coins, int numCoins) {
        this.coins = coins;
        this.numCoins = numCoins;
    }

    public void showListCoins() {
        for (int i = 0; i < numCoins; i++) {
            System.out.println((i + 1) + ") " + coins.get(i).coinName + "\t$" + coins.get(i).coinPrice);
        }
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
        numCoins++;
    }

    public void updateCoins() {
        for (int i = 0; i < numCoins; i++) {
            coins.get(i).updateCoinPrice(coins.get(i).getCoinCurrentPrice());
        }
    }

    public Coin findCoin(String coinName) {
        int i = 0;
        boolean coinFound = false;
        Coin objectiveCoin = null;
        while (i < numCoins && !coinFound) {
            if (coins.get(i).coinName.equals(coinName)){
                objectiveCoin = coins.get(i);
                coinFound = true;
            }
            i++;
        }
        return objectiveCoin;
    }
}
