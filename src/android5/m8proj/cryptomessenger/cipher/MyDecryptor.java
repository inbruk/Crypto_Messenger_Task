package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyDecryptor  extends CryptorBase implements IMessageDecryptor {

    public MyDecryptor(String password)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException
    {
        Init("AES", password, Cipher.DECRYPT_MODE);
    }

    public String decryptMessage(byte[] messageBytes)
            throws BadPaddingException, IllegalBlockSizeException
    {
        byte[] tempBytes1 = cipher.doFinal(messageBytes);
        byte[] tempBytes2 = cipher.doFinal(tempBytes1);
        byte[] decBytes = cipher.doFinal(tempBytes2);
        return new String(decBytes); // используется кодовая страница по умолчанию для этой ОС
    }
}
