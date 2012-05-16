package objects;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.05.2012
 * Time: 19:08:00
 * To change this template use File | Settings | File Templates.
 */
public class PlanCashiers {
    private int planId;
    private int planCashId;
    private Date date;
    private String routeNumber;
    private double planBase;
    private double factBase;
    private double planRzd;
    private double factRzd;

    public PlanCashiers() {
    }

    public PlanCashiers(int planId, int planCashId, Date date, String routeNumber, double planBase, double planRzd) {
        this.planId = planId;
        this.planCashId = planCashId;
        this.date = date;
        this.routeNumber = routeNumber;
        this.planBase = planBase;
        this.planRzd = planRzd;
    }

    public PlanCashiers(int planId, int planCashId, Date date, String routeNumber, double planBase, double factBase, double planRzd, double factRzd) {
        this.planId = planId;
        this.planCashId = planCashId;
        this.date = date;
        this.routeNumber = routeNumber;
        this.planBase = planBase;
        this.factBase = factBase;
        this.planRzd = planRzd;
        this.factRzd = factRzd;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getPlanCashId() {
        return planCashId;
    }

    public void setPlanCashId(int planCashId) {
        this.planCashId = planCashId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public double getPlanBase() {
        return planBase;
    }

    public void setPlanBase(double planBase) {
        this.planBase = planBase;
    }

    public double getPlanRzd() {
        return planRzd;
    }

    public void setPlanRzd(double planRzd) {
        this.planRzd = planRzd;
    }

    public double getFactBase() {
        return factBase;
    }

    public void setFactBase(double factBase) {
        this.factBase = factBase;
    }

    public double getFactRzd() {
        return factRzd;
    }

    public void setFactRzd(double factRzd) {
        this.factRzd = factRzd;
    }
}
