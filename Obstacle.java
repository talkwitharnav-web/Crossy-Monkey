import java.util.*;
public class Obstacle {
    private boolean isObstacle;
    private ArrayList<String> obstacleTypes = new ArrayList<String>(); // List of possible obstacle types
    private String type = "None";

    // Default constructor that initializes the list with some default obstacles
    public Obstacle() {
        obstacleTypes.add("Snake");
        obstacleTypes.add("Spike");
        obstacleTypes.add("Banana Peel");
        randomize();
    }

    // This constructor takes in string input of all obstacles seperated by , eg "Banana, Peel, Spike, Kid", then throws it into arraylist
    public Obstacle(String obstacles) {
        String[] obstacleArray = obstacles.split(","); // Split the input string into an array
        for (String obstacle : obstacleArray) {
            obstacleTypes.add(obstacle.trim()); // Add each obstacle to the list, trimming whitespace
        }
        randomize();
    }

    private void randomize() {
        if (Math.random() < 0.3) { // 30% chance for an obstacle
            isObstacle = true;
            type = obstacleTypes.get((int) (Math.random() * obstacleTypes.size()));
        }
    }

    public boolean isObstacle() { return isObstacle; }
    public String getType() { return type; }

    public void setSafe() {
        isObstacle = false;
        type = "None";
    }
}
