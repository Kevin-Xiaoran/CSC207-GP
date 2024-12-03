package use_case.portfolio;

import java.util.ArrayList;

import entity.SimulatedHolding;

/**
 * Interface for managing portfolio data.
 */
public interface PortfolioDataAccessInterface {

    /**
     * Get the portfolio list.
     *
     * @return the portfolio list
     */
    ArrayList<SimulatedHolding> getPortfolioList();

    /**
     * Save the portfolio list.
     */
    void savePortfolioList();

    /**
     * Add a holding to the portfolio list.
     *
     * @param simulatedHolding the holding to add
     */
    void addToPortfolioList(SimulatedHolding simulatedHolding);

    /**
     * Remove a holding from the portfolio list.
     *
     * @param simulatedHolding the holding to remove
     */
    void removeFromPortfolioList(SimulatedHolding simulatedHolding);

    /**
     * Create a new portfolio list.
     */
    void createPortfolioList();
}
