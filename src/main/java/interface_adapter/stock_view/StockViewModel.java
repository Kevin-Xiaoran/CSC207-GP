package interface_adapter.stock_view;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Stock View.
 */
public class StockViewModel extends ViewModel<StockViewState> {

    public StockViewModel() {
        super("stock view");
        setState(new StockViewState());
    }
}
