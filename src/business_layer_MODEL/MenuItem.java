package business_layer_MODEL;

public abstract class MenuItem implements java.io.Serializable{

    public MenuItem() {

    }

    public abstract int computePrice();

    protected String itemName;
    protected int itemPrice;
    protected CompositeProduct compositeProduct;

    public MenuItem(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

}
