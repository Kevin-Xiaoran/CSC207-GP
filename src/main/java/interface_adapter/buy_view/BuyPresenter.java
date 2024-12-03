package interface_adapter.buy_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_view.HomeViewModel;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.stock_view.StockViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;

/**
 * The Presenter for the Buy Use Case.
 */
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
        portfolioViewModel.firePropertyChanged("addNewStock");

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
