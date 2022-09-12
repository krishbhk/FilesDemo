package com.example.filesDemo.controller;

import com.example.filesDemo.encryption.AES;
import com.example.filesDemo.model.Doc;
import com.example.filesDemo.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.Key;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/")
    public String get(Model model) {
        List<Doc> docs = fileStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/uploadFiles")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws Exception {
        for (MultipartFile file: files) {
            fileStorageService.saveFile(file);
        }
        return "redirect:/";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) throws Exception {
        Doc doc = fileStorageService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(AES.decrypt(doc.getEncData())));
    }
}
