package interface_adapter.buy_view;

/**
 * The state for the Buy View Model.
 */
public class BuyState {
    private String price = "";
    private String quantity = "";
    private String symbol = "";

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String qunatity) {
        this.quantity = qunatity;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
