package use_case.home_view;

import data_access.DBUserDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HomeViewInteratorTest {

    @Test
    public void successSearchWithGivenTextTest() {
        final SearchInputData inputData = new SearchInputData("COST");
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                assertEquals("COST", searchOutputData.getStock().getSymbol());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Do nothing right now
            }

            @Override
            public void switchToPortfolio() {
                // Do nothing right now
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // Do nothing right now
            }

            @Override
            public void switchToLoginView() {
                // Do nothing right now
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.search(inputData);
    }

    @Test
    public void successSearchWithDefaultTextTest() {
        final SearchInputData inputData = new SearchInputData("");
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                assertEquals("NVDA", searchOutputData.getStock().getSymbol());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Do nothing right now
            }

            @Override
            public void switchToPortfolio() {
                // Do nothing right now
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // Do nothing right now
            }

            @Override
            public void switchToLoginView() {
                // Do nothing right now
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.search(inputData);
    }

    @Test
    public void failSearchWithWrongTextTest() {
        final SearchInputData inputData = new SearchInputData("ABC");
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to fetch stock data", errorMessage);
            }

            @Override
            public void switchToPortfolio() {
                // Do nothing right now
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // Do nothing right now
            }

            @Override
            public void switchToLoginView() {
                // Do nothing right now
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.search(inputData);
    }

    @Test
    public void switchToPortfolioListViewTest() {
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPortfolio() {
                assert true;
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // Do nothing right now
            }

            @Override
            public void switchToLoginView() {
                // Do nothing right now
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.switchToPortfolio();
    }

    @Test
    public void switchToWatchListViewTest() {
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPortfolio() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // Do nothing right now
                assert true;
            }

            @Override
            public void switchToLoginView() {
                // Do nothing right now
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.switchToWatchList();
    }

    @Test
    public void switchToLoginViewTest() {
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPortfolio() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // Do nothing right now
                assert true;
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.switchToLoginView();
    }

    @Test
    public void switchToSignupViewTest() {
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPortfolio() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
                assert true;
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // Do nothing
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.switchToSignupView();
    }

    @Test
    public void getWatchlistDataTest() {
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPortfolio() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                // Do nothing right now
                assert true;
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                int expected = 3;
                int actual = watchList.size();
                assertEquals(expected, actual);
            }

            @Override
            public void deleteLocalData() {
                // Do nothing
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.getWatchListData();
    }

    @Test
    public void deleteLocalDataTest() {
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
        final FileUserDataAccessObject watchListData = new FileUserDataAccessObject(simulatedHoldingFactory);
        final DBUserDataAccessObject stockData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToPortfolio() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToWatchList(ArrayList<Stock> watchListSymbols) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void getWatchListData(ArrayList<Stock> watchList) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void deleteLocalData() {
                int watchListSizeExpected = 3;
                int watchListSizeActual = 0;
                int portfolioListSizeExpected = 0;
                int portfolioListSizeActual = 0;

                final String mainFilePath = "src/main/java/data_access/";
                final String watchListFilePath = "watchlist.txt";
                final String portfolioFilePath = "portfolio.txt";

                // Using FileReader and BufferedReader to read the file
                try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath + portfolioFilePath))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        portfolioListSizeActual++;
                    }
                }
                catch (IOException ex) {
                    // Normally, this means the file doesn't exist
                    assert false;
                }

                // Using FileReader and BufferedReader to read the file
                try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath + watchListFilePath))) {
                    final String line = reader.readLine();
                    if (line != null) {
                        watchListSizeActual = line.split(",").length;
                    }
                }
                catch (IOException ex) {
                    // Normally, this means the file doesn't exist
                    assert false;
                }

                assertEquals(watchListSizeExpected, watchListSizeActual);
                assertEquals(portfolioListSizeExpected, portfolioListSizeActual);
            }
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, watchListData, successPresenter);
        interactor.deleteLocalData();
    }
}
