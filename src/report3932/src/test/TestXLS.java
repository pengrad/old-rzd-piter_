package test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestXLS {

    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
// create a new sheet
        HSSFSheet s = wb.createSheet();
// declare a row object reference
        HSSFRow r = null;
// declare a cell object reference
        HSSFCell c = null;
// create 2 cell styles
        HSSFCellStyle cs = wb.createCellStyle();
        HSSFCellStyle cs2 = wb.createCellStyle();
        HSSFDataFormat df = wb.createDataFormat();

// create 2 fonts objects
        HSSFFont f = wb.createFont();
        HSSFFont f2 = wb.createFont();

// Set font 1 to 12 point type, blue and bold
        f.setFontHeightInPoints((short) 12);
        f.setColor(HSSFColor.RED.index);
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

// Set font 2 to 10 point type, red and bold
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(HSSFColor.GREEN.index);
        f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

// Set cell style and formatting
        cs.setFont(f);
        cs.setDataFormat(df.getFormat("#,##0.0"));

// Set the other cell style and formatting
        cs2.setBorderBottom(cs2.BORDER_THIN);
        cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
        cs2.setFont(f2);


// Define a few rows
        for (short rownum = (short) 0; rownum < 30; rownum++) {
            HSSFRow r1 = s.createRow(rownum);
            for (short cellnum = (short) 0; cellnum < 10; cellnum += 2) {
                HSSFCell c1 = r1.createCell(cellnum);
                HSSFCell c2 = r1.createCell(cellnum + 1);
                c2.setCellStyle(cs2);
                c1.setCellValue((double) rownum + (cellnum / 10));
                c2.setCellValue(new HSSFRichTextString("Hello! " + cellnum));
            }
        }

// Save
        FileOutputStream out = new FileOutputStream("d:\\workbook.xls");
        wb.write(out);
        out.close();
    }

}
