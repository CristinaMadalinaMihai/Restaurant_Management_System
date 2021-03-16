package presentation_layer_VIEW;

import business_layer_MODEL.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Chef extends JFrame implements Observer {

    private JPanel chefPanel;
    private JScrollPane scrollPane;
    private Observable o;
    private Object arg;
    private Restaurant restaurant;
    private JTable chefTable;

    public Chef(Restaurant restaurant) throws HeadlessException {
        this.restaurant = restaurant;
        restaurant.addObserver(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(chefPanel);
        this.setLocation(725, 400);
        this.scrollPane.setPreferredSize(new Dimension(775,350));
        this.setTitle("WAITER AREA");
        this.setVisible(true);
        this.pack();
    }


    @Override
    public void update(Observable o, Object arg) {
        this.o = o;
        this.arg = arg;
        String[] columnName = {"Order ID", "Table No", "Products"};
        Object[][] data = new Object[50][];
        String[] currentOrder = arg.toString().split("/");
        int i = 0;
        for (String order : currentOrder) {
            String[] orderInfo = order.split(";");
            String[] ids = orderInfo[0].split(",");
            data[i] = new Object[]{ids[0], ids[1], orderInfo[1]};
            i++;
        }
        Object[][] dataModel = new Object[i][];
        for (int j = 0; j < i; j++)
            dataModel[j] = data[j];
        JTable myTable = new JTable(dataModel, columnName);
        myTable.setPreferredScrollableViewportSize(new Dimension(150, 100));
        scrollPane.setViewportView(myTable);
    }
}
