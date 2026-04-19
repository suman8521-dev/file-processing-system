package com.file;
import com.file.utility.CryptoUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;

@SpringBootApplication
public class SbtFileHeandlingProjetct1Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SbtFileHeandlingProjetct1Application.class, args);

	}

}
