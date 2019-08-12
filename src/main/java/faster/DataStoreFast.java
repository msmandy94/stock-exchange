//package faster;
//
//import com.sun.jmx.remote.internal.ArrayQueue;
//import com.sun.org.apache.xerces.internal.impl.xs.util.ObjectListImpl;
//import entity.MatchedOrder;
//import entity.Order;
//import javafx.collections.ObservableList;
//import javafx.collections.transformation.SortedList;
//
//import java.util.*;
//
///**
// * @author Mandeep Singh on 12/08/19 at 7:44 PM
// */
//public class DataStoreFast {
//    // hot's are top 10
//    TreeMap<Order, Integer> hotBuyingOrders = new TreeMap<>((o1, o2) -> {
//        if (o1.getPrice() > o2.getPrice()) {
//            return -1; // ascending
//        } else if (o1.getPrice() < o2.getPrice()) {
//            return 1; // ascending
//        } else {
//            return o1.getOrderTime().compareTo(o2.getOrderTime());// oldest date first
//        }
//    });
//
//    TreeMap<Order, Integer> hotSellingOrders = new TreeMap<>((o1, o2) -> {
//        if (o1.getPrice() > o2.getPrice()) {
//            return 1; // descending
//        } else if (o1.getPrice() < o2.getPrice()) {
//            return -1; // descending
//        } else {
//            return o1.getOrderTime().compareTo(o2.getOrderTime());// oldest date first
//        }
//    });
//
//    // remaining
//    TreeMap<OrderRange, Integer> buyBuckets = new TreeMap<>((o1, o2) -> {
//        if (o1.getFirstPrice() > o2.getFirstPrice() && o1.getLastPrice() > o2.getLastPrice()) {
//            return 1;
//        } else if (o1.getFirstPrice() < o2.getFirstPrice() && o1.getLastPrice() < o2.getLastPrice()){
//            return -1;
//        } else{
//            // range overlaps
//            // todo: a case where all the orders in a range are of same price
//            return 0;
//        }
//    });
//    // remaining
//    TreeMap<OrderRange, Integer> sellBuckets = new TreeMap<>((o1, o2) -> {
//        if (o1.getFirstPrice() > o2.getFirstPrice() && o1.getLastPrice() > o2.getLastPrice()) {
//            return -1;
//        } else if (o1.getFirstPrice() < o2.getFirstPrice() && o1.getLastPrice() < o2.getLastPrice()){
//            return 1;
//        } else{
//            // range overlaps
//            // todo: a case where all the orders in a range are of same price
//            return 0;
//        }
//    });
//
//    TreeSet<OrderRange> buyBuckets1 = new TreeSet<>(new Comparator<OrderRange>() {
//        @Override
//        public int compare(OrderRange o1, OrderRange o2) {
//            return 0;
//        }
//    });
//
//    public synchronized void newBuyOrder(Order order) {
//
//    }
//
//    public synchronized void newSellOrder(Order order) {
//
//    }
//}
