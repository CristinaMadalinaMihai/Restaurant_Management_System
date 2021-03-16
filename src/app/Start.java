package app;

import controller.Controller;
import business_layer_MODEL.Restaurant;
import data_layer.RestaurantSerializator;
import presentation_layer_VIEW.Admin;
import presentation_layer_VIEW.Chef;
import presentation_layer_VIEW.Waiter;

public class Start {
    public static void main(String[] args) {
        Admin adminGUI = new Admin();
        Waiter waiterGUI = new Waiter();
        String arg = args[0];
        System.out.println(arg);
        Restaurant restaurant = new RestaurantSerializator().writeObject(arg);
        Chef chefGUI = new Chef(restaurant);
        Controller controller = new Controller(adminGUI, waiterGUI, chefGUI, restaurant);
    }
}
