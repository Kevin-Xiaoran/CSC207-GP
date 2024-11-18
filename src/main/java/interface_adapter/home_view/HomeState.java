package interface_adapter.home_view;

import entity.Stock;

import java.util.ArrayList;

/**
 * The state for the Home View Model.
 */
public class HomeState {
    private String symbol;
    private ArrayList<Stock> watchList;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ArrayList<Stock> getWatchList() {
        return watchList;
    }

    public void setWatchList(ArrayList<Stock> watchList) {
        this.watchList = watchList;
    }
}
