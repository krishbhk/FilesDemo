package com.example.filesDemo.service;

import com.example.filesDemo.model.Doc;
import com.example.filesDemo.repository.FilesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {

    @Autowired
    private FilesRepo filesRepo;

    public Doc saveFile(MultipartFile file) throws IOException {
        String docname = file.getOriginalFilename();
        String doctype = file.getContentType();
        byte[] docdata = file.getBytes();
        System.out.println("---------------"+docname+"======"+doctype+"->>>>>>>>>>>>>"+docdata);

            Doc doc = new Doc(docname,doctype,docdata);
            return filesRepo.save(doc);
//            return savedDoc;
    }

    public Optional<Doc> getFile(Integer fileId) {
        return filesRepo.findById(fileId);
    }

    public Optional<Doc> getFile(String fileName) {
        return filesRepo.findByDocName(fileName);
    }

    public List<Doc> getFiles(){
        return filesRepo.findAll();
    }
}
