package leetcodecn;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String args[]) {

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });

        th.start();
    }

}