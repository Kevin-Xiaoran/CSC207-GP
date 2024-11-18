package view;

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

    private final String viewName = "home view";
    private final HomeViewModel homeViewModel;
    private HomeController homeController;

    // Search components
    private final JTextField searchTextField = new JTextField(20);

    // Stock view components
    private final JLabel stockLabel = new JLabel("Stock View");
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

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        // Config search components style
        searchTextField.setText("NVDA");
        final JPanel searchPanel = new JPanel();
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        // Add search stock button listener
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {
                    final HomeState currentState = homeViewModel.getState();
                    currentState.setSymbol("NVDA");

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
        portfolioPanel.setLayout(new BoxLayout(portfolioPanel, BoxLayout.LINE_AXIS));
        portfolioPanel.add(portfolioLabel);
        portfolioPanel.add(portfolioButton);
        portfolioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeController.switchToPortfolio();
            }
        });

        // Config watch list components style
        final JPanel watchListPanel = new JPanel();
        watchListPanel.setLayout(new BoxLayout(watchListPanel, BoxLayout.LINE_AXIS));
        watchListPanel.add(watchListLabel);
        watchListPanel.add(watchListButton);
        watchListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeController.switchToWatchList();
            }
        });
        // Watch list stock information
        watchListContentPanel.setLayout(new BoxLayout(watchListContentPanel, BoxLayout.Y_AXIS));
        watchListContentPanel.setBackground(Color.WHITE);

        // Config login components style
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.LINE_AXIS));
        loginPanel.add(loginButton);
        loginButton.setText("Log Out");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                if (IsLoggedIn.isLoggedIn()) {
//                    showLogoutDialog();
//                }
//                else {
//                    homeController.switchToLoginView();
//                }
                // Preload watchList data
                homeController.getWatchList();
            }
        });

        // Config frame style
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add all components
        this.add(searchPanel);
        this.add(portfolioPanel);
        this.add(watchListPanel);
        this.add(watchListContentPanel);
        this.add(loginPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HomeState state = (HomeState) evt.getNewValue();
        final ArrayList<Stock> watchList = state.getWatchList();
        watchListContentPanel.removeAll();
        updateWatchListComponents(watchList);
        watchListContentPanel.revalidate();
        watchListContentPanel.repaint();
    }

    public void updateWatchListComponents(ArrayList<Stock> watchList) {
        for (Stock stock : watchList) {
            this.addWatchListItem(watchListContentPanel, stock.getSymbol(), String.valueOf(stock.getClosePrice()));
        }
    }

    private void addWatchListItem(JPanel contentPanel, String code, String price) {
        final JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        stockPanel.setBackground(Color.WHITE);

        // Left part: Stock code and price
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        final JLabel stockCodeLabel = new JLabel(code);
        stockCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        final JLabel stockPriceLabel = new JLabel(price);
        stockPriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);

        // Right part: up and down information
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

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

        rightPanel.add(viewStockButton);

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        contentPanel.add(stockPanel);
    }

    public String getViewName() {
        return viewName;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;

        // Preload watchList data
        homeController.getWatchList();
    }

    public void changeLoginButtonText() {
        if (IsLoggedIn.isLoggedIn()) {
            loginButton.setText("Log Out");
        }
        else {
            loginButton.setText("Log In");
        }
    }

    public void showLogoutDialog() {
        final int response = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            IsLoggedIn.setLoggedIn(false);
            changeLoginButtonText();
        }
    }
}
