package controllers;

import com.pengrad.rzd.report.Report;
import managers.Load3932Manager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import utils.Helper;
import view.TXTView;
import view.XLSView;
import view.XMLView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Controller
public class CDownload3932 {
    private Report report;
    private Load3932Manager load3932Manager;

    public void setLoad3932Manager(Load3932Manager load3932Manager) {
        this.load3932Manager = load3932Manager;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @RequestMapping(value = "3932/direction.htm", method = RequestMethod.GET)
    public String direction(HttpServletRequest request, Model model) {
        model.addAttribute("repSegment", load3932Manager.getSegmentForDirection());
        model.addAttribute("linkLevel", request.getContextPath() + "/3932/sector.htm?idDirection=");
        model.addAttribute("linkDownloadXML", request.getContextPath() + "/3932/XML/download.htm?segment=" + Helper.SEGMENT_DIRECTION + "&idSegment=");
        model.addAttribute("linkDownloadTXT", request.getContextPath() + "/3932/TXT/download.htm?segment=" + Helper.SEGMENT_DIRECTION + "&idSegment=");
        model.addAttribute("linkDownloadXLS", request.getContextPath() + "/3932/XLS/download.htm?segment=" + Helper.SEGMENT_DIRECTION + "&idSegment=");
        return "/pages/load3932.jsp";
    }

    @RequestMapping(value = "3932/sector.htm", method = RequestMethod.GET)
    public String sector(HttpServletRequest request,
                         @RequestParam(value = "idDirection", required = true) int idDirection,
                         Model model) {
        model.addAttribute("repSegment", load3932Manager.getSegmentForSector(idDirection));
        model.addAttribute("linkLevel", request.getContextPath() + "/3932/station.htm?idSector=");
        model.addAttribute("linkDownloadXML", request.getContextPath() + "/3932/XML/download.htm?segment=" + Helper.SEGMENT_SECTOR + "&idSegment=");
        model.addAttribute("linkDownloadTXT", request.getContextPath() + "/3932/TXT/download.htm?segment=" + Helper.SEGMENT_SECTOR + "&idSegment=");
        model.addAttribute("linkDownloadXLS", request.getContextPath() + "/3932/XLS/download.htm?segment=" + Helper.SEGMENT_SECTOR + "&idSegment=");

        return "/pages/load3932.jsp";
    }

    @RequestMapping(value = "3932/station.htm", method = RequestMethod.GET)
    public String station(HttpServletRequest request,
                          @RequestParam(value = "idSector", required = true) int idSector,
                          Model model) {
        model.addAttribute("repSegment", load3932Manager.getSegmentForStation(idSector));
        model.addAttribute("linkDownloadXML", request.getContextPath() + "/3932/XML/download.htm?segment=" + Helper.SEGMENT_STATION + "&idSegment=");
        model.addAttribute("linkDownloadTXT", request.getContextPath() + "/3932/TXT/download.htm?segment=" + Helper.SEGMENT_STATION + "&idSegment=");
        model.addAttribute("linkDownloadXLS", request.getContextPath() + "/3932/XLS/download.htm?segment=" + Helper.SEGMENT_STATION + "&idSegment=");
        return "/pages/load3932.jsp";
    }


    @ModelAttribute("linkDownloadXMLByStation")
    public String linkDownloadXMLByStation(HttpServletRequest request) {
        return request.getContextPath() + "/3932/XML/download.htm?segment=" + Helper.SEGMENT_STATION + "&idSegment=";
    }

    @ModelAttribute("linkDownloadTXTByStation")
    public String linkDownloadTXTByStation(HttpServletRequest request) {
        return request.getContextPath() + "/3932/TXT/download.htm?segment=" + Helper.SEGMENT_STATION + "&idSegment=";
    }

    @ModelAttribute("linkDownloadXLSByStation")
    public String linkDownloadXLSByStation(HttpServletRequest request) {
        return request.getContextPath() + "/3932/XLS/download.htm?segment=" + Helper.SEGMENT_STATION + "&idSegment=";
    }

    @RequestMapping(value = "3932/XML/download.htm", method = RequestMethod.GET)
    public ModelAndView loadXML(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "segment", required = true) int segment,
                                @RequestParam(value = "idSegment", required = true) int idSegment,
                                @RequestParam(value = "typeTerm", required = true) int typeTerm,
                                @RequestParam(value = "dateReport", required = true) String dateReport,
                                Model model) throws Exception {
        HashMap map = new HashMap();
        map.put("report", report);
        map.put("segment", segment);
        map.put("idSegment", idSegment);
        map.put("typeTerm", typeTerm);
        map.put("dateReport", new SimpleDateFormat("dd.MM.yyyy").parse(dateReport));
        ModelAndView mav = new ModelAndView(new XMLView(), map);
        return mav;
    }

    @RequestMapping(value = "3932/TXT/download.htm", method = RequestMethod.GET)
    public ModelAndView loadTXT(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "segment", required = true) int segment,
                                @RequestParam(value = "idSegment", required = true) int idSegment,
                                @RequestParam(value = "typeTerm", required = true) int typeTerm,
                                @RequestParam(value = "dateReport", required = true) String dateReport,
                                Model model) throws Exception {
        HashMap map = new HashMap();
        map.put("report", report);
        map.put("segment", segment);
        map.put("idSegment", idSegment);
        map.put("typeTerm", typeTerm);
        map.put("dateReport", new SimpleDateFormat("dd.MM.yyyy").parse(dateReport));
        ModelAndView mav = new ModelAndView(new TXTView(), map);
        return mav;
    }

    @RequestMapping(value = "3932/XLS/download.htm", method = RequestMethod.GET)
    public ModelAndView loadXLS(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "segment", required = true) int segment,
                                @RequestParam(value = "idSegment", required = true) int idSegment,
                                @RequestParam(value = "typeTerm", required = true) int typeTerm,
                                @RequestParam(value = "dateReport", required = true) String dateReport,
                                Model model) throws Exception {
        HashMap map = new HashMap();
        map.put("report", report);
        map.put("segment", segment);
        map.put("idSegment", idSegment);
        map.put("typeTerm", typeTerm);
        map.put("dateReport", new SimpleDateFormat("dd.MM.yyyy").parse(dateReport));
        ModelAndView mav = new ModelAndView(new XLSView(), map);
        return mav;
    }
}
