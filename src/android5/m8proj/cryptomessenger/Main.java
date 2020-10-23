package android5.m8proj.cryptomessenger;

import android5.m8proj.cryptomessenger.cipher.*;
import android5.m8proj.cryptomessenger.communication.*;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {

        int srvPort, toPort;
        String toAddress;

        IMessageReceiver rcv = new UDPMessageReceiver();
        IMessageSender snd = new UDPMessageSender();
        Scanner scan = new Scanner(System.in);
        Charset cset = Charset.defaultCharset();

        try {
            System.out.println("Проектное задание - крипто мсессенджер. Модули 8-11 .");
            System.out.println("Используются UDP сокеты. 1 сообщение на 1 пакет.");
            System.out.println("Длинна сообщения до 70 символов (нет проверки и разбивки более длинного).");
            System.out.println("Peer2Peer соединение без отдельного сервера.");
            System.out.println("Теоретически должно работать с белыми IP в интернете.");
            System.out.println("Введите @quit для выхода из чата.");
            System.out.println("Если ничего не вводить и просто нажать Enter, то ничего не отсылается,");
            System.out.println(" но все накопленные принятые сообщения печатаются.");
            System.out.println("Для правильной работы сервера UDP пришлось применить знания о многозадачности,");
            System.out.println(" которые мы не изучали в модулях, к которым относится проектная работа.");
            System.out.println("В программе нет валидации ввода, обработки ошибок сети, обработки ошибок шифрования, логирования,");
            System.out.println(" работы с разными кодировками (кроме кодировки по умолчанию), нет нотификации доставки сообщений,");
            System.out.println(" также работа с консолью самая простецкая (так как другой не учили).");
            System.out.println("Для проверки запускать внутри IDEA. Все уже в проекте настроено для запуска двух экземпляров.");
            System.out.println("Первый экземпляр запускать Shift+F10, а второй как на отладку Shift+F9.");
            System.out.println();
            System.out.println("Параметры 1-го приложения:");
            System.out.println("Введите порт своего сервера (1000-65535):4000");
            System.out.println("Введите порт IP адрес собеседника (например, 127.0.0.1):127.0.0.1");
            System.out.println("Введите порт собеседника (1000-65535):5000");
            System.out.println("Введите наш пароль шифрования:12345");
            System.out.println("Введите пароль шифрования собеседника:54321");
            System.out.println();
            System.out.println("Параметры 2-го приложения:");
            System.out.println("Введите порт своего сервера (1000-65535):5000");
            System.out.println("Введите порт IP адрес собеседника (например, 127.0.0.1):127.0.0.1");
            System.out.println("Введите порт собеседника (1000-65535):4000");
            System.out.println("Введите наш пароль шифрования:54321");
            System.out.println("Введите пароль шифрования собеседника:12345");
            System.out.println();
            System.out.println("Внимание ! Если программа завершилась ошибками, или вы ее остановили, то порты остаются захваченными,");
            System.out.println(" и в следующий раз надо указывать другие порты.");
            System.out.println("------------------------------------------------------------------------------");

            System.out.print("Введите порт своего сервера (1000-65535):");
            String srvPortStr = scan.nextLine();
            srvPort = Integer.valueOf(srvPortStr);

            System.out.print("Введите порт IP адрес собеседника (например, 127.0.0.1):");
            toAddress = scan.nextLine();

            System.out.print("Введите порт собеседника (1000-65535):");
            String toPortStr = scan.nextLine();
            toPort = Integer.valueOf(toPortStr);

            System.out.print("Введите наш пароль шифрования:");
            String encPassword = scan.nextLine();

            System.out.print("Введите пароль шифрования собеседника:");
            String decPassword = scan.nextLine();

            rcv.Initialize(srvPort);
            snd.Initialize(toAddress, toPort);

            IMessageEncryptor encryptor = new MyEncryptor(encPassword);
            IMessageDecryptor decryptor = new MyDecryptor(decPassword);

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

        System.out.print("Завершение работы...");

    }
}
