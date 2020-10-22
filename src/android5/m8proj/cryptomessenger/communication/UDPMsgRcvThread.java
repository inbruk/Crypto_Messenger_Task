package android5.m8proj.cryptomessenger.communication;

import java.io.*;
import java.net.*;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class UDPMsgRcvThread extends Thread implements Closeable
{
    public final static int MAX_PACKET_BUFFER_SIZE = 32000;
    private byte[] tempBuffer = new byte[MAX_PACKET_BUFFER_SIZE];

    private int toPort;
    private DatagramSocket socket;

    private Queue<byte[]> receivedPacketsData = new LinkedList<>();
    public  byte[] getOnePacketData() {
        byte[] result;
        synchronized (receivedPacketsData) {
            result = receivedPacketsData.poll();
        }
        return result;
    }

    public UDPMsgRcvThread(int toPrt) throws SocketException {
        toPort = toPrt;
        socket = new DatagramSocket(toPort);
    }

    @SneakyThrows
    public void run() {
        while(true) {
            DatagramPacket packet = new DatagramPacket(tempBuffer, tempBuffer.length);
            socket.receive(packet);

            // InetAddress fromAddress = packet.getAddress();
            // int fromPort = packet.getPort();

            int byteLength = packet.getLength();
            byte[] currentPacketBuffer = Arrays.copyOfRange(tempBuffer, 0, byteLength);

            synchronized (receivedPacketsData) {
                receivedPacketsData.offer(currentPacketBuffer);
            }
        }
    }

    public void close() {
        socket.close();
    }

}
