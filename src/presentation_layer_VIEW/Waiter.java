package presentation_layer_VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Waiter extends JFrame{
    private JPanel waiterPanel;
    private JButton generateBillButton;
    private JTextField dateField;
    private JTextField orderedItem;
    private JTextField orderIdBill;
    private JTextField tableNumber;
    private JTextField orderId;
    private JButton viewOrders;
    private JButton createOrder;
    private JScrollPane scrollPane;
    private JTextField tableNoBill;
    private JTable ordersTable;

    public Waiter() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(waiterPanel);
        this.setLocation(725, 5);
        this.setTitle("WAITER AREA");
        this.setVisible(true);
        this.scrollPane.setPreferredSize(new Dimension(525,350));
        this.dateField.setEnabled(false);
        this.pack();
    }

    public void setDateField(String dateField) {
        this.dateField.setText(dateField);
    }

    public String getOrderedItem() {
        if (orderedItem.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"Insert name of the item","Order warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternName = Pattern.compile("^([a-z]+(([a-zA-Z ])?[a-zA-Z]*)*)+([,][\\s][a-zA-Z ][a-zA-Z]*)*$");
        if (!patternName.matcher(orderedItem.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Name of the items must be differentiated by ', '","Order warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return orderedItem.getText();
    }

    public String getOrderIdBill() {
        if (orderIdBill.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Insert order number","Bill warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternPrice = Pattern.compile("^\\d+$");
        if (!patternPrice.matcher(orderIdBill.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Order ID must contain only decimal","Bill warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return orderIdBill.getText();
    }

    public String getTableNoBill() {
        if (tableNoBill.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Insert table number","Bill warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternPrice = Pattern.compile("^\\d+$");
        if (!patternPrice.matcher(tableNoBill.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Table no must contain only decimal","Bill warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return tableNoBill.getText();
    }

    public String getTableNumber() {
        if (tableNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Insert table number!","Table no warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternPrice = Pattern.compile("^\\d+$");
        if (!patternPrice.matcher(tableNumber.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Table number must contain only decimal","Table no warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return tableNumber.getText();
    }

    public String getOrderId() {
        if (orderId.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Insert order number","Order warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Pattern patternPrice = Pattern.compile("^\\d+$");
        if (!patternPrice.matcher(orderId.getText()).matches()) {
            JOptionPane.showMessageDialog(this,"Order ID must contain only decimal","Order warning",JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return orderId.getText();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void viewOrdersButton(ActionListener actionListener) {
        viewOrders.addActionListener(actionListener);
    }

    public void createOrderButton(ActionListener actionListener) {
        createOrder.addActionListener(actionListener);
    }

    public void generateBillButtonButton(ActionListener actionListener) {
        generateBillButton.addActionListener(actionListener);
    }

}
