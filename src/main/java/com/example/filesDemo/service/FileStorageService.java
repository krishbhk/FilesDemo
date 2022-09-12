package com.example.filesDemo.service;

import com.example.filesDemo.model.Doc;
import com.example.filesDemo.repository.FilesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {

    @Autowired
    private FilesRepo filesRepo;

    private static final String ALGO = "AES";
    private static final byte[] keyValue = "Ad0#2s!3oGyRq!5F".getBytes();

    public static byte[] encrypt(byte[] Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data);
        //String encryptedValue = new BASE64Encoder().encode(encVal);
        return encVal;
    }

    private static Key generateKey() throws Exception {

        Key key = null;
        key = new SecretKeySpec(keyValue, ALGO);
        System.out.println("KEY:=============>"+key);
        return key;
    }

    public Doc saveFile(MultipartFile file) throws Exception {
        String docname = file.getOriginalFilename();
        String doctype = file.getContentType();
        byte[] docdata = file.getBytes();
        byte[] encData = encrypt(docdata);




        System.out.println("---------------"+docname+"======"+doctype+"->>>>>>>>>>>>>"+docdata+"<><><><><><><><>"+encData);

            Doc doc = new Doc(docname,doctype,encData);
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
