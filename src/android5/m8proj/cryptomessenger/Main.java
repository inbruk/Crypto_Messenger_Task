package android5.m8proj.cryptomessenger;

import android5.m8proj.cryptomessenger.cipher.*;
import android5.m8proj.cryptomessenger.communication.*;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Scanner;

public class Main {

    public static <ex> void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {

        int srvPort, toPort;
        String toAddress;

        IMessageReceiver rcv = new UDPMessageReceiver();
        IMessageSender snd = new UDPMessageSender();
        Scanner scan = new Scanner(System.in);
        Charset cset = Charset.defaultCharset();

        try {
            System.out.println("Проектное задание - крипто мсессенджер. Модули 8-11. Используются UDP сокеты.");
            System.out.println("1 сообщение на 1 пакет. Длинна сообщения до 70 символов.");
            System.out.println("Peer2Peer соединение без отдельного сервера.");
            System.out.println("Теоретически должно работать с белыми IP.");
            System.out.println("Введите @quit для выхода из чата.");
            System.out.println("Если ничего не вводить и просто нажать Enter, то ничего не отсылается,");
            System.out.println("но все накопленные принятые сообщения печатаются.");
            System.out.println("------------------------------------------------------------------------------");

            System.out.print("Введите порт своего сервера (1000-65536):");
            String srvPortStr = scan.nextLine();
            srvPort = Integer.valueOf(srvPortStr);

            System.out.print("Введите порт IP адрес собеседника (например, 127.0.0.1):");
            toAddress = scan.nextLine();

            System.out.print("Введите порт собеседника (1000-65536):");
            String toPortStr = scan.nextLine();
            toPort = Integer.valueOf(toPortStr);

            System.out.print("Введите наш пароль шифрования:");
            String encPassword = scan.nextLine();

            System.out.print("Введите пароль шифрования собеседника:");
            String decPassword = scan.nextLine();

            rcv.Initialize(srvPort);
            snd.Initialize(toAddress, toPort);

            IMessageEncryptor encryptor = new StandartEncryptor("AES", encPassword);
            IMessageDecryptor decryptor = new StandartDecryptor("AES", decPassword);

            while (true) {

                // получаем все переданные нам, и пока не полученные записи (UDP пакеты) и печатаем их
                byte[] currPacketData;
                do {
                    currPacketData = rcv.ReceiveMessage();
                    if( currPacketData!=null ) {
                        String rcvMsgStr = decryptor.decryptMessage(currPacketData);
                        System.out.println(rcvMsgStr);
                    }
                } while ( currPacketData!=null );

                // получили отсылаемую запись от пользователя
                System.out.print(">>");
                String sndMsgStr = scan.nextLine();

                // проверим на наличие команды
                if( sndMsgStr.equals("@quit")) {
                    break;
                }

                if( sndMsgStr!=null && sndMsgStr.length()>0 ) {
                    byte[] sndMsgBuffer = encryptor.encryptMessage(sndMsgStr);
                    snd.SendMessage(sndMsgBuffer);
                }
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            snd.Done();
            rcv.Done();
        }
    }
}
