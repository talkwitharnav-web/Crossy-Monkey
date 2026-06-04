import java.util.*;
public class Obstacle {
    private boolean isObstacle;
    private ArrayList<String> obstacleTypes = new ArrayList<String>(); // List of possible obstacle types

    // Default constructor that initializes the list with some default obstacles
    public Obstacle() {
        obstacleTypes.add("Snake");
        obstacleTypes.add("Spike");
        obstacleTypes.add("Banana Peel");
    }

    // This constructor takes in string input of all obstacles seperated by , eg "Banana, Peel, Spike, Kid", then throws it into arraylist
    public Obstacle(String obstacles) {
        String[] obstacleArray = obstacles.split(","); // Split the input string into an array
        for (String obstacle : obstacleArray) {
            obstacleTypes.add(obstacle.trim()); // Add each obstacle to the list, trimming whitespace
        }
    }
}
