package android5.m8proj.cryptomessenger;

import java.util.*;

class MyThread extends Thread {
    private int count = 0;
    private int mls = 0;
    private List<String> messageBufferList;

    public MyThread(String name, int ms, List<String> msgBuff) {
        this.setName(name);
        mls = ms;
        messageBufferList = msgBuff;
    }

    public void run() {
        while (true) {

            messageBufferList.add( this.getName() + " : " + count );

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

        List<String>  receivedMessages = new LinkedList<>();

        MyThread one = new MyThread("one", 1111, receivedMessages);
        MyThread two = new MyThread("two", 1333, receivedMessages);
        MyThread three = new MyThread("three", 1777, receivedMessages);

        one.start();
        two.start();
        three.start();

        Scanner scan = new Scanner(System.in);
        int cnt = 0;
        while (true) {
            System.out.println( ">>" );
            String inpStr =  scan.nextLine();

            ListIterator<String> itr = receivedMessages.listIterator();
            while ( itr.hasNext()) {
                String str = itr.next();
                System.out.println();
            }

            cnt++;
            if (cnt>10) break;
        }

    }
}
