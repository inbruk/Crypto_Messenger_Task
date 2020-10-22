package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface IMessageCypher {
    byte[] encryptMessage(String message) throws BadPaddingException, IllegalBlockSizeException;
    String decryptMessage(byte[] messageBytes);
}
