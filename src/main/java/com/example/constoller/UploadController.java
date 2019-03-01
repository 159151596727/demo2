package com.example.constoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 李印锋 on 2019/3/1 16:43
 */
@RestController
public class UploadController {
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                System.out.println("上传失败，请选择文件");
            }
            String fileName = file.getOriginalFilename();
            String filePath = "C:\\Users\\Administrator\\Desktop\\uploadMap\\";
            File dest = new File(filePath + fileName);
            file.transferTo(dest);
            System.out.println("上传成功");
        } catch (Exception e) {
            System.out.println("上传失败");
        }
    }
}
