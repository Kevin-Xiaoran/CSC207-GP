package interface_adapter.home_view;

import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.stock_view.StockViewModel;
import interface_adapter.stock_view.StockViewState;
import interface_adapter.stock_view.WatchListViewState;
import interface_adapter.watchlist_view.WatchListViewModel;
import use_case.home_view.HomeOutputBoundary;
import use_case.home_view.SearchOutputData;
import use_case.home_view.WatchlistOutputBoundary;
import view.WatchListView;

import java.util.ArrayList;

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

    public void prepareSuccessView(SearchOutputData searchOutputData) {
        // Show stock view
        final StockViewState stockViewState = new StockViewState();
        stockViewState.setStock(searchOutputData.getStock());
        this.stockViewModel.setState(stockViewState);
        this.stockViewModel.firePropertyChanged("switchToStockView");

        viewManagerModel.setState("StockView");
        viewManagerModel.firePropertyChanged();
    }
}