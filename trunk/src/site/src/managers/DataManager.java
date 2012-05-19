package managers;

import objects.SegmentInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collection;

public class DataManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<SegmentInfo> getDirections() {
        return db.query("select dir_id,dir_name from direction order by dir_name;", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        });
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
        return db.queryForObject("select d.dir_id,d.dir_name from direction d join sector s on d.dir_id=s.sect_dir_id\n" +
                "join station_sector_cross ssc on s.sect_id=ssc.sect_id where ssc.stat_id=? limit 1", new RowMapper<SegmentInfo>() {
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

    public Collection<SegmentInfo> getSectorByIdDirection(int idDirection) {
        return db.query("select s.sect_id,s.sect_name from sector s where s.sect_dir_id=?", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idDirection);
    }

    public SegmentInfo getSectorByIdStation(int idStation) {
        return db.queryForObject("select s.sect_id,s.sect_name from sector s\n" +
                "join station_sector_cross ssc on s.sect_id=ssc.sect_id where ssc.stat_id=? limit 1", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idStation);
    }

    public SegmentInfo getStationById(int idStation) {
        return db.queryForObject("select stat_id,stat_name from station where stat_id=?", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idStation);
    }

    public Collection<SegmentInfo> getStationByIdSector(int idSector) {
        return db.query("select s.stat_id,s.stat_name from station s join station_sector_cross sc on s.stat_id=sc.stat_id where sc.sect_id=?", new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new SegmentInfo(rs.getInt(1), rs.getString(2));
            }
        }, idSector);
    }
}
