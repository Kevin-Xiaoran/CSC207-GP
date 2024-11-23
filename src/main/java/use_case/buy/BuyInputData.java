package use_case.buy;

/**
 * The Input Data for the Buy Use Case.
 */
public class BuyInputData {

    private final String symbol;
    private final double price;
    private final int quantity;

    public BuyInputData(String symbol, double price, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public String getSymbol() { return symbol; }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}