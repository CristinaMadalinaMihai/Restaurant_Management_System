package business_layer_MODEL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order {

    private int orderId;
    private int tableNo;
    private String date;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public Order(int orderId, int tableNo) {
        this.orderId = orderId;
        this.tableNo = tableNo;
        this.date = dateFormat.format(new Date());
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableNo() {
        return tableNo;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                tableNo == order.tableNo &&
                date.equals(order.date) &&
                Objects.equals(dateFormat, order.dateFormat);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + orderId;
        result = result * prime + tableNo;
        result = result * prime + date.hashCode();
        return result;
        //return Objects.hash(orderId, tableNo, date, dateFormat, itemName);
    }
}
