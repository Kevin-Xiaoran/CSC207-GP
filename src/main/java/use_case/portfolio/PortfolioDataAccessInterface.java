package use_case.portfolio;

import java.util.ArrayList;

import entity.SimulatedHolding;

/**
 * DAO for the Portfolio use case.
 */

public interface PortfolioDataAccessInterface {

    /**
     * An arraylist of current stock holdings.
     */
    ArrayList<SimulatedHolding> getPortfolioList();

    /**
     * Saves the portfolio.
     */
    void savePortfolioList();

    /**
     * Adds stock to portfolio list.
     * @param simulatedHolding the holding to be added.
     */
    void addToPortfolioList(SimulatedHolding simulatedHolding);

    /**
     * Removes stock from portfolio list.
     * @param simulatedHolding the holding to be removed.
     */
    void removeFromPortfolioList(SimulatedHolding simulatedHolding);

    /**
     * Creates a portfolio list.
     */
    void createPortfolioList();

}
