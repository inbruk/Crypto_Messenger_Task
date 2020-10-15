package android5.m8proj.cryptomessenger.communication;

import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class UDPMsgRcvThread extends Thread implements Closeable {

    private int toPort;
    private DatagramSocket socket;
    private byte[] packetBuffer;

    private boolean isReceived;
    public boolean isReceived() { return isReceived; }

    private CommunicationMessage result;
    public CommunicationMessage getResult() { return result;  }

    public UDPMsgRcvThread(int toPrt) throws SocketException {
        toPort = toPrt;
        socket = new DatagramSocket();
        socket.bind( new InetSocketAddress(toPort) );
        isReceived = false;
        packetBuffer = new byte[ CommunicationMessage.MAX_PACKET_BUFF_SIZE ];
    }

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
