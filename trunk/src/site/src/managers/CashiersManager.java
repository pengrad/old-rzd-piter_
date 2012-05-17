package managers;

import objects.Cashiers;
import objects.PlanCashiers;
import objects.SegmentInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.04.2012
 * Time: 22:14:15
 * To change this template use File | Settings | File Templates.
 */
public class CashiersManager {
    private DataSource dataSource;
    private JdbcTemplate db;
    private DataManager dataManager;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Collection<Cashiers> getCashiers() {
        String query;
        query = "select c.cash_id,c.cash_fio,ss.sect_id,ss.sect_name,comment from cashier c\n" +
                "                                            join sector ss on c.cash_sect_id=ss.sect_id\n" +
                "                                            order by ss.sect_name,c.cash_fio;";
        return db.query(query, new RowMapper<Cashiers>() {
            public Cashiers mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                return new Cashiers(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }
        });
    }

    public Cashiers getCashiersById(final int idCashiers, final int year, final int month) {
        String query;
        query = "select cash_id,cash_fio,cash_sect_id,comment from cashier where cash_id=?;";
        return db.queryForObject(query, new RowMapper<Cashiers>() {
            public Cashiers mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                Cashiers cashiers = new Cashiers(rs.getInt(1), rs.getString(2), rs.getInt(3), "", rs.getString(4));
                String query = " select pc.plan_id,pc.plan_cash_id,pc.date,pc.route_number,pc.plan_base,\n"+
                        "(select sum(t.S) from file f join ticket t on f.FileId=t.FileId where f.CashierId=pc.plan_cash_id and date(t.TimeCalcReport)=pc.date) as fact_base,\n"+
                        "plan_rzd,\n"+
                        "(select sum(t.S) from file f join ticket t on f.FileId=t.FileId where f.CashierId=pc.plan_cash_id and date(t.TimeCalcReport)=pc.date and t.TicketTypeL=2213) as fact_rzd\n" +
                        " from plan_cashier pc \n" +
                        " where plan_cash_id=? and date between '" + year + "-" + month + "-01' and '" + year + "-" + month + "-31'\n" +
                        " order by date;";
//                System.out.println(query);
                cashiers.setPlanCashiers(
                        db.query(query, new RowMapper<PlanCashiers>() {
                            public PlanCashiers mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                                return new PlanCashiers(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8));
                            }
                        }, idCashiers));
                return cashiers;
            }
        }, idCashiers);
    }

    public boolean isExistCashiers(int idCashiers) {
        String query;
        query = "select count(*) from cashier where cash_id=?;";
        return !(db.queryForInt(query, idCashiers) == 0);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addCashiers(Cashiers cashiers) {
        String query;
        query = "insert into cashier (cash_id,cash_fio,cash_sect_id,comment) values (?,?,?,?)";
        db.update(query, cashiers.getIdCashiers(), cashiers.getFioCashiers(), cashiers.getIdSector(), cashiers.getComments());
        query = "insert into plan_cashier (plan_cash_id,date,route_number,plan_base,plan_rzd) values (?,?,?,?,?)";
        for (PlanCashiers pc : cashiers.getPlanCashiers()) {
            db.update(query, cashiers.getIdCashiers(), pc.getDate(), pc.getRouteNumber(), pc.getPlanBase(), pc.getPlanRzd());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCashiers(Cashiers cashiers) {
        System.out.println(cashiers.getIdCashiers());
        String query;
        query = "update cashier set cash_fio=?,cash_sect_id=?,comment=? where cash_id=?;";
        db.update(query, cashiers.getFioCashiers(), cashiers.getIdSector(), cashiers.getComments(), cashiers.getIdCashiers());
        query = "update plan_cashier set route_number=?,plan_base=?,plan_rzd=? where plan_cash_id=? and date=?;";
        for (PlanCashiers pc : cashiers.getPlanCashiers()) {
            db.update(query, pc.getRouteNumber(), pc.getPlanBase(), pc.getPlanRzd(), cashiers.getIdCashiers(), pc.getDate());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCashiers(int idCashiers) {
        String query;
        query = "delete from plan_cashier where plan_cash_id=?;";
        db.update(query, idCashiers);
        query = "delete from cashier where cash_id=?;";
        db.update(query, idCashiers);
    }
}