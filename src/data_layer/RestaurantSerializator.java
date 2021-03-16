package data_layer;

import business_layer_MODEL.Restaurant;

import java.io.*;

public class RestaurantSerializator {

    public void readObject(Restaurant restaurant) {

        try {
            // saving the restaurant info in a file
            FileOutputStream fileOut = new FileOutputStream("restaurant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // method of serialization od Restaurant's class object
            out.writeObject(restaurant);

            // closing streams
            out.close();
            fileOut.close();

            System.out.printf("Serialized data is saved in restaurant.ser\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Restaurant writeObject(String args) {

        Restaurant restaurant = null;
        try {
            // Reading the object from a file
            FileInputStream fileIn = new FileInputStream(args);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // method of de-serialization od Restaurant's class object
            restaurant = (Restaurant) in.readObject();

            // closing streams
            in.close();
            fileIn.close();
            System.out.printf("Serialized data is taken from restaurant.ser\n");
            return restaurant;
        } catch (IOException i) {
            i.printStackTrace();
            return restaurant;
        } catch (ClassNotFoundException c) {
            System.out.println("Restaurant class not found");
            c.printStackTrace();
            return restaurant;
        }

    }

}
