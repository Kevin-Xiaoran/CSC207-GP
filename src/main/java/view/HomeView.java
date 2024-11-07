package view;

import interface_adapter.home_view.HomeController;
import interface_adapter.home_view.HomeViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/**
 * The View for the Home Page.
 */
public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {

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

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        // Config search components style
        searchTextField.setText("Search");
        final JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchTextField);

        // Config stock view components style
        final JPanel stockViewPanel = new JPanel();
        stockViewPanel.setLayout(new BoxLayout(stockViewPanel, BoxLayout.LINE_AXIS));
        stockViewPanel.add(stockLabel);
        stockViewPanel.add(stockButton);
        stockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeController.search("NVDA");
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

        // Config frame style
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add all components
        this.add(searchPanel);
        this.add(stockViewPanel);
        this.add(portfolioPanel);
        this.add(watchListPanel);
        this.add(loginPanel);
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

    public String getViewName() {
        return viewName;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
