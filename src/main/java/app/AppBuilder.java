package app;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBUserDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.home_view.HomeController;
import interface_adapter.home_view.HomePresenter;
import interface_adapter.home_view.HomeViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.stock_view.StockViewModel;
import interface_adapter.watchlist_view.WatchListViewModel;
import interface_adapter.portfolio.PortfolioViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.home_view.*;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final StockFactory stockFactory = new CommonStockFactory();
    private final SimulatedHoldingFactory simulatedHoldingFactory = new CommonSimulatedHoldingFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject(userFactory, stockFactory);
    private final FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject(stockFactory, simulatedHoldingFactory);

    private HomeView homeView;
    private HomeViewModel homeViewModel;
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private WatchListView watchListView;
    private WatchListViewModel watchListViewModel;
    private PortfolioView portfolioView;
    private PortfolioViewModel portfolioViewModel;
    private StockView stockView;
    private StockViewModel stockViewModel;
    private ArrayList watchList = fileUserDataAccessObject.getWatchList();

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Home View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        homeViewModel = new HomeViewModel();
        homeView = new HomeView(homeViewModel);
        cardPanel.add(homeView, homeView.getViewName());

        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel,viewManagerModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel, viewManagerModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel =  new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the WatchList View to the application.
     * @return this builder
     */
    public AppBuilder addWatchListView() {
        watchListViewModel = new WatchListViewModel();
        watchListView = new WatchListView(watchListViewModel, viewManagerModel,dbUserDataAccessObject);
        cardPanel.add(watchListView, watchListView.getViewName());
        return this;
    }

    /**
     * Adds the Portfolio View to the application.
     * @return this builder
     */
    public AppBuilder addPortfolioView() {
        portfolioViewModel = new PortfolioViewModel();
        portfolioView = new PortfolioView(portfolioViewModel, viewManagerModel);
        cardPanel.add(portfolioView, portfolioView.getViewName());
        return this;
    }

    /**
     * Adds the Stock View to the application.
     * @return this builder
     */
    public AppBuilder addStockView() {
        stockViewModel = new StockViewModel();
        stockView = new StockView(viewManagerModel);
        cardPanel.add(stockView, stockView.getViewName());
        return this;
    }

    /**
     * Adds the Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomeUseCase() {
        final HomeOutputBoundary homeOutputBoundary = new HomePresenter(homeViewModel,
                loginViewModel, signupViewModel, viewManagerModel, portfolioViewModel, stockViewModel);
        final HomeInputBoundary homeInteractor = new HomeInteractor(dbUserDataAccessObject, homeOutputBoundary);

        final HomeController controller = new HomeController(homeInteractor);
        homeView.setHomeController(controller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                dbUserDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, signupViewModel, homeViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                dbUserDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(dbUserDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(dbUserDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Stock MarketPlace");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
