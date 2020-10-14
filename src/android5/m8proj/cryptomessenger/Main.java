package android5.m8proj.cryptomessenger;

class MyThread extends Thread {
    public MyThread(String name, int ms) {
        this.setName(name);
        mls = ms;
    }

    private int count = 0;
    private int mls = 0;

    public void run() {
        while (true) {
            System.out.println( this.getName() + " : " + count );
            try {
                Thread.sleep(mls);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            count++;
            if (count>10) break;
        }
    }
}

public class Main {

    public static void main(String[] args) {

        MyThread one = new MyThread("one", 1111);
        MyThread two = new MyThread("two", 1333);
        MyThread three = new MyThread("three", 1777);

        one.start();
        two.start();
        three.start();

        int cnt = 0;
        while (true) {

            cnt++;
            if (cnt>10) break;
        }

    }
}
