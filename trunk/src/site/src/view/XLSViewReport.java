package view;

import org.springframework.web.servlet.View;
import report.Report;
import report.cashiers.report2.ReportCashiers1Builder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.02.2012
 * Time: 11:23:27
 * To change this template use File | Settings | File Templates.
 */
public class XLSViewReport implements View {

    public String getContentType() {
        return "application/vnd.ms-exce";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Report report = (Report) model.get("report");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-exce");            //        " + report.getName()
        response.setHeader("Content-Disposition", "attachment; filename=" + report.getName() + ".xls");
        report.writeReport((OutputStream) response.getOutputStream());
    }
}