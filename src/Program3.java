import java.io.*;
import java.nio.file.*;
import java.util.*;

class Program3 {

    public static void main(String[] args) {
        Program3 solution = new Program3();

        // setup output
        PrintStream newOut;
        try {
            newOut = new PrintStream(args[1]);
            System.setOut(newOut);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // read data
        // parse test cases
        // call algorithms
        solution.parseAndRunTests(solution.readLines(args[0]), Integer.parseInt(args[2]));
    }

    private List<String> readLines(String file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void parseAndRunTests(List<String> lines, int algo) {
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).trim().split(" ");
            assert tokens.length == 2;

            int numItems = Integer.parseInt(tokens[0]);
            int capacity = Integer.parseInt(tokens[1]);

            List<Item> items = new ArrayList<>();
            for (int j = i + 1; j <= i + numItems; j++) {
                String[] item = lines.get(j).trim().split(" ");
                items.add(new Item(j - i, Integer.parseInt(item[0]), Integer.parseInt(item[1])));
            }

            List<Item> chosenItems = new ArrayList<>();
            long totalProfit = -1;
            long start = -1;
            long end = -1;
            switch (algo) {
                case 0: {
                    start = System.currentTimeMillis();
                    totalProfit = Algorithms.greedy1(items, capacity, chosenItems);
                    end = System.currentTimeMillis();
                }
                break;
                case 1: {
                    start = System.currentTimeMillis();
                    totalProfit = Algorithms.greedy2(items, capacity, chosenItems);
                    end = System.currentTimeMillis();
                }
                break;
                case 2: {
                    start = System.currentTimeMillis();
                    totalProfit = Algorithms.backtracking(items, capacity, chosenItems);
                    end = System.currentTimeMillis();
                }
                break;
                default:
                    throw new IllegalArgumentException("Invalid Algorithm number: " + algo);
            }

            System.out.print(numItems + " " + totalProfit + " " + (end - start) + " ");
            chosenItems.sort(Comparator.comparingInt(item -> item.index()));
            for (Item item : chosenItems) {
                System.out.print(item.index() + " ");
            }
            System.out.println();

            i += numItems; // skip current test case lines
        }
    }
}