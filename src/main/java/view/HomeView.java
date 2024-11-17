package view;

import interface_adapter.home_view.HomeController;
import interface_adapter.home_view.HomeState;
import interface_adapter.home_view.HomeViewModel;
import interface_adapter.login.LoginState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The View for the Home Page.
 */
public class HomeView extends AbstractViewWithBackButton implements ActionListener, PropertyChangeListener {

    private static final String RIGHTARROW = "->";

    private final String viewName = "home view";
    private final HomeViewModel homeViewModel;
    private HomeController homeController;

    // Search components
    private final JTextField searchTextField = new JTextField(30);

    // Stock view components
    private final JLabel stockLabel = new JLabel("Stock View");
    private final JButton stockButton = new JButton(RIGHTARROW);

    // Portfolio components
    private final JLabel portfolioLabel = new JLabel("Portfolio");
    private final JButton portfolioButton = new JButton(RIGHTARROW);

    // Watch list components
    private final JLabel watchListLabel = new JLabel("Watchlist");
    private final JButton watchListButton = new JButton(RIGHTARROW);
    private final JLabel firstStockLabel = new JLabel(" - AAPL");
    private final JButton firstStockButton = new JButton(RIGHTARROW);
    private final JLabel secondStockLabel = new JLabel(" - NVDA");
    private final JButton secondStockButton = new JButton(RIGHTARROW);
    private final JLabel thirdStockLabel = new JLabel(" - META");
    private final JButton thirdStockButton = new JButton(RIGHTARROW);

    // Login/Logout component
    private final JButton loginButton = new JButton("Login");
    private final JButton signupButton = new JButton("Sign up");

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        // Config search components style
        searchTextField.setText("AAPL");
        final JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchTextField);
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

        // Config stock view components style
        final JPanel stockViewPanel = new JPanel();
        stockViewPanel.setLayout(new BoxLayout(stockViewPanel, BoxLayout.LINE_AXIS));
        stockViewPanel.add(stockLabel);
        stockViewPanel.add(stockButton);
        stockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(stockButton)) {
                    final HomeState currentState = homeViewModel.getState();
                    currentState.setSymbol("AAPL");

                    homeController.search(currentState.getSymbol());
                }
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

        // Config login components style
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.LINE_AXIS));
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeController.switchToLoginView();
            }
        });

        // Config signup components style
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.LINE_AXIS));
        signupPanel.add(signupButton);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeController.switchToSignupView();
            }
        });

        // Config frame style
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add all components
        this.add(searchPanel);
        this.add(stockViewPanel);
        this.add(portfolioPanel);
        this.add(watchListPanel);
        this.add(loginPanel);
        this.add(signupPanel);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    void backButtonAction() {
        System.out.println("Back button clicked");
    }

    public String getViewName() {
        return viewName;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
