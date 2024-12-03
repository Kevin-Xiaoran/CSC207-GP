package use_case.portfolio;

import java.util.ArrayList;

import entity.CommonStockFactory;
import entity.DebugMode;
import entity.SimulatedHolding;
import entity.Stock;
import use_case.home_view.HomeDataAccessInterface;

/**
 * The Portfolio Interactor.
 */
public class PortfolioInteractor implements PortfolioInputBoundary {
    private static final int INITIAL_STOCK_PRICE = 100;
    private static final int STOCK_PRICE_INCREMENT = 100;
    private static final int ADDITIONAL_PRICE = 50;
    private static final double FAKE_VOLUME = 10000000.2;

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

        if (DebugMode.debugMode) {
            // Using fake data
            int stockPrice = INITIAL_STOCK_PRICE;
            final CommonStockFactory stockFactory = new CommonStockFactory();
            for (SimulatedHolding simulatedHolding : simulatedHoldings) {
                final Stock stock = stockFactory.create(
                        simulatedHolding.getSymbol(),
                        0,
                        stockPrice + ADDITIONAL_PRICE,
                        FAKE_VOLUME,
                        0,
                        0
                );
                stockList.add(stock);

                stockPrice += STOCK_PRICE_INCREMENT;
            }
        }
        else {
            // Using real data
            for (SimulatedHolding simulatedHolding : simulatedHoldings) {
                final Stock stock = homeDataAccessObject.getStock(simulatedHolding.getSymbol());
                stockList.add(stock);
            }
        }
    }
}
