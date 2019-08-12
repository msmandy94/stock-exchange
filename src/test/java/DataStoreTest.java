import entity.Order;
import entity.Stock;
import entity.Type;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Mandeep Singh on 12/08/19 at 11:28 PM
 */
public class DataStoreTest {

    @Test
    public void DataStoreTest() throws ParseException {

        AllOrderMatchers.start();

        AllDataStores.placeOrder(new Order(1, Order.parseDate("11:00"), Stock.TCS, Type.BUY, 10, 65));
        AllDataStores.placeOrder(new Order(2, Order.parseDate("12:01"), Stock.TCS, Type.BUY, 10, 64));
        AllDataStores.placeOrder(new Order(3, Order.parseDate("12:03"), Stock.TCS, Type.BUY, 10, 61));
        AllDataStores.placeOrder(new Order(4, Order.parseDate("12:04"), Stock.TCS, Type.BUY, 10, 63));
        AllDataStores.placeOrder(new Order(5, Order.parseDate("12:05"), Stock.TCS, Type.BUY, 10, 60));

        AllDataStores.placeOrder(new Order(6, Order.parseDate("12:06"), Stock.TCS, Type.SELL, 10, 66));
        AllDataStores.placeOrder(new Order(7, Order.parseDate("12:07"), Stock.TCS, Type.SELL, 10, 67));
        AllDataStores.placeOrder(new Order(8, Order.parseDate("12:08"), Stock.TCS, Type.SELL, 10, 78));
        AllDataStores.placeOrder(new Order(9, Order.parseDate("12:09"), Stock.TCS, Type.SELL, 10, 70));
        AllDataStores.placeOrder(new Order(10, Order.parseDate("12:10"), Stock.TCS, Type.SELL, 10, 66));

        AllDataStores.placeOrder(new Order(11, Order.parseDate("13:00"), Stock.TCS, Type.BUY, 15, 66));
        AllDataStores.placeOrder(new Order(11, Order.parseDate("14:00"), Stock.TCS, Type.BUY, 15, 66));

        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
