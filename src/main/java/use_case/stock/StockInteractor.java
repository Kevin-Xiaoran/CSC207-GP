package use_case.stock;

import java.util.ArrayList;

import entity.Stock;
import use_case.watchlist.WatchListDataAccessInterface;
import use_case.watchlist.WatchListModifyDataAccessInterface;

/**
 * The Stock Interactor.
 */
public class StockInteractor implements StockInputBoundary {

    private final StockOutputBoundary stockPresenter;
    private final WatchListDataAccessInterface watchListDataAccessInterface;
    private final WatchListModifyDataAccessInterface watchListModifyDataAccessInterface;

    public StockInteractor(StockOutputBoundary stockPresenter,
                           WatchListDataAccessInterface watchListDataAccessInterface,
                           WatchListModifyDataAccessInterface watchListModifyDataAccessInterface) {
        this.stockPresenter = stockPresenter;
        this.watchListDataAccessInterface = watchListDataAccessInterface;
        this.watchListModifyDataAccessInterface = watchListModifyDataAccessInterface;
    }

    @Override
    public void buyStock(Stock stock) {
        stockPresenter.presentBuyView(stock);
    }

    @Override
    public void toggleWatchlist(Stock stock, Boolean shouldModifyData) {
        final ArrayList<String> watchList = watchListDataAccessInterface.getWatchList();
        if (watchList.contains(stock.getSymbol())) {
            if (shouldModifyData) {
                watchListModifyDataAccessInterface.removeFromWatchList(stock.getSymbol());
                stockPresenter.presentRemoveFromWatchlist(stock);
                stockPresenter.updateFavouriteButton(false);
            }
            else {
                stockPresenter.updateFavouriteButton(true);
            }
        }
        else {
            if (shouldModifyData) {
                watchListModifyDataAccessInterface.addToWatchList(stock.getSymbol());
                stockPresenter.presentAddToWatchlist(stock);
                stockPresenter.updateFavouriteButton(true);
            }
            else {
                stockPresenter.updateFavouriteButton(false);
            }
        }
    }
}
