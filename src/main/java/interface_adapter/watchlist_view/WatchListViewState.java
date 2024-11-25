package interface_adapter.stock_view;

import entity.Stock;

import java.util.ArrayList;

/**
 * The state for the Stock View Model.
 */
public class WatchListViewState {
    private String symbol;
    private Stock stock;
    private ArrayList<String> watchlist;

    public String getSymbol() {
        return symbol;
    }

    public Stock getStock() {
        return stock;
    }

    public ArrayList<String> getWatchlist() {
        return watchlist;
    }

    public void setStock(Stock newStock) {
        this.stock = newStock;
    }

    public void setWatchlist(ArrayList<String> newWatchlist) {
        this.watchlist = newWatchlist;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void add(String ticker) {
        if (this.watchlist != null) {
            this.watchlist.add(ticker);
        }
    }

    public void remove(String ticker) {
        if (this.watchlist != null) {
            this.watchlist.remove(ticker);
        }
    }

    public void resetWatchlist() {
        if (this.watchlist != null) {
            final ArrayList<String> temp = new ArrayList<>();
            for (String ticker : this.watchlist) {
                if (ticker.equals("AAPL") || ticker.equals("COST") || ticker.equals("NVDA")) {
                    temp.add(ticker);
                }
            }

            this.watchlist.clear();
            this.watchlist.addAll(temp);
        }
    }
}
