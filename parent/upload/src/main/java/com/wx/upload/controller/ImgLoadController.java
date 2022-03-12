package com.wx.upload.controller;



import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.ImgModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class ImgLoadController {
    // 上传文件存储目录
    @RequestMapping("upload")
    public CommonResult upload(MultipartFile file, HttpServletRequest req) throws IOException {

        String dir ="C:\\Users\\Master\\IdeaProjects\\qian-springcloud\\qian\\web\\img\\" ;
        String dir1 ="C:\\Users\\Master\\IdeaProjects\\lv_shi_qian_tai\\web\\img\\";
        String dest = "C:\\Users\\Master\\IdeaProjects\\qian-springcloud\\out\\artifacts\\qian_war_exploded\\img\\";
        String dest1 = "C:\\Users\\Master\\IdeaProjects\\lv_shi_qian_tai\\out\\artifacts\\lv_shi_qian_tai_war_exploded\\img\\";
        String filename = file.getOriginalFilename();
        String src = "/img/" + filename;
        String dirPath = dir + file.getOriginalFilename();
        String dirPath1 = dir1 + file.getOriginalFilename();
        String path = dest+filename;
        String path1 = dest1+filename;
        File dirfile = new File(dir);
       File files = new File(dest);
        File dirfile1 = new File(dir1);
        File files1 = new File(dest1);
      if (!files.exists()) {
            files.mkdirs();
        }
        inputStreamToFile(file.getInputStream(),new File(path));

        if (!files1.exists()) {
            files1.mkdirs();
        }
        inputStreamToFile(file.getInputStream(),new File(path1));

        if (!dirfile.exists()) {
            dirfile.mkdirs();
            System.out.println("========");
        }
        inputStreamToFile(file.getInputStream(),new File(dirPath));
        if (!dirfile1.exists()) {
            dirfile1.mkdirs();
            System.out.println("========");
        }
        inputStreamToFile(file.getInputStream(),new File(dirPath1));



        ImgModel img = new ImgModel();
        img.setSrc(src);

        return CommonResult.success(img);
    }

    private void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
