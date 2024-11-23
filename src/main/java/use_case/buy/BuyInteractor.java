package use_case.buy;

import entity.CommonSimulatedHoldingFactory;
import entity.SimulatedHolding;
import entity.Stock;
import use_case.portfolio.PortfolioDataAccessInterface;

/**
 * The Login Interactor.
 */
public class BuyInteractor implements BuyInputBoundary {

    private final BuyOutputBoundary buyPresenter;
    private final PortfolioDataAccessInterface portfolioDataAccessObject;

    public BuyInteractor(BuyOutputBoundary buyOutputBoundary, PortfolioDataAccessInterface portfolioDataAccessObject) {
        this.buyPresenter = buyOutputBoundary;
        this.portfolioDataAccessObject = portfolioDataAccessObject;
    }

    @Override
    public void execute(BuyInputData buyInputData) {
        final String symbol = buyInputData.getSymbol();
        final double price = buyInputData.getPrice();
        final int quantity = buyInputData.getQuantity();

        final CommonSimulatedHoldingFactory commonSimulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final SimulatedHolding simulatedHolding = commonSimulatedHoldingFactory.create(symbol, price, quantity);

        // Add simulated holding into database
        portfolioDataAccessObject.addToPortfolioList(simulatedHolding);

        // Pass data to Portfolio View
        final BuyOutputData buyOutputData = new BuyOutputData(simulatedHolding);
        buyPresenter.prepareSuccessView(buyOutputData);
    }

    @Override
    public void switchToHomeView() {
        buyPresenter.switchToHomeView();
    }
}
