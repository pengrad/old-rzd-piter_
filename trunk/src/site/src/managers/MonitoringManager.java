package managers;

import objects.MonitoringSegment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MonitoringManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<MonitoringSegment> getUploadForDirection(Date date) {
        String query;
        query = "select d.dir_id,d.dir_name,\n" +
                "                           (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     join sector sec on ssc.sect_id=sec.sect_id\n" +
                "                                        where TypeTerm='МКТК' and sec.sect_dir_id=d.dir_id and date(f.TimeCreate)=?\n" +
                "                            ) as countUploadMKTK,\n" +
                "                            (100\n" +
                "                            ) as countNourmMKTK,\n" +
                "                            (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     join sector sec on ssc.sect_id=sec.sect_id\n" +
                "                                        where TypeTerm='PKTK' and sec.sect_dir_id=d.dir_id and date(f.TimeCreate)=?\n" +
                "                             ) as countUploadPKTK,\n" +
                "                             (100\n" +
                "                             ) as countNourmPKTK\n" +
                "                             from direction d order by d.dir_name;";
        return db.query(query, new RowMapper<MonitoringSegment>() {
            public MonitoringSegment mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                MonitoringSegment mi = new MonitoringSegment();
                mi.setId(rs.getInt("dir_id"));
                mi.setName(rs.getString("dir_name"));
                mi.setCountUploadMKTK(rs.getInt("countUploadMKTK"));
                mi.setCountNormMKTK(rs.getInt("countNourmMKTK"));
                mi.setCountUploadPKTK(rs.getInt("countUploadPKTK"));
                mi.setCountNormPKTK(rs.getInt("countNourmPKTK"));
                return mi;
            }
        }, date, date);
    }

    public Collection<MonitoringSegment> getUploadForSector(int idDirection, Date date) {
        String query;
        query = "select s.sect_id,s.sect_name,\n" +
                "                             (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     where TypeTerm='МКТК' and ssc.sect_id=s.sect_id and date(f.TimeCreate)=?\n" +
                "                              ) as countUploadMKTK,\n" +
                "                              (100\n" +
                "                               ) as countNourmMKTK,\n" +
                "                             (select count(*) from file f\n" +
                "                                     join station_sector_cross ssc on f.PlaceTerm=ssc.stat_id\n" +
                "                                     where TypeTerm='PKTK' and ssc.sect_id=s.sect_id and date(f.TimeCreate)=?\n" +
                "                              ) as countUploadPKTK,\n" +
                "                              (100\n" +
                "                               ) as countNourmPKTK\n" +
                "                          from sector s where s.sect_dir_id=? order by s.sect_name;";
        return db.query(query, new RowMapper<MonitoringSegment>() {
            public MonitoringSegment mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                MonitoringSegment mi = new MonitoringSegment();
                mi.setId(rs.getInt("sect_id"));
                mi.setName(rs.getString("sect_name"));
                mi.setCountUploadMKTK(rs.getInt("countUploadMKTK"));
                mi.setCountNormMKTK(rs.getInt("countNourmMKTK"));
                mi.setCountUploadPKTK(rs.getInt("countUploadPKTK"));
                mi.setCountNormPKTK(rs.getInt("countNourmPKTK"));
                return mi;
            }
        }, date, date, idDirection);
    }

    public Collection<MonitoringSegment> getUploadForStation(int idSector, Date date) {
        String query;
        query = "select s.stat_id,s.stat_name,\n" +
                "                             (select count(*) from file f\n" +
                "                                     where TypeTerm='МКТК' and f.PlaceTerm=s.stat_id and date(f.TimeCreate)=?\n" +
                "                             ) as countUploadMKTK,\n" +
                "                             (100\n" +
                "                             ) as countNourmMKTK,\n" +
                "                             (select count(*) from file f\n" +
                "                                     where TypeTerm='PKTK' and f.PlaceTerm=s.stat_id and date(f.TimeCreate)=?\n" +
                "                             ) as countUploadPKTK,\n" +
                "                             (100\n" +
                "                             ) as countNourmPKTK\n" +
                "                                  from station s\n" +
                "                                  join station_sector_cross ssc on s.stat_id=ssc.stat_id where ssc.sect_id=?\n" +
                "                             order by  s.stat_name;";
        return db.query(query, new RowMapper<MonitoringSegment>() {
            public MonitoringSegment mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                MonitoringSegment mi = new MonitoringSegment();
                mi.setId(rs.getInt("stat_id"));
                mi.setName(rs.getString("stat_name"));
                mi.setCountUploadMKTK(rs.getInt("countUploadMKTK"));
                mi.setCountNormMKTK(rs.getInt("countNourmMKTK"));
                mi.setCountUploadPKTK(rs.getInt("countUploadPKTK"));
                mi.setCountNormPKTK(rs.getInt("countNourmPKTK"));
                return mi;
            }
        }, date, date, idSector);
    }

    
}
