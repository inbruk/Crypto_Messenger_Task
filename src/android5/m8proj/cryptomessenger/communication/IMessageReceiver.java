package android5.m8proj.cryptomessenger.communication;

import java.io.IOException;
import java.net.SocketException;

public interface IMessageReceiver {
    void Initialize(int toPrt) throws SocketException;
    byte[]  ReceiveMessage() throws IOException;
    void Done() throws InterruptedException;
}
