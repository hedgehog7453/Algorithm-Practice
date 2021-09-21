package leetcodecn;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

// LC-CN 1116
class ZeroEvenOddThreadYield {

    private int n;
    private AtomicInteger ai = new AtomicInteger(0);

    public ZeroEvenOddThreadYield(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (ai.get() != 0 && ai.get() != 2) {
                Thread.yield();
            }
            printNumber.accept(0);
            ai.incrementAndGet();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (ai.get() != 3) {
                Thread.yield();
            }
            printNumber.accept(i);
            ai.set(0);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            while (ai.get() != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            ai.set(2);
        }
    }
}
