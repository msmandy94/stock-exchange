import entity.Order;
import entity.Stock;
import entity.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mandeep Singh on 12/08/19 at 3:10 PM
 */
public class StockExchangeMain {
    public static void main(String[] args) {

        AllOrderMatchers.start();

        // start taking inputs
        boolean flag = true;
        System.out.println("type in the orders:");
        System.out.println("<time> <stock> <BUY/SELL> <qty> <price>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int counter = 0;
        while (flag) {
            try {
                String line = reader.readLine();
                String[] split = line.split(" ");
                if (split.length != 5) {
                    System.out.println("invalid format");
                }
                Date date = new SimpleDateFormat("hh:mm").parse(split[0]);
                Order order = new Order(counter++, date, Stock.valueOf(split[1]), Type.valueOf(split[2]), Integer.valueOf(split[3]), Float.valueOf(split[4]));
                AllDataStores.placeOrder(order);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
