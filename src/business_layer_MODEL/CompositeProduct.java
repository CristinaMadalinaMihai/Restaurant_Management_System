package business_layer_MODEL;

import java.util.Set;

public class CompositeProduct extends MenuItem {

    private Set<MenuItem> menuItemSet;
    private String productName;

    public CompositeProduct(String name, Set<MenuItem> menuItemSet) {
        this.productName = name;
        this.menuItemSet = menuItemSet;
    }

    public Set<MenuItem> getMenuItemSet() {
        return menuItemSet;
    }

    @Override
    public int computePrice() {
        int price = 0;
        for (MenuItem menuItem : menuItemSet) {
            price += menuItem.computePrice();
        }
        return price;
    }

    public void removeItem(MenuItem menuItem) {
        menuItemSet.remove(menuItem);
    }

    @Override
    public String toString() {
        String components = new String();
        for(MenuItem menuItem : menuItemSet) {
            components = components + menuItem.getItemName() + ", ";
        }
        return components;
    }
}
