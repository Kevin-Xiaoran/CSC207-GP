package use_case.buy;

/**
 * Input Boundary for actions which are related to buying stock.
 */
public interface BuyInputBoundary {

    /**
     * Executes the buy use case.
     * @param buyInputData the input data
     */
    void execute(BuyInputData buyInputData);

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();
}
