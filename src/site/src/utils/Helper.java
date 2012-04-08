package utils;

import com.pengrad.rzd.report.Report;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 15.02.2012
 * Time: 13:19:31
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    public static enum segment {
        station, sector, direction
    }

    public static enum typeTerm {
        ALL, MKTK, PKTK
    }

    public final static int SEGMENT_DIRECTION = 0;
    public final static int SEGMENT_SECTOR = 1;
    public final static int SEGMENT_STATION = 2;

    public final static int FORMAT_XML = 0;
    public final static int FORMAT_TXT = 1;
    public final static int FORMAT_XLS = 2;


    public final static int TYPE_TERM_ALL = 0;
    public final static int TYPE_TERM_MKTK = 1;
    public final static int TYPE_TERM_PKTK = 2;


    public static Report.ReportSegment convertTypeSegment(int typeSegment) {
        switch (typeSegment) {
            case SEGMENT_DIRECTION: {
                return Report.ReportSegment.DIRECTION;
            }
            case SEGMENT_SECTOR: {
                return Report.ReportSegment.SECTOR;
            }
            case SEGMENT_STATION: {
                return Report.ReportSegment.STATION;
            }
            default: {
                return Report.ReportSegment.STATION;
            }
        }
    }

    public static Report.TerminalType convertTypeTerm(int typeTerm) {
        switch (typeTerm) {
            case TYPE_TERM_ALL: {
                return Report.TerminalType.ALL;
            }
            case TYPE_TERM_MKTK: {
                return Report.TerminalType.MKTK;
            }
            case TYPE_TERM_PKTK: {
                return Report.TerminalType.PKTK;
            }
            default: {
                return Report.TerminalType.ALL;
            }
        }
    }

    public static Date getCurrentDate() throws Exception {
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.clear(Calendar.MINUTE);
//        gc.clear(Calendar.SECOND);
//        gc.clear(Calendar.MILLISECOND);
        return new SimpleDateFormat("dd.MM.yyyy").parse(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
//        return gc.getTime();
    }

}
