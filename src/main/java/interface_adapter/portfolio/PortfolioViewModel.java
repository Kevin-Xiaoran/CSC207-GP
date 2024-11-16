package interface_adapter.portfolio;

import java.util.ArrayList;
import java.util.List;

/**
 * The View Model for the Portfolio View.
 */

public class PortfolioViewModel {
    private List<String[]> stockList;

    public PortfolioViewModel() {
        this.stockList = new ArrayList<>();
    }

    public List<String[]> getStockList() {
        return stockList;
    }

    public void setStockList(List<String[]> stockList) {
        this.stockList = stockList;
    }

}