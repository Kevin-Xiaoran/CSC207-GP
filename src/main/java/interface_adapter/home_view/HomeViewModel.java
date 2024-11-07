package interface_adapter.home_view;

import interface_adapter.ViewModel;

/**
 * The View Model for the Home View.
 */
public class HomeViewModel extends ViewModel<HomeState> {

    public HomeViewModel() {
        super("home view");
        setState(new HomeState());
    }

}
