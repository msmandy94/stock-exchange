import entity.Order;
import entity.Stock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Mandeep Singh on 11/08/19 at 6:52 PM
 */
public class AllDataStores {
    private static Map<Stock, DataStore> dataStoreByStock = new HashMap<>();
    private static Map<Stock, AtomicBoolean> locksByStock = new HashMap<>();
    private static Date lastOrderDate;

    static {
        for (Stock stock : Stock.values()) {
            dataStoreByStock.put(stock, new DataStore());
            locksByStock.put(stock, new AtomicBoolean());
        }
    }

    public static void placeOrder(Order order) {
        // validate time
        validate(order);
        dataStoreByStock.get(order.getStock()).placeOrder(order);
    }

    public static DataStore getDataStoreByStockType(Stock stock) {
        return dataStoreByStock.get(stock);
    }


    private static void validate(Order order) {
        if (order.getOrderTime() == null) {
            throw new IllegalArgumentException("date is mandatory field in order");
        }
        if (lastOrderDate == null) {
            lastOrderDate = order.getOrderTime();
            return;
        }
        if (lastOrderDate.after(order.getOrderTime())) {
            throw new IllegalArgumentException("this order has past date");
        }
    }
}
