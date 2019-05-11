package tn.biat.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import  tn.biat.service.UploadService;

@RestController
public class UploadController {
	
	private final UploadService uploadService;
	
	public UploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	@PostMapping("/upload")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Map<String, String>> upload(@RequestParam("file") MultipartFile file, @RequestParam String label, Principal principal) throws Exception{
		return uploadService.upload(file, label, principal.getName());
	}
}
