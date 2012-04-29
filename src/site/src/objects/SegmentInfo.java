package objects;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 20:37:13
 * To change this template use File | Settings | File Templates.
 */
public class SegmentInfo {
    private int id;
    private String name;
    private int countUploadMKTK;
    private int countNormMKTK;
    private int countUploadPKTK;
    private int countNormPKTK;
    private int countUploadABP09;
    private int countNormABP09;
    private int countUploadSPKI102M;
    private int countNormSPKI102M;


    public SegmentInfo() {
    }

    public SegmentInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public int getCountUploadABP09() {
        return countUploadABP09;
    }

    public void setCountUploadABP09(int countUploadABP09) {
        this.countUploadABP09 = countUploadABP09;
    }

    public int getCountNormABP09() {
        return countNormABP09;
    }

    public void setCountNormABP09(int countNormABP09) {
        this.countNormABP09 = countNormABP09;
    }

    public int getCountUploadSPKI102M() {
        return countUploadSPKI102M;
    }

    public void setCountUploadSPKI102M(int countUploadSPKI102M) {
        this.countUploadSPKI102M = countUploadSPKI102M;
    }

    public int getCountNormSPKI102M() {
        return countNormSPKI102M;
    }

    public void setCountNormSPKI102M(int countNormSPKI102M) {
        this.countNormSPKI102M = countNormSPKI102M;
    }
}
