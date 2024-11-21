package interface_adapter.buy_view;

/**
 * The state for the Buy View Model.
 */
public class BuyState {
    private String price = "";
    private String quantity = "";

    public String getPrice() {
        return price;
    }

    public String getQunatity() {
        return quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String qunatity) { this.quantity = qunatity; }
}
