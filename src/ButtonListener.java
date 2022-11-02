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

public class ButtonListener implements ActionListener {

  private final int delay;
  private final SnakeGame view;
  private final SnakeModel model;
  private final Timer timer;

  public ButtonListener(SnakeGame view, SnakeModel model) {
    this.view = view;
    this.model = model;
    this.delay = 750;
    this.timer = new Timer(delay, new TimerListener(view, model));
  }

  public void actionPerformed(ActionEvent event) {
    JButton button = (JButton) event.getSource();
    String text = button.getText();

    if (text.equals("Start Game")) {
      button.setText("Restart Game");
    }

    button.setEnabled(false);
    model.initialize();
    timer.restart();
  }
}
