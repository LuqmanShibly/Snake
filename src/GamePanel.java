import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {


  private static final int SCREEN_WIDTH = 600;
  private static final int SCREEN_HEIGHT = 600;
  private static final int UNIT_SIZE = 25;
  private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
  private final int DELAY = 75;
  private final int x[] = new int[GAME_UNITS];
  private final int y[] = new int[GAME_UNITS];
  private int bodyParts = 6;
  private int applesEaten;
  private int appleX;
  private int appleY;
  private char direction = 'R';
  private boolean running = false;
  private Timer timer;
  private Random rand; //


  public GamePanel() {
    rand = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    startGame();
  }

  /**
   *
   */
  public void startGame() {
    newApple();
    running  = true;
    timer = new Timer(DELAY, this);
    timer.start();
  }

  /**
   * s
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  /**
   *
   * @param g
   */
  public void draw(Graphics g) {
    if (running) {
      g.setColor(Color.red);
      g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

      for (int i = 0; i < bodyParts; i ++) {
        if (i == 0) {
          g.setColor(Color.green);
        } else {
          g.setColor(new Color(45, 180, 0));
        }
        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
      }
      g.setColor(Color.red);
      g.setFont(new Font("Ink Free", Font.BOLD, 40));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score + " + applesEaten)) / 2, g.getFont().getSize());
    } else {
      gameOver(g);
    }
  }

  public void newApple() {
    this.appleX = rand.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
    this.appleY = rand.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;

  }

  public void move () {
    for (int i = bodyParts; i > 0; i--) {
      x[i] = x[i-1];
      y[i] = y[i-1];
    }

    switch (direction) {
      case 'U':
        y[0] = y[0] - UNIT_SIZE;
        break;
      case 'D':
        y[0] = y[0] + UNIT_SIZE;
        break;
      case 'L':
        x[0] = x[0] - UNIT_SIZE;
        break;
      case 'R':
        x[0] = x[0] + UNIT_SIZE;
        break;
    }
  }

  public void checkApple() {
    if ((x[0] == appleX) && (y[0] == appleY)) {
      this.bodyParts++;
      this.applesEaten++;
      newApple();
    }
  }

  public void checkCollisions() {
    // check if it hits the body
    for (int i = bodyParts; i > 0; i--) {
      if ((x[0] == x[i]) && (y[0] == y[i]))
        this.running = false;
    }

    // check if it hits the left border
    if (x[0] < 0)
      this.running = false;

    // check if it hits the right border
    if (x[0] > SCREEN_WIDTH)
      this.running = false;

    // check if the head touches the top border
    if (y[0] < 0)
      this.running = false;

    // check if head touches bottom border
    if (y[0] > SCREEN_HEIGHT)
      this.running = false;

    if (!this.running)
      this.timer.stop();
  }

  public void gameOver(Graphics g) {
    // Shows the score in the gamer over
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 40));
    FontMetrics metrics1 = getFontMetrics(g.getFont());
    g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score + " + applesEaten)) / 2, g.getFont().getSize());

    // Game over text
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 75));
    FontMetrics metrics2 = getFontMetrics(g.getFont());
    g.drawString("Game over", (SCREEN_WIDTH - metrics2.stringWidth("Game over")) / 2, SCREEN_HEIGHT / 2);
  }

  @Override
  public void actionPerformed (ActionEvent e) {
    if (running) {
      move();
      checkApple();
      checkCollisions();
    }
    repaint();
  }

  public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
          if (direction != 'R')
            direction = 'L';
          break;

        case KeyEvent.VK_RIGHT:
          if (direction != 'L')
            direction = 'R';
          break;

          case KeyEvent.VK_DOWN:
            if (direction != 'U')
              direction = 'D';
            break;

          case KeyEvent.VK_UP:
            if (direction != 'D')
              direction = 'U';
            break;
      }
    }
  }
}