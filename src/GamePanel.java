import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel{
  private static final long serialVersionUID = 1L;
  private final int MARGIN;
  private final int SCORE_AREA_HEIGHT;
  private final int UNIT_SIZE;
  private final SnakeModel model;

  public GamePanel(SnakeModel model) {
    this.model = model;
    this.MARGIN = 10;
    this.UNIT_SIZE = 25;
    this.SCORE_AREA_HEIGHT = 36 + MARGIN;
    this.setBackground(Color.black);

    Dimension gameArea = model.getGameArea();
    int width = gameArea.width * UNIT_SIZE + 2 * MARGIN;
    int height = gameArea.height * UNIT_SIZE + 2 * MARGIN;
    this.setPreferredSize(new Dimension(width, height));
    setKeyBindings();
  }

  public void setKeyBindings() {
    InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = this.getActionMap();
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");

    actionMap.put("up", new MovementAction(model, 'U', 'D'));
    actionMap.put("down", new MovementAction(model, 'D', 'U'));
    actionMap.put("left", new MovementAction(model, 'L', 'R'));
    actionMap.put("right", new MovementAction(model, 'R', 'L'));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Dimension gameArea = model.getGameArea();
    drawSnake(g);
    drawScore(g, gameArea);
    if (model.isGameOver()) {
      drawGameOver(g, gameArea);
    } else {
      drawApple(g);
    }
  }


  public void drawSnake(Graphics g) {
    Snake snake = model.getSnake();
    List<Point> cells = snake.getCells();
    Point head = cells.get(0);
    drawSnakeCell(g, head, Color.green);
    for (int i = 1; i < cells.size(); i++) {
      Color color = new Color(45, 180, 0);
      head = cells.get(i);
      drawSnakeCell(g, head, color);
    }
  }

  public void drawSnakeCell(Graphics g, Point point, Color color) {
    int x = MARGIN + point.x * UNIT_SIZE;
    int y = MARGIN + SCORE_AREA_HEIGHT + point.y * UNIT_SIZE;
    if (point.y >= 0) {
      g.setColor(color);
      g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
    }
  }

  public void drawScore(Graphics g, Dimension gameArea) {
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 36));
    FontMetrics metrics = getFontMetrics(g.getFont());
    int width = 2 * MARGIN + gameArea.width * UNIT_SIZE;
    String text = "SCORE: " + model.getApplesEaten();
    int textWidth = metrics.stringWidth(text);
    g.drawString(text, (width - textWidth) / 2, g.getFont().getSize());
}

  public void drawGameOver(Graphics g, Dimension gameArea) {
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 72));
    FontMetrics metrics = getFontMetrics(g.getFont());
    String text = "Game Over";
    int textWidth = metrics.stringWidth(text);
    g.drawString(text, (getWidth() - textWidth) / 2, getHeight() / 2);
  }

  public void drawApple(Graphics g) {
    g.setColor(Color.red);
    Point point = model.getAppleLocation();
    if (point != null) {
        int a = point.x * UNIT_SIZE + MARGIN + 1;
        int b = point.y * UNIT_SIZE + MARGIN + SCORE_AREA_HEIGHT + 1;
        g.fillOval(a, b, UNIT_SIZE - 2, UNIT_SIZE - 2);
    }
  }
}
