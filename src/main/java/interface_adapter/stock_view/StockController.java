package interface_adapter.stock_view;

import entity.Stock;
import use_case.stock.StockInputBoundary;

/**
 * Controller for the Stock Use Case.
 */
public class StockController {

    private final StockInputBoundary stockInteractor;

    public StockController(StockInputBoundary stockInteractor) {
        this.stockInteractor = stockInteractor;
    }

    /**
     * Executes the buy Stock.
     *  @param stock the stock to buy
     */
    public void buyStock(Stock stock) {
        stockInteractor.buyStock(stock);
    }

    /**
     * Verify watchlist.
     *  @param stock the username to sign up
     *  @param shouldModifyData the username to sign up
     */
    public void toggleWatchlist(Stock stock, Boolean shouldModifyData) {
        stockInteractor.toggleWatchlist(stock, shouldModifyData);
    }
}
