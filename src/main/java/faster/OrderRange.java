//package faster;
//
//import entity.Order;
//
//import java.util.Comparator;
//import java.util.TreeMap;
//
///**
// * @author Mandeep Singh on 12/08/19 at 7:55 PM
// */
//public class OrderRange {
//    static int capacity = 10;
//    TreeMap<Order, Integer> items;
//
//    OrderRange(Comparator<Order> comparator) {
//        items = new TreeMap<>(comparator);
//    }
//
//    public float getFirstPrice() {
//        return items.firstKey().getPrice();
//    }
//
//    public float getLastPrice() {
//        return items.lastKey().getPrice();
//    }
//
//    public Order getFirstOrder() {
//        return items.firstKey();
//    }
//
//    public Order getLstOrder() {
//        return items.lastKey();
//    }
//
//
//    // O(log(n))
//    public void put(Order order) {
//        if (items.size() >= capacity) {
//            throw new IllegalArgumentException("capacity exceeded");
//        }
//        items.put(order, order.getQty());
//    }
//
//    // O(log(n))
//    public void remove(Order order) {
//        if (items.size() <= 0) {
//            throw new IllegalArgumentException("capacity exceeded");
//        }
//        items.remove(order);
//    }
//
//    // O(log(n))
//    public void update(Order order, Integer qty) {
//        if (items.size() <= 0) {
//            throw new IllegalArgumentException("capacity exceeded");
//        }
//        items.put(order, qty);
//    }
//}
