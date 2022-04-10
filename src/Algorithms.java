import java.util.*;

public class Algorithms {

    private static List<Item> items, returnItems;
    private static int capacity;
    public static void setContext(List<Item> items, int capacity, List<Item> returnItems) {
        Algorithms.items = items;
        Algorithms.capacity = capacity;
        Algorithms.returnItems = returnItems;
    }

    public static int greedy1() {
        List<Item> copyItems = new ArrayList<>(items);
        copyItems.sort(Comparator.comparing(i -> i.profitPerWeight(), Comparator.reverseOrder()));

        int totalProfit = 0;
        int curWeight = 0;
        for (Item i : copyItems) {
            if (curWeight + i.weight() <= capacity) {
                curWeight += i.weight();
                totalProfit += i.profit();
                returnItems.add(i);
            }
        }

        return totalProfit;
    }

    public static long greedy2() {
        long greedy1 = greedy1();
        Item maxItem = Collections.max(items, Comparator.comparing(i -> i.profit()));
        long max = maxItem.profit();
        if (max > greedy1 && maxItem.weight() <= capacity) {
            returnItems.clear();
            returnItems.add(maxItem);
            return max;
        }
        return greedy1;
    }

    // context for backtracking
    private static long maxProfit;
    public static long getMaxProfit() { return maxProfit; }

    private static String includedItems;
    public static String getIncludedItems() { return includedItems; }

    public static void resetBacktrackingContext() {
        maxProfit = 0;
        includedItems = "";
    }


    public static void backtracking(final int itemIndex, final int weight, final int profit, String solution) {
        if (profit > maxProfit && weight <= capacity) {
            maxProfit = profit;
            includedItems = solution;
        }

        if (itemIndex < items.size() && isPromising(itemIndex, weight)) { // have more items to consider
            // yes
            int newWeight = weight + items.get(itemIndex).weight();
            int newProfit = profit + items.get(itemIndex).profit();
            backtracking(itemIndex + 1, newWeight, newProfit, solution + (itemIndex + 1) + " "); // by considering the current item
            
            // no
            // no change to weight or profit
            backtracking(itemIndex + 1, weight, profit, solution); // without considering the current item
        }
    }

    private static boolean isPromising(int itemIndex, int weight) {
        if (weight > capacity) {
            return false;
        }

        long bound = greedy2();
        return (maxProfit < bound);
    }
}
