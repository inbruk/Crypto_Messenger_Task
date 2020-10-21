package android5.m8proj.cryptomessenger.communication;

import java.io.*;
import java.net.*;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class UDPMsgRcvThread extends Thread implements Closeable
{

    private int toPort;
    private DatagramSocket socket;
    private byte[] packetBuffer;

    @Getter @Setter
    private boolean isReceived = false;

    private CommunicationMessage result = null;
    public CommunicationMessage getResult() {
        synchronized ( result ) {
            if (isReceived) {
                isReceived = false;
                return result;
            } else {
                return null;
            }
        }
    }

    public UDPMsgRcvThread(int toPrt) throws SocketException {
        toPort = toPrt;
        socket = new DatagramSocket(toPort);
        packetBuffer = new byte[ CommunicationMessage.MAX_PACKET_BUFF_SIZE ];
    }

    @SneakyThrows
    public void run() {
        while(true) {
            synchronized ( result ) {
                if (isReceived == false) {
                    DatagramPacket packet = new DatagramPacket(packetBuffer, packetBuffer.length);
                    socket.receive(packet);

                    InetAddress fromAddress = packet.getAddress();
                    int fromPort = packet.getPort();
                    result = new CommunicationMessage(
                            fromAddress.toString(), fromPort, CommunicationMessage.LOCAL_IP_ADDRESS, toPort, packetBuffer
                    );
                    isReceived = true;
                }
            }
            sleep(300);
        }
    }

    public void close() {
        isReceived = false;
        result = null;
        packetBuffer = null;
        socket.close();
    }

}
