import java.util.List;
import java.util.ArrayList;


public class Solution {
  
  public static void main(String args[]) {
    int[] res = solution(15324);
    for (int i = 0; i < res.length; i++) {
      System.out.printf("%d ", res[i]);
    }
  }
  
  private static List<Integer> res = new ArrayList<Integer>();
    
  public static int[] solution(int area) {
    if (area == 1) {
      res.add(1);
      return res.stream().mapToInt(i -> i).toArray();
    } else if (area == 0) {
      return res.stream().mapToInt(i -> i).toArray();
    } else {
      int next = (int) Math.sqrt(area);
      res.add(next*next);
      return solution(area-next*next);
    }
  }
}
