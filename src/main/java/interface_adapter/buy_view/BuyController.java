package interface_adapter.buy_view;

import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;

/**
 * The controller for the Buy Use Case.
 */
public class BuyController {

    private final BuyInputBoundary buyUseCaseInteractor;

    public BuyController(BuyInputBoundary buyUseCaseInteractor) {
        this.buyUseCaseInteractor = buyUseCaseInteractor;
    }

    /**
     * Executes the Buy Use Case.
     * @param symbol the symbol of the stock
     * @param price the price of the stock
     * @param quantity the quantity of the stock
     */

    public void execute(String symbol, double price, double quantity) {
        final BuyInputData buyInputData = new BuyInputData(
                symbol, price, quantity);

        buyUseCaseInteractor.execute(buyInputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        buyUseCaseInteractor.switchToHomeView();
    }

    /**
     * Executes the "switch to StockView" Use Case.
     */
    public void switchToStockView() {
        buyUseCaseInteractor.switchToStockView();
    }
}
