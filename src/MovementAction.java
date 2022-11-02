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

public class MovementAction extends AbstractAction {
  private static final long serialVersionUID = 1L;

  private final char newDirection;
  private final char oppositeDirection;
  private final SnakeModel model;

  public MovementAction(SnakeModel model, char newDirection, char oppositeDirection) {
    this.model = model;
    this.newDirection = newDirection;
    this.oppositeDirection = oppositeDirection;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (model.isRunning()) {
      Snake snake = model.getSnake();
      char direction = snake.getDirection();
      if (direction != oppositeDirection && direction != newDirection) {
        snake.setDirection(newDirection);
      }
    }
  }
}

