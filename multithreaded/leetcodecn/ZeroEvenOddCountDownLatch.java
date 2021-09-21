package leetcodecn;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

// LC-CN 1116
public class ZeroEvenOddCountDownLatch {

    private int n;

    private CountDownLatch zeroLatch = new CountDownLatch(0);
    private CountDownLatch evenLatch = new CountDownLatch(1); // 偶数
    private CountDownLatch oddLatch = new CountDownLatch(1); // 奇数

    public ZeroEvenOddCountDownLatch(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroLatch.await();
            printNumber.accept(0); // 打印0
            zeroLatch = new CountDownLatch(1);
            if ((i & 1) == 1) {
                oddLatch.countDown(); // 如果是奇数，就打印奇数
            } else {
                evenLatch.countDown();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                evenLatch.await(); // 开始打印偶数
                printNumber.accept(i);
                evenLatch = new CountDownLatch(1);
                zeroLatch.countDown(); // 是否zero线程
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 1) {
                oddLatch.await(); // 开始打印奇数
                printNumber.accept(i);
                oddLatch = new CountDownLatch(1);
                zeroLatch.countDown(); // 是否zero线程
            }
        }
    }
}
