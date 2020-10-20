package android5.m8proj.cryptomessenger.communication;

import java.net.*;

public class UDPMessageReceiver implements IMessageReceiver {

    private UDPMsgRcvThread thread;

    public void Initialize(int toPrt) throws SocketException {
        thread = new UDPMsgRcvThread(toPrt);
    }

    public CommunicationMessage ReceiveMessage() {

        if ( thread.isAlive()==false ) {
            thread.start();
        }

        if( thread.isReceived() ) {
            CommunicationMessage result = thread.getResult();
            return result;
        } else {
            return null;
        }

    }

    public void Done() throws InterruptedException {
        thread.join();
        thread.close();
    }

}
