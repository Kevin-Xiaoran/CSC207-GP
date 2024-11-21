package use_case.buy;

/**
 * The Input Data for the Buy Use Case.
 */
public class BuyInputData {

    private final String price;
    private final String quantity;

    public BuyInputData(String price, String quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    String getPrice() {
        return price;
    }

    String getQuantity() {
        return quantity;
    }

}
