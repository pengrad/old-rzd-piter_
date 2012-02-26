package objects;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 20:37:13
 * To change this template use File | Settings | File Templates.
 */
public class MonitoringSegment {
    private int id;
    private String name;
    private boolean status;
    private int countExist;
    private int countNorm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCountExist() {
        return countExist;
    }

    public void setCountExist(int countExist) {
        this.countExist = countExist;
    }

    public int getCountNorm() {
        return countNorm;
    }

    public void setCountNorm(int countNorm) {
        this.countNorm = countNorm;
    }
}
