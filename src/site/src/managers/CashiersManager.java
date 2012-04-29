package managers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;

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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }


}