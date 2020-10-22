package android5.m8proj.cryptomessenger;

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

            System.out.print("Введите порт своего сервера:");
            String srvPortStr = scan.nextLine();
            srvPort = Integer.valueOf(srvPortStr);

            System.out.print("Введите порт IP адрес собеседника:");
            toAddress = scan.nextLine();

            System.out.print("Введите порт собеседника:");
            String toPortStr = scan.nextLine();
            toPort = Integer.valueOf(toPortStr);

            rcv.Initialize(srvPort);
            snd.Initialize(toAddress, toPort);

            while (true) {

                // получаем все переданные нам, и пока не полученные записи (UDP пакеты) и печатаем их
                byte[] currPacketData;
                do {
                    currPacketData = rcv.ReceiveMessage();
                    if( currPacketData!=null ) {
                        // преобразование массив байтов в строку
                        // Внимание ! используется кодовая страница по умолчанию для этой ОС !
                        String rcvMsgStr = new String(currPacketData);

                        // выведем полученное сообщение
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

                    // сконвертим строку в байты
                    // Внимание ! используется кодовая страница по умолчанию для этой ОС !
                    byte[] sndMsgBuffer = sndMsgStr.getBytes();

                    // вышлем сообщение
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
