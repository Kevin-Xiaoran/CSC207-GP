package entity;

/**
 * A simple implementation of the Stock interface.
 */
public class CommonStock implements Stock {

    private final String symbol;
    private final int openPrice;
    private final int closePrice;
    private final int volume;
    private final int high;
    private final int low;

    public CommonStock(String symbol, int openPrice, int closePrice, int volume, int high, int low) {
        this.symbol = symbol;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.high = high;
        this.low = low;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getOpenPrice() {
        return openPrice;
    }

    @Override
    public int getClosePrice() {
        return closePrice;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public int getHigh() {
        return high;
    }

    @Override
    public int getLow() {
        return low;
    }
}
