package use_case.home_view;

import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;
import interface_adapter.home_view.WatchlistPresenter;
import use_case.home_view.WatchlistOutputBoundary;
import use_case.home_view.WatchlistInputBoundary;
import use_case.watchlist.WatchListDataAccessInterface;

/**
 * The Home View Interactor.
 */
public class WatchlistInteractor implements WatchlistInputBoundary {

    private final WatchListDataAccessInterface watchlistDataAccessInterface;
    private final WatchlistOutputBoundary watchlistPresenter;

    public WatchlistInteractor(WatchListDataAccessInterface watchlistDataAccessInterface,
                               WatchlistOutputBoundary watchlistPresenter) {
        this.watchlistDataAccessInterface = watchlistDataAccessInterface;
        this.watchlistPresenter = watchlistPresenter;
    }

    public void search(SearchInputData searchInputData) {
//        final String stockSymbol = searchInputData.getStockSymbol();
//        try {
//            final Stock stock = homeDataAccessInterface.getStock(stockSymbol);
//            final SearchOutputData searchOutputData = new SearchOutputData(stock, false);
//            homePresenter.prepareSuccessView(searchOutputData);
//        }
//        catch (Exception err) {
//            err.printStackTrace();
//            homePresenter.prepareFailView("Failed to fetch stock data");
//        }
        final StockFactory stockFactory = new CommonStockFactory();
        final Stock stock = stockFactory.create(searchInputData.getStockSymbol(), 128.2, 322.1, 100002322, 500.1, 100.23);

        final SearchOutputData searchOutputData = new SearchOutputData(stock, false);
        watchlistPresenter.prepareSuccessView(searchOutputData);
    }


    @Override
    public void getWatchListData() {

    }
}