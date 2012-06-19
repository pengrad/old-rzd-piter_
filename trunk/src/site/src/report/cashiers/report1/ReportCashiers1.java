package report.cashiers.report1;

import managers.DataManager;
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

public class ReportCashiers1 {
    private HSSFWorkbook wb;
    private JdbcTemplate db;
    private DataManager dataManager;

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    public Report init(int year, int month, int idSector) throws Exception {
        int[] width = new int[35];
        Report report = new Report();
        Table table1 = new Table();
        Caption caption = new Caption(Caption.H1, "Персональное плановое задание по сбору денежной выручке контролерами - кассирами билетными (разъездными)");
        table1.addCaption(caption);
        caption = new Caption(Caption.H2, dataManager.getSectorById(idSector).getName() + " за " + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        table1.addCaption(caption);
        Header header = new Header();
        Row row = new Row(20);
        row.addCell(new Cell(1, 2, 20, "№", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "Ф.И.О", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "", Cell.styleHeader));
        row.addCell(new Cell(31, 1, 31 * 50, Helper.getMonthNameByNumber(month) + " " + year, Cell.styleHeader));
        row.addCell(new Cell(1, 2, 50, "ИТОГО", Cell.styleHeader));
        width[0] = 20;
        width[1] = 100;
        width[2] = 100;
        width[34] = 50;
        header.addRow(row);
        row = new Row(20);
        for (int i = 1; i <= 31; i++) {
            width[i + 2] = 50;
            row.addCell(new Cell(1, 1, 50, i, Cell.styleHeader));
        }
        table1.setColumnWidth(width);
        header.addRow(row);

        table1.setHeader(header);
        report.addTable(table1);
        //Вторая страница
        Table table2 = new Table();
        caption = new Caption(Caption.H1, "Персональное плпновое задание по сбору доходов от продажи проездных документов работникам ОАО \"РЖД\" контролерами - кассирами билетными (разъездными)");
        table2.addCaption(caption);
        caption = new Caption(Caption.H2, dataManager.getSectorById(idSector).getName() + "  за " + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        table2.addCaption(caption);
        Header header2 = new Header();
        row = new Row(20);
        row.addCell(new Cell(1, 2, 20, "№", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "Ф.И.О", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "", Cell.styleHeader));
        row.addCell(new Cell(31, 1, 31 * 50, Helper.getMonthNameByNumber(month) + " " + year, Cell.styleHeader));
        row.addCell(new Cell(1, 2, 50, "ИТОГО", Cell.styleHeader));
        header2.addRow(row);
        width = new int[35];
        width[0] = 20;
        width[1] = 100;
        width[2] = 100;
        width[34] = 50;
        row = new Row(20);
        for (int i = 1; i <= 31; i++) {
            row.addCell(new Cell(1, 1, 50, i, Cell.styleHeader));
            width[i + 2] = 50;
        }
        table2.setColumnWidth(width);
        header2.addRow(row);
        table2.setHeader(header);
        report.addTable(table2);

        String query;
        query = "select 1 as sort," + Cell.styleDefault + " as typeCell,c.cash_id,c.cash_fio,'№ маршрута' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.route_number from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "'' as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 2 as sort," + Cell.styleDefault + " as typeCell,c.cash_id,c.cash_fio,'плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.plan_base from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "cast((select sum(pc.plan_base) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31') as char) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 1 as sort," + Cell.styleResFirstLear + " as typeCell,0 as cash_id,'ИТОГО' as cash_fio,'кол-во ККБР на линии' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "count(*) as c" + day + ",\n";
        }
        query = query + "count(*) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 2 as sort," + Cell.styleResFirstLear + " as typeCell,0 as cash_id,'ИТОГО' as cash_fio,'плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "sum((select pc.plan_base from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "')) as c" + day + ",\n";
        }
        query = query + "sum((select sum(pc.plan_base) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31')) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by typeCell,cash_fio,sort;";
        Collection<Row> dataTable1 = db.query(query, new RowMapper<Row>() {
            public Row mapRow(ResultSet rs, int i) throws SQLException {
                Row row = new Row();
                row.addCell(new Cell(1, 1, i, Cell.styleDefault));
                for (int c = 4; c <= rs.getMetaData().getColumnCount(); c++) {
                    if (c == 4) {
                        if (i % 2 == 0) {
                            row.addCell(new Cell(1, 2, rs.getString(c), rs.getInt(2)));
                        }
                    } else {
                        row.addCell(new Cell(1, 1, rs.getString(c), rs.getInt(2)));
                    }
                }
                return row;
            }
        });
        table1.setData(dataTable1);

        query = "select 1 as sort," + Cell.styleDefault + " as typeCell,c.cash_id,c.cash_fio,'№ маршрута' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.route_number from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "'' as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 2 as sort," + Cell.styleDefault + " as typeCell,c.cash_id,c.cash_fio,'плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select pc.plan_rzd from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "cast((select sum(pc.plan_rzd) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31') as char) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 1 as sort," + Cell.styleResFirstLear + " as typeCell,0 as cash_id,'ИТОГО' as cash_fio,'кол-во ККБР на линии' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "count(*) as c" + day + ",\n";
        }
        query = query + "count(*) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 2 as sort," + Cell.styleResFirstLear + " as typeCell,0 as cash_id,'ИТОГО' as cash_fio,'плановое задание' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "sum((select pc.plan_rzd from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date='" + year + "-" + month + "-" + day + "')) as c" + day + ",\n";
        }
        query = query + "sum((select sum(pc.plan_rzd) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31')) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by typeCell,cash_fio,sort;";
        Collection<Row> dataTable2 = db.query(query, new RowMapper<Row>() {
            public Row mapRow(ResultSet rs, int i) throws SQLException {
                Row row = new Row();
                row.addCell(new Cell(1, 1, i, Cell.styleDefault));
                for (int c = 4; c <= rs.getMetaData().getColumnCount(); c++) {
                    if (c == 4) {
                        if (i % 2 == 0) {
                            row.addCell(new Cell(1, 2, rs.getString(c), rs.getInt(2)));
                        }
                    } else {
                        row.addCell(new Cell(1, 1, rs.getString(c), rs.getInt(2)));
                    }
                }
                return row;
            }
        });
        table2.setData(dataTable2);
        return report;
    }
}
