package use_case.logout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    @Test
    void testLogoutInputData() {
        // Test the LogoutInputData class
        LogoutInputData inputData = new LogoutInputData("testUser");
        assertEquals("testUser", inputData.getUsername());
    }

    @Test
    void testLogoutOutputData() {
        // Test the LogoutOutputData class
        LogoutOutputData outputData = new LogoutOutputData("testUser", false);
        assertEquals("testUser", outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testSuccessfulLogout() {
        // Prepare input data
        LogoutInputData inputData = new LogoutInputData("existingUser");

        // Use in-memory user data access object
        InMemoryLogoutUserDataAccess userDataAccessObject = new InMemoryLogoutUserDataAccess();
        userDataAccessObject.setCurrentUsername("existingUser");

        // Create a test presenter
        TestLogoutOutputBoundary logoutPresenter = new TestLogoutOutputBoundary();

        // Create Interactor instance
        LogoutInteractor logoutInteractor = new LogoutInteractor(userDataAccessObject, logoutPresenter);

        // Execute test
        logoutInteractor.execute(inputData);

        // Verify that the current username is set to null
        assertNull(userDataAccessObject.getCurrentUsername());

        // Verify that the presenter was called correctly
        assertTrue(logoutPresenter.successViewCalled);
        assertEquals("existingUser", logoutPresenter.outputData.getUsername());
    }

    @Test
    void testLogoutWhenNoUserLoggedIn() {
        // Prepare input data with no user logged in
        LogoutInputData inputData = new LogoutInputData(null);

        // Use in-memory user data access object
        InMemoryLogoutUserDataAccess userDataAccessObject = new InMemoryLogoutUserDataAccess();

        // Create a test presenter
        TestLogoutOutputBoundary logoutPresenter = new TestLogoutOutputBoundary();

        // Create Interactor instance
        LogoutInteractor logoutInteractor = new LogoutInteractor(userDataAccessObject, logoutPresenter);

        // Execute test
        logoutInteractor.execute(inputData);

        // Verify that the current username is still null
        assertNull(userDataAccessObject.getCurrentUsername());

        // Verify that the presenter was called correctly
        assertTrue(logoutPresenter.successViewCalled);
        assertNull(logoutPresenter.outputData.getUsername());
    }

    // In-memory user data access object implementation
    static class InMemoryLogoutUserDataAccess implements LogoutUserDataAccessInterface {
        private String currentUsername;

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public void setCurrentUsername(String username) {
            this.currentUsername = username;
        }
    }

    // Test presenter class
    static class TestLogoutOutputBoundary implements LogoutOutputBoundary {
        boolean successViewCalled = false;
        LogoutOutputData outputData;

        @Override
        public void prepareSuccessView(LogoutOutputData outputData) {
            this.successViewCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            fail("Expected success, but failure view was called with message: " + errorMessage);
        }
    }
}
