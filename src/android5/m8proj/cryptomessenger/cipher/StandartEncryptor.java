package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class StandartEncryptor extends CryptorBase implements IMessageEncryptor {

    public StandartEncryptor(String cipherName, String password)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException
    {
        Init(cipherName, password, Cipher.ENCRYPT_MODE);
    }

    public byte[] encryptMessage(String message)
            throws BadPaddingException, IllegalBlockSizeException
    {
        byte[] messageBytes = message.getBytes(); // используется кодовая страница по умолчанию для этой ОС
        return cipher.doFinal(messageBytes);
    }
}
