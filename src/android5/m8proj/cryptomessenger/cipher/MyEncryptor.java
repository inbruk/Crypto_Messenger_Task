package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyEncryptor extends CryptorBase implements IMessageEncryptor  {

    public MyEncryptor(String password)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException
    {
        Init("AES", password, Cipher.ENCRYPT_MODE);
    }

    public byte[] encryptMessage(String message)
            throws BadPaddingException, IllegalBlockSizeException
    {
        byte[] messageBytes = message.getBytes(); // используется кодовая страница по умолчанию для этой ОС
        byte[] tempBytes1 = cipher.doFinal(messageBytes);
        byte[] tempBytes2 = cipher.doFinal(tempBytes1);
        return cipher.doFinal(tempBytes2);
    }
}
