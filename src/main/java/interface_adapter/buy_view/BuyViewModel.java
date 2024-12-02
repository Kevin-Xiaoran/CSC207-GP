package interface_adapter.buy_view;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Buy View.
 */

public class BuyViewModel extends ViewModel<BuyState> {

    public static final String BUY_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public BuyViewModel() {
        super("buy view");
        setState(new BuyState());
    }
}
