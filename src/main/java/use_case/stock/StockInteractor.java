package use_case.stock;

import entity.Stock;
import interface_adapter.stock_view.StockOutputBoundary;
import use_case.watchlist.WatchListModifyDataAccessInterface;

public class StockInteractor implements StockInputBoundary {

    private final StockOutputBoundary stockPresenter;
    private final WatchListModifyDataAccessInterface watchlistDataAccess;

    public StockInteractor(StockOutputBoundary stockPresenter, WatchListModifyDataAccessInterface watchlistDataAccess) {
        this.stockPresenter = stockPresenter;
        this.watchlistDataAccess = watchlistDataAccess;
    }

    @Override
    public void buyStock(Stock stock) {
        stockPresenter.presentBuyView(stock);
    }

    @Override
    public void toggleWatchlist(Stock stock) {
        if (watchlistDataAccess.isInWatchList(stock.getSymbol())) {
            watchlistDataAccess.removeFromWatchList(stock.getSymbol());
            stockPresenter.presentRemoveFromWatchlist(stock);
        }
        else {
            watchlistDataAccess.addToWatchList(stock.getSymbol());
            stockPresenter.presentAddToWatchlist(stock);
        }
    }
}
