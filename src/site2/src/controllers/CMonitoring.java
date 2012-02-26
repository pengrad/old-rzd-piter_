package controllers;

import managers.FileManager;
import managers.MonitoringManager;
import objects.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 21:05:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CMonitoring {
    private MonitoringManager monitoringManager;
    private FileManager fileManager;

    public void setMonitoringManager(MonitoringManager monitoringManager) {
        this.monitoringManager = monitoringManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @RequestMapping(value = "mon/direction.htm", method = RequestMethod.GET)
    public String monDirection(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("monSegment", monitoringManager.getSegmentForDirection());
        model.addAttribute("linkLevel", request.getContextPath() + "/mon/sector.htm?idDirection=");
        return "/pages/monitoring.jsp";
    }

    @RequestMapping(value = "mon/sector.htm", method = RequestMethod.GET)
    public String monSector(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "idDirection", required = true) int idDirection, Model model) {
        model.addAttribute("monSegment", monitoringManager.getSegmentForSector(idDirection));
        model.addAttribute("linkLevel", request.getContextPath() + "/mon/station.htm?idSector=");
        return "/pages/monitoring.jsp";
    }

    @RequestMapping(value = "mon/station.htm", method = RequestMethod.GET)
    public String monStation(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "idSector", required = true) int idSector, Model model) {
        model.addAttribute("monSegment", monitoringManager.getSegmentForStation(idSector));
        return "/pages/monitoring.jsp";
    }

    @RequestMapping(value = "mon/uploadFileByStation.htm", method = RequestMethod.GET)
    @ResponseBody
    public Collection<File> monStationByKod(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "dateReport", required = false) String dateReport, @RequestParam(value = "idStation", required = false) Integer idStation, Model model) throws Exception {
        return fileManager.getFiles(new SimpleDateFormat("dd.MM.yyyy").parse(dateReport), idStation);
    }

}
