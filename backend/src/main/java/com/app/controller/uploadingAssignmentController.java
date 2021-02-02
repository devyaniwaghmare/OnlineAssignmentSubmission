package com.app.controller;


import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.dto.FileUploadResponse;
import com.app.pojos.Assignment;
import com.app.pojos.FacultyCourseSubjectMapping;
import com.app.repository.AssignmentRepo;
import com.app.repository.FacultyCouresSubjectMappingRepo;
import com.app.service.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.el.stream.Optional;

@RestController
@CrossOrigin("*")
public class uploadingAssignmentController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private AssignmentRepo assignmentRepo;
	
	@Autowired
	private FacultyCouresSubjectMappingRepo mappingRepo;
	
	@PostMapping("single/upload") 
    FileUploadResponse uploadAssignment(@RequestParam String assignment,@RequestParam("file") MultipartFile file)
	{
		
		FileUploadResponse response = null;
		
		try {
			
			
			Assignment u = new ObjectMapper().readValue(assignment, Assignment.class);
			
		    java.util.Optional<FacultyCourseSubjectMapping> f=  mappingRepo.findById(u.getMap().getMid());
		    f.get().setStatus(true);
			String fileName = fileStorageService.stroreFile(file);
			System.out.println(fileName);
			//http://localhost:8080/download/fileNamw.extension
			String url = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download/")
					.path(fileName)
					.toUriString();
			String contentType = file.getContentType();
		    response = new FileUploadResponse(fileName,contentType,url);
		    u.setAssignmentDocument(response.getUrl().toString());
		    
		    assignmentRepo.save(u);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return response;
		
		
	}
	
	@GetMapping("/download/{fileName}")
	ResponseEntity<Resource> downloadSavingFile(@PathVariable String fileName,HttpServletRequest request)
	{
		
		Resource resource = fileStorageService.downloadFile(fileName);
		
		MediaType contentType = MediaType.APPLICATION_PDF;
		String mimeType = "";
		try {
			
			 mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			 
		} catch (IOException e) {
			
			mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			
			e.printStackTrace();
		}
		return ResponseEntity.ok()
			   .contentType(MediaType.parseMediaType(mimeType))
			   //.header(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName"+resource.getFilename())
			   .header(HttpHeaders.CONTENT_DISPOSITION,"inline;attachment;fileName"+resource.getFilename())
			   .body(resource);
	}
}
