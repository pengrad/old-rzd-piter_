package controllers;

import managers.CashiersManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.04.2012
 * Time: 17:20:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CCashiers {
    private CashiersManager cashiersManager;

    public void setCashiersManager(CashiersManager cashiersManager) {
        this.cashiersManager = cashiersManager;
    }

    @RequestMapping(value = "cashiers/view.htm", method = RequestMethod.GET)
    public String getDirection(Model model) {
        return "/pages/cashiers.jsp";
    }

}
