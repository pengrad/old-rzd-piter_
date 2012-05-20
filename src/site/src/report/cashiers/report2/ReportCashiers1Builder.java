package report.cashiers.report2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 10.05.2012
 * Time: 0:47:40
 * To change this template use File | Settings | File Templates.
 */
public class ReportCashiers1Builder {
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        db = new JdbcTemplate(dataSource);
    }

    public void build(int year, int month, int idSector, OutputStream os) throws Exception {
        String query;
        query = "select\n" +
                "c.cash_fio,\n";
        for (int day = 1; day <= 31; day++) {
            query = query + "coalesce((select sum(t.S) from ticket t join file f on t.FileId=f.FileId where f.PlaceTerm=c.cash_stat_id and f.CashiersId=c.cash_id and f.TypeTerm='PKTK' and date(t.TimeCalcReport)='" + year + "-" + month + "-" + day + "'),0) as c" + day + ",\n";
        }
        query = query + "coalesce((select sum(t.S) from ticket t join file f on t.FileId=f.FileId where f.PlaceTerm=c.cash_stat_id and f.CashiersId=c.cash_id and f.TypeTerm='PKTK' and date(t.TimeCalcReport) between '" + year + "-" + month + "-1' and '" + year + "-" + month + "-31'),0) as itog\n";
        query = query + "from cashiers c\n" +
                "join station_sector_cross sc on c.cash_stat_id=sc.stat_id\n" +
                "where sc.sect_id=" + idSector + ";";
        System.out.println(query);
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet();
        Collection<ArrayList<String>> data = db.query(query, new RowMapper<ArrayList<String>>() {
            public ArrayList<String> mapRow(ResultSet rs, int i) throws SQLException {
                ArrayList<String> al = new ArrayList<String>();
                for (int c = 1; c <= 32; c++) {
                    al.add(rs.getString(c));
                }
                return al;
            }
        });

        int r = 1;
        for (ArrayList<String> list : data) {
            Row row = s.createRow(r);
            int j = 0;
            for (String ss : list) {
                row.createCell(j).setCellValue(ss);
                j++;
            }
            r++;
        }
        wb.write(os);
    }
}

