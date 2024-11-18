package use_case.home_view;

import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;

/**
 * The Home View Interactor.
 */
public class HomeInteractor implements HomeInputBoundary {

    private final HomeDataAccessInterface homeDataAccessObject;
    private final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeDataAccessInterface homeDataAccessObject,
                          HomeOutputBoundary homeInteractor) {
        this.homeDataAccessObject = homeDataAccessObject;
        this.homePresenter = homeInteractor;
    }

    @Override
    public void search(HomeInputData searchInputData) {
//        final String stockSymbol = searchInputData.getStockSymbol();
//        final Stock stock = homeDataAccessObject.getStock(stockSymbol);

        // Fake data to save usage limit
        final StockFactory stockFactory = new CommonStockFactory();
        final Stock stock = stockFactory.create("NVDA", 128.2, 322.1, 100002322, 500.1, 100.23);

        final HomeOutputData searchOutputData = new HomeOutputData(stock, false);
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

}
