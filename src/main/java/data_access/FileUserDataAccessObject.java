package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import entity.*;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.watchlist.WatchListDataAccessInterface;
import use_case.watchlist.WatchListModifyDataAccessInterface;

/**
 * DAO for user data implemented using a File to persist the data.
 */
public class FileUserDataAccessObject implements WatchListDataAccessInterface, WatchListModifyDataAccessInterface {

    private final ArrayList<String> watchList = new ArrayList<>();
    private final ArrayList<SimulatedHolding> portfolioList = new ArrayList<>();

    private final String mainFilePath = "src/main/java/data_access/";
    private final String watchListFilePath = "watchlist.txt";
    private final String portfolioFilePath = "portfolio.txt";

    private final StockFactory stockFactory;
    private final SimulatedHoldingFactory simulatedHoldingFactory;

    public FileUserDataAccessObject(StockFactory stockFactory, SimulatedHoldingFactory simulatedHoldingFactory) {
        this.stockFactory = stockFactory;
        this.simulatedHoldingFactory = simulatedHoldingFactory;
    }

    // Watch list related APIs
    @Override
    public ArrayList<String> getWatchList() {
        // Using FileReader and BufferedReader to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath + watchListFilePath))) {
            final String line = reader.readLine();
            if (line != null) {
                Collections.addAll(watchList, line.split(","));
            }

            return watchList;
        }
        catch (IOException ex) {
            // Normally, this means the file doesn't exist
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void saveWatchList() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(mainFilePath + watchListFilePath));
            writer.write(String.join(",", watchList));
            writer.newLine();

            writer.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addToWatchList(String symbol) {
        watchList.add(symbol);
        saveWatchList();
    }

    @Override
    public void removeFromWatchList(String symbol) {
        watchList.remove(symbol);
        saveWatchList();
    }

    // Portfolio list related APIs
    public ArrayList<SimulatedHolding> getPortfolioList() {
        // Using FileReader and BufferedReader to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath + portfolioFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] data = line.split(",");
                final String symbol = data[0];
                final double price = Double.parseDouble(data[1]);
                final int amount = Integer.parseInt(data[2]);
                final SimulatedHolding simulatedHolding = simulatedHoldingFactory.create(symbol, price, amount);

                portfolioList.add(simulatedHolding);
            }

            return portfolioList;
        }
        catch (IOException ex) {
            // Normally, this means the file doesn't exist
            throw new RuntimeException(ex);
        }
    }

    public void savePortfolioList() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(mainFilePath + watchListFilePath));
            for (SimulatedHolding simulatedHolding : portfolioList) {
                writer.write(simulatedHolding.getSymbol() + ",");
                writer.write(simulatedHolding.getPurchasePrice() + ",");
                writer.write(simulatedHolding.getPurchaseAmount() + ",");
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException ex) {
            // Normally, this means the file doesn't exist
            throw new RuntimeException(ex);
        }
    }

    public void addToPortfolioList(SimulatedHolding simulatedHolding) {
        portfolioList.add(simulatedHolding);
        savePortfolioList();
    }

    public void removeFromPortfolioList(SimulatedHolding simulatedHolding) {
        portfolioList.remove(simulatedHolding);
        savePortfolioList();
    }
}
