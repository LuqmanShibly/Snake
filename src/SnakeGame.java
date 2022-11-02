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




public class SnakeGame implements Runnable {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new SnakeGame());
  }

  private final GamePanel gamePanel;
  private final JButton restartButton;
  private final SnakeModel model;

  public SnakeGame() {
    this.model = new SnakeModel();
    this.restartButton = new JButton("Start Game");
    this.gamePanel = new GamePanel(model);
  }

  @Override
  public void run() {
    JFrame frame = new JFrame("Snake");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(gamePanel, BorderLayout.CENTER);
    frame.add(createButtonPanel(), BorderLayout.SOUTH);

    frame.pack();
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private JPanel createButtonPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    panel.setBackground(Color.black);

    restartButton.addActionListener(new ButtonListener(this, model));
    panel.add(restartButton);

    return panel;
  }

  public JButton getRestartButton() {
    return restartButton;
  }

  public void repaint() {
    gamePanel.repaint();
  }
}
