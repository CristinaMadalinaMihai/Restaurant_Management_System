package business_layer_MODEL;

public class BaseProduct extends MenuItem {

    public BaseProduct(String itemName, int itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    public int computePrice() {
        return getItemPrice();
    }
}
