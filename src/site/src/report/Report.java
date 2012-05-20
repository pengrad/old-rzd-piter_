package report;

import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 19.05.12
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public interface Report {
    public String getName();
    public void writeReport(OutputStream os) throws Exception;
}
