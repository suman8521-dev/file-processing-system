package com.file.service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.file.utility.CryptoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.crypto.SecretKey;

@Service
public class MovieServiceImpl implements MovieService {

    @Value("${project.poster}")
    private String path;

    private final SecretKey secretKey;
    public MovieServiceImpl(@Value("${crypto.secret.key}") String key) {
        this.secretKey = CryptoUtil.getKeyFromString(key);
    }


    @Override
	public String uploadFile(String path, MultipartFile file) throws IOException {

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filepath = path + File.separator + filename;

        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        try {
            byte[] fileBytes = file.getBytes();

            String encrypted = CryptoUtil.encrypt(fileBytes, secretKey);

            Files.write(Paths.get(filepath), encrypted.getBytes());

        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }

        return filename;
	}

	@Override
	public InputStream getResourceFile(String path, String filename) throws Exception {
        String filepath = path + File.separator + filename;

        byte[] encryptedBytes = Files.readAllBytes(Paths.get(filepath));
        String encryptedString = new String(encryptedBytes);

        byte[] decryptedBytes = CryptoUtil.decrypt(encryptedString, secretKey);

        return new ByteArrayInputStream(decryptedBytes);
	}

    @Override
    public byte[] downloadMovieFile(String filename) throws Exception {
        String filepath = path + "/" + filename;

        byte[] encryptedBytes = Files.readAllBytes(Paths.get(filepath));
        String encrypted = new String(encryptedBytes);

        return CryptoUtil.decrypt(encrypted, secretKey);
    }


}
