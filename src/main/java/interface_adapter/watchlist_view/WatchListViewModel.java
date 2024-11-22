package interface_adapter.watchlist_view;

import interface_adapter.ViewModel;
import interface_adapter.stock_view.StockViewState;
import interface_adapter.stock_view.WatchListViewState;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * The View Model for the WatchList View.
 */
public class WatchListViewModel extends ViewModel<WatchListViewState> {

    public WatchListViewModel() {
        super("watch list view");
        setState(new WatchListViewState());
    }
}
