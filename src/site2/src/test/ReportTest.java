package test;

import com.pengrad.rzd.report.Report;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 22:18:36
 * To change this template use File | Settings | File Templates.
 */
public class ReportTest implements Report{


    public void buildXml(Date dateReport, ReportSegment segment, int segmentId, OutputStream os) throws IOException {
      FileInputStream fif = new FileInputStream("c:\\K_B_00025381_001068_110822.xml");
        int i = 0;
        while ((i = fif.read()) != -1) {
            os.write(i);
        }
        fif.close();
        os.close();
    }

    public void buildText(Date dateReport, ReportSegment segment, int segmentId, OutputStream os) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void buildXml(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void buildText(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void buildXls(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException, InvalidFormatException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void buildXmlNonAggregate(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
