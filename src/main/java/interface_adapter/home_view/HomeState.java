package interface_adapter.home_view;

import entity.Stock;

import java.util.ArrayList;

/**
 * The state for the Home View Model.
 */
public class HomeState {
    private String symbol;
    private ArrayList<Stock> watchList;
    private String errorMessage;

    public String getSymbol() {
        return symbol;
    }

    public ArrayList<Stock> getWatchList() {
        return watchList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setWatchList(ArrayList<Stock> watchList) {
        this.watchList = watchList;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
