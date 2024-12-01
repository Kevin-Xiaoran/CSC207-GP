package entity;

public class CommonSimulatedHolding implements SimulatedHolding {
    private String symbol;
    private double purchasePrice;
    private double purchaseAmount;

    public CommonSimulatedHolding(String symbol, double purchasePrice, double purchaseAmount) {
        this.symbol = symbol;
        this.purchasePrice = purchasePrice;
        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    @Override
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
}

