package entity;

public class CommonSimulatedHolding implements SimulatedHolding {
    private String symbol;
    private double purchasePrice;
    private int purchaseAmount;

    public CommonSimulatedHolding(String symbol, double purchasePrice, int purchaseAmount) {
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
    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    @Override
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
}

