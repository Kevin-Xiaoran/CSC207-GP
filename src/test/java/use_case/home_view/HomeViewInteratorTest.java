package use_case.home_view;

import data_access.DBUserDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HomeViewInteratorTest {

    @Test
    public void successTest() {
        final SearchInputData inputData = new SearchInputData("NVDA");
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
            public void switchToWatchList(ArrayList<String> watchList) {
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
        };

        final HomeInputBoundary interactor = new HomeInteractor(stockData, watchListData, successPresenter);
        interactor.search(inputData);
    }
}
