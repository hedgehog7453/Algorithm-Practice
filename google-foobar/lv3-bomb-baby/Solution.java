import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
  
  public static void main(String args[]) {
    System.out.println(solution("2", "1"));
  }
  
  public static String solution(String x, String y) {
    BigInteger steps = new BigInteger("0");
    BigInteger lx = new BigInteger(x); 
    BigInteger ly = new BigInteger(y);
    while (!lx.equals(ly)) {
      BigInteger l = lx.compareTo(ly) == 1 ? lx : ly;
      BigInteger s = lx.compareTo(ly) == 1 ? ly : lx;
      BigInteger m = l.subtract(s).divide(s); 
      if (m.compareTo(new BigInteger("1")) == 1) {
        l = l.subtract(s.multiply(m));
        steps = steps.add(m);
        lx = l;
        ly = s;
      } else {
        lx = l.subtract(s);
        ly = s;
        steps = steps.add(new BigInteger("1"));
      }
    }
    if (lx.toString().equals("1") && ly.toString().equals("1")) {
      return steps.toString();
    } else {
      return "impossible";
    }
  }
  
  
  // Guess I was stupid thinking that using BigInteger is banned in the challenge. 
  public static String solutionThatFailedTest4ForSomeReason(String x, String y) {
    return getSolution(x, y, "0");
  }
  
  private static String getSolution(String x, String y, String n) {
    if (x.equals("1") && y.equals("1")) return n;
    if (x.equals(y)) return "impossible";
    return getSolution(minStr(x, y), subtract(x, y), increment(n));
  }
  
  private static String subtract(String x, String y) {
    int c = compare(x, y);
    if (c == 0) return "0";
    String l = c == 1 ? x : y; // large number
    String s = c == 1 ? y : x; // small number
    int ll = l.length(); // long length
    int sl = s.length(); // short length
    // Append 0s to shorter number to make the two numbers have the same length 
    int diff = ll - sl;
    String append = "";
    for (int i = 0; i < diff; i++) {
      append += "0";
    }
    s = append + s;
    // do the subtraction
    List<String> digits = new ArrayList<String>();
    int borrow = 0;
    for (int i = 1; i <= ll; i++) {
      int ld = toInt(l.charAt(ll-i)); // digit of long
      int sd = toInt(s.charAt(ll-i)); // digit of short
      if (ld - sd - borrow >= 0) {
        digits.add(0, Integer.valueOf(ld-sd-borrow).toString());
        borrow = 0;
      } else {
        digits.add(0, Integer.valueOf(10+(ld-sd-borrow)).toString());
        borrow = 1;
      }
    }
    // remove the prepending 0s
    while (digits.get(0).equals("0")) {
      digits.remove(0);
    }
    // assemble the digits
    String res = "";
    for (int i = 0; i < digits.size(); i++) {
      res += digits.get(i);
    }
    return res;
  }
  
  private static String minStr(String x, String y) {
    int c = compare(x, y);
    return c > 0 ? y : x;
  }

  // 1 if x > y, 0 if x == 1, -1 if x < y
  private static int compare(String x, String y) {
    if (x.equals(y)) return 0;
    int xl = x.length();
    int yl = y.length();
    if (xl > yl) return 1;
    if (xl < yl) return -1;
    // same number of digits
    for (int i = 0; i < xl; i++) {
      if (x.charAt(i) > y.charAt(i)) return 1;
      if (x.charAt(i) < y.charAt(i)) return -1;
    }
    return 0;
  }
  
  private static String increment(String n) {
    int len = n.length();
    int s = len - 1;
    while (s >= 0 && toInt(n.charAt(s)) == 9) {
      s--;
    }
    String res = "";
    if (s == -1) {
      res += "1";
      for (int i = 0; i < len; i++) {
        res += "0";
      }
      return res;
    }
    for (int i = 0; i < s; i++) {
      res += Character.toString(n.charAt(i));
    }
    res += Character.toString(n.charAt(s) + 1);
    for (int i = s+1; i < len; i++) {
      res += "0";
    }
    return res;
  }
  
  private static int toInt(char n) {
    return n - 48;
  }

}