package use_case.buy;

/**
 * The output boundary for the Buy Use Case.
 */
public interface BuyOutputBoundary {

    /**
     * Prepares the success view for the Buy Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BuyOutputData outputData);

    /**
     * Switches to the Home View.
     */
    void switchToHomeView();
}
