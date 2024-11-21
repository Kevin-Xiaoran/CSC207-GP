package interface_adapter.buy_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.IsLoggedIn;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.home_view.HomeViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.BuyView;

public class BuyPresenter implements BuyOutputBoundary {

    private BuyViewModel buyViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public BuyPresenter(BuyViewModel buyViewModel, ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.buyViewModel = buyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(BuyOutputData response) {
        // On success, switch to the home view.
        // Store the stock information later.

        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
