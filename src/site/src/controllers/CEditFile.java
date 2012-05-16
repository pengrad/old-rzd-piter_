package controllers;

import managers.FileManager;
import objects.ErrorAJAX;
import objects.File;
import objects.Ticket;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.core.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import utils.Helper;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.02.2012
 * Time: 1:21:43
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class CEditFile {
    private FileManager fileManager;
    private MessageSource messageSource;


    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @InitBinder
    protected void initBinder(ServletRequest servletRequest, ServletRequestDataBinder servletRequestDataBinder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        dateFormat.setLenient(false);
        servletRequestDataBinder.registerCustomEditor(Date.class, "timeOpen", new CustomDateEditor(dateFormat, true));
        servletRequestDataBinder.registerCustomEditor(Date.class, "timeClose", new CustomDateEditor(dateFormat, true));
        servletRequestDataBinder.registerCustomEditor(Date.class, "timeCreate", new CustomDateEditor(dateFormat, true));
        servletRequestDataBinder.registerCustomEditor(Date.class, "timeCalcReport", new CustomDateEditor(dateFormat, true));
        servletRequestDataBinder.registerCustomEditor(Date.class, "t", new CustomDateEditor(dateFormat, true));
        servletRequestDataBinder.registerCustomEditor(Date.class, "p", new CustomDateEditor(dateFormat, true));

    }


    @RequestMapping(value = "edit/searchForEditFile.htm", method = RequestMethod.GET)
    public String openEditKFilePage(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "typeTimeCalcReport", required = false) Helper.typeTimeCalcReport typeTimeCalcReport,
                                    @RequestParam(value = "dateReport", required = false) String dateReport,
                                    @RequestParam(value = "station", required = false) Integer station,
                                    @RequestParam(value = "typeTerm", required = false) Helper.typeTerm typeTerm,
                                    Model model) throws Exception {
        if (typeTimeCalcReport == null) {
            typeTimeCalcReport = Helper.typeTimeCalcReport.DATE_UPLOAD;
        }
        if (dateReport == null) {
            dateReport = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        }
        if (station == null) {
            station = 2004003;
        }
        if (typeTerm == null) {
            typeTerm = Helper.typeTerm.ALL;
        }
        model.addAttribute("typeTimeCalcReport", typeTimeCalcReport);
        model.addAttribute("dateReport", dateReport);
        model.addAttribute("station", station);
        model.addAttribute("typeTerm", typeTerm);
        model.addAttribute("files", fileManager.getFiles(new SimpleDateFormat("dd.MM.yyyy").parse(dateReport), station, typeTimeCalcReport, typeTerm));
        return "/pages/editFile.jsp";
    }

    @RequestMapping(value = "edit/viewFile.htm", method = RequestMethod.GET)
    public String viweFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileId", required = true) Integer fileId, Model model) {
        model.addAttribute("file", fileManager.getFileById(fileId));
        model.addAttribute("readonly", true);
        return "/pages/file.jsp";
    }

    @RequestMapping(value = "edit/editFile.htm", method = RequestMethod.GET)
    public String editFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileId", required = true) Integer fileId, Model model) {
        model.addAttribute("file", fileManager.getFileById(fileId));
        model.addAttribute("readonly", false);
        return "/pages/file.jsp";
    }

    @RequestMapping(value = "edit/deleteFile.htm", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileId", required = true) Integer fileId, Model model) {
        try {
            fileManager.deleteFile(fileId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "edit/deleteTicket.htm", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteTicket(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "ticketId", required = true) Integer ticketId, Model model) {
        try {
            fileManager.deleteTicket(ticketId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(value = "edit/updateFile.htm", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ErrorAJAX> processFileForm(@Valid File file, BindingResult result, HttpServletRequest request) {
        ArrayList<ErrorAJAX> listError = new ArrayList<ErrorAJAX>();
        if (result.hasErrors()) {
            Collection<FieldError> tt = result.getFieldErrors();
            for (FieldError fieldError : tt) {
                if (fieldError.getCode().indexOf("typeMismatch") == -1) {
                    listError.add(new ErrorAJAX(fieldError.getField(), fieldError.getDefaultMessage()));
                } else {
                    listError.add(new ErrorAJAX(fieldError.getField(), "Значение не соответствует формату"));
                }
            }
            return listError;
        } else {
            return listError;
        }
    }

    @RequestMapping(value = "edit/updateTicket.htm", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ErrorAJAX> processTicketForm(@Valid Ticket ticket, BindingResult result, HttpServletRequest request) {
        ArrayList<ErrorAJAX> listError = new ArrayList<ErrorAJAX>();
        if (result.hasErrors()) {
            Collection<FieldError> tt = result.getFieldErrors();
            for (FieldError fieldError : tt) {
                if (fieldError.getCode().indexOf("typeMismatch") == -1) {
                    listError.add(new ErrorAJAX(fieldError.getField(), fieldError.getDefaultMessage()));
                } else {
                    listError.add(new ErrorAJAX(fieldError.getField(), "Значение не соответствует формату"));
                }
            }
            return listError;
        } else {
            return listError;
        }
    }


    @RequestMapping(value = "edit/viewTicket.htm", method = RequestMethod.GET)
    public String viweTicket(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "ticketId", required = true) Integer ticketId, Model model) {
        model.addAttribute("ticket", fileManager.getTicketById(ticketId));
        model.addAttribute("readonly", true);
        return "/pages/ticket.jsp";
    }

    @RequestMapping(value = "edit/editTicket.htm", method = RequestMethod.GET)
    public String editTicket(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "ticketId", required = true) Integer ticketId, Model model) {
        model.addAttribute("ticket", fileManager.getTicketById(ticketId));
        model.addAttribute("readonly", false);
        return "/pages/ticket.jsp";
    }


//    @RequestMapping(value = "edit/editFile.htm", method = RequestMethod.GET)
//
//    public String openEditKFilePage(HttpServletRequest request, HttpServletResponse response) {
//        return "/pages/editFile.jsp";
//    }


}