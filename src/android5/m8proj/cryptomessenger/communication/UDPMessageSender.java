package android5.m8proj.cryptomessenger.communication;

import java.io.*;
import java.net.*;

public class UDPMessageSender implements IMessageSender {

    private int toPort;
    private InetAddress address;
    private DatagramSocket socket;

    public void Initialize(String toUrlOrAdd, int toPrt) throws SocketException, UnknownHostException {
        address = InetAddress.getByName(toUrlOrAdd);
        toPort = toPrt;
        socket = new DatagramSocket();
    }

    public void SendMessage(byte[] packetBuffer) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetBuffer, packetBuffer.length, address, toPort);
        socket.send(packet);
    }

    public void Done() throws InterruptedException {
        socket.close();
    }

}
