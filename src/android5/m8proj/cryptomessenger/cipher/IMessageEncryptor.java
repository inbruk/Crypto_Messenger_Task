package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface IMessageEncryptor {
    byte[] encryptMessage(String message) throws BadPaddingException, IllegalBlockSizeException;
}
