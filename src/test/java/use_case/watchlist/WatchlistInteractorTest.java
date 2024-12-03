package use_case.watchlist;


import data_access.DBUserDataAccessObject;
import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;
import org.junit.jupiter.api.Test;
import use_case.home_view.SearchInputData;
import use_case.watchlist.*;
import use_case.watchlist.WatchlistInteractor;

import use_case.watchlist.WatchlistOutputBoundary;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WatchlistInteractorTest {

    @Test
    public void successSearchWithValidSymbolTest() {
        // Arrange
        final SearchInputData inputData = new SearchInputData("AAPL");
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        WatchlistOutputBoundary successPresenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                assertEquals("AAPL", searchOutputData.getStock().getSymbol());
            }
        };

        // Directly instantiate the interactor
        WatchlistInteractor interactor = new WatchlistInteractor(null, successPresenter, stockData);

        // Act
        interactor.search(inputData);
    }


    @Test
    public void failSearchWithInvalidSymbolTest() {
        // Arrange
        final SearchInputData inputData = new SearchInputData("INVALID");
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        WatchlistOutputBoundary failurePresenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                fail("Use case success is unexpected.");
            }
        };

        final WatchlistInteractor interactor = new WatchlistInteractor(null, failurePresenter, stockData);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> interactor.search(inputData));

        String expectedMessage = "API Error: 422";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void searchWithEmptySymbolTest() {
        // Arrange
        final SearchInputData inputData = new SearchInputData("  ");
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        final WatchlistInteractor interactor = new WatchlistInteractor(null, null, stockData);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> interactor.search(inputData));
        assertEquals("Stock symbol cannot be null or empty", exception.getMessage());
    }

    @Test
    public void updateWatchlistTest() {
        // Arrange
        final ArrayList<Stock> watchlist = new ArrayList<>();
        final StockFactory stockFactory = new CommonStockFactory();
        watchlist.add(stockFactory.create("AAPL", 150.0, 160.0, 1000, 170.0, 140.0));
        watchlist.add(stockFactory.create("COST", 480.0, 490.0, 2000, 500.0, 470.0));

        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        WatchlistOutputBoundary successPresenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                fail("No search operation expected.");
            }
        };

        final WatchlistInteractor interactor = new WatchlistInteractor(null, successPresenter, stockData);

        // Act
        interactor.updateWatchlist(watchlist);

        // Assert
        assertEquals(2, watchlist.size());
        assertEquals("AAPL", watchlist.get(0).getSymbol());
        assertEquals("COST", watchlist.get(1).getSymbol());
    }

    @Test
    public void getStockDataTest() {
        // Arrange
        StockFactory stockFactory = new CommonStockFactory();
        DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);
        WatchlistInteractor interactor = new WatchlistInteractor(null, null, stockData);

        // Act
        Stock stock = interactor.getStockData("AAPL");

        // Assert
        assertEquals("AAPL", stock.getSymbol());
    }

    @Test
    public void updateWatchlistWithEmptyListTest() {
        // Arrange
        final ArrayList<Stock> watchlist = new ArrayList<>();
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        final WatchlistInteractor interactor = new WatchlistInteractor(null, null, stockData);

        // Act
        interactor.updateWatchlist(watchlist);

        // Assert
        assertEquals(0, watchlist.size());
    }

    @Test
    public void searchWithEmptySymbolThrowsException() {
        // Arrange
        final SearchInputData inputData = new SearchInputData(" ");
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, new CommonStockFactory());
        final WatchlistInteractor interactor = new WatchlistInteractor(null, null, stockData);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> interactor.search(inputData));
        assertEquals("Stock symbol cannot be null or empty", exception.getMessage());
    }


    @Test
    public void searchWithInvalidSymbolThrowsRuntimeException() {
        // Arrange
        final SearchInputData inputData = new SearchInputData("INVALID");
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory) {
            @Override
            public Stock getStock(String stockSymbol) {
                throw new RuntimeException("Stock data not found");
            }
        };

        final WatchlistInteractor interactor = new WatchlistInteractor(null, null, stockData);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> interactor.search(inputData));
        assertTrue(exception.getMessage().contains("Stock data not found"));
    }

    @Test
    public void testSearchWithEmptyStockSymbol() {
        SearchInputData inputData = new SearchInputData(" ");
        WatchlistInteractor interactor = new WatchlistInteractor(null, null, new DBUserDataAccessObject(null, new CommonStockFactory()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> interactor.search(inputData));
        assertEquals("Stock symbol cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testSearchWithNullStockSymbol() {
        SearchInputData inputData = new SearchInputData(null);
        WatchlistInteractor interactor = new WatchlistInteractor(null, null, new DBUserDataAccessObject(null, new CommonStockFactory()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> interactor.search(inputData));
        assertEquals("Stock symbol cannot be null or empty", exception.getMessage());
    }

}
