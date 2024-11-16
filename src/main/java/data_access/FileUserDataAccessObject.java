package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import entity.Stock;
import entity.StockFactory;
import entity.User;
import entity.UserFactory;
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
    private final ArrayList<Stock> portfolioList = new ArrayList<Stock>();

    private final String mainFilePath = "src/main/java/data_access/";
    private final String watchListFilePath = "watchlist.txt";

    private final StockFactory stockFactory;

    public FileUserDataAccessObject(StockFactory stockFactory) throws IOException {
        this.stockFactory = stockFactory;
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
}
