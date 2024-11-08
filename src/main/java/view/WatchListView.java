package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.watchlist_view.WatchListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class WatchListView extends JPanel {

    private final ViewManagerModel viewManagerModel;

    public WatchListView(WatchListViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // return button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setBackground(Color.WHITE);

        // create return button
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        topPanel.add(backButton, BorderLayout.WEST);

        // Listener for return button to home page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("home view");
                viewManagerModel.firePropertyChanged();
            }
        });

        add(topPanel, BorderLayout.NORTH);

        // stock information
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        addStockItem(contentPanel, "AAPL", "$226.97", "−$0.26", "+0.11%", "+$44.09", "+24.10%");
        addStockItem(contentPanel, "COST", "$953.20", "+$39.27", "+4.30%", "+$386.00", "+68.03%");
        addStockItem(contentPanel, "QQQ", "$514.07", "+$0.56", "+0.60%", "+$141.25", "+37.87%");

        add(contentPanel, BorderLayout.CENTER);
    }

    private void addStockItem(JPanel contentPanel, String code, String price, String dailyChange, String dailyPercentage, String totalChange, String totalPercentage) {
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

        final JLabel dailyChangeLabel = new JLabel(dailyChange + "(" + dailyPercentage + ")");
        dailyChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        final JLabel totalChangeLabel = new JLabel(totalChange + "(" + totalPercentage + ")");
        totalChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        rightPanel.add(dailyChangeLabel);
        rightPanel.add(totalChangeLabel);

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        contentPanel.add(stockPanel);
    }

    public String getViewName() {
        return "WatchListView";
    }
}

