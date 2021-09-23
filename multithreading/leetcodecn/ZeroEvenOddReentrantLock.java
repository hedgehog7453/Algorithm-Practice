package leetcodecn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.function.IntConsumer;

// LC-CN 1116
class ZeroEvenOddReentrantLock {

    private int n;

    private volatile int curVal = 0;

    private Lock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition odd = lock.newCondition();
    private Condition even = lock.newCondition();

    public ZeroEvenOddReentrantLock(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i++) {
                if (curVal != 0) {
                    zero.await();
                }
                printNumber.accept(0);
                if (i % 2 == 1) {
                    curVal = 1;
                    odd.signal();
                } else {
                    curVal = 2;
                    even.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 2; i <= n; i += 2) {
                if (curVal != 2) {
                    even.await();
                }
                printNumber.accept(i);
                curVal = 0;
                zero.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i += 2) {
                if (curVal != 1) {
                    odd.await();
                }
                printNumber.accept(i);
                curVal = 0;
                zero.signal();
            }
        } finally {
            lock.unlock();
        }
    }

}
