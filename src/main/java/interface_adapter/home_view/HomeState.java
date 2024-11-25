package interface_adapter.home_view;

import entity.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void add(Stock stock) {
        this.watchList.add(stock);
        Collections.sort(this.watchList, new Comparator<Stock>() {
            public int compare(Stock o1, Stock o2) {
                return o1.getSymbol().compareTo(o2.getSymbol());
            }
        });
        System.out.println(watchList.size());
    }

    public void remove(Stock stock) {
        int i = 0;
        for (Stock s : watchList) {
            if (s.getSymbol().equals(stock.getSymbol())) {
                break;
            }
            i++;
        }
        this.watchList.remove(i);
        System.out.println(watchList.size());
    }

    public void resetWatchList() {
        final ArrayList<Stock> temp = new ArrayList<>();
        for (Stock s : watchList) {
            if (s.getSymbol().equals("AAPL") || s.getSymbol().equals("COST") || s.getSymbol().equals("NVDA")) {
                temp.add(s);
            }
        }
        this.watchList.clear();
        this.watchList.addAll(temp);
    }
}
