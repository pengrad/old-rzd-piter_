package test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Report3932Xls {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Справка 3932");
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        cs.setWrapText(true);
        cell.setCellStyle(cs);
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,12));

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellStyle(cs);
        cell.setCellValue("Оформлено билетов и выручка");
        sheet.addMergedRegion(new CellRangeAddress(3,4,0,1));

        cell = row.createCell(2);
        cell.setCellStyle(cs);
        cell.setCellValue("Всего");
        sheet.addMergedRegion(new CellRangeAddress(3,4,2,2));

        cell = row.createCell(3);
        cell.setCellStyle(cs);
        cell.setCellValue("в том числе: платные");
        sheet.addMergedRegion(new CellRangeAddress(3,4,3,3));


        // Save
        String filename = "d:\\workbook.xls";
        if (wb instanceof XSSFWorkbook) {
            filename = filename + "x";
        }
        FileOutputStream out = new FileOutputStream(filename);
        wb.write(out);
        out.close();
    }

}
