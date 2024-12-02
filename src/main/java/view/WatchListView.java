package view;

import entity.Stock;
import view.HomeViewComponents.ScrollablePanel;

import data_access.DBUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_view.WatchlistController;
import interface_adapter.stock_view.WatchListViewState;
import interface_adapter.watchlist_view.WatchListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class WatchListView extends JPanel implements PropertyChangeListener {

    private final ViewManagerModel viewManagerModel;
    private final WatchListViewModel watchListViewModel;
    private final ScrollablePanel contentPanel = new ScrollablePanel();
    private WatchlistController watchlistController;

    public WatchListView(WatchListViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        this.watchListViewModel = viewModel;
        this.watchListViewModel.addPropertyChangeListener(this);

        // return button
        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setBackground(Color.WHITE);

        // create return button
        final JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        topPanel.add(backButton, BorderLayout.WEST);

        // Listener for return button to home page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.popView();
            }
        });

        add(topPanel, BorderLayout.NORTH);

        // stock information
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = contentPanel.wrapInScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

    }

    private void addStockItem(JPanel contentPanel, String code, String price, String dailyChange, String dailyPercentage, String volume) {
        final JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBackground(Color.WHITE);

        final boolean isFirstStock = contentPanel.getComponentCount() == 0;
        if (isFirstStock) {
            stockPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));
        }
        else {
            stockPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        }

        stockPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        stockPanel.setMinimumSize(new Dimension(0, 80));

        stockPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToStockInformation(code);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stockPanel.setBackground(new Color(240, 240, 240));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stockPanel.setBackground(Color.WHITE);
            }
        });

        // Left part: Stock code and price
        final JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        final JLabel stockCodeLabel = new JLabel(code);
        stockCodeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        final JLabel stockPriceLabel = new JLabel(price);
        stockPriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(stockCodeLabel);
        leftPanel.add(stockPriceLabel);
        leftPanel.add(Box.createVerticalGlue());

        // Right part: up and down information
        final JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);

        rightPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JLabel dailyChangeLabel = new JLabel(dailyChange + " (" + dailyPercentage + ")");
        dailyChangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dailyChangeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        final JLabel volumeLabel = new JLabel(volume);
        volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        volumeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(dailyChangeLabel);
        rightPanel.add(volumeLabel);
        rightPanel.add(Box.createVerticalGlue());

        // Add all to stockPanel
        stockPanel.add(leftPanel, BorderLayout.WEST);
        stockPanel.add(rightPanel, BorderLayout.EAST);

        // Add all information
        contentPanel.add(stockPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("watchList".equals(evt.getPropertyName())) {
            final WatchListViewState watchListViewState = (WatchListViewState) evt.getNewValue();
            if (watchListViewState.getWatchlist() != null) {
                updateWatchList(watchListViewState.getWatchlist());
            }
        }
    }

    private void updateWatchList(ArrayList<Stock> updatedWatchList) {
        contentPanel.removeAll();
        createWatchListview(updatedWatchList);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void goToStockInformation(String code) {
        if (watchlistController == null) {
            throw new IllegalStateException("WatchlistController is not initialized.");
        }
        final WatchListViewState currentState = watchListViewModel.getState();
        currentState.setSymbol(code);
        watchlistController.search(currentState.getSymbol());

        viewManagerModel.pushView("StockView");
    }

    private void createWatchListview(ArrayList<Stock> WatchList) {
        for (Stock stock : WatchList) {
            final String code = stock.getSymbol();
            final String price = "$" + stock.getClosePrice();
            final String dailyChange = String.valueOf(stock.getDailyChange());
            final String dailyPercentage = stock.getDailyPercentage() + "%";
            final String volume = String.valueOf(stock.getVolume());

            addStockItem(contentPanel, code, price, dailyChange, dailyPercentage, volume);
        }
    }

    public String getViewName() {
        return "WatchListView";
    }

    public void setwatchlistController(WatchlistController watchlistController) {
        this.watchlistController = watchlistController;
    }
}

