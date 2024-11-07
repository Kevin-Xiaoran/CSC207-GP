package view.HomeViewComponents;

import javax.swing.*;

/**
 * A panel containing a label and a button.
 * - AAPL          ->
 */
public class WatchListLabelButtonPanel extends JPanel {
    WatchListLabelButtonPanel(JLabel label, JButton button) {
        this.add(label);
        this.add(button);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
}
