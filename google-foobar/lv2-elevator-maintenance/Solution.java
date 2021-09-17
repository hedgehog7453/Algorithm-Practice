import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Solution {
  
  public static void main(String args[]) {
    String[] res = solution3(new String[] 
      {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"});
    for (int i = 0; i < res.length; i++) {
      System.out.printf("%s ", res[i]);
    }
  }
  
  public static String[] solution(String[] l) {
    List<String> list = Arrays.asList(l); 
    String[] sorted = sort(list, 0).toArray(new String[0]);
    return sorted;
  }
  
  private static List<String> sort(List<String> l, int i) {
    int maxKey = 0;
    if (i == 3) {
      return l;
    }
    HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
    for (String s : l) {
      String[] vNums = s.split("\\.");
      Integer key = vNums.length <= i ? -1 : Integer.valueOf(vNums[i]);
      if (map.containsKey(key)) {
        List<String> strs = map.get(key);
        strs.add(s);
        map.put(key, strs);
      } else {
        List<String> strs = new ArrayList<String>();
        strs.add(s);
        map.put(key, strs);
      }
      maxKey = Math.max(key, maxKey);
    }
    List<String> sorted = new ArrayList<String>();
    for (int k = -1; k <= maxKey; k++) {
      if (map.containsKey(k)) {
        sorted.addAll(sort(map.get(k), i+1));
      }
    }
    return sorted;
  }
}
