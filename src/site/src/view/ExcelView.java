package view;


/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 11.04.11
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */

import exportExcel.GenerateReportExcel;
import objects.table.Report;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook hssfWorkbook, HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        Report[] report = (Report[]) model.get("reports");
        GenerateReportExcel rr =(GenerateReportExcel)model.get("generateReportExcel");
        rr.createHSSFWorkBook(hssfWorkbook);
        rr.generateStandartStyles();
        rr.make(report);
    }
}
