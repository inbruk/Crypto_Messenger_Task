package android5.m8proj.cryptomessenger.communication;

import java.net.*;

public class UDPMessageReceiver implements IMessageReceiver {

    private UDPMsgRcvThread thread;

    public void Initialize(int toPrt) throws SocketException {
        thread = new UDPMsgRcvThread(toPrt);
        thread.start();
    }

    public byte[] ReceiveMessage() {
        return thread.getOnePacketData();
    }

    public void Done() throws InterruptedException {
        thread.join();
        thread.close();
    }

}
