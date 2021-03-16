package controller;

import data_layer.RestaurantSerializator;
import data_layer.TxtFileWriter;
import presentation_layer_VIEW.Admin;
import business_layer_MODEL.MenuItem;
import business_layer_MODEL.CompositeProduct;
import business_layer_MODEL.BaseProduct;
import business_layer_MODEL.Restaurant;
import business_layer_MODEL.Order;
import presentation_layer_VIEW.Chef;
import presentation_layer_VIEW.Waiter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.*;

public class Controller {

    private Admin adminGUI;
    private Waiter waiterGUI;
    private Chef chefGUI;
    private Restaurant restaurant;

    public Controller(Admin adminGUI, Waiter waiterGUI, Chef chefGUI, Restaurant restaurant) throws HeadlessException {
        this.adminGUI = adminGUI;
        this.waiterGUI = waiterGUI;
        this.chefGUI = chefGUI;
        this.restaurant = restaurant;

        adminGUI.viewMenuButton(new viewMenu());
        adminGUI.createBPbutton(new createBP());
        adminGUI.createCPbutton(new createCP());
        adminGUI.editBPbutton(new editBP());
        adminGUI.editCPbutton(new editCP());
        adminGUI.deleteBPbutton(new deleteBP());
        adminGUI.deleteCPbutton(new deleteCP());

        waiterGUI.viewOrdersButton(new viewOrders());
        waiterGUI.createOrderButton(new createOrder());
        waiterGUI.generateBillButtonButton(new generateBill());
    }

