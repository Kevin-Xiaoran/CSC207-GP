package interface_adapter.watchlist_view;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * The View Model for the WatchList View.
 */
public class WatchListViewModel {
    private final ArrayList<String> watchList = new ArrayList<>();
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public ArrayList<String> getWatchList() {
        return new ArrayList<>(watchList);
    }

    // add to watchlist
    public void addWatchList(String symbol) {
        watchList.add(symbol);
        pcs.firePropertyChange("watchList", null, new ArrayList<>(watchList));
    }

    // remove from watchlist
    public void removeWatchList(String symbol) {
        watchList.remove(symbol);
        pcs.firePropertyChange("watchList", null, new ArrayList<>(watchList));
    }
    // return State of Stock, return ture if stock is already in the watchlist, false else
    public boolean checkStockState(String symbol) {
        return watchList.contains(symbol);
    }
}
