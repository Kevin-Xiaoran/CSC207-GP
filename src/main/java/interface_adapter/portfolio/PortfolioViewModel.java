package interface_adapter.portfolio;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

import java.util.ArrayList;
import java.util.List;

/**
 * The View Model for the Portfolio View.
 */

public class PortfolioViewModel extends ViewModel<PortfolioState> {
    public PortfolioViewModel() {
        super("porfolio view");
        setState(new PortfolioState());
    }
}