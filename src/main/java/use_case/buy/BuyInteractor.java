package use_case.buy;

import entity.Stock;

/**
 * The Login Interactor.
 */
public class BuyInteractor implements BuyInputBoundary {

    private final BuyUserDataAccessInterface buyUserDataAccessObject;
    private final BuyOutputBoundary buyPresenter;

    public BuyInteractor(BuyUserDataAccessInterface userDataAccessInterface, BuyOutputBoundary buyOutputBoundary) {
        this.buyUserDataAccessObject = userDataAccessInterface;
        this.buyPresenter = buyOutputBoundary;
    }

    @Override
    public void execute(BuyInputData buyInputData) {
        final String price = buyInputData.getPrice();
        final String quantity = buyInputData.getQuantity();

        // More code is needed to save the purchased stock information
        // final Stock stock = buyUserDataAccessObject.get(buyInputData.getPrice());
        // buyUserDataAccessObject.setCurrentPrice(stock.getPrice());

        final BuyOutputData buyOutputData = new BuyOutputData();
        buyPresenter.prepareSuccessView(buyOutputData);
    }

    @Override
    public void switchToHomeView() {
        buyPresenter.switchToHomeView();
    }
}
