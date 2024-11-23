package interface_adapter.stock_view;

import entity.Stock;
import use_case.stock.StockInputBoundary;

public class StockController {

    private final StockInputBoundary stockInteractor;

    public StockController(StockInputBoundary stockInteractor) {
        this.stockInteractor = stockInteractor;
    }

    public void buyStock(Stock stock) {
        stockInteractor.buyStock(stock);
    }

    public void toggleWatchlist(Stock stock, Boolean shouldModifyData) {
        stockInteractor.toggleWatchlist(stock, shouldModifyData);
    }
}
