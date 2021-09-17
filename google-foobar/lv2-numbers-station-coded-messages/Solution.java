public class Solution {
  
  public static void main(String args[]) {    
    int[] res = solution(new int[] {4, 3, 10, 2, 8}, 12);
    System.out.printf("%d, %d", res[0], res[1]);
  }
  
  public static int[] solution(int[] l, int t) {
    // cursors
    int start = 0;
    int end = 0;
    // current sum
    int sum = l[0];
    // start iteration
    while (end < l.length) {
      if (sum == t) {
        // reached rum
        return new int[] {start, end};
      } else if (sum < t) {
        // if sum is less than target, increase right cursor
        end++;
        if (end < l.length) {
          sum += l[end];
        } else {
          // if right cursor reached the end of the sequence, return {-1, -1}
          return new int[] {-1, -1};
        }
      } else if (sum > t) {
        // if sum is more than target, increase the left cursor
        if (start < end) {
          sum -= l[start];
          start++;
        } else if (start == end && end < l.length) {
          // if left cursor meets right cursor, increase both of them
          start++;
          end++;
          sum = l[start];
        } else {
          // if right cursor reached the end of the sequence, return {-1, -1}
          return new int[] {-1, -1};
        }
      }
    }
    // if loop finishes without reaching the target, return {-1, -1}
    return new int[] {-1, -1};
  }
}
