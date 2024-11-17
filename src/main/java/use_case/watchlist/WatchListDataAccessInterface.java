package use_case.watchlist;

import java.util.ArrayList;

public interface WatchListDataAccessInterface {

    /**
     * Returns the user's watchlist.
     * @return the user's watchlist
     */
    ArrayList<String> getWatchList();

    /**
     * Saves the watchlist.
     */
    void saveWatchList();
}
