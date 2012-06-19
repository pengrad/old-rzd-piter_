package controllers;

import managers.DataManager;
import objects.SegmentInfo;
import objects.table.Report;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import report.cashiers.report1.ReportCashiers1;
import report.cashiers.report1.ReportCashiers3;
import utils.Helper;
import utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 17.05.2012
 * Time: 18:13:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CReport {
    private DataManager dataManager;
    private ReportCashiers1 reportCashiers1;
    private ReportCashiers3 reportCashiers3;

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setReportCashiers1(ReportCashiers1 reportCashiers1) {
        this.reportCashiers1 = reportCashiers1;
    }

    public void setReportCashiers3(ReportCashiers3 reportCashiers3) {
        this.reportCashiers3 = reportCashiers3;
    }

    @RequestMapping(value = "report/listReport.htm", method = RequestMethod.GET)
    public String listReport() throws Exception {
        return "/pages/report/listReport.jsp";
    }

    @RequestMapping(value = "report/listReportCashiers.htm", method = RequestMethod.GET)
    public String listReportCashiers(Model model) throws Exception {
        Collection<SegmentInfo> directions = dataManager.getDirections();
        model.addAttribute("directions", directions);
        return "/pages/report/listReportCashier.jsp";
    }


    @RequestMapping(value = "report/reportCashiers1.htm", method = RequestMethod.GET)
    @ResponseBody
    public int reportCashiers1(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "sector", required = false) Integer idSector,
            Model model,
            HttpServletRequest req
    ) throws Exception {
        Integer repKey = Utils.getKey();
        Report report = reportCashiers1.init(year, month, idSector);
        req.getSession().setAttribute(repKey.toString(), report);
        return repKey;
    }

    @RequestMapping(value = "report/reportCashiers3.htm", method = RequestMethod.GET)
    @ResponseBody
    public int reportCashiers3(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "sector", required = false) Integer idSector,
            Model model,
            HttpServletRequest req
    ) throws Exception {
        Integer repKey = Utils.getKey();
        Report report = reportCashiers3.init(year, month, idSector);
        req.getSession().setAttribute(repKey.toString(), report);
        return repKey;
    }

    @RequestMapping(value = "report/reportHtml.htm", method = RequestMethod.GET)
    public String reportHtml(
            @RequestParam(value = "repKey", required = false) Integer repKey,
            Model model,
            HttpServletRequest req
    ) throws Exception {
        Report report = (Report) req.getSession().getAttribute(repKey.toString());
        model.addAttribute("report", report);
        model.addAttribute("repKey", repKey);
        return "/pages/report/report.jsp";
    }


}
