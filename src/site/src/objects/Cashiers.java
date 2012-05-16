package objects;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.05.2012
 * Time: 16:02:55
 * To change this template use File | Settings | File Templates.
 */
public class Cashiers {
    private int idCashiers;
    @Size(min = 5, message = "Длина не должна быть меньше 5 символов")
    private String fioCashiers;
    private int idSector;
    private String nameSector;
    @Size(max = 5, message = "Длина не должна быть больше 100 символов")
    private String comments;
    private Collection<PlanCashiers> planCashiers = new ArrayList<PlanCashiers>();


    public Cashiers() {
    }

    public Cashiers(int idCashiers, String fioCashiers, int idSector, String nameSector, String comments) {
        this.idCashiers = idCashiers;
        this.fioCashiers = fioCashiers;
        this.idSector = idSector;
        this.nameSector = nameSector;
        this.comments = comments;
    }

    public int getIdCashiers() {
        return idCashiers;
    }

    public void setIdCashiers(int idCashiers) {
        this.idCashiers = idCashiers;
    }

    public String getFioCashiers() {
        return fioCashiers;
    }

    public void setFioCashiers(String fioCashiers) {
        this.fioCashiers = fioCashiers;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Collection<PlanCashiers> getPlanCashiers() {
        return planCashiers;
    }

    public void setPlanCashiers(Collection<PlanCashiers> planCashiers) {
        this.planCashiers = planCashiers;
    }
}
