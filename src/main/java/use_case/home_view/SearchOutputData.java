package use_case.home_view;

import entity.Stock;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final Stock stock;
    private final boolean useCaseFailed;

    public SearchOutputData(Stock stock, boolean useCaseFailed) {
        this.stock = stock;
        this.useCaseFailed = useCaseFailed;
    }

    public Stock getStock() {
        return stock;
    }
}
