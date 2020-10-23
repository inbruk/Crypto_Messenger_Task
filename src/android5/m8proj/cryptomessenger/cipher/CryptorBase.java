package android5.m8proj.cryptomessenger.cipher;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.*;

public abstract class CryptorBase {

    protected byte[] keyBytes;
    protected SecretKey secretKey;
    protected Cipher cipher;

    protected void Init(String cipherName, String password, int cipherMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        keyBytes = new byte[16];
        Arrays.fill(keyBytes, (byte)0 );

        byte[] passwordBytes = password.getBytes(); // используется кодовая страница по умолчанию для этой ОС
        for(int i=0; i<passwordBytes.length; i++)
            keyBytes[i] = passwordBytes[i];

        secretKey = new SecretKeySpec(keyBytes, cipherName);
        cipher = Cipher.getInstance( cipherName + "/ECB/PKCS5Padding" );
        cipher.init(cipherMode, secretKey);
    }

}
