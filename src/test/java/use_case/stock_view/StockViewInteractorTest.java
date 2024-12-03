package use_case.stock;

import entity.Stock;
import entity.CommonStock;
import org.junit.jupiter.api.Test;
import use_case.watchlist.WatchListDataAccessInterface;
import use_case.watchlist.WatchListModifyDataAccessInterface;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StockViewInteractorTest {

    @Test
    public void buyStockTest() {
        // Arrange
        StockOutputBoundary stockPresenter = new StockOutputBoundary() {
            @Override
            public void presentBuyView(Stock stock) {
                assertEquals("AAPL", stock.getSymbol());
            }

            @Override
            public void presentAddToWatchlist(Stock stock) {}

            @Override
            public void presentRemoveFromWatchlist(Stock stock) {}

            @Override
            public void updateFavouriteButton(Boolean isFavourite) {

            }

            public void updateFavouriteButton(boolean isFavourite) {}
        };
        WatchListDataAccessInterface watchListDataAccess = new WatchListDataAccessInterface() {
            @Override
            public ArrayList<String> getWatchList() {
                return new ArrayList<>();
            }

            @Override
            public void saveWatchList() {

            }

            @Override
            public void createWatchList() {

            }
        };
        WatchListModifyDataAccessInterface watchListModifyDataAccess = new WatchListModifyDataAccessInterface() {
            @Override
            public void addToWatchList(String symbol) {}

            @Override
            public void removeFromWatchList(String symbol) {}
        };
        StockInteractor stockInteractor = new StockInteractor(stockPresenter, watchListDataAccess, watchListModifyDataAccess);
        Stock stock = new CommonStock("AAPL", 100.0, 120.0, 1000.0, 130.0, 95.0);

        // Act
        stockInteractor.buyStock(stock);
    }

    @Test
    public void toggleWatchlist_AddToWatchlistTest() {
        // Arrange
        StockOutputBoundary stockPresenter = new StockOutputBoundary() {
            @Override
            public void presentBuyView(Stock stock) {}

            @Override
            public void presentAddToWatchlist(Stock stock) {
                assertEquals("AAPL", stock.getSymbol());
            }

            @Override
            public void presentRemoveFromWatchlist(Stock stock) {}

            @Override
            public void updateFavouriteButton(Boolean isFavourite) {

            }

            public void updateFavouriteButton(boolean isFavourite) {
                assertTrue(isFavourite);
            }
        };
        WatchListDataAccessInterface watchListDataAccess = new WatchListDataAccessInterface() {
            @Override
            public ArrayList<String> getWatchList() {
                return new ArrayList<>();
            }

            @Override
            public void saveWatchList() {

            }

            @Override
            public void createWatchList() {

            }
        };
        WatchListModifyDataAccessInterface watchListModifyDataAccess = new WatchListModifyDataAccessInterface() {
            @Override
            public void addToWatchList(String symbol) {
                assertEquals("AAPL", symbol);
            }

            @Override
            public void removeFromWatchList(String symbol) {}
        };
        StockInteractor stockInteractor = new StockInteractor(stockPresenter, watchListDataAccess, watchListModifyDataAccess);
        Stock stock = new CommonStock("AAPL", 100.0, 120.0, 1000.0, 130.0, 95.0);

        // Act
        stockInteractor.toggleWatchlist(stock, true);
    }

    @Test
    public void toggleWatchlist_RemoveFromWatchlistTest() {
        // Arrange
        StockOutputBoundary stockPresenter = new StockOutputBoundary() {
            @Override
            public void presentBuyView(Stock stock) {}

            @Override
            public void presentAddToWatchlist(Stock stock) {}

            @Override
            public void presentRemoveFromWatchlist(Stock stock) {
                assertEquals("AAPL", stock.getSymbol());
            }

            @Override
            public void updateFavouriteButton(Boolean isFavourite) {

            }

            public void updateFavouriteButton(boolean isFavourite) {
                assertFalse(isFavourite);
            }
        };
        WatchListDataAccessInterface watchListDataAccess = new WatchListDataAccessInterface() {
            @Override
            public ArrayList<String> getWatchList() {
                ArrayList<String> watchList = new ArrayList<>();
                watchList.add("AAPL");
                return watchList;
            }

            @Override
            public void saveWatchList() {

            }

            @Override
            public void createWatchList() {

            }
        };
        WatchListModifyDataAccessInterface watchListModifyDataAccess = new WatchListModifyDataAccessInterface() {
            @Override
            public void addToWatchList(String symbol) {}

            @Override
            public void removeFromWatchList(String symbol) {
                assertEquals("AAPL", symbol);
            }
        };
        StockInteractor stockInteractor = new StockInteractor(stockPresenter, watchListDataAccess, watchListModifyDataAccess);
        Stock stock = new CommonStock("AAPL", 100.0, 120.0, 1000.0, 130.0, 95.0);

        // Act
        stockInteractor.toggleWatchlist(stock, true);
    }

    @Test
    public void toggleWatchlist_UpdateFavouriteButtonWithoutModifyingData_AddToWatchlistTest() {
        // Arrange
        StockOutputBoundary stockPresenter = new StockOutputBoundary() {
            @Override
            public void presentBuyView(Stock stock) {}

            @Override
            public void presentAddToWatchlist(Stock stock) {}

            @Override
            public void presentRemoveFromWatchlist(Stock stock) {}

            @Override
            public void updateFavouriteButton(Boolean isFavourite) {

            }

            public void updateFavouriteButton(boolean isFavourite) {
                assertFalse(isFavourite);
            }
        };
        WatchListDataAccessInterface watchListDataAccess = new WatchListDataAccessInterface() {
            @Override
            public ArrayList<String> getWatchList() {
                return new ArrayList<>();
            }

            @Override
            public void saveWatchList() {

            }

            @Override
            public void createWatchList() {

            }
        };
        WatchListModifyDataAccessInterface watchListModifyDataAccess = new WatchListModifyDataAccessInterface() {
            @Override
            public void addToWatchList(String symbol) {}

            @Override
            public void removeFromWatchList(String symbol) {}
        };
        StockInteractor stockInteractor = new StockInteractor(stockPresenter, watchListDataAccess, watchListModifyDataAccess);
        Stock stock = new CommonStock("AAPL", 100.0, 120.0, 1000.0, 130.0, 95.0);

        // Act
        stockInteractor.toggleWatchlist(stock, false);
    }

    @Test
    public void toggleWatchlist_UpdateFavouriteButtonWithoutModifyingData_RemoveFromWatchlistTest() {
        // Arrange
        StockOutputBoundary stockPresenter = new StockOutputBoundary() {
            @Override
            public void presentBuyView(Stock stock) {}

            @Override
            public void presentAddToWatchlist(Stock stock) {}

            @Override
            public void presentRemoveFromWatchlist(Stock stock) {}

            @Override
            public void updateFavouriteButton(Boolean isFavourite) {

            }

            public void updateFavouriteButton(boolean isFavourite) {
                assertTrue(isFavourite);
            }
        };
        WatchListDataAccessInterface watchListDataAccess = new WatchListDataAccessInterface() {
            @Override
            public ArrayList<String> getWatchList() {
                ArrayList<String> watchList = new ArrayList<>();
                watchList.add("AAPL");
                return watchList;
            }

            @Override
            public void saveWatchList() {

            }

            @Override
            public void createWatchList() {

            }
        };
        WatchListModifyDataAccessInterface watchListModifyDataAccess = new WatchListModifyDataAccessInterface() {
            @Override
            public void addToWatchList(String symbol) {}

            @Override
            public void removeFromWatchList(String symbol) {}
        };
        StockInteractor stockInteractor = new StockInteractor(stockPresenter, watchListDataAccess, watchListModifyDataAccess);
        Stock stock = new CommonStock("AAPL", 100.0, 120.0, 1000.0, 130.0, 95.0);

        // Act
        stockInteractor.toggleWatchlist(stock, false);
    }
}