package android5.m8proj.cryptomessenger.communication;

import java.io.*;
import java.net.*;

public class UDPMessageSender implements IMessageSender {

    private int toPort;
    private DatagramSocket socket;
    private byte[] packetBuffer;

    public void Initialize(String toUrlOrAdd, int toPrt) throws SocketException {

    }

    public CommunicationMessage SendMessage() throws IOException {

    }

    public void Done() throws InterruptedException {

    }

}
