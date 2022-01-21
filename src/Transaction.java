
public class Transaction {
    private double investment;
    private double priceBought;
    Coin coin;


    public Transaction(Coin coin, double investment, double priceBought) {
        this.coin = coin;
        this.investment = investment;
        this.priceBought = priceBought;
    }





    public double getInvestment() {
        return investment;
    }

    public double getPriceBought() {
        return priceBought;
    }

    public double getCoinAmount() {
        Utilities utilities = new Utilities();
        return utilities.roundOffTo2DecPlaces(investment / priceBought);
    }

    public double getBalance() {
        Utilities utilities = new Utilities();
        return utilities.roundOffTo2DecPlaces(coin.coinPrice * getCoinAmount() - investment);
    }






}
