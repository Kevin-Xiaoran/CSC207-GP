package interface_adapter.watchlist_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.stock_view.StockViewModel;
import interface_adapter.stock_view.StockViewState;
import use_case.watchlist.WatchlistOutputBoundary;

/**
 * The Presenter for the Search Use Case.
 */
public class WatchlistPresenter implements WatchlistOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StockViewModel stockViewModel;
    private final WatchListViewModel watchListViewModel;

    public WatchlistPresenter(
                         ViewManagerModel viewManagerModel,
                         StockViewModel stockViewModel,
                         WatchListViewModel watchListViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.stockViewModel = stockViewModel;
        this.watchListViewModel = watchListViewModel;
    }

    @Override
    public void prepareSuccessView(use_case.watchlist.SearchOutputData searchOutputData) {
        // Show stock view
        final StockViewState stockViewState = new StockViewState();
        stockViewState.setStock(searchOutputData.getStock());
        this.stockViewModel.setState(stockViewState);
        this.stockViewModel.firePropertyChanged("switchToStockView");

        viewManagerModel.pushView("StockView");
    }
}