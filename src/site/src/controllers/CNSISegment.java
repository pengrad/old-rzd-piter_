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
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 21:05:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CNSISegment {
    private MonitoringManager monitoringManager;
    private FileManager fileManager;

    public void setMonitoringManager(MonitoringManager monitoringManager) {
        this.monitoringManager = monitoringManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @RequestMapping(value = "nsiSegment/view.htm", method = RequestMethod.GET)
    public String monDirection(HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("monSegment", monitoringManager.getSegmentForDirection());
            model.addAttribute("linkLevel", request.getContextPath() + "/mon/sector.htm?idDirection=");
        return "/pages/nsiSegment.jsp";
    }

}