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
    private int countUploadMKTK;
    private int countNormMKTK;
    private int countUploadPKTK;
    private int countNormPKTK;
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

    public int getCountUploadMKTK() {
        return countUploadMKTK;
    }

    public void setCountUploadMKTK(int countUploadMKTK) {
        this.countUploadMKTK = countUploadMKTK;
    }

    public int getCountNormMKTK() {
        return countNormMKTK;
    }

    public void setCountNormMKTK(int countNormMKTK) {
        this.countNormMKTK = countNormMKTK;
    }

    public int getCountUploadPKTK() {
        return countUploadPKTK;
    }

    public void setCountUploadPKTK(int countUploadPKTK) {
        this.countUploadPKTK = countUploadPKTK;
    }

    public int getCountNormPKTK() {
        return countNormPKTK;
    }

    public void setCountNormPKTK(int countNormPKTK) {
        this.countNormPKTK = countNormPKTK;
    }
}
