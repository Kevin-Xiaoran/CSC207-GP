package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Parent View for the all views that need back button.
 */

abstract class AbstractViewWithBackButton extends JPanel {
    private final JButton backButton = new JButton("Back");

    AbstractViewWithBackButton() {
        // Configure back button
        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        topPanel.add(backButton, BorderLayout.WEST);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the abstract method to be implemented by subclasses
                backButtonAction();
            }
        });

        // Add panel(s)
        this.add(topPanel);
    }

    abstract void backButtonAction();
}
