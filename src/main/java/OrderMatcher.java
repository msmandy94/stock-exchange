import java.util.Queue;

/**
 * @author Mandeep Singh on 11/08/19 at 8:08 PM
 */
public class OrderMatcher implements Runnable {
    private Stock stock;

    OrderMatcher(Stock stock) {
        this.stock = stock;
        Thread.currentThread().setName("OrderMatcherThread" + stock.name());
    }

    @Override
    public void run() {
        DataStore dataStore = AllDataStores.getDataStoreByStockType(stock);
        Queue<Order> pendingOrderBook = dataStore.getPendingOrderQueue();
        while (isRunning()) {
            Order order = pendingOrderBook.poll();
            assert order != null;
            switch (order.getType()) {
                case BUY:
                    dataStore.newBuyOrder(order);
                    break;
                case SELL:
                    dataStore.newSellOrder(order);
                    break;
                default:
                    // todo handle error quietly
                    throw new IllegalArgumentException("Order's type can only be BUY or SELL");
            }
        }
    }

    private boolean isRunning() {
        return true;
    }

    public Stock getStock() {
        return stock;
    }
}
