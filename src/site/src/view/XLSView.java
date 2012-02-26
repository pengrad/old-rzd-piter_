package view;

import com.pengrad.rzd.report.Report;
import org.springframework.web.servlet.View;
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
public class XLSView implements View {

    public String getContentType() {
        return "application/vnd.ms-exce";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Report report = (Report) model.get("report");
        Date date = (Date) model.get("dateReport");
        int segment = (Integer) model.get("segment");
        int typeTerm = (Integer) model.get("typeTerm");
        int idSegment = (Integer) model.get("idSegment");
        int id = (Integer) model.get("idSegment");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-exce");
        response.setHeader("Content-Disposition", "attachment; filename=3932_" + new SimpleDateFormat("yyyyMMdd").format(date) + "_"+Helper.convertTypeTerm(typeTerm)+"_"+Helper.convertTypeSegment(segment)+"_" + idSegment + ".xls");
        report.buildXls(date, Helper.convertTypeSegment(segment), idSegment, Helper.convertTypeTerm(typeTerm), (OutputStream) response.getOutputStream());
    }
}