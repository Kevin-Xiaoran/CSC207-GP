package interface_adapter.stock_view;

import entity.Stock;

/**
 * The state for the Stock View Model.
 */
public class StockViewState {
    private Stock stock;
    private Boolean isFavorite;

    public Stock getStock() {
        return stock;
    }

    public Boolean getIsFavorite() { return isFavorite; }

    public void setStock(Stock newStock) {
        this.stock = newStock;
    }

    public void setIsFavorite(Boolean isFavorite) { this.isFavorite = isFavorite; }
}
