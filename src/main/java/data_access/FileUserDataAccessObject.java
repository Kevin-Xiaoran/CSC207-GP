package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import entity.*;
import use_case.buy.BuyUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.portfolio.PortfolioDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.watchlist.WatchListDataAccessInterface;
import use_case.watchlist.WatchListModifyDataAccessInterface;

/**
 * DAO for user data implemented using a File to persist the data.
 */
public class FileUserDataAccessObject implements WatchListDataAccessInterface, WatchListModifyDataAccessInterface, PortfolioDataAccessInterface, BuyUserDataAccessInterface {

    private final ArrayList<String> watchList = new ArrayList<>();
    private final ArrayList<SimulatedHolding> portfolioList = new ArrayList<>();

    private final String mainFilePath = "src/main/java/data_access/";
    private final String watchListFilePath = "watchlist.txt";
    private final String portfolioFilePath = "portfolio.txt";
    private final String userIsLoggedInFilePath = "userIsLoggedIn.txt";

    private SimulatedHoldingFactory simulatedHoldingFactory;

    private boolean userIsLoggedIn;

    public FileUserDataAccessObject() {
    }

    public FileUserDataAccessObject(SimulatedHoldingFactory simulatedHoldingFactory) {
        this.simulatedHoldingFactory = simulatedHoldingFactory;
    }

    // Watch list related APIs
    @Override
    public ArrayList<String> getWatchList() {
        // Check if watchlist.txt file exists
        final Boolean isFileExisted = new File(mainFilePath + watchListFilePath).isFile();

        if (isFileExisted) {
            // Using FileReader and BufferedReader to read the file
            try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath + watchListFilePath))) {
                watchList.clear();
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
        else {
            // If the watchlist file doesn't exist, we create a new one
            createWatchList();

            // Get watchlist data again
            return this.getWatchList();
        }
    }

    @Override
    public void saveWatchList() {
        final BufferedWriter writer;
        try {
            // Override original file
            writer = new BufferedWriter(new FileWriter(mainFilePath + watchListFilePath, false));
            writer.write(String.join(",", watchList));

            writer.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void createWatchList() {
        this.watchList.clear();
        this.watchList.add("AAPL");
        this.watchList.add("COST");
        this.watchList.add("NVDA");
        this.saveWatchList();
    }

    @Override
    public void addToWatchList(String symbol) {
        watchList.add(symbol);
        watchList.sort(String::compareTo);
        saveWatchList();
    }

    @Override
    public void removeFromWatchList(String symbol) {
        watchList.remove(symbol);
        saveWatchList();
    }

    // Portfolio list related APIs
    public ArrayList<SimulatedHolding> getPortfolioList() {
        // Check if watchlist.txt file exists
        final Boolean isFileExisted = new File(mainFilePath + portfolioFilePath).isFile();

        if (isFileExisted) {
            // Using FileReader and BufferedReader to read the file
            try (BufferedReader reader = new BufferedReader(new FileReader(mainFilePath + portfolioFilePath))) {
                // Clear all data first
                portfolioList.clear();

                String line;
                while ((line = reader.readLine()) != null) {
                    final String[] data = line.split(",");
                    final String symbol = data[0];
                    final double price = Double.parseDouble(data[1]);
                    final double amount = Double.parseDouble(data[2]);
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
        else {
            // If file doesn't exist, we create one first
            savePortfolioList();

            return this.getPortfolioList();
        }
    }

    public void savePortfolioList() {
        final BufferedWriter writer;
        try {
            // Override original file
            writer = new BufferedWriter(new FileWriter(mainFilePath + portfolioFilePath, false));
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

    @Override
    public void createPortfolioList() {
        // Create an empty portfolio list file
        this.portfolioList.clear();
        savePortfolioList();
    }

    public void addToPortfolioList(SimulatedHolding simulatedHolding) {
        boolean duplicate = false;
        SimulatedHolding updatedHolding = null;

        for (SimulatedHolding data : new ArrayList<>(portfolioList)) {
            if (data.getSymbol().equals(simulatedHolding.getSymbol())) {
                double newAmount = simulatedHolding.getPurchaseAmount() + data.getPurchaseAmount();
                double newPrice = roundToOneDecimalPlace(
                        (simulatedHolding.getPurchasePrice() * simulatedHolding.getPurchaseAmount()
                                + data.getPurchasePrice() * data.getPurchaseAmount()) / newAmount);

                updatedHolding = simulatedHoldingFactory.create(data.getSymbol(), newPrice, newAmount);

                portfolioList.remove(data);
                duplicate = true;
                break;
            }
        }
        if (duplicate) {
            portfolioList.add(updatedHolding);
        }
        else {
            portfolioList.add(simulatedHolding);
        }
        savePortfolioList();
    }

    public void removeFromPortfolioList(SimulatedHolding simulatedHolding) {
        portfolioList.remove(simulatedHolding);
        savePortfolioList();
    }

    // Method to get the login status of the user
    public boolean isUserLoggedIn() {
        // Check whether the file exists
        final File file = new File(mainFilePath + userIsLoggedInFilePath);
        if (file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                final String line = reader.readLine();
                if (line != null) {
                    // Converts the read string to a Boolean value
                    userIsLoggedIn = Boolean.parseBoolean(line);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            // If the file does not exist, the default setting is not logged in
            userIsLoggedIn = false;
            saveUserLoginStatus();
        }
        return userIsLoggedIn;
    }

    // Method to save the login status of the user
    public void saveUserLoginStatus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mainFilePath + userIsLoggedInFilePath, false))) {
            // Converts a Boolean value to a string and writes it to a file
            writer.write(Boolean.toString(userIsLoggedIn));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Change the login status of the user
    public void setUserLoggedIn(boolean loggedIn) {
        this.userIsLoggedIn = loggedIn;
        saveUserLoginStatus();
    }

    /**
     * Helper method to round a double to one decimal place.
     * @param value The double value to be rounded.
     * @return The rounded value.
     */
    private double roundToOneDecimalPlace(double value) {
        return BigDecimal.valueOf(value)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }
}




