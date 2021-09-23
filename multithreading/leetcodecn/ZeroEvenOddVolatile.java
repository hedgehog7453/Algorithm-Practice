package leetcodecn;

import java.util.function.IntConsumer;

// LC-CN 1116
public class ZeroEvenOddVolatile {

    private int n;
    private volatile int curVal = 0;

    public ZeroEvenOddVolatile(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (curVal != 0) {
                Thread.yield(); // 让出资源
            }
            printNumber.accept(0);
            if (i % 2 == 1) {
                curVal = 1;
            } else {
                curVal = 2;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (curVal != 2) {
                Thread.yield(); // 让出资源
            }
            printNumber.accept(i);
            curVal = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (curVal != 1) {
                Thread.yield(); // 让出资源
            }
            printNumber.accept(i);
            curVal = 0;
        }
    }

}
