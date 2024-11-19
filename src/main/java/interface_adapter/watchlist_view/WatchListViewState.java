package interface_adapter.stock_view;

import entity.Stock;

import java.util.ArrayList;

/**
 * The state for the Stock View Model.
 */
public class WatchListViewState {
    private Stock stock;
    private ArrayList<String> watchlist;

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
}
