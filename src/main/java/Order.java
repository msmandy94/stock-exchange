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
    private float amount;

    public Order(int orderId, Date orderTime, Stock stock, Type type, int qty, float amount) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.stock = stock;
        this.type = type;
        this.qty = qty;
        this.amount = amount;
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

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", stock=" + stock +
                ", type=" + type +
                ", qty=" + qty +
                ", amount=" + amount +
                '}';
    }
}

