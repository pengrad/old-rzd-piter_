package com.pengrad.rzd.report;

import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.XMLWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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

    public void buildXml(Date dateReport, ReportSegment segment, int segmentId, OutputStream os) throws IOException {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Report3932 report = Report3932.buildReport(template, dateReport, segment, segmentId);
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

    public void buildText(Date dateReport, ReportSegment segment, int segmentId, OutputStream os) throws IOException {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Report3932 report = Report3932.buildReport(template, dateReport, segment, segmentId);
        List<Map<String, Object>> listIncoms = report.getIncoms();
        List<Map<String, Object>> listTickets = report.getTickets();
        List<Map<String, Object>> listAbonements = report.getAbonements();
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> record : listIncoms) {
            sb.append(recordToString(record)).append('\n');
        }
        for (Map<String, Object> record : listTickets) {
            sb.append(recordToString(record)).append('\n');
        }
        for (Map<String, Object> record : listAbonements) {
            sb.append(recordToString(record)).append('\n');
        }
        os.write(sb.toString().getBytes("UTF-8"));
        os.flush();
    }

    private void addAttributesToElement(Element e, Map<String, Object> attrs) {
        for (Map.Entry<String, Object> attr : attrs.entrySet()) {
            e.addAttribute(attr.getKey(), attr.getValue() == null ? null : attr.getValue().toString());
        }
    }

    private StringBuilder recordToString(Map<String, Object> record) {
        StringBuilder sb = new StringBuilder();
        for (String header : Report3932.HEADERS) {
            Object o = record.get(header);
            if (o != null) {
                sb.append(Arrays.copyOf(o.toString().toCharArray(), 10));
                sb.append(' ');
            }
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        Report3932Builder report = new Report3932Builder();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        report.setDataSource(context.getBean("mainDataSource", DataSource.class));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GregorianCalendar gc = new GregorianCalendar(2012, 1, 12);
         report.buildXml(gc.getTime(), ReportSegment.DIRECTION, 0, System.out);
        //System.out.println(baos.toString());
        //   report.buildText(gc.getTime(), ReportSegment.DIRECTION, 0, new FileOutputStream("d:\\12.txt"));
        report.buildText(gc.getTime(), ReportSegment.DIRECTION, -12, System.out);
        List<Integer> stationId = new ArrayList<Integer>(0);//Collections.nCopies(1, 12);
        JdbcTemplate t =  new JdbcTemplate(context.getBean("mainDataSource", DataSource.class));
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(t);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("stations", stationId.size() > 0 ? stationId : "null");
        List<Map<String,Object>> l = namedParameterJdbcTemplate.queryForList("call t(:stations);", parameters);
        for(Map<String,Object> ll : l ) {
            System.out.println(ll.get("FileId"));
        }

    }
}
