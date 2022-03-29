public record Item(int index, int weight, int profit) {
    public double profitPerWeight() {
        return profit / (double)weight;
    }
}
