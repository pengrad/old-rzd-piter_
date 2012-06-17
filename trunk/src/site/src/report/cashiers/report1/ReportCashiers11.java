package report.cashiers.report1;

import objects.table.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.Helper;


import javax.sql.DataSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ReportCashiers11 {
    private HSSFWorkbook wb;
    private JdbcTemplate db;

    public String getName() {
        return "report";
    }

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    public void init(int year, int month, int idSector) throws Exception {
        Report report1 = new Report();
        Caption caption = new Caption(Caption.H1, "Персональное плановое задание по сбору денежной выручке контролерами - кассирами билетными (разъездными) ВУ _______________  за " + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        Table table1 = new Table();
        Header header = new objects.table.Header();
        Row row = new Row(20);
        row.addCell(new Cell(1, 2, 20, "№", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "Ф.И.О", Cell.styleHeader));
        row.addCell(new Cell(31, 1, 31 * 50, "2012", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 50, "ИТОГО", Cell.styleHeader));
        header.addRow(row);
        row = new Row(20);
        for (int i = 1; i <= 31; i++) {
            row.addCell(new Cell(1, 1, 50, i, Cell.styleHeader));
        }
        header.addRow(row);
        table1.addCaption(caption);
        table1.setHeader(header);
        report1.addTable(table1);
        Report report2 = new Report();
        caption = new Caption(Caption.H1, "Персональное плпновое задание по сбору доходов от продажи проездных документов работникам ОАО \"РЖД\" контролерами - кассирами билетными (разъездными) ВУ _______________" + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        Table table2 = new Table();
        header = new objects.table.Header();
        row = new Row(20);
        row.addCell(new Cell(1, 2, 20, "№", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "Ф.И.О", Cell.styleHeader));
        row.addCell(new Cell(31, 1, 31 * 50, "2012", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 50, "ИТОГО", Cell.styleHeader));
        header.addRow(row);
        row = new Row(20);
        for (int i = 1; i <= 31; i++) {
            row.addCell(new Cell(1, 1, 50, i, Cell.styleHeader));
        }
        header.addRow(row);
        table1.addCaption(caption);
        table1.setHeader(header);
        report1.addTable(table1);
        String query;
        query = "select c.cash_id,c.cash_fio,'№ маршрута' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.route_number from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "'' as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by cash_fio;";
        Collection<ArrayList<String>> dataRoute = db.query(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 2; c <= 35; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });
        query = "select 'ИТОГО','кол-во ККБР на линии' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "count(*) as c" + day + ",\n";
        }
        query = query + "count(*) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + ";";
        ArrayList<String> sumRoute = db.queryForObject(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 1; c <= 34; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });

        query = "select c.cash_id,c.cash_fio,'плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.plan_base from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "cast((select sum(pc.plan_base) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31') as char) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by cash_fio";
        Collection<ArrayList<String>> dataPlanBase = db.query(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 2; c <= 35; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });

        query = "select 'ИТОГО','плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "sum((select pc.plan_base from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "')) as c" + day + ",\n";
        }
        query = query + "sum((select sum(pc.plan_base) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31')) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + ";";
        ArrayList<String> sumPlanBase = db.queryForObject(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 1; c <= 34; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });

        //Персональное плановое задание по сбору доходов от продажи проездных документов работникам ОАО "РЖД" контролерами - кассирами билетными
        query = "select c.cash_id,c.cash_fio,'плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.plan_rzd from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "cast((select sum(pc.plan_rzd) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31') as char) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by cash_fio";
        Collection<ArrayList<String>> dataPlanRzd = db.query(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 2; c <= 35; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });

        query = "select 'ИТОГО','плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "sum((select pc.plan_rzd from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "')) as c" + day + ",\n";
        }
        query = query + "sum((select sum(pc.plan_rzd) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31')) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + ";";
        ArrayList<String> sumPlanRzd = db.queryForObject(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 1; c <= 34; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });
    }

//        InputStream inp = getClass().getResourceAsStream("/res/cashier1.xls");
//        wb = new HSSFWorkbook(inp);
//        HSSFCellStyle styleBase = wb.createCellStyle();
//        // Настройка выравнивания и стиля текста
//        styleBase.setFillBackgroundColor(HSSFColor.WHITE.index);
//        styleBase.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        styleBase.setFillForegroundColor(HSSFColor.WHITE.index);
//        styleBase.setBorderBottom(CellStyle.BORDER_THIN);
//        styleBase.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//        styleBase.setBorderLeft(CellStyle.BORDER_THIN);
//        styleBase.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//        styleBase.setBorderRight(CellStyle.BORDER_THIN);
//        styleBase.setRightBorderColor(IndexedColors.BLACK.getIndex());
//        styleBase.setBorderTop(CellStyle.BORDER_THIN);
//        styleBase.setTopBorderColor(IndexedColors.BLACK.getIndex());
//        // Настройка шрифта
//        HSSFFont font = wb.createFont();
//        font.setFontName("Times New Roman");
////        font.setFontHeight();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        styleBase.setFont(font);
//
//        HSSFSheet sheet1 = wb.getSheetAt(0);
//        HSSFSheet sheet2 = wb.getSheetAt(1);
//
//        Iterator<ArrayList<String>> iDataRoute = dataRoute.iterator();
//        Iterator<ArrayList<String>> iDataPlanBase = dataPlanBase.iterator();
//        Iterator<ArrayList<String>> iDataPlanRzd = dataPlanRzd.iterator();
//        int r = 10;
//        while (iDataRoute.hasNext()) {
//            ArrayList<String> lDataRoute = iDataRoute.next();
//            ArrayList<String> lDataPlanBase = iDataPlanBase.next();
//            ArrayList<String> lDataPlanRsd = iDataPlanRzd.next();
//            HSSFRow rows1 = sheet1.createRow(r);
//            HSSFCell cells1 = rows1.createCell(0);
//            cells1.setCellValue(r - 9);
//            cells1.setCellStyle(styleBase);
//
//            HSSFRow rows2 = sheet2.createRow(r);
//            HSSFCell cells2 = rows2.createCell(0);
//            cells2.setCellValue(r - 9);
//            cells2.setCellStyle(styleBase);
//
//            for (int i = 1; i < (lDataRoute.size()) + 1; i++) {
//                cells1 = rows1.createCell(i);
//                cells1.setCellValue(lDataRoute.get(i - 1));
//                cells1.setCellStyle(styleBase);
//                cells2 = rows2.createCell(i);
//                cells2.setCellValue(lDataRoute.get(i - 1));
//                cells2.setCellStyle(styleBase);
//            }
//            r++;
//            rows1 = sheet1.createRow(r);
//            cells1 = rows1.createCell(0);
//            cells1.setCellValue(r - 9);
//            cells1.setCellStyle(styleBase);
//
//            rows2 = sheet2.createRow(r);
//            cells2 = rows2.createCell(0);
//            cells2.setCellValue(r - 9);
//            cells2.setCellStyle(styleBase);
//
//            for (int i = 1; i < (lDataRoute.size()) + 1; i++) {
//                cells1 = rows1.createCell(i);
//                cells1.setCellValue(lDataPlanBase.get(i - 1));
//                cells1.setCellStyle(styleBase);
//                cells2 = rows2.createCell(i);
//                cells2.setCellValue(lDataPlanRsd.get(i - 1));
//                cells2.setCellStyle(styleBase);
//            }
//            r++;
//        }
//        HSSFRow rows1 = sheet1.createRow(r);
//        HSSFCell cells1 = rows1.createCell(0);
//        cells1.setCellValue(r - 9);
//        cells1.setCellStyle(styleBase);
//
//        HSSFRow rows2 = sheet2.createRow(r);
//        HSSFCell cells2 = rows2.createCell(0);
//        cells2.setCellValue(r - 9);
//        cells2.setCellStyle(styleBase);
//
//        for (int i = 1; i < (sumRoute.size()) + 1; i++) {
//            cells1 = rows1.createCell(i);
//            cells1.setCellValue(sumRoute.get(i - 1));
//            cells1.setCellStyle(styleBase);
//            cells2 = rows2.createCell(i);
//            cells2.setCellValue(sumRoute.get(i - 1));
//            cells2.setCellStyle(styleBase);
//        }
//        r++;
//        rows1 = sheet1.createRow(r);
//        cells1 = rows1.createCell(0);
//        cells1.setCellValue(r - 9);
//        cells1.setCellStyle(styleBase);
//
//        rows2 = sheet2.createRow(r);
//        cells2 = rows2.createCell(0);
//        cells2.setCellValue(r - 9);
//        cells2.setCellStyle(styleBase);
//
//        for (int i = 1; i < (sumRoute.size()) + 1; i++) {
//            cells1 = rows1.createCell(i);
//            cells1.setCellValue(sumPlanBase.get(i - 1));
//            cells1.setCellStyle(styleBase);
//            cells2 = rows2.createCell(i);
//            cells2.setCellValue(sumPlanRzd.get(i - 1));
//            cells2.setCellStyle(styleBase);
//        }
//        for (int i = 10; i < r; i = i + 2) {
//            sheet1.addMergedRegion(new CellRangeAddress(i, i + 1, 1, 1));
//            sheet2.addMergedRegion(new CellRangeAddress(i, i + 1, 1, 1));
//        }
//    }
//
//
//    public void writeReport(OutputStream os) throws Exception {
//        wb.write(os);
//    }
}