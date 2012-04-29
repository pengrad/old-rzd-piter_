package controllers;

import managers.FileManager;
import objects.FileUploadMessage;
import objects.File;
import objects.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import parser.ParserKFileXML;
import utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
    private ParserKFileXML parserKFileXML;
    private FileManager fileManager;

    public void setParserKFileXML(ParserKFileXML parserKFileXML) {
        this.parserKFileXML = parserKFileXML;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @RequestMapping(value = "upload/uploadFile.htm", method = RequestMethod.GET)
    public String openUploadKFilePage(HttpServletRequest request, HttpServletResponse response) {
        return "/pages/uploadFile.jsp";
    }

    @RequestMapping(value = "upload/upload.htm", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadMessage setFileUpload(
            @RequestParam(value = "file", required = false) MultipartFile kfile,
            @RequestParam(value = "typeTimeCalcReport", required = true) Helper.typeTimeCalcReport typeTimeCalcReport,
            @RequestParam(value = "timeCalcReport", required = false) String timeCalcReport) {
        MultipartFile multipartFile = kfile;
        String fileName = "";
        try {
            fileName = multipartFile.getOriginalFilename();
            System.out.println(fileName);
            InputStream is = null;

            try {
                is = multipartFile.getInputStream();
                File fileLoad = null;
                if (fileName.substring(fileName.length() - 3, fileName.length()).equalsIgnoreCase("XML")) {
                    fileLoad = new ParserKFileXML().parse(is);
                } else {

                }
                if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_FILE)) { //отчетная дата из KFile
                    for (Ticket ticket : fileLoad.getTickets()) {
                        ticket.setTimeCalcReport(ticket.getT());
                    }
                }
                if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_UPLOAD)) {  //отчетная дата - дата загрузки
                    for (Ticket ticket : fileLoad.getTickets()) {
                        ticket.setTimeCalcReport(new Date());
                    }
                }
                if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_INPUT)) { //произвольная отчетная дата
                    Date d = parserKFileXML.parseDate(timeCalcReport);
                    for (Ticket ticket : fileLoad.getTickets()) {
                        ticket.setTimeCalcReport(d);
                    }
                }

                fileManager.addFile(fileLoad);
                return new FileUploadMessage(0, "ок", "ок");
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
            return new FileUploadMessage(100, "Ошибка формата файла", e.getMessage());
        }
    }


    @RequestMapping(value = "upload/uploadAll.htm", method = RequestMethod.GET)
    @ResponseBody
    public String allUpload
            (HttpServletRequest
                    request, HttpServletResponse
                    response) {
        try {
            java.io.File ff = new java.io.File("H:\\проект РЖД\\2010-11\\2010-11");
            java.io.File[] kk = ff.listFiles();
            for (int i = 0; i < kk.length; i++) {
                InputStream is = new FileInputStream(kk[i]);
                File fileLoad = parserKFileXML.parse(is);
                for (Ticket ticket : fileLoad.getTickets()) {
                    ticket.setTimeCalcReport(ticket.getT());
                }
                System.out.println("add");
                fileManager.addFile(fileLoad);
                is.close();
            }
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR";
        }
    }
}
