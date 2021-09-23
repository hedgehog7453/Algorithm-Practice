package leetcodecn;

import java.util.concurrent.Semaphore;

// LC-CN 1114
public class PrintInOrderSemaphore {

    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public PrintInOrderSemaphore() {}

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        printThird.run();
    }
}
