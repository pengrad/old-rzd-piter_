package controllers;

import managers.CashiersManager;
import objects.Cashiers;
import objects.ErrorAJAX;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.04.2012
 * Time: 17:20:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CEditCashiersInKFile {
    private CashiersManager cashiersManager;

    public void setCashiersManager(CashiersManager cashiersManager) {
        this.cashiersManager = cashiersManager;
    }

    @RequestMapping(value = "cashiers/view.htm", method = RequestMethod.GET)
    public String getDirection(Model model) {
        model.addAttribute("cashiers", cashiersManager.getCashiers());
        return "/pages/cashiers.jsp";
    }

//    @RequestMapping(value = "cashiers/get.htm", method = RequestMethod.GET)
//    @ResponseBody
//    public Cashiers getcashiers(
//            @RequestParam(value = "idCashiers", required = false) Integer idCashiers,
//            Model model) {
//        return cashiersManager.getCashiersById(idCashiers);
//    }

    @RequestMapping(value = "cashiers/add.htm", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ErrorAJAX> add(@Valid Cashiers cashiers, BindingResult result, HttpServletRequest request) {
        ArrayList<ErrorAJAX> errors = new ArrayList<ErrorAJAX>();
        if (result.hasErrors()) {
            Collection<FieldError> tt = result.getFieldErrors();
            for (FieldError fieldError : tt) {
                if (fieldError.getCode().indexOf("typeMismatch") == -1) {
                    errors.add(new ErrorAJAX(fieldError.getField(), fieldError.getDefaultMessage()));
                } else {
                    errors.add(new ErrorAJAX(fieldError.getField(), "Значение не соответствует формату"));
                }
            }
        }
        if (cashiersManager.isExistCashiers(cashiers.getIdCashiers())) {
            errors.add(new ErrorAJAX("idCashiers", "Сотрудник с таким кодом уже существует"));
        }
        if (errors.size() == 0) {
            cashiersManager.addCashiers(cashiers);
        }
        return errors;
    }

    @RequestMapping(value = "cashiers/update.htm", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ErrorAJAX> update(@Valid Cashiers cashiers, BindingResult result, HttpServletRequest request) {
        ArrayList<ErrorAJAX> errors = new ArrayList<ErrorAJAX>();
        if (result.hasErrors()) {
            Collection<FieldError> tt = result.getFieldErrors();
            for (FieldError fieldError : tt) {
                if (fieldError.getCode().indexOf("typeMismatch") == -1) {
                    errors.add(new ErrorAJAX(fieldError.getField(), fieldError.getDefaultMessage()));
                } else {
                    errors.add(new ErrorAJAX(fieldError.getField(), "Значение не соответствует формату"));
                }
            }
        }
        if (errors.size() == 0) {
            cashiersManager.updateCashiers(cashiers);
        }
        return errors;
    }

    @RequestMapping(value = "cashiers/delete.htm", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ErrorAJAX> delete(
            @RequestParam(value = "idCashiers", required = false) Integer idCashiers,
            Model model) {
        ArrayList<ErrorAJAX> errors = new ArrayList<ErrorAJAX>();
        cashiersManager.deleteCashiers(idCashiers);
        return errors;
    }
}