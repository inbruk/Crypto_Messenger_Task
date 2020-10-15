package android5.m8proj.cryptomessenger.communication;

public class CommunicationMessage {
    public static final String LOCAL_IP_ADDRESS = "127.0.0.1";
    public static final int MAX_PACKET_BUFF_SIZE = 1400; // в байтах

    private String fromUrlOrAddress;
    private int fromPort;
    private String toUrlOrAddress;
    private int toPort;
    private byte[] messageData;

    public CommunicationMessage(
        String fromUA,
        int fromP,
        String toUA,
        int toP,
        byte[] msgD
    ) {
        fromUrlOrAddress = fromUA;
        fromPort = fromP;
        toUrlOrAddress = toUA;
        toPort = toP;
        messageData = msgD;
    }

    public String getFromUrlOrAddress() {
        return fromUrlOrAddress;
    }
    public int getFromPort() {
        return fromPort;
    }
    public byte[] getMessageData() {
        return messageData;
    }
    public String getToUrlOrAddress() {
        return toUrlOrAddress;
    }
    public int getToPort() { return toPort; }
}
