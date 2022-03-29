import java.util.*;

public class Algorithms {
    public static int greedy1(List<Item> items, int capacity, List<Item> outChosenItems) {
        items.sort(Comparator.comparing(i -> i.profitPerWeight(), Comparator.reverseOrder()));

        int totalProfit = 0;
        int curWeight = 0;
        for (Item i : items) {
            if (curWeight + i.weight() <= capacity) {
                curWeight += i.weight();
                totalProfit += i.profit();
                outChosenItems.add(i);
            }
        }

        return totalProfit;
    }

    public static long greedy2(List<Item> items, int capacity, List<Item> outChosenItems) {
        long greedy1 = greedy1(items, capacity, outChosenItems);
        Item maxItem = Collections.max(items, Comparator.comparing(i -> i.profit()));
        long max = maxItem.profit();
        if (max > greedy1) {
            outChosenItems.clear();
            outChosenItems.add(maxItem);
        }
        return Math.max(greedy1, max);
    }

    public static long backtracking(List<Item> items, int capacity, List<Item> outChosenItems) {
        return 0;
    }
}
