package use_case.watchlist;

import data_access.DBUserDataAccessObject;
import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;
import org.junit.jupiter.api.Test;
import use_case.home_view.*;
import use_case.home_view.WatchlistInputBoundary;
import use_case.home_view.WatchlistInteractor;
import use_case.home_view.WatchlistOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WatchlistlnteractorTest {

    @Test
    public void successSearchWithValidSymbolTest() {
        final SearchInputData inputData = new SearchInputData("AAPL");
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        WatchlistOutputBoundary successPresenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                assertEquals("AAPL", searchOutputData.getStock().getSymbol());
            }
        };

        final WatchlistInputBoundary interactor = new use_case.home_view.WatchlistInteractor(null, successPresenter, stockData);
        interactor.search(inputData);
    }

    @Test
    public void failSearchWithInvalidSymbolTest() {
        final SearchInputData inputData = new SearchInputData("INVALID");
        final StockFactory stockFactory = new CommonStockFactory();
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(null, stockFactory);

        WatchlistOutputBoundary failurePresenter = new WatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                fail("Use case success is unexpected.");
            }
        };

        final WatchlistInputBoundary interactor = new WatchlistInteractor(null, failurePresenter, stockData);

        Exception exception = assertThrows(RuntimeException.class, () -> interactor.search(inputData));

        String expectedMessage = "API Error: 422";
        assertTrue("Exception message did not match the expected content: " + exception.getMessage(),
                exception.getMessage().contains(expectedMessage) ||
                                exception.getMessage().contains("Stock data not found for symbol: INVALID"));
    }

    @Test
    public void updateWatchlistTest() {
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

        final WatchlistInputBoundary interactor = new WatchlistInteractor(null, successPresenter, stockData);

        // Act
        interactor.updateWatchlist(watchlist);

        // Assert
        assertEquals(2, watchlist.size());
        assertEquals("AAPL", watchlist.get(0).getSymbol());
        assertEquals("COST", watchlist.get(1).getSymbol());
    }
}
