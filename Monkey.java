// This holds status on the monkey and its actions.
public class Monkey {
    private int lives = 3; // The monkey starts with 3 lives.
    private int score = 0; // The score of the monkey
    private int currentBar = 0; // Which bar the monkey currently is on.

    public Monkey() {
        // Default constructor
    }
    
    public Monkey(int lives) {
        this.lives = lives;
    }
}
