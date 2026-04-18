package com.file;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.*;

@SpringBootApplication
public class SbtFileHeandlingProjetct1Application {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(SbtFileHeandlingProjetct1Application.class, args);

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair pair = keyGen.generateKeyPair();

        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        System.out.println("publicssssssssssssssssssssssssssssss"+publicKey.toString());
        System.out.println("private"+privateKey.toString());
	}

}
