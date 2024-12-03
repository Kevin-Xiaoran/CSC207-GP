package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import entity.SimulatedHolding;
import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioState;
import interface_adapter.portfolio.PortfolioViewModel;

/**
 * The View for the Portfolio Use Case.
 */
public class PortfolioView extends JPanel implements PropertyChangeListener {

    private final PortfolioViewModel portfolioViewModel;
    private final ViewManagerModel viewManagerModel;
    private PortfolioController portfolioController;
    private JPanel contentPanel;
    private JLabel currentAmount;
    private JLabel todayChangeLabel;
    private JLabel allTimeChangeLabel;

    public PortfolioView(PortfolioViewModel portfolioViewModel,
                         ViewManagerModel viewManagerModel) {
        this.portfolioViewModel = portfolioViewModel;
        this.portfolioViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // panel setup
        final int smallMargin = 10;
        final int mediumMargin = 20;
        final int mediumTextSize = 16;
        final JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
        statisticsPanel.setBorder(BorderFactory.createEmptyBorder(smallMargin, smallMargin, mediumMargin, smallMargin));
        statisticsPanel.setBackground(Color.WHITE);

        // Container for return button and current amount
        final JPanel topRowPanel = new JPanel();
        topRowPanel.setLayout(new BoxLayout(topRowPanel, BoxLayout.Y_AXIS));
        topRowPanel.setBackground(Color.WHITE);

        // Return button
        final String sansSerif = "SansSerif";
        final JButton backButton = new JButton("\u2190");
        backButton.setFont(new Font(sansSerif, Font.PLAIN, mediumMargin));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("home view");
                viewManagerModel.firePropertyChanged();
            }
        });

        // Current amount
        currentAmount = new JLabel("$0");
        currentAmount.setBorder(BorderFactory.createEmptyBorder(0, 0, smallMargin, 0));
        currentAmount.setFont(new Font(sansSerif, Font.PLAIN, mediumMargin));

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
        dummyValue.setFont(new Font(sansSerif, Font.PLAIN, mediumTextSize));
        mainPanel.add(dummyValue, BorderLayout.EAST);

        // Add topRowPanel to statisticsPanel
        statisticsPanel.add(mainPanel);

        // Portfolio amount changes
        todayChangeLabel = new JLabel("Today: $0 (0%)");
        todayChangeLabel.setFont(new Font(sansSerif, Font.PLAIN, mediumTextSize));
        todayChangeLabel.setBorder(BorderFactory.createEmptyBorder(smallMargin, 0, smallMargin, 0));
        statisticsPanel.add(todayChangeLabel);

        allTimeChangeLabel = new JLabel("All Time: $0 (0%)");
        allTimeChangeLabel.setFont(new Font(sansSerif, Font.PLAIN, mediumTextSize));
        allTimeChangeLabel.setBorder(BorderFactory.createEmptyBorder(smallMargin, 0, smallMargin, 0));
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
        final int width = 400;
        final int height = 300;
        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(width, height));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addStockItem(String code, double closePrice, double purchasePrice, int amount, double openPrice) {
        final JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBackground(Color.WHITE);
        // Create a matte border and an empty border
        final Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Combine them using CompoundBorder
        final Border combinedBorder = BorderFactory.createCompoundBorder(emptyBorder, matteBorder);
        stockPanel.setBorder(combinedBorder);

        // Calculate values
        final double currentValue = closePrice * amount;
        final double dailyChange = (closePrice - openPrice) * amount;
        final double allTimeChange = (closePrice - purchasePrice) * amount;
        final double dailyPercentage = ((closePrice - openPrice) / openPrice) * 100;
        final double allTimePercentage = ((closePrice - purchasePrice) / purchasePrice) * 100;

        // final values
        final int smallMargin = 10;
        final int mediumTextSize = 16;
        final int semiLargeTextSize = 18;
        final int largeTextSize = 24;
        final String sansSerif = "SansSerif";

        // Left part: Stock code and price
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        final JLabel stockCodeLabel = new JLabel(code);
        stockCodeLabel.setFont(new Font(sansSerif, Font.BOLD, largeTextSize));
        stockCodeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, smallMargin, 0));

        final JLabel stockPriceLabel = new JLabel("$" + String.format("%.2f", currentValue));
        stockPriceLabel.setFont(new Font(sansSerif, Font.PLAIN, semiLargeTextSize));
        stockPriceLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, smallMargin, 0));

        final JLabel stockAmountLabel = new JLabel("Amount: " + amount);
        stockAmountLabel.setFont(new Font(sansSerif, Font.PLAIN, mediumTextSize));

        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);
        leftPanel.add(stockAmountLabel);

        // Right part: daily change and total change
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(smallMargin, 0, 0, smallMargin));

        final JLabel dailyChangeLabel = new JLabel(String.format("%+.2f (%.2f%%)", dailyChange, dailyPercentage));
        dailyChangeLabel.setFont(new Font(sansSerif, Font.PLAIN, mediumTextSize));
        dailyChangeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, smallMargin, 0));

        final JLabel totalChangeLabel = new JLabel(String.format("%+.2f (%.2f%%)", allTimeChange, allTimePercentage));
        totalChangeLabel.setFont(new Font(sansSerif, Font.PLAIN, mediumTextSize));
        totalChangeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, smallMargin, 0));

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

    /**
     * Sets the controller for the portfolio.
     */
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

            final int percentageMultiplier = 100;

            final int minSize = Math.min(portfolioList.size(), stockList.size());
            for (int i = 0; i < minSize; i++) {
                final SimulatedHolding holding = portfolioList.get(i);
                final Stock stock = stockList.get(i);
                totalValue += stock.getClosePrice() * holding.getAmount();
                totalDailyChange += (stock.getClosePrice() - stock.getOpenPrice()) * holding.getAmount();
                totalAllTimeChange += (stock.getClosePrice() - holding.getPurchasePrice()) * holding.getAmount();
                addStockItem(holding.getSymbol(), stock.getClosePrice(), holding.getPurchasePrice(), holding.getAmount(), stock.getOpenPrice());
            }

            currentAmount.setText("$" + String.format("%.2f", totalValue));
            todayChangeLabel.setText(String.format("Today: %+.2f (%.2f%%)", totalDailyChange, (totalDailyChange / (totalValue - totalDailyChange)) * percentageMultiplier));
            allTimeChangeLabel.setText(String.format("All Time: %+.2f (%.2f%%)", totalAllTimeChange, (totalAllTimeChange / (totalValue - totalAllTimeChange)) * percentageMultiplier));

            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }
}
