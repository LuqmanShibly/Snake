import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import java.util.List;

public class SnakeModel {

  private Snake snake;
  private int applesEaten;
  private Dimension gameArea;
  private Point appleLocation;
  private boolean running;
  private boolean gameOver;
  private Random rand;

  public SnakeModel() {
    this.rand = new Random();
    this.snake = new Snake();
    this.gameArea = new Dimension(24, 24);
  }

  public void initialize() {
    this.running = true;
    this.gameOver = false;
    this.snake.initialize();
    this.applesEaten = 0;
    Point point = generateApple();
    // edge case; if apple is under snake originally
    int y = (point.y == 0) ? 1 : point.y;
    this.appleLocation = new Point(point.x, y);
  }


  public void checkCollisions() {
    Point head = snake.getHead();
    if (head.x < 0 || head.x > gameArea.width) {
      this.gameOver = true;
      return;
    }

    if (head.y < 0 || head.y > gameArea.height) {
      this.gameOver = true;
      return;
    }

    List<Point> cells = snake.getCells();
    for (int i = 0; i < cells.size() - 1; i++) {
      Point cell = cells.get(i);
      if (head.x == cell.x && head.y == cell.y) {
        gameOver = true;
        return;
      }
    }

  }

  public Point generateApple() {
    int x = rand.nextInt(gameArea.width);
    int y = rand.nextInt(gameArea.height);
    this.appleLocation = new Point(x, y);
    return getAppleLocation();
  }

  public Point getAppleLocation() {
    return this.appleLocation;
  }

  public void incrementApplesEaten() {
    this.applesEaten++;
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public boolean isGameOver() {
    return this.gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public Dimension getGameArea() {
    return this.gameArea;
  }

  public int getApplesEaten() {
    return applesEaten;
  }

  public Snake getSnake() {
    return this.snake;
  }
}

