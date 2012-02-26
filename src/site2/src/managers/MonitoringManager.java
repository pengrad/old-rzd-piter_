package managers;

import objects.MonitoringSegment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 20:34:46
 * To change this template use File | Settings | File Templates.
 */
public class MonitoringManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<MonitoringSegment> getSegmentForDirection() {
        String query;
        query = "select dir_id,dir_name from direction order by dir_name;";
        return db.query(query, new RowMapper<MonitoringSegment>() {
            public MonitoringSegment mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                MonitoringSegment mi = new MonitoringSegment();
                mi.setId(rs.getInt(1));
                mi.setName(rs.getString(2));
                mi.setStatus(true);
                mi.setCountExist(100);
                mi.setCountNorm(100);
                return mi;
            }
        });
    }

    public Collection<MonitoringSegment> getSegmentForSector(int idDirection) {
        String query;
        query = "select sect_id,sect_name from sector where sect_dir_id=? order by sect_name;";
        return db.query(query, new RowMapper<MonitoringSegment>() {
            public MonitoringSegment mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                MonitoringSegment mi = new MonitoringSegment();
                mi.setId(rs.getInt(1));
                mi.setName(rs.getString(2));
                mi.setStatus(true);
                mi.setCountExist(100);
                mi.setCountNorm(100);
                return mi;
            }
        }, idDirection);
    }

    public Collection<MonitoringSegment> getSegmentForStation(int idSector) {
        String query;
        query = "select\n" +
                "st.stat_id,stat_name\n" +
                "from station st\n" +
                "join station_sector_cross cr\n" +
                "on st.stat_id=cr.stat_id\n" +
                "where sect_id=? order by stat_name;";
        return db.query(query, new RowMapper<MonitoringSegment>() {
            public MonitoringSegment mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                MonitoringSegment mi = new MonitoringSegment();
                mi.setId(rs.getInt(1));
                mi.setName(rs.getString(2));
                mi.setStatus(true);
                mi.setCountExist(100);
                mi.setCountNorm(100);
                return mi;
            }
        }, idSector);
    }
}
