package interface_adapter.portfolio;

import entity.SimulatedHolding;
import entity.Stock;
import use_case.portfolio.PortfolioOutputBoundary;

import java.util.ArrayList;

public class PortfolioPresenter implements PortfolioOutputBoundary {

    private final PortfolioViewModel portfolioViewModel;

    public PortfolioPresenter(final PortfolioViewModel portfolioViewModel) {
        this.portfolioViewModel = portfolioViewModel;
    }

    @Override
    public void presentPortfolioListData(ArrayList<SimulatedHolding> portfolioList, ArrayList<Stock> stockList) {
        final PortfolioState portfolioState = portfolioViewModel.getState();
        portfolioState.setSimulatedHoldings(portfolioList);
        portfolioState.setStocks(stockList);
        portfolioViewModel.setState(portfolioState);
        portfolioViewModel.firePropertyChanged("getPortfolioList");
    }
}
