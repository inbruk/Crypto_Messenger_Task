package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class StandartEncryptor implements IMessageCypher {

    private Cipher cryptor;
    private Cipher decryptor;

    public StandartEncryptor(String cipherName, String encPpassword,  String decPpassword)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        byte[] keyBytes = encPpassword.getBytes(); // используется кодовая страница по умолчанию для этой ОС
        SecretKey secretKey = new SecretKeySpec(keyBytes, cipherName);
        cryptor = Cipher.getInstance( cipherName + "/ECB/PKCS5Padding" );
        cryptor.init(Cipher.ENCRYPT_MODE, secretKey);
    }

    public byte[] encryptMessage(String message)
            throws BadPaddingException, IllegalBlockSizeException {

        byte[] messageBytes = message.getBytes(); // используется кодовая страница по умолчанию для этой ОС
        return cipher.doFinal(messageBytes);
    }

    public String decryptMessage(byte[] messageBytes)
            throws BadPaddingException, IllegalBlockSizeException {

        byte[] messageBytes = cipher.doFinal(messageBytes);
        new String(currPacketData); // используется кодовая страница по умолчанию для этой ОС
    }

}
