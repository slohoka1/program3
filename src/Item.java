public record Item(int weight, int profit) {
    public double profitPerWeight() {
        return profit / (double)weight;
    }
}
