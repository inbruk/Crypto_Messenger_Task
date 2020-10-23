package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface IMessageDecryptor {
    String decryptMessage(byte[] messageBytes) throws BadPaddingException, IllegalBlockSizeException;
}
