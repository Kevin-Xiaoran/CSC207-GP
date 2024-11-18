package view;

import entity.CommonStockFactory;
import entity.Stock;
import entity.StockFactory;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * The View for displaying detailed information about a single stock.
 */
public class StockView extends AbstractViewWithBackButton {

    private final ViewManagerModel viewManagerModel;
    private JLabel stockSymbolLabel;
    private JLabel stockPriceLabel;
    private JLabel openPriceLabel;
    private JLabel closePriceLabel;
    private JLabel lowPriceLabel;
    private JLabel highPriceLabel;
    private JLabel volumeLabel;

    public StockView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        initializeComponents(contentPanel);

        final StockFactory stockFactory = new CommonStockFactory();
        final Stock stock = stockFactory.create("NVDA", 132.2, 130.5,
                100000000, 140.32, 128.9);
        updateStockData(stock);

        add(Box.createVerticalGlue());
        add(contentPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(createActionButtonsPanel());
        add(Box.createVerticalGlue());
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

        JButton buyButton = new JButton("BUY");
        buyButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyButton.setFocusPainted(false);

        buyButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Buy action triggered!");
        });

        JButton favoriteButton = new JButton("★");
        favoriteButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        favoriteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        favoriteButton.setFocusPainted(false);

        favoriteButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Added to favorites!");
        });

        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(buyButton);
        actionPanel.add(Box.createRigidArea(new Dimension(20, 0))); // 两个按钮间距
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
        volumeLabel.setText("Volume: " + stock.getVolume() + " M");

        double volume = stock.getVolume();
        if (volume >= 1_000_000) {
            volumeLabel.setText("Volume: " + (volume / 1_000_000) + " M");
        } else if (volume >= 1_000) {
            volumeLabel.setText("Volume: " + (volume / 1_000) + " K");
        } else {
            volumeLabel.setText("Volume: " + volume);
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
        viewManagerModel.setState("home view");
        viewManagerModel.firePropertyChanged();
    }
}