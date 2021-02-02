package com.app.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.dto.FileUploadResponse;
import com.app.repository.AssignmentRepo;
import com.app.repository.FacultyCouresSubjectMappingRepo;
import com.app.repository.SubmittedAssignmentRepo;
import com.app.service.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
@RequestMapping("/submit")
public class SubmittedAssignmentController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private AssignmentRepo assignmentRepo;
	
	@Autowired
	private FacultyCouresSubjectMappingRepo mappingRepo;
	
	@Autowired
	private SubmittedAssignmentRepo submittedAssignmentRepo;
	
	@PostMapping("/upload") 
    FileUploadResponse uploadAssignment(@RequestParam String assignment,@RequestParam("file") MultipartFile file)
	{
		
		FileUploadResponse response = null;
		com.app.pojos.SubmittedAssignment u;
		
		try {
			
			 u = new ObjectMapper().readValue(assignment,com.app.pojos.SubmittedAssignment.class);
			 System.out.println(u);
			 u.setStatus(true);
		    //java.util.Optional<FacultyCourseSubjectMapping> f=  mappingRepo.findById(u.getMap().getMid());
		   // f.get().setStatus(true);
			String fileName = fileStorageService.stroreFile(file);
			System.out.println(fileName);
			//http://localhost:8080/download/fileNamw.extension
			String url = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download/")
					.path(fileName)
					.toUriString();
			String contentType = file.getContentType();
		    response = new FileUploadResponse(fileName,contentType,url);
		    u.setSolvedAssignmentDoct(response.getUrl().toString());
		   
		    
		    submittedAssignmentRepo.save(u);
			
			
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
