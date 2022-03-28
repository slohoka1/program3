import java.util.*;

public class Algorithms {
    public static int greedy1(List<Item> items, int capacity) {
        items.sort(Comparator.comparing(i -> i.profitPerWeight(), Comparator.reverseOrder()));

        int totalProfit = 0;
        int curWeight = 0;
        for (Item i : items) {
            if (curWeight + i.weight() <= capacity) {
                curWeight += i.weight();
                totalProfit += i.profit();
            }
        }

        return totalProfit;
    }

    public static long greedy2(List<Item> items, int capacity) {
        long greedy1 = greedy1(items, capacity);
        long max = Collections.max(items, Comparator.comparing(i -> i.profit())).profit();

        return Math.max(greedy1, max);
    }
}
