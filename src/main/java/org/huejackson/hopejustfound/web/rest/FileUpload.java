package org.huejackson.hopejustfound.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.huejackson.hopejustfound.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api")
public class FileUpload {

    private final MailService mailService;

    public FileUpload( MailService mailService) {
        this.mailService = mailService;
    }

@PostMapping ("/post")
@Timed
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            //*Email file to admin    add  method here*//*
        mailService.sendMessageWithAttachment(file, true, true);
            message = "You successfully uploaded " + file.getName() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
