package managers;

import objects.SegmentInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.04.2012
 * Time: 22:14:15
 * To change this template use File | Settings | File Templates.
 */
public class DataManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public SegmentInfo getDirectionById(int idDirection) {
        return db.queryForObject("select dir_id,dir_name from direction where dir_id=?", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idDirection);
    }

    public SegmentInfo getDirectionByIdSector(int idSector) {
        return db.queryForObject("select d.dir_id,d.dir_name from direction d join sector s on d.dir_id=s.sect_dir_id where s.sect_id=? limit 1", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idSector);
    }

    public SegmentInfo getDirectionByIdStation(int idStation) {
        return db.queryForObject("select d.dir_id,d.dir_name from direction d join sector s on d.dir_id=s.sect_dir_id join station_sector_cross ssc on s.sect_id=ssc.sect_id where ssc.stat_id=? limit 1", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idStation);
    }

    public SegmentInfo getSectorById(int idSector) {
        return db.queryForObject("select sect_id,sect_name from sector where sect_id=?", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idSector);
    }

    public SegmentInfo getSectorByIdStation(int idStation) {
        return db.queryForObject("select s.sect_id,s.sect_name from sector s join station_sector_cross ssc on s.sect_id=ssc.sect_id where ssc.stat_id=? limit 1", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idStation);
    }

    public SegmentInfo getStationById(int idStation) {
        return db.queryForObject("select stat_id,stat_name from ststion where stat_id=?", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idStation);
    }
}
