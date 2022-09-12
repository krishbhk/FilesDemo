package com.example.filesDemo.service;

import com.example.filesDemo.encryption.AES;
import com.example.filesDemo.model.Doc;
import com.example.filesDemo.repository.FilesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {

    @Autowired
    private FilesRepo filesRepo;

    public Doc saveFile(MultipartFile file) throws Exception {
        String docname = file.getOriginalFilename();
        String doctype = file.getContentType();
        byte[] encData = AES.encrypt(file.getBytes());

            Doc doc = new Doc(docname,doctype,encData);
            return filesRepo.save(doc);
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

    public void deleteFile(Integer fileId){
        filesRepo.deleteById(fileId);
    }
}
