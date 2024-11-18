package entity;

/**
 * A simple implementation of the Stock interface.
 */
public class CommonStock implements Stock {

    private final String symbol;
    private final double openPrice;
    private final double closePrice;
    private final double volume;
    private final double high;
    private final double low;

    public CommonStock(String symbol, double openPrice, double closePrice, double volume, double high, double low) {
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
    public double getOpenPrice() {
        return openPrice;
    }

    @Override
    public double getClosePrice() {
        return closePrice;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public double getHigh() {
        return high;
    }

    @Override
    public double getLow() {
        return low;
    }

    @Override
    public double getDailyChange() {
        return closePrice - openPrice;
    }

    @Override
    public double getDailyPercentage() {
        return (closePrice - openPrice) / openPrice * 100;
    }
}
