package com.pengrad.rzd.report;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public interface Report {

    enum ReportSegment {
        STATION, SECTOR, DIRECTION
    }

    enum TerminalType {
        MKTK, PKTK, ALL
    }

    void buildXml(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException;
    void buildText(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException;
    void buildXls(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException, InvalidFormatException;
    void buildXmlNonAggregate(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException;
}