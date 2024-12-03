package view;

import data_access.FileUserDataAccessObject;
import entity.SimulatedHoldingFactory;
import entity.Stock;
import interface_adapter.change_password.IsLoggedIn;
import interface_adapter.home_view.HomeController;
import interface_adapter.home_view.HomeState;
import interface_adapter.home_view.HomeViewModel;
import interface_adapter.login.LoginState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The View for the Home Page.
 */
public class HomeView extends JPanel implements PropertyChangeListener {

    private static final String RIGHTARROW = "->";
    private static final String FONTFAMILY = "SansSerif";

    private final String viewName = "home view";
    private final HomeViewModel homeViewModel;
    private HomeController homeController;

    // Welcome component
    private final JLabel welcomeLabel;

    // Search components
    private final JTextField searchTextField = new JTextField(20);
    private final JLabel searchErrorMessageLabel = new JLabel();

    // Stock view components
    private final JButton searchButton = new JButton(RIGHTARROW);

    // Portfolio components
    private final JLabel portfolioLabel = new JLabel("Portfolio");
    private final JButton portfolioButton = new JButton(RIGHTARROW);

    // Watch list components
    private final JLabel watchListLabel = new JLabel("Watchlist");
    private final JButton watchListButton = new JButton(RIGHTARROW);
    private final JPanel watchListContentPanel = new JPanel();

    // Login/Logout component
    private JButton loginButton = new JButton();

    // Add DAO
    private final FileUserDataAccessObject dataAccessObject;

