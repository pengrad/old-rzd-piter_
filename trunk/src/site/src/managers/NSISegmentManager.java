package managers;

import objects.SegmentInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class NSISegmentManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<SegmentInfo> getDirection() {
        String query;
        query = "select d.dir_id,d.dir_name from direction d order by d.dir_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo si = new SegmentInfo();
                si.setId(rs.getInt("dir_id"));
                si.setName(rs.getString("dir_name"));
                return si;
            }
        });
    }

    public Collection<SegmentInfo> getSector(int idDirection) {
        String query;
        query = "select s.sect_id,s.sect_name from sector s where s.sect_dir_id=? order by s.sect_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo si = new SegmentInfo();
                si.setId(rs.getInt("sect_id"));
                si.setName(rs.getString("sect_name"));
                return si;
            }
        }, idDirection);
    }

    public Collection<SegmentInfo> getStation(int idSector) {
        String query;
        query = "select s.stat_id,s.stat_name from station s\n" +
                "                                  join station_sector_cross ssc on s.stat_id=ssc.stat_id where ssc.sect_id=?\n" +
                "                             order by  s.stat_name;";
        return db.query(query, new RowMapper<SegmentInfo>() {
            public SegmentInfo mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                SegmentInfo mi = new SegmentInfo();
                mi.setId(rs.getInt("stat_id"));
                mi.setName(rs.getString("stat_name"));
                return mi;
            }
        }, idSector);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDirection(int idDirection) throws Exception {
        String query;
        query = "create temporary table segment(stat_id int);";
        db.execute(query);
        try {
            query = "insert into segment (select ssc.stat_id from station_sector_cross ssc\n" +
                    "                                        join sector sc on  ssc.sect_id=sc.sect_id\n" +
                    "                                        join direction d on sc.sect_dir_id=?)";
            db.update(query, idDirection);
            query = "delete from station_sector_cross\n" +
                    "        where sect_id  in ( \n" +
                    "                   select sc.sect_id  from sector sc\n" +
                    "                                      join direction d on sc.sect_dir_id=?);";
            db.update(query, idDirection);
            query = "delete from station where stat_id in (select * from segment);";
            db.update(query);
            query = "delete from sector where sect_dir_id=?;";
            db.update(query, idDirection);
            query = "delete from direction where dir_id=?;";
            db.update(query, idDirection);
        } finally {
            query = "drop table segment;";
            db.execute(query);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSector(int idSector) throws Exception {
        String query;
        query = "create temporary table segment(stat_id int);";
        db.execute(query);
        try {
            query = "insert into segment (select ssc.stat_id from station_sector_cross ssc join sector sc on  ssc.sect_id=?);";
            db.update(query, idSector);
            query = "delete from station_sector_cross where sect_id=?;";
            db.update(query, idSector);
            query = "delete from station where stat_id in (select * from segment);";
            db.update(query);
            query = "delete from sector where sect_id=?;";
            db.update(query, idSector);
        } finally {
            query = "drop table segment;";
            db.execute(query);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStation(int idStation) throws Exception {
        String query;
        query = "delete from station_sector_cross where stat_id=?;";
        db.update(query, idStation);
        query = "delete from station where stat_id=?;";
        db.update(query, idStation);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addDirection(String nameDirection) throws Exception {
        String query;
        query = "insert into direction (dir_name) values(?);";
        db.update(query, nameDirection);
        int id = db.queryForInt("select LAST_INSERT_ID()");
        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateDirection(int idDirection, String nameDirection) throws Exception {
        String query;
        query = "update direction dir_name=? where dir_id=?;";
        db.update(query, nameDirection, idDirection);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addSector(String nameSector, int idDirection) throws Exception {
        String query;
        query = "insert into sector (sect_name,sect_dir_id) values(?,?);";
        db.update(query, nameSector, idDirection);
        int id = db.queryForInt("select LAST_INSERT_ID()");
        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateSector(int idSector, String nameSector) throws Exception {
        String query;
        query = "update sector sect_name=? where sect_id=?;";
        db.update(query, nameSector, idSector);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addStation(int idStation, String nameStation, int idSector) throws Exception {
        String query;
        query = "insert into station (stat_id,stat_name) values(?,?);";
        db.update(query, idStation, nameStation);
        query = "insert into station_sector_cross (stat_id,sect_id) values (?,?);";
        db.update(query, idStation, idSector);
        return idStation;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStation(int idStation, String nameStation) throws Exception {
        String query;
        query = "update station stat_name=? where sect_id=?;";
        db.update(query, nameStation, idStation);
    }
}

