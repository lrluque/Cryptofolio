import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Coin {
    public String coinName;
    public String coinURL;
    public double coinPrice;


    public Coin(String coinName, String coinURL) {
        this.coinName = coinName;
        this.coinURL = coinURL;
        this.coinPrice = getCoinCurrentPrice();
    }

    public double getCoinCurrentPrice() {
        String price = null;
        try {
            Document doc = Jsoup.connect(coinURL).get();
            price = doc.selectXpath("/html/body/div[3]/div/div[1]/main/div/div[7]/div/div/section/div[2]/div[1]/div[1]/div[3]/span").text().replaceFirst("\\$", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.parseDouble(price);
    }

    public void updateCoinPrice(double newPrice) {
        this.coinPrice = newPrice;
    }
}
