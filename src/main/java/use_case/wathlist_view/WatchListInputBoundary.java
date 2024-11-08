package use_case.wathlist_view;

public interface WatchListInputBoundary {

    /**
     * Executes the search use case.
     * @param searchInputData the input data
     */
    void search(WatchListInputData searchInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
