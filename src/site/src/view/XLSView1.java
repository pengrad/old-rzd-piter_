package view;

import com.pengrad.rzd.report.Report;
import org.springframework.web.servlet.View;
import report.cashiers.report1.ReportCashiers1Builder;
import utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.02.2012
 * Time: 11:23:27
 * To change this template use File | Settings | File Templates.
 */
public class XLSView1 implements View {

    public String getContentType() {
        return "application/vnd.ms-exce";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int year = (Integer) model.get("year");
        int month = (Integer) model.get("month");
        int idSector = (Integer) model.get("idSector");
        ReportCashiers1Builder rb=(ReportCashiers1Builder)model.get("reportCashiers1Builder");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-exce");
        response.setHeader("Content-Disposition", "attachment; filename=test.xls");
        rb.build(year, month, idSector, (OutputStream) response.getOutputStream());
    }
}