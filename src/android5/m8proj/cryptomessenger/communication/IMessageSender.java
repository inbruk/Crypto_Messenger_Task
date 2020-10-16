package android5.m8proj.cryptomessenger.communication;

import java.io.IOException;
import java.net.SocketException;

public interface IMessageSender {
    void Initialize(String toUrlOrAdd, int toPrt) throws SocketException;
    CommunicationMessage SendMessage() throws IOException;
    void Done() throws InterruptedException;
}
