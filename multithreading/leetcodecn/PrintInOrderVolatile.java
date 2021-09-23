package leetcodecn;

// LC-CN 1114
public class PrintInOrderVolatile {

    private volatile int count = 1;

    public void first(Runnable printFirst) throws InterruptedException {
        while (count != 1);
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (count != 2);
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (count != 3);
        printThird.run();
        count = 1;
    }
}
