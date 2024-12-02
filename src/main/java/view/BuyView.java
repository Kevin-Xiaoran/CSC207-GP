package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy_view.BuyController;
import interface_adapter.buy_view.BuyState;
import interface_adapter.buy_view.BuyViewModel;

/**
 * The View for the user buy a stock.
 */
public class BuyView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "buy view";
    private final ViewManagerModel viewManagerModel;
    private final BuyViewModel buyViewModel;
    private final JTextField quantityInputField = new JTextField(15);
    private final JTextField priceInputField = new JTextField(15);
    private final JButton buy;
    private final JButton cancel;
    private BuyController buyController;

    public BuyView(final BuyViewModel viewModel, final ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.buyViewModel = viewModel;
        this.buyViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        final JLabel title = new JLabel("Buy Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel priceInfo = new LabelTextPanel(new JLabel("Price"), priceInputField);
        final LabelTextPanel quantityInfo = new LabelTextPanel(new JLabel("Quantity"), quantityInputField);

        priceInputField.setEditable(true);
        priceInputField.setText(buyViewModel.getState().getPrice());
        final JPanel buttons = new JPanel();
        buy = new JButton("Buy");
        buttons.add(buy);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        setupBuyButton();
        setupCancelButton();
        setupQuantityInputFieldListener();
        setupPriceInputFieldListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(priceInfo);
        this.add(quantityInfo);
        this.add(buttons);
    }

    private void setupBuyButton() {
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    final double quantity = Double.parseDouble(quantityInputField.getText());
                    if (quantity > 0) {
                        final BuyState buyState = buyViewModel.getState();
                        buyController.execute(
                                buyState.getSymbol(),
                                Double.parseDouble(buyState.getPrice()),
                                quantity);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please enter a positive quantity.");
                    }
                }
                catch (final NumberFormatException event) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
                }
            }
        });
    }

    private void setupCancelButton() {
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buyController.switchToStockView();
            }
        });
    }

    private void setupQuantityInputFieldListener() {
        quantityInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final BuyState currentState = buyViewModel.getState();
                currentState.setQuantity(quantityInputField.getText());
                buyViewModel.setState(currentState);
            }

            @Override public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void setupPriceInputFieldListener() {
        priceInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final BuyState currentState = buyViewModel.getState();
                currentState.setPrice(priceInputField.getText());
                buyViewModel.setState(currentState);
            }

            @Override public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(final ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        final BuyState state = (BuyState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(final BuyState state) {
        priceInputField.setText(state.getPrice());
        quantityInputField.setText(state.getQuantity());
    }

    public String getViewName() {
        return viewName;
    }

    public void setBuyController(final BuyController buyController) {
        this.buyController = buyController;
    }
}
