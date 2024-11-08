package interface_adapter.watchlist_view;

import use_case.wathlist_view.WatchListInputBoundary;
import use_case.wathlist_view.WatchListInputData;

/**
 * The controller for the Login Use Case.
 */
public class WatchListController {

    private final WatchListInputBoundary WatchListUseCaseInteractor;

    public WatchListController(WatchListInputBoundary WatchListInteractor, WatchListInputBoundary watchListUseCaseInteractor) {
        this.WatchListUseCaseInteractor = watchListUseCaseInteractor;
    }
}
