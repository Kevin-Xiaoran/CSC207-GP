package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio.PortfolioViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;
import javax.swing.BorderFactory;


public class PortfolioView extends JPanel {

    private final ViewManagerModel viewManagerModel;

    public PortfolioView(PortfolioViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // panel setup
        final JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
        statisticsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        statisticsPanel.setBackground(Color.WHITE);

        // Container for return button and current amount
        final JPanel topRowPanel = new JPanel();
        topRowPanel.setLayout(new BoxLayout(topRowPanel, BoxLayout.Y_AXIS));
        topRowPanel.setBackground(Color.WHITE);

        // Return button
        final JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Sample action, replace with your own logic
                viewManagerModel.setState("home view");
                viewManagerModel.firePropertyChanged();
            }
        });

        // Current amount
        final JLabel currentAmount = new JLabel("$12345");
        currentAmount.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        currentAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));

        // Add return button and current amount to the top row
        topRowPanel.add(backButton);
        topRowPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        topRowPanel.add(currentAmount);

        // Create a sub-panel with BorderLayout
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add top row panel to the left side (WEST)
        mainPanel.add(topRowPanel, BorderLayout.WEST);

        // Dummy value on the right side (EAST)
        final JLabel dummyValue = new JLabel("");
        dummyValue.setFont(new Font("SansSerif", Font.PLAIN, 16));
        mainPanel.add(dummyValue, BorderLayout.EAST);

        // Add topRowPanel to statisticsPanel
        statisticsPanel.add(mainPanel);

        // portfolio amount changes
        percentChangeLayout(statisticsPanel, "Today", "+$44.09", "+24.10%");
        percentChangeLayout(statisticsPanel, "All Time", "+$84.09", "+40.10%");
        add(statisticsPanel, BorderLayout.NORTH);

        // Create the content panel
        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Create a sub-panel with BorderLayout
        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);

        // Portfolio title on the left
        final JLabel portfolioTitle = new JLabel("Portfolio");
        portfolioTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        portfolioTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        titlePanel.add(portfolioTitle, BorderLayout.WEST);

        // Dummy text on the right
        final JLabel dummyLabel = new JLabel("");
        dummyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dummyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.add(dummyLabel, BorderLayout.EAST);

        // Add the sub-panel to the main content panel
        contentPanel.add(titlePanel);

        addStockItem(contentPanel, "AAPL", "$226.97", "−$0.26", "+0.11%", "+$44.09", "+24.10%");
        addStockItem(contentPanel, "COST", "$953.20", "+$39.27", "+4.30%", "+$386.00", "+68.03%");
        addStockItem(contentPanel, "QQQ", "$514.07", "+$0.56", "+0.60%", "+$141.25", "+37.87%");

        add(contentPanel, BorderLayout.CENTER);
    }

    private void percentChangeLayout(JPanel statisticsPanel, String title, String totalChange, String totalPercentage) {
        final JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Left part: Title
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        final JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        leftPanel.add(titleLabel);

        // Right part: up and down information
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

        final JLabel totalChangeLabel = new JLabel(totalChange + "(" + totalPercentage + ")");
        totalChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        rightPanel.add(totalChangeLabel);

        // Add all to displayPanel
        displayPanel.add(leftPanel, BorderLayout.WEST);
        displayPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        statisticsPanel.add(displayPanel);
    }

    private void addStockItem(JPanel contentPanel, String code, String price, String dailyChange, String dailyPercentage, String totalChange, String totalPercentage) {
        final JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBackground(Color.WHITE);
        // Create a matte border and an empty border
        final Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
        final Border emptyBorder = BorderFactory.createEmptyBorder(0, 30, 0, 0);

        // Combine them using CompoundBorder
        final Border combinedBorder = BorderFactory.createCompoundBorder(emptyBorder, matteBorder);

        // Set the combined border
        stockPanel.setBorder(combinedBorder);

        // Left part: Stock code and price
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        final JLabel stockCodeLabel = new JLabel(code);
        stockCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        stockCodeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        final JLabel stockPriceLabel = new JLabel(price);
        stockPriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);

        // Right part: up and down information
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

        final JLabel dailyChangeLabel = new JLabel(dailyChange + "(" + dailyPercentage + ")");
        dailyChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dailyChangeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        final JLabel totalChangeLabel = new JLabel(totalChange + "(" + totalPercentage + ")");
        totalChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        totalChangeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        rightPanel.add(dailyChangeLabel);
        rightPanel.add(totalChangeLabel);

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        contentPanel.add(stockPanel);
    }

    public String getViewName() {
        return "PortfolioView";
    }
}

