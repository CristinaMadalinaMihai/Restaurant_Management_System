package data_layer;

import business_layer_MODEL.CompositeProduct;
import business_layer_MODEL.MenuItem;
import business_layer_MODEL.Order;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Set;

public class TxtFileWriter {

    public TxtFileWriter() {
    }

    public void generateBill(Set<MenuItem> menuItems, Order order) {
        CompositeProduct currentOrder = new CompositeProduct(String.valueOf(order.getOrderId()), menuItems);
        String fileName = "bill" + String.valueOf(order.getOrderId()) + ".txt";

        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("Order ID: " + order.getOrderId() + "\r\n");
            writer.write("Date: " + order.getDate() +"\r\n");
            writer.write("Product & price:\r\n");
            for (MenuItem menuItem : menuItems) {
                writer.write(menuItem.getItemName() + " --- " + menuItem.getItemPrice() + "\r\n");
            }
            writer.write("Total price: " + currentOrder.computePrice() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
