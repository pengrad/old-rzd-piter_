package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import view.XLSView1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "report/listReport.htm", method = RequestMethod.GET)
    public String listReport(HttpServletRequest request, HttpServletResponse response,
                             Model model) throws Exception {
        return "/pages/report/cashier/listReport.jsp";
    }

    @RequestMapping(value = "report/listReportCashiers.htm", method = RequestMethod.GET)
    public String listReportCashiers(HttpServletRequest request, HttpServletResponse response,
                                     Model model) throws Exception {
        return "/pages/report/cashier/listReportCashier.jsp";
    }

}
