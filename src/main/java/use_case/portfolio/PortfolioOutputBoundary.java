package use_case.portfolio;

import java.util.ArrayList;

import entity.SimulatedHolding;
import entity.Stock;

/**
 * Output Data for the portfolio Use Case.
 */
public interface PortfolioOutputBoundary {
    /**
     * Present portfolio list data for Portfolio View.
     * @param portfolioList portfolio list data
     * @param stockList portfolio list data
     */
    void presentPortfolioListData(ArrayList<SimulatedHolding> portfolioList, ArrayList<Stock> stockList);
}
