package interface_adapter.stock_view;

import entity.Stock;
import interface_adapter.ViewManagerModel;

public class StockPresenter implements StockOutputBoundary {

    private final StockViewModel stockViewModel;
    private final ViewManagerModel viewManagerModel;

    public StockPresenter(StockViewModel stockViewModel, ViewManagerModel viewManagerModel) {
        this.stockViewModel = stockViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentBuyView(Stock stock) {
        stockViewModel.setStock(stock);
        viewManagerModel.setState("buy view");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentAddToWatchlist(Stock stock) {
        System.out.println("Stock " + stock.getSymbol() + " added to watchlist.");
    }

    @Override
    public void presentRemoveFromWatchlist(Stock stock) {
        System.out.println("Stock " + stock.getSymbol() + " removed from watchlist.");
    }
}
