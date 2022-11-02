import java.util.*;
import java.awt.Point;

public class Snake {

  private List<Point> cells;
  private char direction;

  public Snake() {
    this.cells = new ArrayList<>();
    initialize();
  }

  public void initialize() {
    this.direction = 'R';
    cells.clear();
    for (int i = 5; i >= 0; i--) {
      cells.add(new Point(i, 0));
    }
  }

  public void addHead(Point head) {
    cells.add(0, head);
  }

  public void addTail(Point tail) {
    cells.add(tail);
  }

  public void removeTail() {
    cells.remove(cells.size() - 1);
  }


  public Point getHead() {
    return cells.get(0);
  }

  public Point getTail() {
    return cells.get(cells.size() - 1);
  }

  public char getDirection() {
    return direction;
  }

  public void setDirection(char c) {
    this.direction = c;
  }

  public List<Point> getCells() {
    return this.cells;
  }
}