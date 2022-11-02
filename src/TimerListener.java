import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TimerListener implements ActionListener {

  private final SnakeGame view;
  private final SnakeModel model;

  public TimerListener(SnakeGame view, SnakeModel model) {
    this.view = view;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
    moveSnake();
    checkApple();
    model.checkCollisions();
    if (model.isGameOver()) {
      Timer timer = (Timer) event.getSource();
      timer.stop();
      model.setRunning(false);
      view.getRestartButton().setEnabled(true);
    }
    view.repaint();
  }

  private void moveSnake() {
    Snake snake = model.getSnake();
    Point head = (Point) snake.getHead().clone();

    switch (snake.getDirection()) {
    case 'U':
        head.y--;
        break;
    case 'D':
        head.y++;
        break;
    case 'L':
        head.x--;
        break;
    case 'R':
        head.x++;
        break;
    }

    snake.removeTail();
    snake.addHead(head);
}

private void checkApple() {
    Point appleLocation = model.getAppleLocation();
    Snake snake = model.getSnake();
    Point head = snake.getHead();
    Point tail = (Point) snake.getTail().clone();

    if (head.x == appleLocation.x && head.y == appleLocation.y) {
        model.incrementApplesEaten();
        snake.addTail(tail);
        model.generateApple();
    }
}
}
