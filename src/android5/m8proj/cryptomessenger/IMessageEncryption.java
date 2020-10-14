package android5.m8proj.cryptomessenger;

public interface IMessageEncryption {
    String encryptMessage(String message, String secretKey);
    String decryptMessage(String message, String secretKey);
}
