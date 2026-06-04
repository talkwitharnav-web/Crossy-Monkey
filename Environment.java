public class Environment {
    private int numberOfBars = 0; // The total number of bars in the environment
    private Obstacle[] bars;

    public Environment() {
        numberOfBars = (int)(Math.random() * 75) + 25; // Generating 75 values, minimum of 25 bars
        initBars();
    }

    public Environment(int numberOfBars) {
        this.numberOfBars = numberOfBars;
        initBars();
    }

    private void initBars() {
        bars = new Obstacle[numberOfBars];
        for (int i = 0; i < numberOfBars; i++) {
            bars[i] = new Obstacle();
        }
        bars[0].setSafe(); // The starting bar should always be safe!
    }

    public int getNumberOfBars() { return numberOfBars; }
    public Obstacle[] getBars() { return bars; }
}
