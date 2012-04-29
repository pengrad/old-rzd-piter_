package managers;

import objects.SegmentInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.Helper;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class MonitoringManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<SegmentInfo> getUploadForDirection(Helper.typeTimeCalcReport typeTimeCalcReport, Date date) {
        String typeSelectDate = "";
        if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_UPLOAD)) {
            typeSelectDate = "date(f.TimeCreate)=?";
        }
        if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_FILE)) {
            typeSelectDate = "? between date(f.TimeOpen) and date(f.TimeClose)";
        }
        String query;
        query = "select d.dir_id,d.dir_name,\n" +
                "                           (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     join sector sec on ssc.sect_id=sec.sect_id\n" +
                "                                        where TypeTerm='МКТК' and sec.sect_dir_id=d.dir_id and " + typeSelectDate + "\n" +
                "                            ) as countUploadMKTK,\n" +
                "                            (100\n" +
                "                            ) as countNourmMKTK,\n" +
                "                            (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     join sector sec on ssc.sect_id=sec.sect_id\n" +
                "                                        where TypeTerm='PKTK' and sec.sect_dir_id=d.dir_id and " + typeSelectDate + "\n" +
                "                             ) as countUploadPKTK,\n" +
                "                             (100\n" +
                "                             ) as countNourmPKTK\n" +
                "                             from direction d order by d.dir_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo si = new SegmentInfo();
                si.setId(rs.getInt("dir_id"));
                si.setName(rs.getString("dir_name"));
                si.setCountUploadMKTK(rs.getInt("countUploadMKTK"));
                si.setCountNormMKTK(rs.getInt("countNourmMKTK"));
                si.setCountUploadPKTK(rs.getInt("countUploadPKTK"));
                si.setCountNormPKTK(rs.getInt("countNourmPKTK"));
                si.setCountUploadABP09(0);
                si.setCountNormABP09(0);
                si.setCountUploadSPKI102M(0);
                si.setCountNormSPKI102M(0);
                return si;
            }
        }, date, date);
    }

    public Collection<SegmentInfo> getUploadForSector(Helper.typeTimeCalcReport typeTimeCalcReport, int idDirection, Date date) {
        String typeSelectDate = "";
        if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_UPLOAD)) {
            typeSelectDate = "date(f.TimeCreate)=?";
        }
        if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_FILE)) {
            typeSelectDate = "? between date(f.TimeOpen) and date(f.TimeClose)";
        }
        String query;
        query = "select s.sect_id,s.sect_name,\n" +
                "                             (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     where TypeTerm='МКТК' and ssc.sect_id=s.sect_id and " + typeSelectDate + "\n" +
                "                              ) as countUploadMKTK,\n" +
                "                              (100\n" +
                "                               ) as countNourmMKTK,\n" +
                "                             (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     where TypeTerm='PKTK' and ssc.sect_id=s.sect_id and " + typeSelectDate + "\n" +
                "                              ) as countUploadPKTK,\n" +
                "                              (100\n" +
                "                               ) as countNourmPKTK\n" +
                "                          from sector s where s.sect_dir_id=? order by s.sect_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo si = new SegmentInfo();
                si.setId(rs.getInt("sect_id"));
                si.setName(rs.getString("sect_name"));
                si.setCountUploadMKTK(rs.getInt("countUploadMKTK"));
                si.setCountNormMKTK(rs.getInt("countNourmMKTK"));
                si.setCountUploadPKTK(rs.getInt("countUploadPKTK"));
                si.setCountNormPKTK(rs.getInt("countNourmPKTK"));
                si.setCountUploadABP09(0);
                si.setCountNormABP09(0);
                si.setCountUploadSPKI102M(0);
                si.setCountNormSPKI102M(0);
                return si;
            }
        }, date, date, idDirection);
    }

    public Collection<SegmentInfo> getUploadForStation(Helper.typeTimeCalcReport typeTimeCalcReport, int idSector, Date date) {
        String typeSelectDate = "";
        if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_UPLOAD)) {
            typeSelectDate = "date(f.TimeCreate)=?";
        }
        if (typeTimeCalcReport.equals(Helper.typeTimeCalcReport.DATE_FILE)) {
            typeSelectDate = "? between date(f.TimeOpen) and date(f.TimeClose)";
        }
        String query;
        query = "select s.stat_id,s.stat_name,\n" +
                "                             (select count(*) from file f\n" +
                "                                     where TypeTerm='МКТК' and f.PlaceTerm=s.stat_id and " + typeSelectDate + "\n" +
                "                             ) as countUploadMKTK,\n" +
                "                             (100\n" +
                "                             ) as countNourmMKTK,\n" +
                "                             (select count(*) from file f\n" +
                "                                     where TypeTerm='PKTK' and f.PlaceTerm=s.stat_id and " + typeSelectDate + "\n" +
                "                             ) as countUploadPKTK,\n" +
                "                             (100\n" +
                "                             ) as countNourmPKTK\n" +
                "                                  from station s\n" +
                "                                  join station_sector_cross ssc on s.stat_id=ssc.stat_id where ssc.sect_id=?\n" +
                "                             order by  s.stat_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo si = new SegmentInfo();
                si.setId(rs.getInt("stat_id"));
                si.setName(rs.getString("stat_name"));
                si.setCountUploadMKTK(rs.getInt("countUploadMKTK"));
                si.setCountNormMKTK(rs.getInt("countNourmMKTK"));
                si.setCountUploadPKTK(rs.getInt("countUploadPKTK"));
                si.setCountNormPKTK(rs.getInt("countNourmPKTK"));
                si.setCountUploadABP09(0);
                si.setCountNormABP09(0);
                si.setCountUploadSPKI102M(0);
                si.setCountNormSPKI102M(0);
                return si;
            }
        }, date, date, idSector);
    }


}
