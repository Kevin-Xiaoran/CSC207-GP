package interface_adapter;

import java.util.Stack;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {
    private final Stack<String> viewStack = new Stack<>();

    /**
     * Constructs a ViewManagerModel with the initial state set to "home view".
     */
    public ViewManagerModel() {
        super("view manager");
        this.setState("home view");
    }

    /**
     * Pushes the current view to the stack and navigates to the specified view.
     *
     * @param viewName The name of the new view to navigate to.
     */
    public void pushView(String viewName) {

        if (!this.getState().equals(viewName)) {
            viewStack.push(this.getState());
        }
        this.setState(viewName);
        this.firePropertyChanged();
    }

    /**
     * Pops the last view from the stack and navigates back to it.
     * If the stack is empty, it stays on the current view.
     */
    public void popView() {

        if (!viewStack.isEmpty()) {
            final String previousView = viewStack.pop();
            this.setState(previousView);
            this.firePropertyChanged();
        }
        else {
            this.setState("home view");
            this.firePropertyChanged();
        }
    }
}
