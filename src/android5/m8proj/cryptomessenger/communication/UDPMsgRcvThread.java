package android5.m8proj.cryptomessenger.communication;

import java.io.*;
import java.net.*;

import lombok.Getter;
import lombok.SneakyThrows;

public class UDPMsgRcvThread extends Thread implements Closeable
{

    private int toPort;
    private DatagramSocket socket;
    private byte[] packetBuffer;

    @Getter
    private boolean isReceived;

    @Getter
    private CommunicationMessage result;

    public UDPMsgRcvThread(int toPrt) throws SocketException {
        toPort = toPrt;
        socket = new DatagramSocket();
        socket.bind( new InetSocketAddress(toPort) );
        isReceived = false;
        packetBuffer = new byte[ CommunicationMessage.MAX_PACKET_BUFF_SIZE ];
    }

    @SneakyThrows
    public void run() {
        DatagramPacket packet = new DatagramPacket(packetBuffer, packetBuffer.length);
        socket.receive(packet);

        InetAddress fromAddress = packet.getAddress();
        int fromPort = packet.getPort();
        result = new CommunicationMessage(
                fromAddress.toString(), fromPort, CommunicationMessage.LOCAL_IP_ADDRESS, toPort, packetBuffer
        );
    }

    public void close() {
        packetBuffer = null;
        socket.close();
    }

}
