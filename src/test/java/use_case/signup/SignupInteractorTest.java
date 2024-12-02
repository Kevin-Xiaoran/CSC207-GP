package use_case.signup;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void testSignupInputData() {
        // Test the SignupInputData class
        SignupInputData inputData = new SignupInputData("testUser", "testPassword", "testPassword");
        assertEquals("testUser", inputData.getUsername());
        assertEquals("testPassword", inputData.getPassword());
        assertEquals("testPassword", inputData.getRepeatPassword());
    }

    @Test
    void testSignupOutputData() {
        // Test the SignupOutputData class
        SignupOutputData outputData = new SignupOutputData("testUser", false);
        assertEquals("testUser", outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testUserAlreadyExists() {
        // Prepare input data
        SignupInputData inputData = new SignupInputData("existingUser", "password123", "password123");

        // Use in-memory user data access object with a UserFactory
        UserFactory userFactory = new CommonUserFactory();
        InMemorySignupUserDataAccess userDataAccessObject = new InMemorySignupUserDataAccess(userFactory);

        // Add an existing user
        User existingUser = userFactory.create("existingUser", "oldPassword", new ArrayList<>(), new ArrayList<>());
        userDataAccessObject.save(existingUser);

        // Create a test presenter
        SignupOutputBoundary userPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Expected failure due to existing user, but success view was called.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("User already exists.", errorMessage);
            }

            @Override
            public void switchToLoginView() {
                fail("switchToLoginView should not be called in this test.");
            }
        };

        // Create Interactor instance
        SignupInteractor interactor = new SignupInteractor(userDataAccessObject, userPresenter, userFactory);

        // Execute test
        interactor.execute(inputData);

        // No additional assertions needed; logic is verified in the presenter
    }

    @Test
    void testPasswordsDoNotMatch() {
        // Prepare input data
        SignupInputData inputData = new SignupInputData("newUser", "password123", "password456");

        // Use in-memory user data access object with a UserFactory
        UserFactory userFactory = new CommonUserFactory();
        InMemorySignupUserDataAccess userDataAccessObject = new InMemorySignupUserDataAccess(userFactory);

        // Create a test presenter
        SignupOutputBoundary userPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Expected failure due to password mismatch, but success view was called.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Passwords don't match.", errorMessage);
            }

            @Override
            public void switchToLoginView() {
                fail("switchToLoginView should not be called in this test.");
            }
        };

        // Create Interactor instance
        SignupInteractor interactor = new SignupInteractor(userDataAccessObject, userPresenter, userFactory);

        // Execute test
        interactor.execute(inputData);

        // No additional assertions needed; logic is verified in the presenter
    }

    @Test
    void testSuccessfulSignup() {
        // Prepare input data
        SignupInputData inputData = new SignupInputData("newUser", "password123", "password123");

        // Use in-memory user data access object with a UserFactory
        UserFactory userFactory = new CommonUserFactory();
        InMemorySignupUserDataAccess userDataAccessObject = new InMemorySignupUserDataAccess(userFactory);

        // Create a test presenter
        TestSignupOutputBoundary userPresenter = new TestSignupOutputBoundary();

        // Create Interactor instance
        SignupInteractor interactor = new SignupInteractor(userDataAccessObject, userPresenter, userFactory);

        // Execute test
        interactor.execute(inputData);

        // Verify that the user was saved
        assertTrue(userDataAccessObject.existsByName("newUser"));
        User savedUser = userDataAccessObject.get("newUser");
        assertEquals("password123", savedUser.getPassword());

        // Verify that the presenter was called correctly
        assertTrue(userPresenter.successViewCalled);
        assertEquals("newUser", userPresenter.outputData.getUsername());
    }

    @Test
    void testSwitchToLoginView() {
        // Create a test presenter
        TestSignupOutputBoundary userPresenter = new TestSignupOutputBoundary();

        // Use in-memory user data access object
        UserFactory userFactory = new CommonUserFactory();
        InMemorySignupUserDataAccess userDataAccessObject = new InMemorySignupUserDataAccess(userFactory);

        // Create Interactor instance
        SignupInteractor interactor = new SignupInteractor(userDataAccessObject, userPresenter, userFactory);

        // Execute test
        interactor.switchToLoginView();

        // Verify that switchToLoginView was called
        assertTrue(userPresenter.switchCalled);
    }

    // In-memory user data access object implementation
    static class InMemorySignupUserDataAccess implements SignupUserDataAccessInterface {
        private final UserFactory userFactory;
        private final ArrayList<User> users = new ArrayList<>();

        public InMemorySignupUserDataAccess(UserFactory userFactory) {
            this.userFactory = userFactory;
        }

        public void save(User user) {
            users.add(user);
        }

        public User get(String username) {
            for (User user : users) {
                if (user.getName().equals(username)) {
                    return user;
                }
            }
            return null;
        }

        @Override
        public boolean existsByName(String username) {
            return get(username) != null;
        }
    }

    // Test presenter class
    static class TestSignupOutputBoundary implements SignupOutputBoundary {
        boolean successViewCalled = false;
        boolean switchCalled = false;
        SignupOutputData outputData;

        @Override
        public void prepareSuccessView(SignupOutputData outputData) {
            this.successViewCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            fail("Expected success, but failure view was called with message: " + errorMessage);
        }

        @Override
        public void switchToLoginView() {
            this.switchCalled = true;
        }
    }
}
