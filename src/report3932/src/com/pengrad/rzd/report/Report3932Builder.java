package com.pengrad.rzd.report;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.XMLWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Report3932Builder implements Report {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void buildXml(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Report3932 report = Report3932.buildReport(template, dateReport, segment, segmentId, terminal);
        List<Map<String, Object>> listIncoms = report.getIncoms();
        List<Map<String, Object>> listTickets = report.getTickets();
        List<Map<String, Object>> listAbonements = report.getAbonements();
        DOMDocument doc = new DOMDocument();
        Element root = doc.addElement("form");
        root.addAttribute("type", "3932");
        Element header = root.addElement("header");
        Element money = header.addElement("money");
        Element tickets = header.addElement("tickets");
        for (Map<String, Object> attr : listIncoms) {
            addAttributesToElement(money.addElement("incom"), attr);
        }
        for (Map<String, Object> attr : listTickets) {
            addAttributesToElement(tickets.addElement("ticket"), attr);
        }
        XMLWriter w = new XMLWriter(os);
        w.write(doc);
    }

    public void buildText(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Report3932 report = Report3932.buildReport(template, dateReport, segment, segmentId, terminal);
        List<Map<String, Object>> listIncoms = report.getIncoms();
        List<Map<String, Object>> listTickets = report.getTickets();
        List<Map<String, Object>> listAbonements = report.getAbonements();
        int fieldLength = 10;
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.copyOf(segment.toString().toLowerCase().toCharArray(), fieldLength)).append(' ').append(Arrays.copyOf(String.valueOf(segmentId).toCharArray(), fieldLength)).append("\n");
        sb.append("terminal  ").append(' ').append(Arrays.copyOf(terminal.toString().toLowerCase().toCharArray(), fieldLength)).append("\n");
        sb.append("date      ").append(' ').append(Arrays.copyOf(report.getDateReportString().toCharArray(), fieldLength)).append("\n");
        for (Map<String, Object> record : listIncoms) {
            sb.append(recordToString(record, fieldLength)).append('\n');
        }
        for (Map<String, Object> record : listTickets) {
            sb.append(recordToString(record, fieldLength)).append('\n');
        }
        for (Map<String, Object> record : listAbonements) {
            sb.append(recordToString(record, fieldLength)).append('\n');
        }
        os.write(sb.toString().getBytes("UTF-8"));
        os.flush();
    }

    public void buildXls(Date dateReport, ReportSegment segment, int segmentId, TerminalType terminal, OutputStream os) throws IOException, InvalidFormatException {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Report3932 report = Report3932.buildReport(template, dateReport, segment, segmentId, terminal);
        List<Map<String, Object>> listIncoms = report.getIncoms();
        List<Map<String, Object>> listTickets = report.getTickets();
        List<Map<String, Object>> listAbonements = report.getAbonements();

        StringBuilder header = new StringBuilder();
        if (segment == ReportSegment.DIRECTION) header.append("По направлению ");
        if (segment == ReportSegment.SECTOR) header.append("По участку ");
        if (segment == ReportSegment.STATION) header.append("По станции ");
        header.append(segmentId).append(", ");
        if (terminal != TerminalType.ALL) header.append("тип терминала ").append(terminal.toString()).append(", ");
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
        header.append("за ").append(f.format(report.getDateReport()));
        InputStream inp = new FileInputStream("res/template3932.xls");
        Workbook wb = WorkbookFactory.create(inp);
        Sheet s = wb.getSheetAt(0);
        s.getRow(1).getCell(0).setCellValue(header.toString());

        Iterator<Map<String, Object>> it = listIncoms.iterator();
        Map<String, Object> m;
        //доходы
        m = it.next();
        s.getRow(5).getCell(2).setCellValue(m.get("sum").toString());
        s.getRow(5).getCell(3).setCellValue(m.get("sumPay").toString());
        s.getRow(5).getCell(12).setCellValue(m.get("sumService").toString());

        //начислено платежей
        m = it.next();
        s.getRow(6).getCell(2).setCellValue(m.get("sum").toString());
        s.getRow(6).getCell(4).setCellValue(m.get("sumFedSoc").toString());
        s.getRow(6).getCell(5).setCellValue(m.get("sumFedNonSoc").toString());
        s.getRow(6).getCell(6).setCellValue(m.get("sumRegion").toString());
        s.getRow(6).getCell(7).setCellValue(m.get("sumWar").toString());
        s.getRow(6).getCell(8).setCellValue(m.get("sumStudy").toString());
        s.getRow(6).getCell(9).setCellValue(m.get("sumRZDPersonal").toString());
        s.getRow(6).getCell(10).setCellValue(m.get("sumRZDWork").toString());

        //выпадающие доходы
        m = it.next();
        s.getRow(7).getCell(2).setCellValue(m.get("sum").toString());
        s.getRow(7).getCell(11).setCellValue(m.get("sumRZDService").toString());

        it = listTickets.iterator();
        //все билеты
        m = it.next();
        s.getRow(8).getCell(2).setCellValue(Integer.valueOf(m.get("sum").toString()));
        s.getRow(8).getCell(3).setCellValue(Integer.valueOf(m.get("sumPay").toString()));
        s.getRow(8).getCell(4).setCellValue(Integer.valueOf(m.get("sumFedSoc").toString()));
        s.getRow(8).getCell(5).setCellValue(Integer.valueOf(m.get("sumFedNonSoc").toString()));
        s.getRow(8).getCell(6).setCellValue(Integer.valueOf(m.get("sumRegion").toString()));
        s.getRow(8).getCell(7).setCellValue(Integer.valueOf(m.get("sumWar").toString()));
        s.getRow(8).getCell(8).setCellValue(Integer.valueOf(m.get("sumStudy").toString()));
        s.getRow(8).getCell(9).setCellValue(Integer.valueOf(m.get("sumRZDPersonal").toString()));
        s.getRow(8).getCell(10).setCellValue(Integer.valueOf(m.get("sumRZDWork").toString()));
        s.getRow(8).getCell(11).setCellValue(Integer.valueOf(m.get("sumRZDService").toString()));
        s.getRow(8).getCell(12).setCellValue(Integer.valueOf(m.get("sumService").toString()));

        //билеты туда
        m = it.next();
        s.getRow(9).getCell(2).setCellValue(Integer.valueOf(m.get("sum").toString()));
        s.getRow(9).getCell(3).setCellValue(Integer.valueOf(m.get("sumPay").toString()));

        //билеты обратно
        m = it.next();
        s.getRow(10).getCell(2).setCellValue(Integer.valueOf(m.get("sum").toString()));
        s.getRow(10).getCell(3).setCellValue(Integer.valueOf(m.get("sumPay").toString()));

        //абонементы
        it = listAbonements.iterator();
        for (int i = 11; i <= 50; i++) {
            m = it.next();
            s.getRow(i).getCell(2).setCellValue(Integer.valueOf(m.get("sum").toString()));
            s.getRow(i).getCell(3).setCellValue(Integer.valueOf(m.get("sumPay").toString()));
            s.getRow(i).getCell(10).setCellValue(Integer.valueOf(m.get("sumRZDWork").toString()));
        }
        wb.write(os);
    }

    private void addAttributesToElement(Element e, Map<String, Object> attrs) {
        for (Map.Entry<String, Object> attr : attrs.entrySet()) {
            e.addAttribute(attr.getKey(), attr.getValue() == null ? null : attr.getValue().toString());
        }
    }

    private StringBuilder recordToString(Map<String, Object> record, int fieldLength) {
        StringBuilder sb = new StringBuilder();
        for (String header : Report3932.HEADERS) {
            Object o = record.get(header);
            if (o != null) {
                sb.append(Arrays.copyOf(o.toString().toCharArray(), fieldLength)).append(' ');
            }
        }
        return sb;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Report3932Builder report = new Report3932Builder();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        report.setDataSource(context.getBean("mainDataSource", DataSource.class));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GregorianCalendar gc = new GregorianCalendar(2012, 1, 12);
         report.buildXml(gc.getTime(), ReportSegment.DIRECTION, 0, TerminalType.ALL, System.out);
        report.buildText(gc.getTime(), ReportSegment.DIRECTION, 123, TerminalType.ALL, System.out);
        report.buildXls(gc.getTime(), ReportSegment.DIRECTION, 123, TerminalType.ALL, new FileOutputStream("d:\\tt.xls"));
    }
}
