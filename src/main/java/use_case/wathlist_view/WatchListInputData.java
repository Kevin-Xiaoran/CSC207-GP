package use_case.wathlist_view;

/**
 * The Input Data for the Search Use Case.
 */
public class WatchListInputData {

    private final String stockSymbol;

    public WatchListInputData(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }
}
