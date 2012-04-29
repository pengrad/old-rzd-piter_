package controllers;

import managers.DataManager;
import managers.FileManager;
import managers.NSISegmentManager;
import objects.Link;
import objects.SegmentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.Helper;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 21:05:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CNSISegment {
    private NSISegmentManager nsiSegmentManager;
    private FileManager fileManager;
    private DataManager dataManager;

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setNsiSegmentManager(NSISegmentManager nsiSegmentManager) {
        this.nsiSegmentManager = nsiSegmentManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @RequestMapping(value = "nsiSegment/direction.htm", method = RequestMethod.GET)
    public String getDirection(Model model) {
        model.addAttribute("typeSegment", Helper.segment.direction);
        model.addAttribute("typeSegmentForLink", Helper.segment.sector);
        model.addAttribute("segment", nsiSegmentManager.getDirection());
        return "/pages/nsiSegment.jsp";
    }

    @RequestMapping(value = "nsiSegment/sector.htm", method = RequestMethod.GET)
    public String getSector(
            @RequestParam(value = "idSegment", required = true) int idDirection,
            Model model) {
        model.addAttribute("typeSegment", Helper.segment.sector);
        model.addAttribute("typeSegmentForLink", Helper.segment.station);
        model.addAttribute("segment", nsiSegmentManager.getSector(idDirection));
        ArrayList<Link> links = new ArrayList<Link>();
        SegmentInfo si = dataManager.getDirectionById(idDirection);
        links.add(new Link(si.getName(), null));
        model.addAttribute("links", links);
        return "/pages/nsiSegment.jsp";
    }

    @RequestMapping(value = "nsiSegment/station.htm", method = RequestMethod.GET)
    public String getStation(
            @RequestParam(value = "idSegment", required = true) int idSector,
            Model model) {
        model.addAttribute("typeSegment", Helper.segment.station);
        model.addAttribute("segment", nsiSegmentManager.getStation(idSector));
        ArrayList<Link> links = new ArrayList<Link>();
        SegmentInfo si1 = dataManager.getDirectionByIdSector(idSector);
        links.add(new Link(si1.getName(), "nsiSegment/sector.htm?idSegment=" + si1.getId()));
        SegmentInfo si2 = dataManager.getSectorById(idSector);
        links.add(new Link(si2.getName(), null));
        model.addAttribute("links", links);
        return "/pages/nsiSegment.jsp";
    }

//    @RequestMapping(value = "nsiSegment/add.htm", method = RequestMethod.GET)
//    public String addSegmentGET(
//            @RequestParam(value = "idSegment", required = true) int idSegment,
//            @RequestParam(value = "typeSegment", required = true) Helper.segment typeSegment,
//            Model model) {
//        model.addAttribute("typeSegment", typeSegment);
//        return "/pages/cashiers.jsp";
//    }

    @RequestMapping(value = "nsiSegment/add.htm", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> addSegmentPOST(
            @RequestParam(value = "typeSegment", required = false) Helper.segment typeSegment,
            @RequestParam(value = "idSegment", required = false) int idSegment,
            @RequestParam(value = "nameSegment", required = false) String nameSegment,
            @RequestParam(value = "idSegmentParent", required = false) int idSegmentParent,
            Model model) {
        HashMap<String, Object> res = new HashMap<String, Object>();
        try {
            if (typeSegment.equals(Helper.segment.direction)) {
                int idDirection = nsiSegmentManager.addDirection(nameSegment);
                res.put("idSegment", idDirection);
            }
            if (typeSegment.equals(Helper.segment.sector)) {
                int idSector = nsiSegmentManager.addSector(nameSegment, idSegmentParent);
                res.put("idSegment", idSector);
            }
            if (typeSegment.equals(Helper.segment.station)) {
                int idStation = nsiSegmentManager.addStation(idSegment, nameSegment, idSegmentParent);
                res.put("idSegment", idStation);
            }
            res.put("state", "ok");
        } catch (Exception e) {
            res.put("state", "error");
            res.put("messageError", e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "nsiSegment/update.htm", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> editSegment(
            @RequestParam(value = "typeSegment", required = false) Helper.segment typeSegment,
            @RequestParam(value = "idSegment", required = false) int idSegment,
            @RequestParam(value = "nameSegment", required = false) String nameSegment,
            @RequestParam(value = "idSegmentParent", required = false) int idSegmentParent,
            Model model) {
        HashMap<String, Object> res = new HashMap<String, Object>();
        try {
            if (typeSegment.equals(Helper.segment.direction)) {
                nsiSegmentManager.updateDirection(idSegment, nameSegment);
            }
            if (typeSegment.equals(Helper.segment.sector)) {
                nsiSegmentManager.updateSector(idSegment, nameSegment);
            }
            if (typeSegment.equals(Helper.segment.station)) {
                nsiSegmentManager.updateStation(idSegment, nameSegment);
            }
            res.put("state", "ok");
        } catch (Exception e) {
            res.put("state", "error");
            res.put("messageError", e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "nsiSegment/delete.htm", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, String> deleteSegment(
            @RequestParam(value = "typeSegment", required = false) Helper.segment typeSegment,
            @RequestParam(value = "idSegment", required = false) int idSegment) {
        HashMap<String, String> res = new HashMap<String, String>();
        try {
            if (typeSegment.equals(Helper.segment.direction)) {
                nsiSegmentManager.deleteDirection(idSegment);
            }
            if (typeSegment.equals(Helper.segment.sector)) {
                nsiSegmentManager.deleteSector(idSegment);
            }
            if (typeSegment.equals(Helper.segment.station)) {
                nsiSegmentManager.deleteStation(idSegment);
            }
            res.put("res", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("res", "error");
        }
        return res;
    }


}