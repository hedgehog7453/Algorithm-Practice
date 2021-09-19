import java.math.BigInteger;

public class Solution {
  
  public static void main(String args[]) {
    System.out.println(solution("4"));
    System.out.println(solution("15"));
  }
  
  public static int solution(String x) {
    BigInteger b = new BigInteger(x);
    int count = 0;
    while (b.compareTo(new BigInteger("1")) == 1) {
      if (b.mod(new BigInteger("2")).compareTo(new BigInteger("0")) == 0) {
        b = b.divide(new BigInteger("2"));
      } else if (b.compareTo(new BigInteger("3")) == 0 || b.mod(new BigInteger("4")).compareTo(new BigInteger("1")) == 0) {
        b = b.subtract(new BigInteger("1"));
      } else {
        b = b.add(new BigInteger("1"));
      }
      count++;
    }
    return count;
  }
  


// Time out. Recursion no good for this problem!
  public static int timeOutSolution(String x) {
    if (x.length() >= 10) {
      if (x.equals("1")) return 0;
      if (x.equals("2")) return 1;
      if (x.equals("3")) return 2;
      if (x.equals("4")) return 2;
      BigInteger b = new BigInteger(x);
      int lastDigit = x.charAt(x.length()-1) - 48;
      if (lastDigit % 2 == 0) {
        return 1 + solution(b.divide(new BigInteger("2")).toString());
      } else {
        int up = solution(b.add(new BigInteger("1")).toString());
        int down = solution(b.subtract(new BigInteger("1")).toString());
        return 1 + Math.min(up, down);
      }
    }
  }
}
