package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonStockFactory implements StockFactory {

    @Override
    public Stock create(String symbol, int openPrice, int closePrice, int volume, int high, int low) {
        return new CommonStock(symbol, openPrice, closePrice, volume, high, low);
    }
}
