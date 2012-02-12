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

public class Report3932 implements Report {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String QUERY_MONEY = "select null class, 'all' type, null subtype, sum(t1.S) sum, sum(t1.S) sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, 0 sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.a,0) <> 1\n" +
            "union all\t\n" +
            "select null class, 'pay' type, null subtype, sum(t1.S) sum, null sumPay, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then t1.S else 0 end) sumFedSoc, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then t1.S else 0 end) sumFedNonSoc, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_region) then t1.S else 0 end) sumRegion, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_war) then t1.S else 0 end) sumWar,\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_study) then t1.S else 0 end) sumStudy,\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then t1.S else 0 end) sumRZDPersonal,\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then t1.S else 0 end) sumRZDWork,\t\n" +
            "\tnull sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.a,0) <> 1\n" +
            "\tand ifnull(t1.TicketTypeL,0) not in (select disc_id from discount_rzdservice)\n" +
            "union all\t\n" +
            "select null class, 'out-pay' type, null subtype, ifnull(sum(t1.S),0) sum, null sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, \n" +
            "\tifnull(sum(t1.S),0) sumRZDService, \n" +
            "\tnull sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(a,0) <> 1\n" +
            "\tand ifnull(t1.TicketTypeL,0) in (select disc_id from discount_rzdservice);";

    public static final String QUERY_TICKETS = "select null class, 'all' type, null subtype, \n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tsum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end) sumPay, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then 1 else 0 end) sumFedSoc, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then 1 else 0 end) sumFedNonSoc, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_region) then 1 else 0 end) sumRegion, \n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_war) then 1 else 0 end) sumWar,\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_study) then 1 else 0 end) sumStudy,\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then 1 else 0 end) sumRZDPersonal,\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then 1 else 0 end) sumRZDWork,\t\n" +
            "\tsum(case when t1.TicketTypeL in (select disc_id from discount_rzdservice) then 1 else 0 end) sumRZDService, \n" +
            "\t0 sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "union all\t\n" +
            "select null class, 'one-way' type, null subtype, \n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tsum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end) sumPay, \n" +
            "\tnull sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\tand ifnull(t1.R,0) = 0\n" +
            "union all\n" +
            "select null class, 'round-trip' type, null subtype,\n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, \n" +
            "\tnull sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\tand ifnull(t1.R,0) = 1;";

    public void buildXml(Date dateReport, ReportSegment segment, int segmentId, OutputStream os) throws IOException {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        // вычисление даты окончания
        SimpleDateFormat db = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateReport);
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date dateReportEnd = c.getTime();
        String dateBegin = db.format(dateReport);
        String dateEnd = db.format(dateReportEnd);
        // получения списка станций в зависимости от сегмента
        Object stationId;
        if (segment == ReportSegment.DIRECTION) {
            stationId = template.queryForList("select distinct t3.stat_id from direction t1 join sector t2 on t1.dir_id=t2.sect_dir_id join station_sector_cross t3 on t2.sect_id=t3.sect_id where dir_id = ?;",
                    Integer.class, segmentId);
        } else if (segment == ReportSegment.SECTOR) {
            stationId = template.queryForList("select distinct t2.stat_id from sector t1 join station_sector_cross t2 on t1.sect_id=t2.sect_id where t1.sect_id = ?;",
                    Integer.class, segmentId);
        } else { // считаем что это станция
            stationId = segmentId;
        }
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("dBegin", dateBegin);
        parameters.addValue("dEnd", dateEnd);
        parameters.addValue("stations", stationId);
        List<Map<String, Object>> listIncoms = namedParameterJdbcTemplate.queryForList(QUERY_MONEY, parameters);
        List<Map<String, Object>> listTickets = namedParameterJdbcTemplate.queryForList(QUERY_TICKETS, parameters);
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


    private void addAttributesToElement(Element e, Map<String, Object> attrs) {
        for (Map.Entry<String, Object> attr : attrs.entrySet()) {
            e.addAttribute(attr.getKey(), attr.getValue() == null ? null : attr.getValue().toString());
        }
    }

    public static void main(String[] args) throws IOException {
        Report3932 report = new Report3932();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        report.setDataSource(context.getBean("mainDataSource", DataSource.class));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GregorianCalendar gc = new GregorianCalendar(2012, 1, 12);
        report.buildXml(gc.getTime(), ReportSegment.DIRECTION, 0, baos);
        System.out.println(baos.toString());
    }
}
