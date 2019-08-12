import entity.MatchedOrder;
import entity.Order;
import entity.Stock;
import entity.Type;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Mandeep Singh on 11/08/19 at 5:43 PM
 */
public class DataStore {
    private Queue<Order> pendingOrderQueue = new LinkedBlockingDeque<>(10);
    private TreeMap<Order, Integer> pendingBuyOrders = new TreeMap<>((o1, o2) -> {
        if (o1.getPrice() > o2.getPrice()) {
            return -1; // ascending
        } else if (o1.getPrice() < o2.getPrice()) {
            return 1; // ascending
        } else {
            return o1.getOrderTime().compareTo(o2.getOrderTime());// oldest date first
        }
    });
    private TreeMap<Order, Integer> pendingSellOrders = new TreeMap<>((o1, o2) -> {
        if (o1.getPrice() > o2.getPrice()) {
            return 1; // descending
        } else if (o1.getPrice() < o2.getPrice()) {
            return -1; // descending
        } else {
            return o1.getOrderTime().compareTo(o2.getOrderTime());// oldest date first
        }
    });
    private List<MatchedOrder> matchedOrders = new ArrayList<>();

    public void placeOrder(Order order) {
        pendingOrderQueue.add(order);

        System.out.println("order placed");
    }

    public Queue<Order> getPendingOrderQueue() {
        return pendingOrderQueue;
    }


    public synchronized void newBuyOrder(Order order) {
        int qty = order.getQty();
        Map.Entry<Order, Integer> availableSellingOrder = pendingSellOrders.firstEntry();
        if (availableSellingOrder == null || availableSellingOrder.getKey().getPrice() > order.getPrice()) {
            // no match happens
            pendingBuyOrders.put(order, order.getQty());
            return;
        }

        while (availableSellingOrder != null) {
            // match happens
            if (availableSellingOrder.getValue() == order.getQty()) {
                MatchedOrder matchedOrder = new MatchedOrder(
                        availableSellingOrder.getKey().getOrderId(), qty, availableSellingOrder.getKey().getPrice(), order.getOrderId());
                System.out.println(matchedOrder);
                // note that the amount at which transaction happened was of selling order.
                pendingSellOrders.remove(availableSellingOrder.getKey());
                break;
            } else if (availableSellingOrder.getValue() > order.getQty()) {
                MatchedOrder matchedOrder = new MatchedOrder(
                        availableSellingOrder.getKey().getOrderId(), qty, availableSellingOrder.getKey().getPrice(), order.getOrderId());
                System.out.println(matchedOrder);

                // note that the amount at which transaction happened was of selling order.
                pendingSellOrders.put(availableSellingOrder.getKey(), availableSellingOrder.getValue() - qty);
                break;
            } else {
                // selling qty is less
                MatchedOrder matchedOrder = new MatchedOrder(
                        availableSellingOrder.getKey().getOrderId(), availableSellingOrder.getValue(), availableSellingOrder.getKey().getPrice(), order.getOrderId());
                System.out.println(matchedOrder);

                qty = qty - availableSellingOrder.getValue();
                pendingSellOrders.remove(availableSellingOrder.getKey());
                availableSellingOrder = pendingSellOrders.firstEntry();
                // don't break
            }
        }
    }

    public synchronized void newSellOrder(Order order) {
        int qty = order.getQty();
        Map.Entry<Order, Integer> availableBuyOrder = pendingBuyOrders.firstEntry();
        if (availableBuyOrder == null || availableBuyOrder.getKey().getPrice() < order.getPrice()) {
            // no match happens
            pendingBuyOrders.put(order, order.getQty());
            return;
        }

        while (availableBuyOrder != null) {
            // match happens
            if (availableBuyOrder.getValue() == qty) {
                MatchedOrder matchedOrder = new MatchedOrder(
                        order.getOrderId(), qty, order.getPrice(), availableBuyOrder.getKey().getOrderId());
                System.out.println(matchedOrder);

                // note that the amount at which transaction happened was of selling order.
                pendingBuyOrders.remove(availableBuyOrder.getKey());
                break;
            } else if (availableBuyOrder.getValue() > qty) {
                MatchedOrder matchedOrder = new MatchedOrder(
                        order.getOrderId(), qty, order.getPrice(), availableBuyOrder.getKey().getOrderId());
                System.out.println(matchedOrder);

                // note that the amount at which transaction happened was of selling order.
                pendingBuyOrders.put(availableBuyOrder.getKey(), availableBuyOrder.getValue() - qty);
                break;
            } else {
                // buying qty is less
                MatchedOrder matchedOrder = new MatchedOrder(
                        order.getOrderId(), availableBuyOrder.getValue(), availableBuyOrder.getKey().getPrice(), availableBuyOrder.getKey().getOrderId());
                System.out.println(matchedOrder);

                qty = qty - availableBuyOrder.getValue();
                pendingBuyOrders.remove(availableBuyOrder.getKey());
                // don't break
                availableBuyOrder = pendingBuyOrders.firstEntry();
            }
        }
    }

    // test
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        dataStore.pendingBuyOrders.put(new Order(1, new Date(1), Stock.TCS, Type.BUY, 10, 65), 10);
        dataStore.pendingBuyOrders.put(new Order(2, new Date(), Stock.TCS, Type.BUY, 10, 66), 10);
        dataStore.pendingBuyOrders.put(new Order(3, new Date(), Stock.TCS, Type.BUY, 10, 69), 10);
        dataStore.pendingBuyOrders.put(new Order(4, new Date(), Stock.TCS, Type.BUY, 10, 66), 40);
        dataStore.pendingBuyOrders.put(new Order(5, new Date(), Stock.TCS, Type.BUY, 10, 60), 50);
        dataStore.pendingBuyOrders.put(new Order(6, new Date(1), Stock.TCS, Type.BUY, 10, 64), 6);
        dataStore.pendingBuyOrders.put(new Order(7, new Date(3), Stock.TCS, Type.BUY, 10, 64), 7);
        dataStore.pendingBuyOrders.put(new Order(8, new Date(2), Stock.TCS, Type.BUY, 10, 64), 80);
        dataStore.pendingBuyOrders.put(new Order(9, new Date(2), Stock.TCS, Type.BUY, 10, 64), 9);
        for (Map.Entry<Order, Integer> entry : dataStore.pendingBuyOrders.entrySet()) {
            System.out.println(entry);
        }
        System.out.println(dataStore.pendingBuyOrders.firstEntry());
    }
}
