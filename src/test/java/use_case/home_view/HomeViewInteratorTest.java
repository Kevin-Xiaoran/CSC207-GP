package use_case.home_view;

import data_access.DBUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.Test;
import use_case.login.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HomeViewInteratorTest {

    @Test
    public void successTest() {
//        LoginInputData inputData = new LoginInputData("Paul", "password");
//        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // For the success test, we need to add Paul to the data access repository before we log in.
//        UserFactory factory = new CommonUserFactory();
//        User user = factory.create("Paul", "password");
//        userRepository.save(user);
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
//            @Override
//            public void prepareSuccessView(LoginOutputData user) {
//                assertEquals("Paul", user.getUsername());
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
//        interactor.execute(inputData);

        final HomeInputData inputData = new HomeInputData("NVDA");
        final UserFactory userFactory = new CommonUserFactory();
        final StockFactory stockFactory = new CommonStockFactory();
        final HomeDataAccessInterface homeData = new DBUserDataAccessObject(userFactory, stockFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData searchOutputData) {
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
            public void switchToWatchList() {
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
        };

        final HomeInputBoundary interactor = new HomeInteractor(homeData, successPresenter);
        interactor.search(inputData);
    }
}
