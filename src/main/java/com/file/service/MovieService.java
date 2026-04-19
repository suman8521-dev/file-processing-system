package com.file.service;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface MovieService {
	 String uploadFile(String path,MultipartFile file) throws IOException;
	 InputStream getResourceFile(String path,String filename) throws Exception;
     byte[] downloadMovieFile(String filename) throws Exception;
}
