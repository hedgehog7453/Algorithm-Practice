package leetcodecn;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

// LC-CN 1116
class ZeroEvenOddSemaphore {

    private int n;

    // 默认 zero 有一个信号量可用
    private Semaphore zero = new Semaphore(1);

    // 默认 even 和 odd 没有可用信号量
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);

    public ZeroEvenOddSemaphore(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++){
            // 首次执行时， zero 有一个可用的信号量
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 1) {
                odd.release(); // 为 odd 增加一个信号量
            }else{
                even.release(); // 为 even 增加一个信号量
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2){
            even.acquire(); // 等待信号量，获取到了信号后，往下走
            printNumber.accept(i);
            zero.release(); // 发送信号量给 zero
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2){
            odd.acquire(); // 等待信号量，获取到了信号后，往下走
            printNumber.accept(i);
            zero.release(); // 发送信号量给 zero
        }
    }

}
