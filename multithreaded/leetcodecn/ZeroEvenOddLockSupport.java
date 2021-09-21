package leetcodecn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

// LC-CN 1116
public class ZeroEvenOddLockSupport {

    private int n;

    private Map<String, Thread> map = new ConcurrentHashMap<>();

    volatile int state = 0; // 0 打印 0 ， 1 打印奇数， 2 打印偶数

    public ZeroEvenOddLockSupport(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        map.put("zero", Thread.currentThread());
        for (int i = 1; i <= n; i++) {
            while (state != 0) {
                LockSupport.park();
            }
            printNumber.accept(0);
            if ((i & 1) == 0) { // 偶数
                state = 2;
            } else {
                state = 1;
            }
            map.forEach((k, v) -> LockSupport.unpark(v)); // 通知其他两个线程
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        map.put("even", Thread.currentThread());
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                LockSupport.park();
            }
            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero")); // 通知zero线程
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        map.put("odd", Thread.currentThread());
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                LockSupport.park();
            }
            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero")); // 通知zero线程
        }
    }
}

