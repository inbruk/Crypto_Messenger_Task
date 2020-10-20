package android5.m8proj.cryptomessenger.communication;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public interface IMessageSender {
    void Initialize(String toUrlOrAdd, int toPrt) throws SocketException, UnknownHostException;
    void SendMessage(byte[] packetBuffer) throws IOException;
    void Done() throws InterruptedException;
}
