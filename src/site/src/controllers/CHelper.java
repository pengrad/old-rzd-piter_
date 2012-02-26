package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 16.02.12
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
//@Controller
public class CHelper {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    @RequestMapping(value = "helper/isExistStation.htm", method = RequestMethod.GET)
//    @ResponseBody
//    public String isExistStation(@RequestParam(value = "idStation", required = true) int idStation){
//     return "yes";
//    }
}
