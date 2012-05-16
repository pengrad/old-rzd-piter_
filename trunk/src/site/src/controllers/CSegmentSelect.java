package controllers;

import managers.CashiersManager;
import managers.DataManager;
import objects.SegmentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.04.2012
 * Time: 17:20:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CSegmentSelect {
    private DataManager dataManager;

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @RequestMapping(value = "segmentSelect/getDirections.htm", method = RequestMethod.GET)
    @ResponseBody
    public Collection<SegmentInfo> getDirections(Model model) {
        return dataManager.getDirections();
    }

    @RequestMapping(value = "segmentSelect/getSectorByIdDirection.htm", method = RequestMethod.GET)
    @ResponseBody
    public Collection<SegmentInfo> getSectorByIdDirection(
            @RequestParam(value = "dirId", required = false, defaultValue = "0") int dirId,
            Model model) {
        return dataManager.getSectorByIdDirection(dirId);
    }

    @RequestMapping(value = "segmentSelect/getStationByIdSector.htm", method = RequestMethod.GET)
    @ResponseBody
    public Collection<SegmentInfo> getStationByIdSector(
            @RequestParam(value = "sectId", required = false, defaultValue = "0") int sectId,
            Model model) {
        return dataManager.getStationByIdSector(sectId);
    }

    @RequestMapping(value = "segmentSelect/getDirectionByIdStation.htm", method = RequestMethod.GET)
    @ResponseBody
    public SegmentInfo getDirectionByIdStation(
            @RequestParam(value = "statId", required = false) int statId,
            Model model) {
        return dataManager.getDirectionByIdStation(statId);
    }

    @RequestMapping(value = "segmentSelect/getSectorByIdStation.htm", method = RequestMethod.GET)
    @ResponseBody
    public SegmentInfo getSectorByIdStation(
            @RequestParam(value = "statId", required = false) int statId,
            Model model) {
        return dataManager.getSectorByIdStation(statId);
    }
}