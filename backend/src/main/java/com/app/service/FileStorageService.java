package com.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	
	private String fileStorageLocation;
	private Path fileStoragePath;
	
	public FileStorageService(@Value("${file.upload.location:temp}") String fileStorageLocation) {
		
		System.out.println("in constructor of "+getClass().getName());
		
		this.fileStorageLocation=fileStorageLocation ;
		fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        try {
        	
        	Files.createDirectories(fileStoragePath);
        	
        }
        catch (Exception e) {
			throw new RuntimeException("Issue in creating diectionry");
		}
	
	}

	
	public String stroreFile(MultipartFile file) {
		
	   	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	   	Path filePath = Paths.get(fileStoragePath+"\\"+fileName);
	   	try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			
			throw new RuntimeException("Issue in storing the file");
		}
	   	return fileName;
	}


	public Resource downloadFile( String fileName) {
		
		
		Resource resource;
		Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
		
		System.out.println(path);
		try {
			 resource = new UrlResource(path.toUri());
			
		} catch (MalformedURLException e) {
			
			throw new RuntimeException("Issue in reading the file", e);
		}
		if(resource.exists() && resource.isReadable())
		{
			return resource;
		}
		else {
			throw new RuntimeException("the file dose not exist or not readable");
		}
	}


	
	
	
}
