package view;

import entity.SimulatedHolding;
import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioState;
import interface_adapter.portfolio.PortfolioViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class PortfolioView extends JPanel implements PropertyChangeListener {

    private final PortfolioViewModel portfolioViewModel;
    private final ViewManagerModel viewManagerModel;
    private PortfolioController portfolioController;
    private JPanel contentPanel;
    private JLabel currentAmount;
    private JLabel todayChangeLabel;
    private JLabel allTimeChangeLabel;

    public PortfolioView(PortfolioViewModel portfolioViewModel, ViewManagerModel viewManagerModel) {
        this.portfolioViewModel = portfolioViewModel;
        this.portfolioViewModel.addPropertyChangeListener(this);
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
        final JButton backButton = new JButton("\u2190");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        backButton.addActionListener(e -> {
            viewManagerModel.setState("home view");
            viewManagerModel.firePropertyChanged();
        });

        // Current amount
        currentAmount = new JLabel("$0");
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

        // Portfolio amount changes
        todayChangeLabel = new JLabel("Today: $0 (0%)");
        todayChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        todayChangeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        statisticsPanel.add(todayChangeLabel);

        allTimeChangeLabel = new JLabel("All Time: $0 (0%)");
        allTimeChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        allTimeChangeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        statisticsPanel.add(allTimeChangeLabel);

        add(statisticsPanel, BorderLayout.NORTH);

        // Create the content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Wrap the content panel in a JScrollPane
        final JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addStockItem(String code, double closePrice, double purchasePrice, double amount, double openPrice) {
        final JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBackground(Color.WHITE);
        // Create a matte border and an empty border
        final Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Combine them using CompoundBorder
        final Border combinedBorder = BorderFactory.createCompoundBorder(emptyBorder, matteBorder);
        stockPanel.setBorder(combinedBorder);

        // Calculate values
        double currentValue = closePrice * amount;
        double dailyChange = (closePrice - openPrice) * amount;
        double allTimeChange = (closePrice - purchasePrice) * amount;
        double dailyPercentage = openPrice != 0 ? ((closePrice - openPrice) / openPrice) * 100 : 0;
        double allTimePercentage = purchasePrice != 0 ? ((closePrice - purchasePrice) / purchasePrice) * 100 : 0;

        // Left part: Stock code and price
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        final JLabel stockCodeLabel = new JLabel(code);
        stockCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        stockCodeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        final JLabel stockPriceLabel = new JLabel("$" + String.format("%.2f", currentValue));
        stockPriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stockPriceLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        final JLabel stockAmountLabel = new JLabel("Amount: " + amount);
        stockAmountLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);
        leftPanel.add(stockAmountLabel);

        // Right part: daily change and total change
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

        final JLabel dailyChangeLabel = new JLabel(String.format("%+.2f (%.2f%%)", dailyChange, dailyPercentage));
        dailyChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dailyChangeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        final JLabel totalChangeLabel = new JLabel(String.format("%+.2f (%.2f%%)", allTimeChange, allTimePercentage));
        totalChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        totalChangeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        rightPanel.add(dailyChangeLabel);
        rightPanel.add(totalChangeLabel);

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information to the content panel
        contentPanel.add(stockPanel);
    }

    public String getViewName() {
        return "PortfolioView";
    }

    public void setController(PortfolioController controller) {
        this.portfolioController = controller;
        controller.getPortfolioList();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("getPortfolioList")) {
            final PortfolioState portfolioState = portfolioViewModel.getState();
            final ArrayList<SimulatedHolding> portfolioList = portfolioState.getSimulatedHoldings();
            final ArrayList<Stock> stockList = portfolioState.getStocks();

            double totalValue = 0;
            double totalDailyChange = 0;
            double totalAllTimeChange = 0;

            int minSize = Math.min(portfolioList.size(), stockList.size());
            for (int i = 0; i < minSize; i++) {
                SimulatedHolding holding = portfolioList.get(i);
                Stock stock = stockList.get(i);
                totalValue += stock.getClosePrice() * holding.getAmount();
                totalDailyChange += (stock.getClosePrice() - stock.getOpenPrice()) * holding.getAmount();
                totalAllTimeChange += (stock.getClosePrice() - holding.getPurchasePrice()) * holding.getAmount();
                addStockItem(holding.getSymbol(), stock.getClosePrice(), holding.getPurchasePrice(), holding.getAmount(), stock.getOpenPrice());
            }

            currentAmount.setText("$" + String.format("%.2f", totalValue));
            todayChangeLabel.setText(String.format("Today: %+.2f (%.2f%%)", totalDailyChange, totalValue != 0 ? (totalDailyChange / totalValue) * 100 : 0));
            allTimeChangeLabel.setText(String.format("All Time: %+.2f (%.2f%%)", totalAllTimeChange, totalValue != 0 ? (totalAllTimeChange / totalValue) * 100 : 0));

            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }
}
