package android5.m8proj.cryptomessenger.communication;

public interface IMessageSender {
    void Initialize(String toUrlOrAdd, int toPrt);
    CommunicationMessage SendMessage();
}
