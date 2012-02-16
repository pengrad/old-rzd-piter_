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

    public static final String[] HEADERS = new String[]{"type", "sum", "sumPay", "sumFedSoc", "sumFedNonSoc", "sumRegion", "sumWar", "sumStudy", "sumRZDPersonal", "sumRZDWork", "sumRZDService", "sumService"};

    public static final String QUERY_MONEY = "select 'm-all' type, ifnull(sum(t1.S),0) sum, ifnull(sum(t1.S),0) sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, 0 sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.a,0) <> 1\n" +
            "\t\n" +
            "union all\t\n" +
            "\n" +
            "select 'm-pay' type, ifnull(sum(t1.S),0) sum, null sumPay, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then t1.S else 0 end),0) sumFedSoc, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then t1.S else 0 end),0) sumFedNonSoc, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_region) then t1.S else 0 end),0) sumRegion, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_war) then t1.S else 0 end),0) sumWar,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_study) then t1.S else 0 end),0) sumStudy,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then t1.S else 0 end),0) sumRZDPersonal,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then t1.S else 0 end),0) sumRZDWork,\t\n" +
            "\tnull sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.a,0) <> 1\n" +
            "\tand ifnull(t1.TicketTypeL,0) not in (select disc_id from discount_rzdservice)\n" +
            "\t\n" +
            "union all\t\n" +
            "\n" +
            "select 'm-out-pay' type, ifnull(sum(t1.S),0) sum, null sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, \n" +
            "\tifnull(sum(t1.S),0) sumRZDService, \n" +
            "\tnull sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(a,0) <> 1\n" +
            "\tand ifnull(t1.TicketTypeL,0) in (select disc_id from discount_rzdservice);";

    public static final String QUERY_TICKETS = "select 't-all' type,\n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then 1 else 0 end),0) sumFedSoc, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then 1 else 0 end),0) sumFedNonSoc, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_region) then 1 else 0 end),0) sumRegion, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_war) then 1 else 0 end),0) sumWar,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_study) then 1 else 0 end),0) sumStudy,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then 1 else 0 end),0) sumRZDPersonal,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then 1 else 0 end),0) sumRZDWork,\t\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdservice) then 1 else 0 end),0) sumRZDService, \n" +
            "\t0 sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\t\n" +
            "union all\t\n" +
            "\n" +
            "select't-one-way' type,\n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, \n" +
            "\tnull sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\tand ifnull(t1.R,0) = 0\n" +
            "\t\n" +
            "union all\n" +
            "\n" +
            "select 't-round' type,\n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, \n" +
            "\tnull sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\tand ifnull(t1.R,0) = 1;";

    public static final String QUERY_ABONEMENTS = "select 'ab-all' type, \n" +
            "\t\tcount(t1.TicketId) as sum,\n" +
            "\t\tifnull(sum(case when t2.L is null then 1 else 0 end),0) as sumPay,\n" +
            "\t\tifnull(sum(case when t2.L is not null then 1 else 0 end),0) as sumRzdWork\n" +
            "from ticket t1\n" +
            "\tjoin file tf on t1.FileId=tf.FileId\n" +
            "\tleft join (select disc_id as L from discount_rzdwork) t2 on t1.TicketTypeL=t2.L\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand tf.PlaceTerm in (:stations)\n" +
            "\tand t1.TicketTypeID in (select a_type from temp_ab)\n" +
            "\n" +
            "union all \n" +
            "\n" +
            "select concat('ab-',cast(ta.a_type as char)) type,\n" +
            "\tifnull(t1.sum,0) as sum,\n" +
            "\tifnull(t1.sumPay,0) as sumPay,\n" +
            "\tifnull(t1.sumRzdWork,0) as sumRzdWork\n" +
            "from temp_ab ta left join \n" +
            "(select t1.TicketTypeId,\n" +
            "\t\tcount(t1.TicketId) as sum,\n" +
            "\t\tifnull(sum(case when t2.L is null then 1 else 0 end),0) as sumPay,\n" +
            "\t\tifnull(sum(case when t2.L is not null then 1 else 0 end),0) as sumRzdWork\n" +
            "from ticket t1 \n" +
            "\tjoin file tf on t1.FileId=tf.FileId\n" +
            "\tleft join (select disc_id as L from discount_rzdwork) t2 on t1.TicketTypeL=t2.L\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand tf.PlaceTerm in (:stations)\n" +
            "\tand t1.TicketTypeID in (select a_type from temp_ab)\n" +
            "group by t1.TicketTypeId\n" +
            ") t1 on ta.a_type=t1.TicketTypeId;";

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
        List<Integer> stationId;
        if (segment == ReportSegment.DIRECTION) {
            stationId = template.queryForList("select distinct t3.stat_id from direction t1 join sector t2 on t1.dir_id=t2.sect_dir_id join station_sector_cross t3 on t2.sect_id=t3.sect_id where dir_id = ?;",
                    Integer.class, segmentId);
        } else if (segment == ReportSegment.SECTOR) {
            stationId = template.queryForList("select distinct t2.stat_id from sector t1 join station_sector_cross t2 on t1.sect_id=t2.sect_id where t1.sect_id = ?;",
                    Integer.class, segmentId);
        } else { // считаем что это станция
            stationId = Collections.nCopies(1, segmentId);
        }
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("dBegin", dateBegin);
        parameters.addValue("dEnd", dateEnd);
        parameters.addValue("stations", stationId.size() > 0 ? stationId : "null");
        List<Map<String, Object>> listIncoms = namedParameterJdbcTemplate.queryForList(QUERY_MONEY, parameters);
        List<Map<String, Object>> listTickets = namedParameterJdbcTemplate.queryForList(QUERY_TICKETS, parameters);
        List<Map<String, Object>> listAbonements = namedParameterJdbcTemplate.queryForList(QUERY_ABONEMENTS, parameters);
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
        // вычисление даты окончания
        SimpleDateFormat db = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateReport);
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date dateReportEnd = c.getTime();
        String dateBegin = db.format(dateReport);
        String dateEnd = db.format(dateReportEnd);
        // получения списка станций в зависимости от сегмента
        List<Integer> stationId;
        if (segment == ReportSegment.DIRECTION) {
            stationId = template.queryForList("select distinct t3.stat_id from direction t1 join sector t2 on t1.dir_id=t2.sect_dir_id join station_sector_cross t3 on t2.sect_id=t3.sect_id where dir_id = ?;",
                    Integer.class, segmentId);
        } else if (segment == ReportSegment.SECTOR) {
            stationId = template.queryForList("select distinct t2.stat_id from sector t1 join station_sector_cross t2 on t1.sect_id=t2.sect_id where t1.sect_id = ?;",
                    Integer.class, segmentId);
        } else { // считаем что это станция
            stationId = Collections.nCopies(1, segmentId);
        }
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("dBegin", dateBegin);
        parameters.addValue("dEnd", dateEnd);
        parameters.addValue("stations", stationId.size() > 0 ? stationId : "null");
        List<Map<String, Object>> listIncoms = namedParameterJdbcTemplate.queryForList(QUERY_MONEY, parameters);
        List<Map<String, Object>> listTickets = namedParameterJdbcTemplate.queryForList(QUERY_TICKETS, parameters);
        List<Map<String, Object>> listAbonements = namedParameterJdbcTemplate.queryForList(QUERY_ABONEMENTS, parameters);
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
        for (String header : HEADERS) {
            Object o = record.get(header);
            if (o != null) {
                sb.append(Arrays.copyOf(o.toString().toCharArray(), 10));
                sb.append(' ');
            }
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        Report3932 report = new Report3932();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        report.setDataSource(context.getBean("mainDataSource", DataSource.class));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GregorianCalendar gc = new GregorianCalendar(2012, 1, 12);
         report.buildXml(gc.getTime(), ReportSegment.DIRECTION, 0, System.out);
        //System.out.println(baos.toString());
        //   report.buildText(gc.getTime(), ReportSegment.DIRECTION, 0, new FileOutputStream("d:\\12.txt"));
        report.buildText(gc.getTime(), ReportSegment.DIRECTION, -12, System.out);
    }
}
