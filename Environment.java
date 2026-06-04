public class Environment {
    private int numberOfBars = 0; // The total number of bars in the environment

    public Environment() {
        numberOfBars = (int)(Math.random() * 75) + 25; // Generating 75 values, minimum of 25 bars
    }

    public Environment(int numberOfBars) {
        this.numberOfBars = numberOfBars;
    }
}
