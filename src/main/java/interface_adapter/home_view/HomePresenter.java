package interface_adapter.home_view;

import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.stock_view.StockViewModel;
import interface_adapter.stock_view.StockViewState;
import use_case.home_view.HomeOutputBoundary;
import use_case.home_view.SearchOutputData;

import java.util.ArrayList;

/**
 * The Presenter for the Search Use Case.
 */
public class HomePresenter implements HomeOutputBoundary {

    private final HomeViewModel homeViewModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PortfolioViewModel portfolioViewModel;
    private final StockViewModel stockViewModel;

    public HomePresenter(HomeViewModel homeViewModel,
                         LoginViewModel loginViewModel,
                         SignupViewModel signupViewModel,
                         ViewManagerModel viewManagerModel,
                         PortfolioViewModel portfolioViewModel,
                         StockViewModel stockViewModel) {

        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.portfolioViewModel = portfolioViewModel;
        this.stockViewModel = stockViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        // Show stock view
        final StockViewState stockViewState = new StockViewState();
        stockViewState.setStock(searchOutputData.getStock());
        this.stockViewModel.setState(stockViewState);
        this.stockViewModel.firePropertyChanged();

        // Clean home view error message
        this.sendErrorMessage("");

        viewManagerModel.setState("StockView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.sendErrorMessage(errorMessage);
    }

    @Override
    public void switchToPortfolio() {
        viewManagerModel.setState("PortfolioView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWatchList() {
        viewManagerModel.setState("WatchListView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void getWatchListData(ArrayList<Stock> watchList) {
        final HomeState homeState = new HomeState();
        homeState.setWatchList(watchList);
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged("getWatchList");
    }

    // Helper function
    private void sendErrorMessage(String errorMessage) {
        final HomeState homeState = this.homeViewModel.getState();
        homeState.setErrorMessage(errorMessage);
        this.homeViewModel.setState(homeState);
        this.homeViewModel.firePropertyChanged("error");
    }
}
