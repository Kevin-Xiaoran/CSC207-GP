package interface_adapter.stock_view;

import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.buy_view.BuyState;
import interface_adapter.buy_view.BuyViewModel;
import interface_adapter.home_view.HomeState;
import interface_adapter.home_view.HomeViewModel;
import interface_adapter.watchlist_view.WatchListViewModel;
import interface_adapter.stock_view.WatchListViewState;
import use_case.stock.StockOutputBoundary;
import view.StockView;

import java.util.ArrayList;

public class StockPresenter implements StockOutputBoundary {

    private final StockViewModel stockViewModel;
    private final BuyViewModel buyViewModel;
    private final HomeViewModel homeViewModel;
    private final WatchListViewModel watchListViewModel;
    private final ViewManagerModel viewManagerModel;

    public StockPresenter(StockViewModel stockViewModel,
                          BuyViewModel buyViewModel,
                          HomeViewModel homeViewModel,
                          WatchListViewModel watchListViewModel,
                          ViewManagerModel viewManagerModel) {
        this.stockViewModel = stockViewModel;
        this.buyViewModel = buyViewModel;
        this.homeViewModel = homeViewModel;
        this.watchListViewModel = watchListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentBuyView(Stock stock) {
        // Pass stock data to buy view
        final BuyState buyState = new BuyState();
        buyState.setSymbol(stock.getSymbol());
        buyState.setPrice(Double.toString(stock.getClosePrice()));
        buyViewModel.setState(buyState);
        buyViewModel.firePropertyChanged();

        // Switch to buy view
        viewManagerModel.setState("buy view");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentAddToWatchlist(Stock stock) {
        final HomeState homeState = homeViewModel.getState();
        final WatchListViewState watchListViewState = watchListViewModel.getState();

        // Pass new watchlist data to homeView
        homeState.add(stock);
        homeViewModel.firePropertyChanged("getWatchList");

        // Pass new watchlist data to watchListView
        watchListViewState.add(stock.getSymbol());
        watchListViewModel.firePropertyChanged("getWatchList");
    }

    @Override
    public void presentRemoveFromWatchlist(Stock stock) {
        final HomeState homeState = homeViewModel.getState();
        final WatchListViewState watchListViewState = watchListViewModel.getState();

        // Pass new watchlist data to homeView
        homeState.remove(stock);
        homeViewModel.firePropertyChanged("getWatchList");

        // Pass new watchlist data to watchListView
        watchListViewState.remove(stock.getSymbol());
        watchListViewModel.firePropertyChanged("watchList");
    }

    @Override
    public void updateFavouriteButton(Boolean isFavourite) {
        final StockViewState stockViewState = stockViewModel.getState();
        stockViewState.setIsFavorite(isFavourite);
        stockViewModel.firePropertyChanged("updateFavouriteButton");
    }
}
