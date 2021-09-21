package leetcodecn;

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