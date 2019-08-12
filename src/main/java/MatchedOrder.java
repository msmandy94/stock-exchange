/**
 * @author Mandeep Singh on 11/08/19 at 5:50 PM
 */
public class MatchedOrder {
    private int sellOrderId;
    private int qty;
    private float price;
    private int buyOrderId;

    public MatchedOrder(int sellOrderId, int qty, float price, int buyOrderId) {
        this.sellOrderId = sellOrderId;
        this.qty = qty;
        this.price = price;
        this.buyOrderId = buyOrderId;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public int getQty() {
        return qty;
    }

    public float getPrice() {
        return price;
    }

    public int getBuyOrderId() {
        return buyOrderId;
    }

    @Override
    public String toString() {
        return "MatchedOrder{" +
                "sellOrderId=" + sellOrderId +
                ", qty=" + qty +
                ", price=" + price +
                ", buyOrderId=" + buyOrderId +
                '}';
    }
}
