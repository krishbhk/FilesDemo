package com.example.filesDemo.service;

import com.example.filesDemo.encryption.AES;
import com.example.filesDemo.encryption.KeyEncrypt;
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

    private final String key = "Ad0#2s!3oGyRq!5F";

    public Doc saveFile(MultipartFile file) throws Exception {



        String docname = file.getOriginalFilename();
        String doctype = file.getContentType();

        byte[] encData = AES.encrypt(key, file.getBytes());
        String encKey = KeyEncrypt.encrypt(key,"MASTER_KEY");

        Doc doc = new Doc(docname,doctype,encKey, encData);
        return filesRepo.save(doc);
    }

    public Optional<Doc> getFile(Integer fileId) {

//        System.out.println();

        return filesRepo.findById(fileId);
    }

    public String getKey(Integer fileId){
          Optional<Doc> reqDoclist = filesRepo.findById(fileId);
          return reqDoclist.get().getDocKey();
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
