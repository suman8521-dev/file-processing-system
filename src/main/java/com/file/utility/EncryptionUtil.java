package com.file.utility;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class EncryptionUtil {


    public static void encryptFile(String inputFile, String outputFile, PublicKey publicKey) throws Exception {

        // Step 1: Generate AES Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();

        // Step 2: AES Encryption (GCM mode)
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] iv = aesCipher.getIV();

        FileInputStream fis = new FileInputStream(inputFile);
        byte[] fileData = fis.readAllBytes();
        fis.close();

        byte[] encryptedData = aesCipher.doFinal(fileData);

        // Step 3: Encrypt AES key using RSA
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKey = rsaCipher.doFinal(aesKey.getEncoded());

        // Step 4: Write to output file
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(encryptedKey);
        fos.write(iv);
        fos.write(encryptedData);
        fos.close();
    }

    public static void decryptFile(String inputFile, String outputFile, PrivateKey privateKey) throws Exception {

        FileInputStream fis = new FileInputStream(inputFile);

        byte[] encryptedKey = fis.readNBytes(256); // RSA 2048
        byte[] iv = fis.readNBytes(12); // GCM IV
        byte[] encryptedData = fis.readAllBytes();

        fis.close();

        // Step 1: Decrypt AES key
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedKey);

        SecretKey aesKey = new javax.crypto.spec.SecretKeySpec(aesKeyBytes, "AES");

        // Step 2: AES Decryption
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, spec);

        byte[] decryptedData = aesCipher.doFinal(encryptedData);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(decryptedData);
        fos.close();
    }

}
