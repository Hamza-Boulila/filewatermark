package com.hbl.filewatermark.services;

public interface IAESEncryptionDecryption
{
    void prepareSecreteKey(String myKey);

    byte[] encrypt(byte[] fileToEncrypt, String secret);

    byte[] decrypt(byte[] fileToDecrypt, String secret);
}
