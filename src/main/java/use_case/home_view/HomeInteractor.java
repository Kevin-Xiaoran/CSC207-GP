package use_case.home_view;

import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;
import use_case.portfolio.PortfolioDataAccessInterface;
import use_case.watchlist.WatchListDataAccessInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Home View Interactor.
 */
public class HomeInteractor implements HomeInputBoundary {

    private final HomeDataAccessInterface homeDataAccessInterface;
    private final WatchListDataAccessInterface watchListDataAccessInterface;
    private final PortfolioDataAccessInterface portfolioDataAccessInterface;
    private final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeDataAccessInterface homeDataAccessInterface,
                          WatchListDataAccessInterface watchListDataAccessInterface,
                          PortfolioDataAccessInterface portfolioDataAccessInterface,
                          HomeOutputBoundary homeInteractor) {
        this.homeDataAccessInterface = homeDataAccessInterface;
        this.watchListDataAccessInterface = watchListDataAccessInterface;
        this.portfolioDataAccessInterface = portfolioDataAccessInterface;
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
        // Default search symbol is NVDA
        String stockSymbol = searchInputData.getStockSymbol();
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            stockSymbol = "NVDA";
        }
        else {
            stockSymbol = stockSymbol.toUpperCase();
        }
        final StockFactory stockFactory = new CommonStockFactory();
        final Stock stock = stockFactory.create(stockSymbol, 128.2, 322.1, 100002322, 500.1, 100.23);

        final SearchOutputData searchOutputData = new SearchOutputData(stock, false);
        homePresenter.prepareSuccessView(searchOutputData);
    }

    @Override
    public void switchToPortfolio() {
        homePresenter.switchToPortfolio();
    }

    @Override
    public void switchToWatchList() {
        final ArrayList<String> watchListData = watchListDataAccessInterface.getWatchList();
        homePresenter.switchToWatchList(watchListData);
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

        int i = 0;
        for (String symbol : watchListData) {
            final Stock stock = stockFactory.create(symbol, i, i, 100002322, 500.1, 100.23);
            watchList.add(stock);
            i += 1;
        }

//        for (String symbol : watchListData) {
//            final Stock stockData = homeDataAccessInterface.getStock(symbol);
//            watchList.add(stockData);
//        }

        homePresenter.getWatchListData(watchList);
    }

    @Override
    public void deleteLocalData() {
        watchListDataAccessInterface.createWatchList();
        portfolioDataAccessInterface.createPortfolioList();

        homePresenter.deleteLocalData();
    }
}
