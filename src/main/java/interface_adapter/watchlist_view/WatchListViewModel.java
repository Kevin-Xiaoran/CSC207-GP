package interface_adapter.watchlist_view;

import entity.Stock;
import interface_adapter.ViewModel;
import java.util.ArrayList;

/**
 * The View Model for the WatchList View.
 */
public class WatchListViewModel extends ViewModel<WatchListViewState> {

    public WatchListViewModel() {
        super("watch list view");
        setState(new WatchListViewState());
    }

    public void updateWatchlist(ArrayList<Stock> stocks) {
        final WatchListViewState newState = getState();
        newState.setWatchlist(stocks);
        setState(newState);
    }

}
