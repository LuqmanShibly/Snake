import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class ButtonListener implements ActionListener {

  private final int delay;
  private final SnakeModel model;
  private final Timer timer;

  public ButtonListener(SnakeGame view, SnakeModel model) {
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
