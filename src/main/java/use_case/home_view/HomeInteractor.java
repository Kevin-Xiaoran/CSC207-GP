package use_case.home_view;

import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;
import use_case.watchlist.WatchListDataAccessInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Home View Interactor.
 */
public class HomeInteractor implements HomeInputBoundary {

    private final HomeDataAccessInterface homeDataAccessInterface;
    private final WatchListDataAccessInterface watchListDataAccessInterface;
    private final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeDataAccessInterface homeDataAccessInterface, WatchListDataAccessInterface watchListDataAccessInterface,
                          HomeOutputBoundary homeInteractor) {
        this.homeDataAccessInterface = homeDataAccessInterface;
        this.watchListDataAccessInterface = watchListDataAccessInterface;
        this.homePresenter = homeInteractor;
    }

    @Override
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
        final Stock stock = stockFactory.create("NVDA", 128.2, 322.1, 100002322, 500.1, 100.23);

        final SearchOutputData searchOutputData = new SearchOutputData(stock, false);
        homePresenter.prepareSuccessView(searchOutputData);
    }

    @Override
    public void switchToPortfolio() {
        homePresenter.switchToPortfolio();
    }

    @Override
    public void switchToWatchList() {
        homePresenter.switchToWatchList();
    }

    @Override
    public void switchToLoginView() {
        homePresenter.switchToLoginView();
    }

    @Override
    public void switchToSignupView() {
        homePresenter.switchToSignupView();
    }

    @Override
    public void getWatchListData() {
        final ArrayList<String> watchListData = watchListDataAccessInterface.getWatchList();
        final ArrayList<Stock> watchList = new ArrayList<>();

        final StockFactory stockFactory = new CommonStockFactory();
        final Stock aapl = stockFactory.create("AAPL", 552.2, 130.3, 100002322, 500.1, 100.23);
        final Stock nvda = stockFactory.create("NVDA", 128.2, 148.2, 100002322, 500.1, 100.23);
        final Stock meta = stockFactory.create("META", 11.2, 559.2, 100002322, 500.1, 100.23);
        final Stock cost = stockFactory.create("COST", 123.4, 234.5, 100002322, 500.1, 100.23);
        final Stock amd = stockFactory.create("AMD", 234.5, 345.6, 100002322, 500.1, 100.23);
        final Stock tsm = stockFactory.create("TSM", 345.6, 456.7, 100002322, 500.1, 100.23);

        final Random rand = new Random();
        final int randNum = rand.nextInt(2);
        if (randNum == 0) {
            watchList.add(aapl);
            watchList.add(nvda);
            watchList.add(meta);
        }
        else {
            watchList.add(cost);
            watchList.add(amd);
            watchList.add(tsm);
        }

//        for (String symbol : watchListData) {
//            final Stock stockData = homeDataAccessInterface.getStock(symbol);
//            watchList.add(stockData);
//        }

        homePresenter.getWatchListData(watchList);
    }
}
