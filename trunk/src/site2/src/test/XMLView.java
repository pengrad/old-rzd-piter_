package test;

import com.pengrad.rzd.report.Report;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 22:05:37
 * To change this template use File | Settings | File Templates.
 */
public class XMLView implements View {
    public XMLView() {
    }

    public String getContentType() {
        return "text/xml";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/xml");
        Report report = (Report) model.get("report");
        Date date = (Date) model.get("date");
        int idStation = (Integer) model.get("idStation");
//        response.setContentLength(xmlAsString.length());
//        report.buildXml(date, idStation, (OutputStream) response.getOutputStream());

//        PrintWriter out = new PrintWriter(response.getOutputStream());
//        out.print(xmlAsString);
//        out.flush();
//        out.close();
    }
}
