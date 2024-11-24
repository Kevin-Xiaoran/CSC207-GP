package use_case.portfolio;

import entity.CommonStockFactory;
import entity.SimulatedHolding;
import entity.Stock;
import use_case.home_view.HomeDataAccessInterface;

import java.util.ArrayList;

public class PortfolioInteractor implements PortfolioInputBoundary{
    private final PortfolioDataAccessInterface portfolioDataAccessObject;
    private final HomeDataAccessInterface homeDataAccessObject;
    private final PortfolioOutputBoundary portfolioPresenter;

    public PortfolioInteractor(PortfolioDataAccessInterface portfolioDataAccessObject,
                               HomeDataAccessInterface homeDataAccessObject,
                               PortfolioOutputBoundary portfolioPresenter) {
        this.portfolioDataAccessObject = portfolioDataAccessObject;
        this.homeDataAccessObject = homeDataAccessObject;
        this.portfolioPresenter = portfolioPresenter;
    }

    @Override
    public void getPortfolioListData() {
        final ArrayList<SimulatedHolding> simulatedHoldings = portfolioDataAccessObject.getPortfolioList();
        final ArrayList<Stock> stockList = new ArrayList<>();

//        // Get data from API
//        for (SimulatedHolding simulatedHolding : simulatedHoldings) {
//            final Stock stock = homeDataAccessObject.getStock(simulatedHolding.getSymbol());
//            stockList.add(stock);
//        }

        // Using fake data
        int i = 100;
        final CommonStockFactory stockFactory = new CommonStockFactory();
        for (SimulatedHolding simulatedHolding : simulatedHoldings) {
            final Stock stock = stockFactory.create(simulatedHolding.getSymbol(),
                    0,
                    i + 50,
                    10000000.2,
                    0,
                    0);
            stockList.add(stock);

            i += 100;
        }

        portfolioPresenter.presentPortfolioListData(simulatedHoldings, stockList);
    }
}
