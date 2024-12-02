package interface_adapter.stock_view;

import entity.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The state for the Stock View Model.
 */
public class WatchListViewState {
    private String symbol;
    private Stock stock;
    private ArrayList<Stock> watchlist;

    public String getSymbol() {
        return symbol;
    }

    public Stock getStock() {
        return stock;
    }

    public ArrayList<Stock> getWatchlist() {
        return watchlist;
    }

    public void setStock(Stock newStock) {
        this.stock = newStock;
    }

    public void setWatchlist(ArrayList<Stock> newWatchlist) {
        this.watchlist = newWatchlist;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void add(Stock stock) {
        if (this.watchlist == null) {
            this.watchlist = new ArrayList<>();
        }
        this.watchlist.add(stock);
        Collections.sort(this.watchlist, new Comparator<Stock>() {
            public int compare(Stock o1, Stock o2) {
                return o1.getSymbol().compareTo(o2.getSymbol());
            }
        });
        System.out.println(watchlist.size());
    }

    public void remove(Stock stock) {
        if (this.watchlist == null) {
            return;
        }
        int i = 0;
        for (Stock s : watchlist) {
            if (s.getSymbol().equals(stock.getSymbol())) {
                break;
            }
            i++;
        }
        if (i < watchlist.size()) {
            this.watchlist.remove(i);
            System.out.println(watchlist.size());
        }
    }

    public void resetWatchList() {
        if (this.watchlist == null) {
            return;
        }
        final ArrayList<Stock> temp = new ArrayList<>();
        for (Stock s : watchlist) {
            if (s.getSymbol().equals("AAPL") || s.getSymbol().equals("COST") || s.getSymbol().equals("NVDA")) {
                temp.add(s);
            }
        }
        this.watchlist.clear();
        this.watchlist.addAll(temp);
        }
}
