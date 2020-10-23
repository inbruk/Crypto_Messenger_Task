package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class StandartDecryptor extends CryptorBase implements IMessageDecryptor {

    public StandartDecryptor(String cipherName, String password)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException
    {
        Init(cipherName, password, Cipher.DECRYPT_MODE);
    }

    public String decryptMessage(byte[] messageBytes)
        throws BadPaddingException, IllegalBlockSizeException
    {
        byte[] decBytes = cipher.doFinal(messageBytes);
        return new String(decBytes); // используется кодовая страница по умолчанию для этой ОС
    }
}
