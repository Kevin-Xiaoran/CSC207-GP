package use_case.home_view;

/**
 * The Input Data for the Search Use Case.
 */
public class HomeInputData {

    private final String stockSymbol;

    public HomeInputData(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }
}