    public HomeView(HomeViewModel homeViewModel) {

        this.dataAccessObject = new FileUserDataAccessObject();

        // Set up homeViewModel and listen any upcoming events
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        // Config welcome label style
        final JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.setBackground(Color.WHITE);
        welcomeLabel = new JLabel("Welcome");
        updateLabelStyle(welcomeLabel, 24);
        welcomePanel.add(welcomeLabel);

        // Config search components style
        final JPanel searchPanel = new JPanel();
        final JPanel searchTextFieldPanel = new JPanel();
        final JPanel searchErrorMessagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchTextField.setText("NVDA");
        searchErrorMessageLabel.setForeground(Color.RED);
        searchErrorMessageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchTextFieldPanel.setBackground(Color.WHITE);
        searchTextFieldPanel.add(searchTextField);
        searchTextFieldPanel.add(searchButton);
        searchErrorMessagePanel.setBackground(Color.WHITE);
        searchErrorMessagePanel.add(searchErrorMessageLabel);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(searchTextFieldPanel);
        searchPanel.add(searchErrorMessagePanel);
        // Add search stock button listener
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {
                        final HomeState currentState = homeViewModel.getState();
                        homeController.search(currentState.getSymbol());
                }
            }
        });
        // Add textField listener
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final HomeState currentState = homeViewModel.getState();
                currentState.setSymbol(new String(searchTextField.getText()));
                homeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        // Config portfolio components style
        final JPanel portfolioPanel = new JPanel();
        final JPanel portfolioLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel portfolioRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateLabelStyle(portfolioLabel, 20);
        portfolioLeftPanel.setBackground(Color.WHITE);
        portfolioLeftPanel.add(portfolioLabel);
        portfolioRightPanel.setBackground(Color.WHITE);
        portfolioRightPanel.add(portfolioButton);
        portfolioPanel.setBackground(Color.WHITE);
        portfolioPanel.setLayout(new BoxLayout(portfolioPanel, BoxLayout.X_AXIS));
        portfolioPanel.add(portfolioLeftPanel);
        portfolioPanel.add(portfolioRightPanel);
        portfolioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (dataAccessObject.isUserLoggedIn()) {
                    homeController.switchToPortfolio();
                }
                else {
                    showLoginRequiredDialog();
                }
            }
        });

        // Config watch list components style
        final JPanel watchListPanel = new JPanel();
        final JPanel watchListLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel watchListRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateLabelStyle(watchListLabel, 20);
        watchListPanel.setBackground(Color.WHITE);
        watchListLeftPanel.setBackground(Color.WHITE);
        watchListRightPanel.setBackground(Color.WHITE);
        watchListPanel.setLayout(new BoxLayout(watchListPanel, BoxLayout.LINE_AXIS));
        watchListLeftPanel.add(watchListLabel);
        watchListRightPanel.add(watchListButton);
        watchListPanel.add(watchListLeftPanel);
        watchListPanel.add(watchListRightPanel);
        watchListPanel.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));
        watchListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (dataAccessObject.isUserLoggedIn()) {
                    homeController.switchToWatchList();
                }
                else {
                    showLoginRequiredDialog();
                }
            }
        });
        // Watch list stock information
        watchListContentPanel.setLayout(new BoxLayout(watchListContentPanel, BoxLayout.Y_AXIS));
        watchListContentPanel.setBackground(Color.WHITE);

        // Config login components style
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel loginPanel = new JPanel();
        loginButton.setPreferredSize(new Dimension(150, 32));
        loginPanel.add(loginButton);
        changeLoginButtonText();
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (dataAccessObject.isUserLoggedIn()) {
                    // If you are logged in, the logout dialog box is displayed
                    showLogoutDialog();
                }
                else {
                    // If you are not logged in, switch to the login view
                    homeController.switchToLoginView();
                    dataAccessObject.setUserLoggedIn(true);
                    changeLoginButtonText();
                }
            }
        });

        // Config frame style
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add all components
        this.add(welcomePanel);
        this.add(searchPanel);
        this.add(portfolioPanel);
        this.add(watchListPanel);
        this.add(watchListContentPanel);
        this.add(loginPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HomeState state = (HomeState) evt.getNewValue();
        if (evt.getPropertyName().equals("getWatchList")) {
            // Display top 3 watchlist data
            ArrayList<Stock> watchList = state.getWatchList();
            if (watchList.size() > 3) {
                watchList = new ArrayList<Stock>(watchList.subList(0, 3));
            }

            watchListContentPanel.removeAll();
            updateWatchListComponents(watchList);
            watchListContentPanel.revalidate();
            watchListContentPanel.repaint();
        }
        else if (evt.getPropertyName().equals("error")) {
            searchErrorMessageLabel.setText(state.getErrorMessage());
        }
    }

    /**
     * Updates the style of a JLabel with the specified font size.
     *
     * @param label    The JLabel to update.
     * @param fontSize The desired font size.
     */
    public void updateLabelStyle(JLabel label, int fontSize) {
        label.setFont(new Font(FONTFAMILY, Font.BOLD, fontSize));
    }

    /**
     * Updates the watch list components with the given list of stocks.
     *
     * @param watchList The list of stocks to display.
     */
    public void updateWatchListComponents(ArrayList<Stock> watchList) {
        for (Stock stock : watchList) {
            final String symbol = stock.getSymbol();
            final String price = String.valueOf(stock.getClosePrice());
            final String dailyChange = String.valueOf(stock.getDailyChange());
            final String dailyPercentage = stock.getDailyPercentage() + "%";
            this.addWatchListItem(watchListContentPanel, symbol, price, dailyChange, dailyPercentage);
        }
    }

    private void addWatchListItem(JPanel contentPanel, String code,
                                  String price, String dailyChange, String dailyPercentage) {
        final JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBackground(Color.WHITE);

        // Left part: Stock code and price
        final JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(16, 64, 0, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        // Right part: up and down information
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(24, 0, 0, 5));

        final JLabel stockCodeLabel = new JLabel("- " + code);
        stockCodeLabel.setFont(new Font(FONTFAMILY, Font.BOLD, 16));
        final JLabel stockPriceLabel = new JLabel(price);
        stockPriceLabel.setFont(new Font(FONTFAMILY, Font.PLAIN, 14));

        final JLabel dailyChangeLabel = new JLabel(dailyChange + " (" + dailyPercentage + ")");
        dailyChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);
        rightPanel.add(dailyChangeLabel);

        final JButton viewStockButton = new JButton(RIGHTARROW);
        viewStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(viewStockButton)) {
                    final HomeState currentState = homeViewModel.getState();
                    currentState.setSymbol(code);

                    homeController.search(currentState.getSymbol());
                }
            }
        });

        // rightPanel.add(viewStockButton);

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        contentPanel.add(stockPanel);
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the HomeController for this view.
     *
     * @param homeController The HomeController to set.
     */
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;

        // Preload watchList data
        homeController.getWatchList();
    }

    /**
     * Displays a logout confirmation dialog and logs out if confirmed.
     */
    public void showLogoutDialog() {
        final int response = JOptionPane.showConfirmDialog(this,
                "Do you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            dataAccessObject.setUserLoggedIn(false);
            dataAccessObject.saveUserLoginStatus();
            changeLoginButtonText();
            homeController.deleteLocalData();
        }
    }

    /**
     * Updates the text of the login button based on the login status.
     */
    public void changeLoginButtonText() {
        if (dataAccessObject.isUserLoggedIn()) {
            loginButton.setText("Log Out");
        }
        else {
            loginButton.setText("Log In");
        }
        homeViewModel.firePropertyChanged();
    }

    /**
     * Displays a dialog indicating that login is required and switches to the login view.
     */
    public void showLoginRequiredDialog() {
        final Object[] options = {"Go to Login"};
        final int response = JOptionPane.showOptionDialog(
                this,
                "You need to login first",
                "Login Required",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]
        );

        if (response == JOptionPane.YES_OPTION) {
            dataAccessObject.setUserLoggedIn(true);
            dataAccessObject.saveUserLoginStatus();
            changeLoginButtonText();
            homeController.switchToLoginView();
        }
    }
}
