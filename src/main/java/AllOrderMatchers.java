import entity.Stock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mandeep Singh on 11/08/19 at 8:08 PM
 */
public class AllOrderMatchers {
    public static void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Stock stock : Stock.values()) {
            executorService.submit(new OrderMatcher(stock));
        }
        executorService.shutdown();
    }
}
