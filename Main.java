import javax.swing.JFrame;

// This is main class for the game.
public class Main {
    public static void main(String[] args) {
        Monkey monkey = new Monkey();
        Environment env = new Environment();

        JFrame frame = new JFrame("Crossy Monkey");
        GamePanel panel = new GamePanel(monkey, env);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
