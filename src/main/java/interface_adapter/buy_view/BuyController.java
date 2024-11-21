package interface_adapter.buy_view;

import use_case.buy.BuyInputBoundary;
import use_case.buy.BuyInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

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
     * @param price the price of the stock
     * @param quantity the quantity of the stock
     */

    public void execute(String price, String quantity) {
        final BuyInputData buyInputData = new BuyInputData(
                price, quantity);

        buyUseCaseInteractor.execute(buyInputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        buyUseCaseInteractor.switchToHomeView();
    }
}
