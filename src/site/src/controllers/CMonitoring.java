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
import utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String monDirection(@RequestParam(value = "date", required = false) String date,
                               Model model
    ) throws Exception {
        Date d = Helper.getCurrentDate();
        if (date != null) {
            d = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        }
        model.addAttribute("date", d);
        model.addAttribute("typeSegment", Helper.segment.direction);
        model.addAttribute("typeSegmentForLink", Helper.segment.sector);
        model.addAttribute("monSegment", monitoringManager.getUploadForDirection(d));
        return "/pages/monitoring.jsp";
    }

    @RequestMapping(value = "mon/sector.htm", method = RequestMethod.GET)
    public String monSector(@RequestParam(value = "date", required = false) String date,
                            @RequestParam(value = "idSegment", required = true) int idDirection,
                            Model model) throws Exception {
        Date d = Helper.getCurrentDate();
        if (date != null) {
            d = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        }
        model.addAttribute("date", d);
        model.addAttribute("idSegment", idDirection);
        model.addAttribute("typeSegment", Helper.segment.sector);
        model.addAttribute("typeSegmentForLink", Helper.segment.station);
        model.addAttribute("monSegment", monitoringManager.getUploadForSector(idDirection, d));
        return "/pages/monitoring.jsp";
    }

    @RequestMapping(value = "mon/station.htm", method = RequestMethod.GET)
    public String monStation(@RequestParam(value = "date", required = false) String date,
                             @RequestParam(value = "idSegment", required = true) int idSector,
                             Model model) throws Exception {
        Date d = Helper.getCurrentDate();
        if (date != null) {
            d = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        }
        model.addAttribute("date", d);
        model.addAttribute("idSegment", idSector);
        model.addAttribute("typeSegment", Helper.segment.station);
        model.addAttribute("typeSegmentForLink", "");
        model.addAttribute("monSegment", monitoringManager.getUploadForStation(idSector, d));
        return "/pages/monitoring.jsp";
    }

    @RequestMapping(value = "mon/uploadFileByStation.htm", method = RequestMethod.GET)
    @ResponseBody
    public Collection<File> monStationByKod(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "typeSegment", required = false) Helper.segment typeSegment,
            @RequestParam(value = "idSegment", required = false) Integer idStation,
            @RequestParam(value = "typeTerm", required = false) Helper.typeTerm typeTerm,
            Model model) throws Exception {
        return fileManager.getFiles(new SimpleDateFormat("dd.MM.yyyy").parse(date), idStation);
    }

}
