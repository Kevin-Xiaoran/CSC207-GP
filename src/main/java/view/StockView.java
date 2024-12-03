package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data_access.FileUserDataAccessObject;
import entity.Stock;
import interface_adapter.ViewManagerModel;
import interface_adapter.stock_view.StockController;
import interface_adapter.stock_view.StockViewModel;
import interface_adapter.stock_view.StockViewState;

/**
 * The View for displaying detailed information about a single stock.
 */
public class StockView extends AbstractViewWithBackButton implements PropertyChangeListener {
    private static final String VIEW_NAME = "StockView";
    private static final String FONT_FAMILY = "SansSerif";
    private static final int FONT_SIZE_TITLE = 24;
    private static final int FONT_SIZE_SUBTITLE = 18;
    private static final int FONT_SIZE_LABEL = 16;
    private static final int PADDING_LARGE = 20;
    private static final int PADDING_SMALL = 10;
    private static final int MILLION = 1_000_000;
    private static final int THOUSAND = 1_000;

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
    private JButton buyButton;
    private String previousView;

    private final FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject();

    public StockView(StockViewModel stockViewModel, ViewManagerModel viewManagerModel) {
        fileUserDataAccessObject.isUserLoggedIn();
        fileUserDataAccessObject.saveUserLoginStatus();

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
        add(Box.createRigidArea(new Dimension(0, PADDING_LARGE)));
        add(createActionButtonsPanel());
        add(Box.createVerticalGlue());
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    private void initializeComponents(JPanel contentPanel) {
        stockSymbolLabel = createLabel(FONT_SIZE_TITLE, Font.BOLD);
        stockPriceLabel = createLabel(FONT_SIZE_SUBTITLE, Font.PLAIN);

        final JPanel stockInfoPanel = new JPanel();
        stockInfoPanel.setLayout(new BoxLayout(stockInfoPanel, BoxLayout.Y_AXIS));
        stockInfoPanel.setBackground(Color.WHITE);
        stockInfoPanel.add(stockSymbolLabel);
        stockInfoPanel.add(stockPriceLabel);

        openPriceLabel = createLabel(FONT_SIZE_LABEL, Font.PLAIN);
        closePriceLabel = createLabel(FONT_SIZE_LABEL, Font.PLAIN);
        lowPriceLabel = createLabel(FONT_SIZE_LABEL, Font.PLAIN);
        highPriceLabel = createLabel(FONT_SIZE_LABEL, Font.PLAIN);
        volumeLabel = createLabel(FONT_SIZE_LABEL, Font.PLAIN);

        contentPanel.add(stockInfoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, PADDING_SMALL)));
        contentPanel.add(openPriceLabel);
        contentPanel.add(closePriceLabel);
        contentPanel.add(lowPriceLabel);
        contentPanel.add(highPriceLabel);
        contentPanel.add(volumeLabel);

        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        stockInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private JPanel createActionButtonsPanel() {
        final JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buyButton = new JButton("BUY");
        buyButton.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_SUBTITLE));
        buyButton.setFocusPainted(false);
        buyButton.addActionListener(event -> {
            final Stock currentStock = stockViewModel.getState().getStock();
            if (currentStock != null && stockController != null) {
                stockController.buyStock(currentStock);
            }
        });

        // Star button for favorite
        favoriteButton = new JButton("\u2606");
        favoriteButton.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_SUBTITLE));
        favoriteButton.setFocusPainted(false);
        favoriteButton.addActionListener(event -> {
            final Stock currentStock = stockViewModel.getState().getStock();
            if (currentStock != null && stockController != null) {
                final boolean isFavorite = "\u2605".equals(favoriteButton.getText());
                stockController.toggleWatchlist(currentStock, true);
            }
        });

        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(buyButton);
        actionPanel.add(Box.createRigidArea(new Dimension(PADDING_LARGE, 0)));
        actionPanel.add(favoriteButton);
        actionPanel.add(Box.createHorizontalGlue());

        return actionPanel;
    }

    /**
     * The update data part .
     * @param stock as the stock that should be shown
     */
    public void updateStockData(Stock stock) {
        stockSymbolLabel.setText(stock.getSymbol());
        stockPriceLabel.setText("Current Price: $" + stock.getClosePrice());
        openPriceLabel.setText("Open Price: $" + stock.getOpenPrice());
        closePriceLabel.setText("Close Price: $" + stock.getClosePrice());
        lowPriceLabel.setText("Low: $" + stock.getLow());
        highPriceLabel.setText("High: $" + stock.getHigh());

        final double volume = stock.getVolume();
        final String volumeText;

        if (volume >= MILLION) {
            volumeText = (volume / MILLION) + " M";
        }
        else if (volume >= THOUSAND) {
            volumeText = (volume / THOUSAND) + " K";
        }
        else {
            volumeText = String.valueOf(volume);
        }

        volumeLabel.setText("Volume: " + volumeText);
    }

    private void updateFavoriteButtonVisibility(String stockSymbol) {
        favoriteButton.setVisible(!"AAPL".equals(stockSymbol) && !"COST".equals(stockSymbol)
                && !"NVDA".equals(stockSymbol));
    }

    private void setButtonVisible(boolean visible) {
        favoriteButton.setVisible(visible);
        buyButton.setVisible(visible);
    }

    private JLabel createLabel(int fontSize, int fontStyle) {
        final JLabel label = new JLabel("");
        label.setFont(new Font(FONT_FAMILY, fontStyle, fontSize));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    void backButtonAction() {
        viewManagerModel.popView();
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

            // Update favourite button UI only
            stockController.toggleWatchlist(updatedStock, false);
        }
        else if ("updateFavouriteButton".equals(evt.getPropertyName())) {
            // Toggle button text between filled and empty star
            if (stockViewState.getIsFavorite()) {
                favoriteButton.setText("\u2605");
            }
            else {
                favoriteButton.setText("\u2606");
            }
        }

        // Update login state
        setButtonVisible(fileUserDataAccessObject.isUserLoggedIn());
    }

    public void setPreviousView(String viewName) {
        this.previousView = viewName;
    }
}