    class viewMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] columnName = {"Menu Item", "Price", "Composition"};
            Object[][] data = new Object[50][];
            int i = 0;
            for (MenuItem menuItem : restaurant.getRestaurantMenu()) {
                if (menuItem.getClass().getSimpleName().equals(CompositeProduct.class.getSimpleName())){
                    String name = menuItem.getItemName();
                    int price = menuItem.computePrice();
                    MenuItem compositeItem = (CompositeProduct) menuItem;
                    data[i] = new Object[]{name, price, compositeItem.toString()};
                    i++;
                } else {
                    String name = menuItem.getItemName();
                    int price = menuItem.getItemPrice();
                    data[i] = new Object[]{name, price, "-"};
                    i++;
                }
            }
            Object[][] dataModel = new Object[i][];
            for (int j = 0; j < i; j++)
                dataModel[j] = data[j];
            JTable myTable = new JTable(dataModel, columnName);
            myTable.setPreferredScrollableViewportSize(new Dimension(100, 100));
            adminGUI.getScrollPane().setViewportView(myTable);
        }
    }

    class createBP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getNewBPcreate();
            String price = adminGUI.getPriceBPcreate();

            if (name != null && price != null) {
                MenuItem newBaseProduct = new BaseProduct(name, Integer.parseInt(price));
                restaurant.createNewMenuItem(newBaseProduct);
                new RestaurantSerializator().readObject(restaurant);
                new viewMenu().actionPerformed(e);
            }
        }
    }

    class createCP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getNewCPcreate();
            if (name != null && adminGUI.getCPcomponents() != null) {
                String[] components = adminGUI.getCPcomponents().split(", ");
                Set<MenuItem> menuItemSet = new HashSet<MenuItem>();
                for(String component : components) {
                    for(MenuItem menuItem : restaurant.getRestaurantMenu()) {
                        if (menuItem.getItemName().equals(component)) {
                            menuItemSet.add(menuItem);
                            break;
                        }
                    }
                }
                MenuItem newCompositeProduct = new CompositeProduct(name, menuItemSet);
                newCompositeProduct.setItemName(name);
                newCompositeProduct.setItemPrice(newCompositeProduct.computePrice());
                restaurant.createNewMenuItem(newCompositeProduct);
                new RestaurantSerializator().readObject(restaurant);
                new viewMenu().actionPerformed(e);
            }
        }
    }

    class editBP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldName = adminGUI.getOldBPedit();
            String newName = adminGUI.getNewBSedit();
            if (oldName != null && newName != null) {
                if(!restaurant.editMenuItem(oldName, newName)) {
                    JOptionPane.showMessageDialog(adminGUI,"Base product not found!","Product name warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    new RestaurantSerializator().readObject(restaurant);
                    new viewMenu().actionPerformed(e);
                }
            }
        }
    }

    class editCP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String compositeName = adminGUI.getOldCPedit();
            if (compositeName != null && adminGUI.getNewCPedit() != null) {
                String[] newComponents = adminGUI.getNewCPedit().split(", ");
                Set<MenuItem> menuItemSet = new HashSet<MenuItem>();
                for (String component : newComponents){
                    for (MenuItem menuItem : restaurant.getRestaurantMenu()) {
                        if (menuItem.getItemName().equals(component)) {
                            menuItemSet.add(menuItem);
                        }
                    }
                }
                boolean found = false;
                MenuItem itemToDelete = new CompositeProduct(compositeName, null);
                for(MenuItem iterator : restaurant.getRestaurantMenu()) {
                    if (iterator.getItemName().equals(compositeName)) {
                        itemToDelete = iterator;
                        found = true;
                    }
                }
                if(!found) {
                    JOptionPane.showMessageDialog(adminGUI,"Composite product not found!","Product name warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    restaurant.deleteMenuItem(itemToDelete);
                    new RestaurantSerializator().readObject(restaurant);
                    MenuItem newOne = new CompositeProduct(compositeName, menuItemSet);
                    newOne.setItemName(compositeName);
                    newOne.setItemPrice(newOne.computePrice());
                    restaurant.createNewMenuItem(newOne);
                    new RestaurantSerializator().readObject(restaurant);
                    new viewMenu().actionPerformed(e);
                }
            }
        }
    }

    class deleteBP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getNameBPdelete();
            if (name != null) {
                boolean found = false;
                MenuItem item = new BaseProduct(name, -1);
                for(MenuItem menuItem : restaurant.getRestaurantMenu()) {
                    if (menuItem.getItemName().equals(name)) {
                        found = true;
                        item = menuItem;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(adminGUI,"Base product not found!","Product name warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    restaurant.deleteMenuItem(item);
                    new RestaurantSerializator().readObject(restaurant);
                    new viewMenu().actionPerformed(e);
                }
            }
        }
    }

    class deleteCP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getNameCPdelete();
            if (name != null) {
                boolean found = false;
                MenuItem item = new CompositeProduct(name, null);
                for (MenuItem menuItem : restaurant.getRestaurantMenu()) {
                    found = true;
                    item = menuItem;
                }
                if (!found) {
                    JOptionPane.showMessageDialog(adminGUI,"Composite product not found!","Product name warning",JOptionPane.WARNING_MESSAGE);
                } else {
                    restaurant.deleteMenuItem(item);
                    new RestaurantSerializator().readObject(restaurant);
                    new viewMenu().actionPerformed(e);
                }
            }
        }
    }

    class viewOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] columnName = {"Order ID", "Table no", "Date", "Items"};
            Object[][] data = new Object[50][];
            int i = 0;
            for (Map.Entry<Order, Set<MenuItem>> itemEntry : restaurant.getOrderInfo().entrySet()) {
                int orderId = itemEntry.getKey().getOrderId();
                int tableNo = itemEntry.getKey().getTableNo();
                String date = itemEntry.getKey().getDate();
                String items = new String();
                Set<MenuItem> menuItemSet = itemEntry.getValue();
                for (MenuItem menuItem : menuItemSet) {
                    items = items + menuItem.getItemName() + ", ";
                }
                System.out.println("items: " + items);
                data[i] = new Object[]{orderId, tableNo, date, items};
                i++;
            }
            Object[][] dataModel = new Object[i][];
            for (int j = 0; j < i; j++)
                dataModel[j] = data[j];
            JTable myTable = new JTable(dataModel, columnName);
            myTable.setPreferredScrollableViewportSize(new Dimension(150, 100));
            waiterGUI.getScrollPane().setViewportView(myTable);
        }
    }

    class createOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String orderID = waiterGUI.getOrderId();
            String tableNo = waiterGUI.getTableNumber();
            String products = waiterGUI.getOrderedItem();
            if (orderID != null && tableNo != null && products != null) {
                Order newOrder = new Order(Integer.parseInt(orderID), Integer.parseInt(tableNo));
                waiterGUI.setDateField(newOrder.getDate());

                String[] items = products.split(", ");
                Set<MenuItem> menuItemSet = new HashSet<MenuItem>();
                for(String item : items) {
                    for(MenuItem menuItem : restaurant.getRestaurantMenu()) {
                        if (menuItem.getItemName().equals(item)) {
                            menuItemSet.add(menuItem);
                            break;
                        }
                    }
                }
                restaurant.createNewOrder(newOrder, menuItemSet);
                new viewOrders().actionPerformed(e);
            }
        }
    }

    class generateBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String orderID = waiterGUI.getOrderIdBill();
            String tableNo = waiterGUI.getTableNoBill();
            if (orderID != null && tableNo != null) {
                Order currentOrder = null;
                Set<MenuItem> currentMenuItem = null;
                for (Map.Entry<Order, Set<MenuItem>> itemEntry : restaurant.getOrderInfo().entrySet()) {
                    if (itemEntry.getKey().getOrderId() == Integer.parseInt(orderID) && itemEntry.getKey().getTableNo() == Integer.parseInt(tableNo)) {
                        currentOrder = itemEntry.getKey();
                        currentMenuItem = itemEntry.getValue();
                    }
                }
                if (currentOrder != null && currentMenuItem != null) {
                    JOptionPane.showMessageDialog(waiterGUI,"Bill generated successfully!","Bill notification",JOptionPane.WARNING_MESSAGE);
                    new TxtFileWriter().generateBill(currentMenuItem, currentOrder);
                } else {
                    JOptionPane.showMessageDialog(waiterGUI,"Cannot generate bill! No such order identification found.","Bill warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
