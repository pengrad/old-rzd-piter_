package report.cashiers.report1;

import managers.DataManager;
import objects.table.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.Helper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ReportCashiers3 {
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
        Report report = new Report();
        Table table1 = new Table();
        int[] width = {20, 150, 50, 100, 100, 100, 100, 100, 100};
        table1.setColumnWidth(width);
        Caption caption = new Caption(Caption.H1, "Выполнение плана по сбору денежной выручке и доходов от продажи проездных документов работникам ОАО \"РЖД\" ККБР");
        table1.addCaption(caption);
        caption = new Caption(Caption.H2, dataManager.getSectorById(idSector).getName() + " за " + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        table1.addCaption(caption);
        Header header = new Header();
        Row row = new Row(20);
        row.addCell(new Cell(1, 2, 20, "№", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 150, "Ф.И.О", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 50, "выходы", Cell.styleHeader));
        row.addCell(new Cell(2, 1, 200, "Общая сумма денежной выручки, руб. (выручка+услуга)", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "%", Cell.styleHeader));
        row.addCell(new Cell(2, 1, 200, "Доход от продажи п.д. работникам ОАО \"РЖД\", руб.", Cell.styleHeader));
        row.addCell(new Cell(1, 2, 100, "%", Cell.styleHeader));
        header.addRow(row);
        row = new Row(20);
        row.addCell(new Cell(1, 1, 100, "план", Cell.styleHeader));
        row.addCell(new Cell(1, 1, 100, "факт", Cell.styleHeader));
        row.addCell(new Cell(1, 1, 100, "план", Cell.styleHeader));
        row.addCell(new Cell(1, 1, 100, "факт", Cell.styleHeader));
        header.addRow(row);
        table1.setHeader(header);
        report.addTable(table1);
        //Вторая таблица
        Table table2 = new Table();
        width = new int[35];
        caption = new Caption(Caption.H1, "Выполнение персонального планового задания по сбору денежной выручке контролерами - кассирами билетными (разъездными)");
        table2.addCaption(caption);
        caption = new Caption(Caption.H2, dataManager.getSectorById(idSector).getName() + " за " + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        table2.addCaption(caption);
        header = new Header();
        row = new Row(20);
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
        table2.setColumnWidth(width);
        header.addRow(row);

        table2.setHeader(header);
        report.addTable(table2);
        //Третья таблица
        Table table3 = new Table();
        caption = new Caption(Caption.H1, "Выполнение персонального планового задания по сбору доходов от продажи проездных документов работникам ОАО \"РЖД\" контролерами - кассирами билетными (разъездными)");
        table3.addCaption(caption);
        caption = new Caption(Caption.H2, dataManager.getSectorById(idSector).getName() + "  за " + Helper.getMonthNameByNumber(month) + " " + year + "г.");
        table3.addCaption(caption);
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
        table3.setColumnWidth(width);
        header2.addRow(row);
        table3.setHeader(header);
        report.addTable(table3);

        String query;
        query = "select \n" +
                Cell.styleDefault + " as typeCell,\n" +
                "t1.cash_fio,\n" +
                "t1.cnt,\n" +
                "t1.pb,\n" +
                "t1.fb,\n" +
                "round(t1.fb/t1.pb*100,1) as p1,\n" +
                "t1.przd,\n" +
                "t1.frzd,\n" +
                "round(t1.frzd/t1.przd*100,1) as p2\n" +
                "from (\n" +
                "    select c.cash_id,\n" +
                "    c.cash_fio,\n" +
                "   ifnull((select count(*) from plan_cashier pc where (pc.plan_base > 0 or pc.plan_rzd > 0) and pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as cnt,\n" +
                "  ifnull((select sum(pc.plan_base) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as pb,\n" +
                "ifnull((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TimeCalcReport between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as fb,\n" +
                "ifnull((select sum(pc.plan_rzd) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as przd,\n" +
                "ifnull((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TicketTypeL in (select disc_id from discount_rzd) and t.TimeCalcReport between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as frzd\n" +
                "from cashier c where c.cash_sect_id="+idSector+" order by cash_fio\n" +
                ") t1\n" +
                "union all\n" +
                "select \n" +
                Cell.styleResFirstLear + " as typeCell,\n" +
                "'ИТОГО' as cash_fio,\n" +
                "sum(t1.cnt) as cnt,\n" +
                "sum(t1.pb) as pb,\n" +
                "sum(t1.fb) as fb,\n" +
                "round(sum(t1.fb)/sum(t1.pb)*100,1) as p1,\n" +
                "sum(t1.przd) as przd,\n" +
                "sum(t1.frzd) as frzd,\n" +
                "round(sum(t1.frzd)/sum(t1.przd)*100,1) as p2\n" +
                "from (\n" +
                "    select c.cash_id,\n" +
                "    c.cash_fio,\n" +
                "   ifnull((select count(*) from plan_cashier pc where (pc.plan_base > 0 or pc.plan_rzd > 0) and pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as cnt,\n" +
                "  ifnull((select sum(pc.plan_base) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as pb,\n" +
                "ifnull((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TimeCalcReport between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as fb,\n" +
                "ifnull((select sum(pc.plan_rzd) from plan_cashier pc where pc.plan_cash_id=c.cash_id and pc.date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as przd,\n" +
                "ifnull((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TicketTypeL in (select disc_id from discount_rzd) and t.TimeCalcReport between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'),0) as frzd\n" +
                "from cashier c where c.cash_sect_id="+idSector+"\n" +
                ") t1";
        Collection<Row> datatable1 = db.query(query, new RowMapper<Row>() {
            public Row mapRow(ResultSet rs, int i) throws SQLException {
                Row row = new Row();
                row.addCell(new Cell(1, 1, i, Cell.styleDefault));
                for (int c = 2; c <= rs.getMetaData().getColumnCount(); c++) {
                    row.addCell(new Cell(1, 1, rs.getString(c), rs.getInt(1)));
                }
                return row;
            }
        });
        table1.setData(datatable1);


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
        query = query + "select 3 as sort," + Cell.styleDefault + " as typeCell,c.cash_id,c.cash_fio,'фактическая выручка+услуга' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and date(t.TimeCalcReport) = '" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "cast((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and date(t.TimeCalcReport) between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31') as char) as itogo\n" +
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
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 3 as sort," + Cell.styleResFirstLear + " as typeCell,0 as cash_id,'ИТОГО' as cash_fio,'фактическая выручка+услуга' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "sum((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and date(t.TimeCalcReport)='" + year + "-" + month + "-" + day + "')) as c" + day + ",\n";
        }
        query = query + "sum((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and date(t.TimeCalcReport) between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31')) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by typeCell,cash_fio,sort;";
        Collection<Row> datatable2 = db.query(query, new RowMapper<Row>() {
            public Row mapRow(ResultSet rs, int i) throws SQLException {
                Row row = new Row();
                row.addCell(new Cell(1, 1, i, Cell.styleDefault));
                for (int c = 4; c <= rs.getMetaData().getColumnCount(); c++) {
                    if (c == 4) {
                        if (i % 3 == 0) {
                            row.addCell(new Cell(1, 3, rs.getString(c), rs.getInt(2)));
                        }
                    } else {
                        row.addCell(new Cell(1, 1, rs.getString(c), rs.getInt(2)));
                    }
                }
                return row;
            }
        });
        table2.setData(datatable2);

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
        query = query + "select 3 as sort," + Cell.styleDefault + " as typeCell,c.cash_id,c.cash_fio,'фактическая выручка+услуга' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "cast((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TicketTypeL in (select disc_id from discount_rzd) and date(t.TimeCalcReport) = '" + year + "-" + month + "-" + day + "') as char) as c" + day + ",\n";
        }
        query = query + "cast((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id  and t.TicketTypeL in (select disc_id from discount_rzd) and date(t.TimeCalcReport) between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31') as char) as itogo\n" +
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
                "from cashier c where c.cash_sect_id=" + idSector + "\n";
        query = query + "union all\n";
        query = query + "select 3 as sort," + Cell.styleResFirstLear + " as typeCell,0 as cash_id,'ИТОГО' as cash_fio,'фактическая выручка+услуга' as cell,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "sum((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TicketTypeL in (select disc_id from discount_rzd) and date(t.TimeCalcReport)='" + year + "-" + month + "-" + day + "')) as c" + day + ",\n";
        }
        query = query + "sum((select round(sum(S),1) from file f join ticket t on f.FileId=t.FileId where f.CashierId=c.cash_id and t.TicketTypeL in (select disc_id from discount_rzd) and date(t.TimeCalcReport) between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31')) as itogo\n" +
                "from cashier c where c.cash_sect_id=" + idSector + " order by typeCell,cash_fio,sort;";
        Collection<Row> datatable3 = db.query(query, new RowMapper<Row>() {
            public Row mapRow(ResultSet rs, int i) throws SQLException {
                Row row = new Row();
                row.addCell(new Cell(1, 1, i, Cell.styleDefault));
                for (int c = 4; c <= rs.getMetaData().getColumnCount(); c++) {
                    if (c == 4) {
                        if (i % 3 == 0) {
                            row.addCell(new Cell(1, 3, rs.getString(c), rs.getInt(2)));
                        }
                    } else {
                        row.addCell(new Cell(1, 1, rs.getString(c), rs.getInt(2)));
                    }
                }
                return row;
            }
        });
        table3.setData(datatable3);
        return report;
    }
}
