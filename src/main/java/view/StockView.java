package view;

import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.stock_view.StockViewModel;
import interface_adapter.stock_view.StockController;
import interface_adapter.stock_view.StockViewState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for displaying detailed information about a single stock.
 */
public class StockView extends AbstractViewWithBackButton implements PropertyChangeListener {

    private final ViewManagerModel viewManagerModel;
    private final StockViewModel stockViewModel;
    private StockController stockController;

    private JLabel stockSymbolLabel;
    private JLabel stockPriceLabel;
    private JLabel openPriceLabel;
    private JLabel closePriceLabel;
    private JLabel lowPriceLabel;
    private JLabel highPriceLabel;
    private JLabel volumeLabel;
    private JButton favoriteButton;
    private String previousView;

    public StockView(StockViewModel stockViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.stockViewModel = stockViewModel;

        this.stockViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        initializeComponents(contentPanel);

        add(Box.createVerticalGlue());
        add(contentPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(createActionButtonsPanel());
        add(Box.createVerticalGlue());
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    private void initializeComponents(JPanel contentPanel) {
        stockSymbolLabel = createLabel("", 24, Font.BOLD);
        stockPriceLabel = createLabel("", 18, Font.PLAIN);

        final JPanel stockInfoPanel = new JPanel();
        stockInfoPanel.setLayout(new BoxLayout(stockInfoPanel, BoxLayout.Y_AXIS));
        stockInfoPanel.setBackground(Color.WHITE);
        stockInfoPanel.add(stockSymbolLabel);
        stockInfoPanel.add(stockPriceLabel);

        openPriceLabel = createLabel("", 16, Font.PLAIN);
        closePriceLabel = createLabel("", 16, Font.PLAIN);
        lowPriceLabel = createLabel("", 16, Font.PLAIN);
        highPriceLabel = createLabel("", 16, Font.PLAIN);
        volumeLabel = createLabel("", 16, Font.PLAIN);

        contentPanel.add(stockInfoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(openPriceLabel);
        contentPanel.add(closePriceLabel);
        contentPanel.add(lowPriceLabel);
        contentPanel.add(highPriceLabel);
        contentPanel.add(volumeLabel);

        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        stockInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private JPanel createActionButtonsPanel() {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JButton buyButton = new JButton("BUY");
        buyButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        buyButton.setFocusPainted(false);

        // Buy Button Action: Invoke the controller to buy stock
        buyButton.addActionListener(e -> {
            Stock currentStock = stockViewModel.getState().getStock();
            if (currentStock != null && stockController != null) {
                stockController.buyStock(currentStock);
            }
        });

        // Star button for favorite
        favoriteButton = new JButton("☆");
        favoriteButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        favoriteButton.setFocusPainted(false);

        // Favorite Button Action: Add or remove stock from watchlist
        favoriteButton.addActionListener(e -> {
            Stock currentStock = stockViewModel.getState().getStock();
            if (currentStock != null && stockController != null) {
                boolean isFavorite = "★".equals(favoriteButton.getText());
                stockController.toggleWatchlist(currentStock, !isFavorite);

                // Toggle button text between filled and empty star
                favoriteButton.setText(isFavorite ? "☆" : "★");
            }
        });

        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(buyButton);
        actionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        actionPanel.add(favoriteButton);
        actionPanel.add(Box.createHorizontalGlue());

        return actionPanel;
    }

    public void updateStockData(Stock stock) {
        stockSymbolLabel.setText(stock.getSymbol());
        stockPriceLabel.setText("Current Price: $" + stock.getClosePrice());
        openPriceLabel.setText("Open Price: $" + stock.getOpenPrice());
        closePriceLabel.setText("Close Price: $" + stock.getClosePrice());
        lowPriceLabel.setText("Low: $" + stock.getLow());
        highPriceLabel.setText("High: $" + stock.getHigh());

        double volume = stock.getVolume();
        String volumeText = (volume >= 1_000_000) ? (volume / 1_000_000) + " M" :
                (volume >= 1_000) ? (volume / 1_000) + " K" : String.valueOf(volume);
        volumeLabel.setText("Volume: " + volumeText);

        // 星星按钮的可见性
        updateFavoriteButtonVisibility(stock.getSymbol());
    }

    private void updateFavoriteButtonVisibility(String stockSymbol) {
        // 隐藏 AAPL, COST, NVDA 的星星按钮
        if ("AAPL".equals(stockSymbol) || "COST".equals(stockSymbol) || "NVDA".equals(stockSymbol)) {
            favoriteButton.setVisible(false);
        }
        else {
            favoriteButton.setVisible(true);
        }
    }

    private JLabel createLabel(String text, int fontSize, int fontStyle) {
        final JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", fontStyle, fontSize));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public String getViewName() {
        return "StockView";
    }

    @Override
    void backButtonAction() {
        if ("StockView".equals(viewManagerModel.getState())) {
            viewManagerModel.setState("WatchListView");
        }
        else {
            viewManagerModel.setState("home view");
        }
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final StockViewState stockViewState = stockViewModel.getState();
        if ("switchToStockView".equals(evt.getPropertyName())) {
            final Stock updatedStock = stockViewState.getStock();
            if (updatedStock != null) {
                updateStockData(updatedStock);
            }
        }
    }

    public void setPreviousView(String viewName) {
        this.previousView = viewName;
    }
}
