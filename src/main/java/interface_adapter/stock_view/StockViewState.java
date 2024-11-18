package interface_adapter.stock_view;

import entity.Stock;

/**
 * The state for the Stock View Model.
 */
public class StockViewState {
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock newStock) {
        this.stock = newStock;
    }
}
