package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mandeep Singh on 11/08/19 at 4:48 PM
 */
public class Order {
    private int orderId;
    private Date orderTime;
    private Stock stock;
    private Type type;
    private int qty;
    private float price;
    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public Order(int orderId, Date orderTime, Stock stock, Type type, int qty, float price) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.stock = stock;
        this.type = type;
        this.qty = qty;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Stock getStock() {
        return stock;
    }

    public Type getType() {
        return type;
    }

    public int getQty() {
        return qty;
    }

    public float getPrice() {
        return price;
    }

    public static Date parseDate(String date) throws ParseException {
        return format.parse(date);
    }

    @Override
    public String toString() {
        return "entity.Order{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", stock=" + stock +
                ", type=" + type +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}

