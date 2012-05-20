package controllers;

import managers.DataManager;
import objects.SegmentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import report.Report;
import report.cashiers.report1.ReportCashiers1;
import view.XLSViewReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;

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

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setReportCashiers1(ReportCashiers1 reportCashiers1) {
        this.reportCashiers1 = reportCashiers1;
    }

    @RequestMapping(value = "report/listReport.htm", method = RequestMethod.GET)
    public String listReport() throws Exception {
        return "/pages/report/cashier/listReport.jsp";
    }

    @RequestMapping(value = "report/listReportCashiers.htm", method = RequestMethod.GET)
    public String listReportCashiers(Model model) throws Exception {
        Collection<SegmentInfo> directions = dataManager.getDirections();
        model.addAttribute("directions", directions);
        return "/pages/report/cashier/listReportCashier.jsp";
    }


    @RequestMapping(value = "report/reportCashiers1.htm", method = RequestMethod.GET)
    public ModelAndView reportCashiers1(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "sector", required = false) Integer idSector
    ) throws Exception {
        HashMap map = new HashMap();
        reportCashiers1.init(year, month, idSector);
        map.put("report", reportCashiers1);
        return new ModelAndView(new XLSViewReport(), map);
    }


}
