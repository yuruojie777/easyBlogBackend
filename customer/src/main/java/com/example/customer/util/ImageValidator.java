package com.example.customer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class ImageValidator {

    public static boolean IsValidAvatar(MultipartFile file) {
        String filename = file.getOriginalFilename();
        long size = file.getSize();
        log.info("{}:{}kb", filename, size/1024);

        if(filename.endsWith(".jpg") || filename.endsWith(".png")) {
            if(size <= 4 * 1024 * 1024) return true;
        }
        return false;
    }
}
