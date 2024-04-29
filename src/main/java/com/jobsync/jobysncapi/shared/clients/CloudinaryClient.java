package com.jobsync.jobysncapi.shared.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "cloudinary-api", url = "http://localhost:8080")
public interface CloudinaryClient {
    @PostMapping(value = "cloudinary/api/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart("file") MultipartFile file);
}