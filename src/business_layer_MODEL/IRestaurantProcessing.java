package business_layer_MODEL;

import java.util.HashSet;
import java.util.Set;

public interface IRestaurantProcessing {

    /**
     *
     * @param oldName represented the name to be changed
     * @param newName represents the new name
     * @pre newName != null
     * @post oldName == newName
     */
    boolean editMenuItem(String oldName, String newName);

    /**
     *
     * @param menuItem represents the item to be inserted
     * @pre menuItem != null
     * @post set.size() == set@pre.size() + 1
     */
    void createNewMenuItem(MenuItem menuItem);

    /**
     *
     * @param menuItem represents the item to be deleted
     * @pre menuItem != null
     * @post set.size() == set@pre.size() - 1
     */
    void deleteMenuItem(MenuItem menuItem);

    /**
     *
     * @param order represents the current order
     * @param menuItem represents the products of the order
     * @pre order != null
     * @pre menuItem != null
     * @post set.size() == set@pre.size() + 1
     */
    void createNewOrder(Order order, Set<MenuItem> menuItem);

}
