package view;

import interface_adapter.watchlist_view.WatchListViewModel;

import javax.swing.*;
import java.awt.*;

public class WatchListView extends JPanel {

    public WatchListView(WatchListViewModel viewModel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        addStockItem("AAPL", "$226.97", "−$0.26", "+0.11%", "+$44.09", "+24.10%");
        addStockItem("COST", "$953.20", "+$39.27", "+4.30%", "+$386.00", "+68.03%");
        addStockItem("QQQ", "$514.07", "+$0.56", "+0.60%", "+$141.25", "+37.87%");
    }

    private void addStockItem(String code, String price, String dailyChange, String dailyPercentage, String totalChange, String totalPercentage) {
        JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        stockPanel.setBackground(Color.WHITE);

        // Left part: Stock code and price
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        JLabel stockCodeLabel = new JLabel(code);
        stockCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel stockPriceLabel = new JLabel(price);
        stockPriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);

        // Right part: up and down information
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

        JLabel dailyChangeLabel = new JLabel(dailyChange + "(" + dailyPercentage + ")");
        dailyChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JLabel totalChangeLabel = new JLabel(totalChange + "(" + totalPercentage + ")");
        totalChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        rightPanel.add(dailyChangeLabel);
        rightPanel.add(totalChangeLabel);

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        add(stockPanel);
    }

    public String getViewName() {
        return "WatchListView";
    }
}
