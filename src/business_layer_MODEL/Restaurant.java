package business_layer_MODEL;

import java.awt.*;
import java.util.*;

/**
 * contains the menu of the restaurant and all the orders
 */
public class Restaurant extends Observable implements IRestaurantProcessing, java.io.Serializable {

    /**
     * contains the items from the menu of the restarant
     */
    private Set<MenuItem> restaurantMenu;
    /**
     * contains the order identification along with the items ordered
     */
    private Map<Order, Set<MenuItem>> orderInfo;

    /**
     * initialises the restaurant menu and the map of orders
     */
    public Restaurant() {
        restaurantMenu = new HashSet<MenuItem>();
        orderInfo = new HashMap<Order, Set<MenuItem>>();
    }

    /**
     * classic getter
     * @return the menu items of the restaurant
     */
    public Set<MenuItem> getRestaurantMenu() {
        return restaurantMenu;
    }

    public Map<Order, Set<MenuItem>> getOrderInfo() {
        return orderInfo;
    }

    /**
     * changes the name of a given item
     * @param oldName represents the name of the item which needs to be changed
     * @param newName represents the new name which needs to be given
     * @return true if the requested item exists in the menu
     */
    @Override
    public boolean editMenuItem(String oldName, String newName) {

        assert validateNewName(newName) : this;
        String namePre = oldName;
        String namePost = newName;

        boolean found = false;
        for(MenuItem iterator : restaurantMenu) {
            if (iterator.getItemName().equals(oldName)) {
                iterator.setItemName(newName);
                found = true;
            }
        }

        assert validateNewNamePostCondition(namePre, namePost): this;
        return found;
    }

    /**
     * adds in the restaurant menu a new item
     * @param menuItem represents the given item which needs to be inserted in the menu
     */
    @Override
    public void createNewMenuItem(MenuItem menuItem) {

        assert validateMenuItem(menuItem): this;
        int sizePre = restaurantMenu.size();

        restaurantMenu.add(menuItem);

        int sizePost = restaurantMenu.size();
        assert validateMenuItemPostCondition(sizePost, sizePre): this;

    }

    /**
     * deletes an item from the menu
     * @param menuItem represents the given item which needs to be removed
     */
    @Override
    public void deleteMenuItem(MenuItem menuItem) {

        assert validateMenuItem(menuItem): this;
        int sizePre = restaurantMenu.size();

        if (menuItem.getClass().getSimpleName().equals(BaseProduct.class.getSimpleName())) {
            for (MenuItem itemm : restaurantMenu) {
                if (itemm.getClass().getSimpleName().equals(CompositeProduct.class.getSimpleName())) {
                    Set<MenuItem> tempSet = ((CompositeProduct) itemm).getMenuItemSet();
                    for (MenuItem baseItem : tempSet) {
                        if (baseItem.getItemName().equals(menuItem.getItemName())) {
                            ((CompositeProduct) itemm).removeItem(baseItem);
                        }
                    }
                }
            }
            restaurantMenu.remove(menuItem);
        }
        else {
            restaurantMenu.remove(menuItem);
        }

        int sizePost = restaurantMenu.size();
        assert validateMenuItemPostCondition(sizePre, sizePost): this;
    }

    /**
     * creates and insert a new item in the order map
     * @param order represents the given order which needs to pe processed
     * @param menuItem represents the given set of items which needs to be processed
     */
    @Override
    public void createNewOrder(Order order, Set<MenuItem> menuItem) {

        assert validateOrder(order, menuItem): this;
        int sizePre = orderInfo.size();

        orderInfo.put(order, menuItem);

        setChanged();
        notifyObservers(toChef());

        int sizePost = orderInfo.size();
        assert validateMenuItemPostCondition(sizePre, sizePost): this;

    }

    /**
     * builds the necessary information of the current orders under the form of a string
     * @return the current orders in a string
     */
    public String toChef() {
        String chefHelper = new String(); // "orderId,tableNo;item1,item2,/"
        for(Map.Entry<Order, Set<MenuItem>> itemEntry : orderInfo.entrySet()) {
            chefHelper = chefHelper + itemEntry.getKey().getOrderId() + "," + itemEntry.getKey().getTableNo() + ";";
            Set<MenuItem> menuItemSet = itemEntry.getValue();
            for (MenuItem menuItem : menuItemSet) {
                chefHelper = chefHelper + menuItem.getItemName() + ",";
            }
            chefHelper = chefHelper + "/";
        }
        return chefHelper;
    }

    /**
     * invariant to validate the given string
     * @param givenString represents the new name given to the item
     * @return true if the given parameter is valid and false, otherwise
     */
    private boolean validateNewName(String givenString){
        if (givenString.equals(null)) {
            String message = "Invalid new name for the item\n";
            throw new IllegalArgumentException(message);
        }
        else
            return true;
    }

    /**
     * validates the post condition for editing a menu item
     * @param preName represents the previous name
     * @param postName represents the new name
     * @return true if the given parameters are valid and false, otherwise
     */
    private boolean validateNewNamePostCondition(String preName, String postName){
        if (!preName.equals(postName)) {
            String message = "Invalid new name for the item\n";
            throw new IllegalArgumentException(message);
        }
        else
            return true;
    }

    /**
     * valiates the menu item given as parameter
     * @param givenMenuItem represents the parameter which needs to be validated
     * @return true if the given parameter is valid and false, otherwise
     */
    private boolean validateMenuItem(MenuItem givenMenuItem){
        if (givenMenuItem == null) {
            String message = "Invalid menu item\n";
            throw new IllegalArgumentException(message);
        } else
            return true;
    }

    /**
     * validates the post condition for creating a menu item or an order
     * @param preSize represents previous size of the set
     * @param postSize represents the new size of the set
     * @return true if the size changed and false, otherwise
     */
    private boolean validateMenuItemPostCondition(int preSize, int postSize){
        if (preSize != postSize) {
            String message = "Invalid menu item\n";
            throw new IllegalArgumentException(message);
        } else
            return true;
    }

    /**
     * validates the order information given as parameters
     * @param givenOrder represents the order identification
     * @param givenMenuItemSet represents the set of required items
     * @return true if the given parameters are valid and false, otherwise
     */
    private boolean validateOrder(Order givenOrder, Set<MenuItem> givenMenuItemSet){
        if (givenOrder == null || givenMenuItemSet == null) {
            String message = "Invalid order\n";
            throw new IllegalArgumentException(message);
        } else
            return true;
    }

}
