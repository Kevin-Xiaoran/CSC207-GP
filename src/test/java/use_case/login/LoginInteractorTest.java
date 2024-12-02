package use_case.login;

import data_access.FileUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    @Test
    void testLoginInputData() {
        // Test the LoginInputData class
        LoginInputData inputData = new LoginInputData("testUser", "testPassword");
        assertEquals("testUser", inputData.getUsername());
        assertEquals("testPassword", inputData.getPassword());
    }

    @Test
    void testLoginOutputData() {
        // Test the LoginOutputData class
        LoginOutputData outputData = new LoginOutputData("testUser", false);
        assertEquals("testUser", outputData.getUsername());
    }

    @Test
    void testUserDoesNotExist() {
        // Prepare input data
        LoginInputData inputData = new LoginInputData("nonexistentUser", "password123");

        // Use in-memory user data access object with a UserFactory
        UserFactory userFactory = new CommonUserFactory();
        InMemoryLoginUserDataAccess userDataAccessObject = new InMemoryLoginUserDataAccess(userFactory);

        // Create a test presenter
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                fail("Expected login failure, but login succeeded.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("nonexistentUser: Account does not exist.", errorMessage);
            }

            @Override
            public void switchToSignUpView() {
                fail("switchToSignUpView should not be called in this test.");
            }
        };

        // Create Interactor instance
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        // Execute test
        loginInteractor.execute(inputData);
    }

    @Test
    void testIncorrectPassword() {
        // Prepare input data
        LoginInputData inputData = new LoginInputData("existingUser", "wrongPassword");

        // Use in-memory user data access object and add a user using UserFactory
        UserFactory userFactory = new CommonUserFactory();
        InMemoryLoginUserDataAccess userDataAccessObject = new InMemoryLoginUserDataAccess(userFactory);
        User user = userFactory.create("existingUser", "correctPassword", new ArrayList<>(), new ArrayList<>());
        userDataAccessObject.save(user);

        // Create a test presenter
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                fail("Expected login failure, but login succeeded.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Incorrect password for \"existingUser\".", errorMessage);
            }

            @Override
            public void switchToSignUpView() {
                fail("switchToSignUpView should not be called in this test.");
            }
        };

        // Create Interactor instance
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        // Execute test
        loginInteractor.execute(inputData);
    }

    @Test
    void testSuccessfulLogin() {
        // Prepare input data
        LoginInputData inputData = new LoginInputData("existingUser", "correctPassword");

        // Use in-memory user data access object and add a user using UserFactory
        UserFactory userFactory = new CommonUserFactory();
        InMemoryLoginUserDataAccess userDataAccessObject = new InMemoryLoginUserDataAccess(userFactory);
        User user = userFactory.create("existingUser", "correctPassword", new ArrayList<>(), new ArrayList<>());
        userDataAccessObject.save(user);

        // Create a test presenter
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                assertEquals("existingUser", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Expected login success, but login failed: " + errorMessage);
            }

            @Override
            public void switchToSignUpView() {
                fail("switchToSignUpView should not be called in this test.");
            }
        };

        // Create a mock FileUserDataAccessObject
        MockFileUserDataAccessObject dataAccessObject = new MockFileUserDataAccessObject();

        // Create Interactor instance
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        // Use reflection to set the private final field 'dataAccessObject'
        try {
            Field dataAccessObjectField = LoginInteractor.class.getDeclaredField("dataAccessObject");
            dataAccessObjectField.setAccessible(true);
            dataAccessObjectField.set(loginInteractor, dataAccessObject);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set dataAccessObject via reflection: " + e.getMessage());
        }

        // Execute test
        loginInteractor.execute(inputData);

        // Verify that dataAccessObject methods were called
        assertTrue(dataAccessObject.isUserLoggedIn());
        assertTrue(dataAccessObject.isSaveUserLoginStatusCalled());

        // Verify that the current username was set
        assertEquals("existingUser", userDataAccessObject.getCurrentUsername());
    }

    @Test
    void testSwitchToSignUpView() {
        // Create a test presenter
        TestLoginOutputBoundary loginPresenter = new TestLoginOutputBoundary();

        // Use in-memory user data access object
        UserFactory userFactory = new CommonUserFactory();
        InMemoryLoginUserDataAccess userDataAccessObject = new InMemoryLoginUserDataAccess(userFactory);

        // Create Interactor instance
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        // Execute test
        loginInteractor.switchToSignUpView();

        // Verify that switchToSignUpView was called
        assertTrue(loginPresenter.switchCalled);
    }

    // In-memory user data access object implementation
    static class InMemoryLoginUserDataAccess implements LoginUserDataAccessInterface {
        private final Map<String, User> users = new HashMap<>();
        private String currentUsername;
        private final UserFactory userFactory;

        public InMemoryLoginUserDataAccess(UserFactory userFactory) {
            this.userFactory = userFactory;
        }

        @Override
        public boolean existsByName(String username) {
            return users.containsKey(username);
        }

        @Override
        public User get(String username) {
            return users.get(username);
        }

        @Override
        public void save(User user) {
            users.put(user.getName(), user);
        }

        @Override
        public void setCurrentUsername(String username) {
            this.currentUsername = username;
        }

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }
    }

    // Mock FileUserDataAccessObject
    static class MockFileUserDataAccessObject extends FileUserDataAccessObject {
        private boolean userLoggedIn = false;
        private boolean saveUserLoginStatusCalled = false;

        @Override
        public void setUserLoggedIn(boolean loggedIn) {
            this.userLoggedIn = loggedIn;
        }

        @Override
        public void saveUserLoginStatus() {
            this.saveUserLoginStatusCalled = true;
        }

        public boolean isUserLoggedIn() {
            return userLoggedIn;
        }

        public boolean isSaveUserLoginStatusCalled() {
            return saveUserLoginStatusCalled;
        }
    }

    // Test presenter class for switchToSignUpView test
    static class TestLoginOutputBoundary implements LoginOutputBoundary {
        boolean switchCalled = false;

        @Override
        public void prepareSuccessView(LoginOutputData outputData) {
            fail("Expected switchToSignUpView to be called, but prepareSuccessView was called.");
        }

        @Override
        public void prepareFailView(String errorMessage) {
            fail("Expected switchToSignUpView to be called, but prepareFailView was called.");
        }

        @Override
        public void switchToSignUpView() {
            switchCalled = true;
        }
    }
}
