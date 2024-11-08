package interface_adapter.watchlist_view;

import java.util.ArrayList;
import java.util.List;

/**
 * The View Model for the Home View.
 */
public class WatchListViewModel {
    private List<String[]> stockList;

    public WatchListViewModel() {
        this.stockList = new ArrayList<>();
    }

    public List<String[]> getStockList() {
        return stockList;
    }

    public void setStockList(List<String[]> stockList) {
        this.stockList = stockList;
    }

}
