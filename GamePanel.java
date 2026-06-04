import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Monkey monkey;
    private Environment env;
    private Timer timer;
    private boolean gameOver = false;
    private boolean gameWon = false;

    public GamePanel(Monkey monkey, Environment env) {
        this.monkey = monkey;
        this.env = env;
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(1000 / 60, this); // 60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Background
        g2d.setColor(new Color(135, 206, 235)); // Sky blue
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Smooth Camera follow monkey
        int cameraX = Math.max(0, (monkey.getCurrentBar() * 100) - getWidth() / 3);
        g2d.translate(-cameraX, 0);

        // Draw Bars & Obstacles
        for (int i = 0; i < env.getNumberOfBars(); i++) {
            int barX = i * 100 + 50;
            g2d.setColor(new Color(139, 69, 19)); // Saddle Brown
            g2d.fillRect(barX, getHeight() / 2, 20, getHeight() / 2); // The bar
            
            Obstacle obs = env.getBars()[i];
            if (obs.isObstacle()) {
                g2d.setColor(Color.RED);
                g2d.fillOval(barX - 10, getHeight() / 2 - 20, 40, 40);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                g2d.drawString(obs.getType(), barX - 25, getHeight() / 2 - 25);
            }
        }

        // Draw Monkey
        g2d.setColor(new Color(101, 67, 33)); // Dark Brown
        int monkeyX = monkey.getCurrentBar() * 100 + 50;
        g2d.fillOval(monkeyX - 10, getHeight() / 2 - 40, 40, 40);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Monkey", monkeyX - 10, getHeight() / 2 - 45);
        
        g2d.translate(cameraX, 0);

        // Draw HUD
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Lives: " + monkey.getLives(), 20, 30);
        g2d.drawString("Score: " + monkey.getScore(), 20, 60);
        g2d.drawString("Progress: " + monkey.getCurrentBar() + " / " + (env.getNumberOfBars() - 1), 20, 90);
        
        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            g2d.drawString("GAME OVER", getWidth() / 2 - 150, getHeight() / 2);
        } else if (gameWon) {
            g2d.setColor(new Color(34, 139, 34)); // Forest green
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            g2d.drawString("YOU WIN!", getWidth() / 2 - 120, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { repaint(); }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver || gameWon) return;
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (monkey.getCurrentBar() < env.getNumberOfBars() - 1) {
                monkey.jump();
                
                Obstacle nextObs = env.getBars()[monkey.getCurrentBar()];
                if (nextObs.isObstacle()) {
                    monkey.hitObstacle();
                    nextObs.setSafe(); // Clear the obstacle off the map once it is hit
                    if (monkey.getLives() <= 0) gameOver = true;
                }
                
                if (monkey.getCurrentBar() == env.getNumberOfBars() - 1) gameWon = true;
            }
        }
    }
    
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}