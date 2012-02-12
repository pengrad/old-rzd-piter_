package com.pengrad.rzd.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public interface Report {

    enum ReportSegment {
        STATION, SECTOR, DIRECTION
    }

    void buildXml(Date dateReport, ReportSegment segment, int segmentId, OutputStream os) throws IOException;
}
