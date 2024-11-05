package com.ecommerce.project.springbootecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    String uploadImage(String path, MultipartFile image) throws IOException {
        String originalFilename=image.getOriginalFilename();

        //Generating random name to the image and concatinating it with the name ( extension will remain same)
        String randomName= UUID.randomUUID().toString();
        String fileName=randomName.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath=path+ File.separator+fileName;

        //check if path exisits and create one if not
        File folder=new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }

        //upload to server
        Files.copy(image.getInputStream(), Paths.get(filePath));

        return fileName;
    }
}
