package interface_adapter.buy_view;

import entity.CommonSimulatedHoldingFactory;
import entity.SimulatedHolding;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.IsLoggedIn;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.home_view.HomeViewModel;
import interface_adapter.portfolio.PortfolioState;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.stock_view.StockViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.BuyView;

public class BuyPresenter implements BuyOutputBoundary {

    private final BuyViewModel buyViewModel;
    private final PortfolioViewModel portfolioViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private final StockViewModel stockViewModel;

    public BuyPresenter(BuyViewModel buyViewModel, PortfolioViewModel portfolioViewModel,
                        ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, StockViewModel stockViewModel) {
        this.buyViewModel = buyViewModel;
        this.portfolioViewModel = portfolioViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.stockViewModel = stockViewModel;
    }

    @Override
    public void prepareSuccessView(BuyOutputData response) {
        portfolioViewModel.firePropertyChanged("getPortfolioList");

        switchToHomeView();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToStockView() {
        viewManagerModel.setState(stockViewModel.getViewName());
        viewManagerModel.firePropertyChanged("switchToStockView");

        viewManagerModel.pushView("StockView");
    }
}