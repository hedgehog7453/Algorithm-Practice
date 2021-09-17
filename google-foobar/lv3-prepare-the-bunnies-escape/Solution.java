import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Stat {

  private int x;
  private int y;
  private int step;
  private boolean hasMoved;

  public Stat(int x, int y, int step) {
    this.x = x;
    this.y = y;
    this.step = step;
    this.hasMoved = false;
  }
  
  public Stat(int x, int y, int step, boolean hasMoved) {
    this.x = x;
    this.y = y;
    this.step = step;
    this.hasMoved = hasMoved;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getStep() {
    return step;
  }
  
  public boolean hasMoved() {
    return hasMoved;
  }

}

public class Solution {

  public static void main(String args[]) {
    int[][] map1 = new int[][] {
      { 0, 0, 0, 0, 0, 0 },
      { 1, 1, 1, 1, 1, 0 },
      { 0, 0, 0, 0, 0, 0 },
      { 0, 1, 1, 1, 1, 1 },
      { 0, 1, 1, 1, 1, 1 },
      { 0, 0, 0, 0, 0, 0 } 
    };

    int[][] map2 = new int[][] {
      { 0, 1, 1, 0 },
      { 0, 0, 0, 1 },
      { 1, 1, 0, 0 },
      { 1, 1, 1, 0 }
    };

    int[][] map3 = new int[][] { { 0 }, { 1 }, { 0 } };
    
    int[][] map4 = new int[][] {
      {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
      {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    System.out.println(solution(map4));
  }

  public static int solution(int[][] map) {
    return getPath(map);
  }
  
  private static List<Stat> getNeighbors(Stat s, int[][] map) {
    List<Stat> neighbors = new ArrayList<Stat>();
    int w = map[0].length;
    int h = map.length;
    int x = s.getX();
    int y = s.getY();
    if (x - 1 >= 0) {  // left
      Stat ns = getNeighborStat(x-1, y, s, map);
      if (ns != null) neighbors.add(ns);
    }
    if (x + 1 < h) {  // right
      Stat ns = getNeighborStat(x+1, y, s, map);
      if (ns != null) neighbors.add(ns);
    }
    if (y - 1 >= 0) {  // up
      Stat ns = getNeighborStat(x, y-1, s, map);
      if (ns != null) neighbors.add(ns);
    }
    if (y + 1 < w) {  // down
      Stat ns = getNeighborStat(x, y+1, s, map);
      if (ns != null) neighbors.add(ns);
    }
    return neighbors;
  }
  
  private static Stat getNeighborStat(int nx, int ny, Stat s, int[][] map) {
    if (map[nx][ny] == 0) {
      return new Stat(nx, ny, s.getStep()+1, s.hasMoved());
    } else {
      if (!s.hasMoved()) {
        return new Stat(nx, ny, s.getStep()+1, true);
      }
    }
    return null;
  }

  private static int getPath(int[][] map) {
    // first stat
    Queue<Stat> queue = new ArrayDeque<Stat>();
    int w = map[0].length;
    int h = map.length;
    queue.add(new Stat(0, 0, 1));
    
    Stat[][] visited = new Stat[h][w];
    int bestSteps = 20 * 20 + 1;
    
    // iterate queue
    while (!queue.isEmpty()) {
      Stat s = queue.poll();
      
      // If goal is reached
      if (s.getX() == h - 1 && s.getY() == w - 1) {
        if (s.getStep() < bestSteps) {
          bestSteps = s.getStep();
        }
        visited[h-1][w-1] = s;
        continue;
      }
      
      // BFS
      List<Stat> neighbors = getNeighbors(s, map);
      for (Stat neighbor : neighbors) {
        Stat v = visited[neighbor.getX()][neighbor.getY()];
        if (v == null || neighbor.getStep() < v.getStep() || (!neighbor.hasMoved() && v.hasMoved())) {
          visited[neighbor.getX()][neighbor.getY()] = neighbor;
          queue.add(neighbor);
        }
        
      }
    }
    return bestSteps;

  }
}