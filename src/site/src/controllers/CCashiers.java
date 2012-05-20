package controllers;

import managers.CashiersManager;
import managers.DataManager;
import objects.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import report.cashiers.report2.ReportCashiers1Builder;
import utils.Helper;
import view.XLSViewReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private DataManager dataManager;

    public void setCashiersManager(CashiersManager cashiersManager) {
        this.cashiersManager = cashiersManager;
    }



    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @RequestMapping(value = "cashiers/view.htm", method = RequestMethod.GET)
    public String getDirection(Model model) {
        model.addAttribute("cashiers", cashiersManager.getCashiers());
        return "/pages/cashiers.jsp";
    }

    @RequestMapping(value = "cashiers/details/view.htm", method = RequestMethod.GET)
    public String getcashiers(
            @RequestParam(value = "idCashiers", required = false) Integer idCashiers,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            Model model) {
        if (year == null || month == null) {
            GregorianCalendar gc = new GregorianCalendar();
            year = gc.get(Calendar.YEAR);
            month = gc.get(Calendar.MONTH) + 1;
        }
        Cashiers cashiers = cashiersManager.getCashiersById(idCashiers, year, month);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("cashiers", cashiers);
        model.addAttribute("direction", dataManager.getDirectionByIdSector(cashiers.getIdSector()));
        model.addAttribute("sector", dataManager.getSectorById(cashiers.getIdSector()));
        return "/pages/cashiersDetails.jsp";
    }

    @RequestMapping(value = "cashiers/details/add.htm", method = RequestMethod.GET)
    public String viewAdd(
            Model model) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH) + 1;
        Cashiers cashiers = new Cashiers(1, "", 1, "", "");
        ArrayList<PlanCashiers> planCashiers = new ArrayList<PlanCashiers>();
        GregorianCalendar g = new GregorianCalendar();
        int max = g.getActualMaximum(Calendar.DAY_OF_MONTH);
        g.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 1; i <= max; i++) {
            g.set(Calendar.DAY_OF_MONTH, i);
            planCashiers.add(new PlanCashiers(0, 0, g.getTime(), "", 0.0, 0.0));
        }
        cashiers.setPlanCashiers(planCashiers);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("cashiers", cashiers);
        SegmentInfo direction = dataManager.getDirectionByIdSector(cashiers.getIdSector());
        model.addAttribute("direction", direction);
        model.addAttribute("sector", dataManager.getSectorById(cashiers.getIdSector()));
        model.addAttribute("directions", dataManager.getDirections());
        model.addAttribute("sectors", dataManager.getSectorByIdDirection(direction.getId()));
        model.addAttribute("action", Helper.action.add);
        return "/pages/cashiersDetailsEdit.jsp";
    }

    @RequestMapping(value = "cashiers/details/edit.htm", method = RequestMethod.GET)
    public String viewUpdate(
            @RequestParam(value = "idCashiers", required = false) Integer idCashiers,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            Model model) {
        if (year == null || month == null) {
            GregorianCalendar gc = new GregorianCalendar();
            year = gc.get(Calendar.YEAR);
            month = gc.get(Calendar.MONTH) + 1;
        }

        Cashiers cashiers = cashiersManager.getCashiersById(idCashiers, year, month);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("cashiers", cashiers);
        SegmentInfo direction = dataManager.getDirectionByIdSector(cashiers.getIdSector());
        model.addAttribute("direction", direction);
        model.addAttribute("sector", dataManager.getSectorById(cashiers.getIdSector()));
        model.addAttribute("directions", dataManager.getDirections());
        model.addAttribute("sectors", dataManager.getSectorByIdDirection(direction.getId()));
        model.addAttribute("action", Helper.action.edit);
        return "/pages/cashiersDetailsEdit.jsp";
    }

    @RequestMapping(value = "cashiers/details/save.htm", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ErrorAJAX> add(HttpServletRequest request) {
        ArrayList<ErrorAJAX> errors = new ArrayList<ErrorAJAX>();
        Cashiers cashiers = new Cashiers();
        ArrayList<PlanCashiers> planCashiers = new ArrayList<PlanCashiers>();
        cashiers.setPlanCashiers(planCashiers);
        try {
            int idCashiers = Integer.parseInt(request.getParameter("idCashiers"));
            if (cashiersManager.isExistCashiers(idCashiers)) {
                errors.add(new ErrorAJAX("idCashiers", "С таким кодом кассир уже существует"));
            }
            cashiers.setIdCashiers(idCashiers);
        } catch (Exception e) {
            errors.add(new ErrorAJAX("idCashiers", "Должно быть числовое значение"));
        }
        cashiers.setFioCashiers(request.getParameter("fioCashiers").toString().trim());
        cashiers.setIdSector(Integer.parseInt(request.getParameter("sector")));
        cashiers.setComments(request.getParameter("comment").toString().trim());
        for (int i = 1; i <= 31; i++) {
            if (request.getParameter("date" + i) == null) {
                continue;
            }
            PlanCashiers pc = new PlanCashiers();
            try {
                pc.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("date" + i)));
            } catch (Exception e) {
                errors.add(new ErrorAJAX("date" + i, "Должна быть дата"));
            }
            try {
//                if (request.getParameter("routeNumber" + i).trim().length() == 0) throw new Exception();
                pc.setRouteNumber(request.getParameter("routeNumber" + i).trim());
            } catch (Exception e) {
                errors.add(new ErrorAJAX("routeNumber" + i, "Должно быть заполнено"));
            }
            try {
                pc.setPlanBase(Double.parseDouble(request.getParameter("planBase" + i)));
            } catch (Exception e) {
                errors.add(new ErrorAJAX("planBase" + i, "Должно быть число"));
            }
            try {
                pc.setPlanRzd(Double.parseDouble(request.getParameter("planRzd" + i)));
            } catch (Exception e) {
                errors.add(new ErrorAJAX("planRzd" + i, "Должно быть число"));
            }
            planCashiers.add(pc);
        }
        if (errors.size() == 0) {
            cashiersManager.addCashiers(cashiers);
        }
        return errors;
    }

    @RequestMapping(value = "cashiers/details/update.htm", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ErrorAJAX> update(HttpServletRequest request) {
        ArrayList<ErrorAJAX> errors = new ArrayList<ErrorAJAX>();
        Cashiers cashiers = new Cashiers();
        ArrayList<PlanCashiers> planCashiers = new ArrayList<PlanCashiers>();
        cashiers.setPlanCashiers(planCashiers);
        try {
            int idCashiers = Integer.parseInt(request.getParameter("idCashiers"));
            cashiers.setIdCashiers(idCashiers);
        } catch (Exception e) {
            errors.add(new ErrorAJAX("idCashiers", "Должно быть числовое значение"));
        }
        cashiers.setFioCashiers(request.getParameter("fioCashiers").toString().trim());
        cashiers.setIdSector(Integer.parseInt(request.getParameter("sector")));
        cashiers.setComments(request.getParameter("comment").toString().trim());
        for (int i = 1; i <= 31; i++) {
            if (request.getParameter("date" + i) == null) {
                continue;
            }
            PlanCashiers pc = new PlanCashiers();
            try {
                pc.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("date" + i)));
            } catch (Exception e) {
                errors.add(new ErrorAJAX("date" + i, "Должна быть дата"));
            }
            try {
//                if (request.getParameter("routeNumber" + i).trim().length() == 0) throw new Exception();
                pc.setRouteNumber(request.getParameter("routeNumber" + i).trim());
            } catch (Exception e) {
                errors.add(new ErrorAJAX("routeNumber" + i, "Должно быть заполнено"));
            }
            try {
                pc.setPlanBase(Double.parseDouble(request.getParameter("planBase" + i)));
            } catch (Exception e) {
                errors.add(new ErrorAJAX("planBase" + i, "Должно быть число"));
            }
            try {
                pc.setPlanRzd(Double.parseDouble(request.getParameter("planRzd" + i)));
            } catch (Exception e) {
                errors.add(new ErrorAJAX("planRzd" + i, "Должно быть число"));
            }
            planCashiers.add(pc);
        }
        if (errors.size() == 0) {
            cashiersManager.updateCashiers(cashiers);
        }
        return errors;
    }

    @RequestMapping(value = "cashiers/details/delete.htm", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ErrorAJAX> delete(
            @RequestParam(value = "idCashiers", required = false) Integer idCashiers,
            Model model) {
        ArrayList<ErrorAJAX> errors = new ArrayList<ErrorAJAX>();
        cashiersManager.deleteCashiers(idCashiers);
        return errors;
    }


}
