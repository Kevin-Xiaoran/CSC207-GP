package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonStockFactory implements StockFactory {

    @Override
    public Stock create(String symbol, double openPrice, double closePrice, double volume, double high, double low) {
        return new CommonStock(symbol, openPrice, closePrice, volume, high, low);
    }
}
