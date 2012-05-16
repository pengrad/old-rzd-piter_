package managers;


import objects.SegmentInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.Helper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 20:34:46
 * To change this template use File | Settings | File Templates.
 */
public class Load3932Manager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<SegmentInfo> getSegmentForDirection() {
        String query;
        query = "select dir_id,dir_name from direction order by dir_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo mi = new SegmentInfo();
                mi.setTypeSegment(Helper.SEGMENT_DIRECTION);
                mi.setId(rs.getInt(1));
                mi.setName(rs.getString(2));
                return mi;
            }
        });
    }

    public Collection<SegmentInfo> getSegmentForSector(int idDirection) {
        String query;
        query = "select sect_id,sect_name from sector where sect_dir_id=? order by sect_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo mi = new SegmentInfo();
                mi.setTypeSegment(Helper.SEGMENT_SECTOR);
                mi.setId(rs.getInt(1));
                mi.setName(rs.getString(2));
                return mi;
            }
        }, idDirection);
    }

    public Collection<SegmentInfo> getSegmentForStation(int idSector) {
        String query;
        query = "select\n" +
                "st.stat_id,stat_name\n" +
                "from station st\n" +
                "join station_sector_cross cr\n" +
                "on st.stat_id=cr.stat_id\n" +
                "where sect_id=? order by stat_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo mi = new SegmentInfo();
                mi.setTypeSegment(Helper.SEGMENT_STATION);
                mi.setId(rs.getInt(1));
                mi.setName(rs.getString(2));
                return mi;
            }
        }, idSector);
    }
}