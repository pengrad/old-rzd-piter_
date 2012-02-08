package controllers;

import managers.UploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.02.2012
 * Time: 1:21:43
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class CUploadFile {
    private UploadManager uploadManager;

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

    @RequestMapping(value = "fileupload.htm", method = RequestMethod.POST)
    @ResponseBody
    public String setFileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "kFile", required = false) MultipartFile kfile) {
        MultipartFile multipartFile = kfile;
        String fileName = "";
        try {
            fileName = multipartFile.getOriginalFilename();
            System.out.println(fileName);
            InputStream is = null;
            try {
                is = multipartFile.getInputStream();
                uploadManager.upload(is, new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").parse("06.02.2012 00:45:33"));
                return "SUCCESS";
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
             return "ERROR";
        }

//        try {
//            response.(1111,"Ошибка загрузки файла");
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        return message;
    }

//    @RequestMapping(value = "test.htm", method = RequestMethod.GET)
//    public String setFileUpload(HttpServletRequest request) {
//        System.out.println("*******************");
//
//        return "1";
//
//    }

}
