package com.pengrad.rzd.report;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report3932 {

    public static final String[] HEADERS = new String[]{"type", "sum", "sumPay", "sumFedSoc", "sumFedNonSoc", "sumRegion", "sumWar", "sumStudy", "sumRZDPersonal", "sumRZDWork", "sumRZDService", "sumService"};
    public static HashMap<String, String> HEADERS_MAP = new HashMap<String, String>(12);

    static {
        HEADERS_MAP.put("type", "type");
        HEADERS_MAP.put("sum", "al");
        HEADERS_MAP.put("sumPay", "pl");
        HEADERS_MAP.put("sumFedSoc", "fs");
        HEADERS_MAP.put("sumFedNonSoc", "fn");
        HEADERS_MAP.put("sumRegion", "rg");
        HEADERS_MAP.put("sumWar", "vs");
        HEADERS_MAP.put("sumStudy", "st");
        HEADERS_MAP.put("sumRZDPersonal", "rl");
        HEADERS_MAP.put("sumRZDWork", "rm");
        HEADERS_MAP.put("sumRZDService", "rs");
        HEADERS_MAP.put("sumService", "bg");
    }

    public static final String QUERY_MONEY = "select 'zd' type, ifnull(round(sum(t1.S),1),0) sum, ifnull(round(sum(t1.S),1),0) sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, 0 sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand ifnull(t1.a,0) <> 1\n" +
            "\t\n" +
            "union all\t\n" +
            "\n" +
            "select 'zn' type, ifnull(sum(round(t1.S,1)),0) sum, null sumPay, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fedsoc) then round(t1.S,1) else 0 end),0) sumFedSoc, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_fednonsoc) then round(t1.S,1) else 0 end),0) sumFedNonSoc, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_region) then round(t1.S,1) else 0 end),0) sumRegion, \n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_war) then round(t1.S,1) else 0 end),0) sumWar,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_study) then round(t1.S,1) else 0 end),0) sumStudy,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdpersonal) then round(t1.S,1) else 0 end),0) sumRZDPersonal,\n" +
            "\tifnull(sum(case when t1.TicketTypeL in (select disc_id from discount_rzdwork) then round(t1.S,1) else 0 end),0) sumRZDWork,\t\n" +
            "\tnull sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand ifnull(t1.a,0) <> 1\n" +
            "\tand ifnull(t1.TicketTypeL,0) not in (select disc_id from discount_rzdservice)\n" +
            "\t\n" +
            "union all\t\n" +
            "\n" +
            "select 'zp' type, ifnull(sum(round(t1.S,1)),0) sum, null sumPay, null sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, \n" +
            "\tifnull(sum(round(t1.S,1)),0) sumRZDService, \n" +
            "\tnull sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand ifnull(a,0) <> 1\n" +
            "\tand ifnull(t1.TicketTypeL,0) in (select disc_id from discount_rzdservice);";

    public static final String QUERY_TICKETS = "select 'br' type,\n" +
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
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\t\n" +
            "union all\t\n" +
            "\n" +
            "select'b1' type,\n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, \n" +
            "\tnull sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\tand ifnull(t1.R,0) = 0\n" +
            "\t\n" +
            "union all\n" +
            "\n" +
            "select 'b2' type,\n" +
            "\tcount(t1.TicketId) sum, \n" +
            "\tifnull(sum(case when t1.TicketTypeL not in (select disc_id from discount) then 1 else 0 end),0) sumPay, \n" +
            "\tnull sumFedSoc, null sumFedNonSoc, null sumRegion, null sumWar, null sumStudy, null sumRZDPersonal, null sumRZDWork, null sumRZDService, null sumService\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand ifnull(t1.A,0) <> 1\n" +
            "\tand ifnull(t1.R,0) = 1;";

    public static final String QUERY_ABONEMENTS = "select 'ba' type, \n" +
            "\t\tcount(t1.TicketId) as sum,\n" +
            "\t\tifnull(sum(case when tL.L is null then 1 else 0 end),0) as sumPay,\n" +
            "\t\tifnull(sum(case when tL.L is not null then 1 else 0 end),0) as sumRZDWork\n" +
            "from ticket t1\n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "\tleft join (select disc_id as L from discount_rzdwork) tL on t1.TicketTypeL=tL.L\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand t1.TicketTypeID in (select a_type from temp_ab)\n" +
            "\n" +
            "union all \n" +
            "\n" +
            "select ta.a_name type,\n" +
            "\tifnull(t1.sum,0) as sum,\n" +
            "\tifnull(t1.sumPay,0) as sumPay,\n" +
            "\tifnull(t1.sumRzdWork,0) as sumRZDWork\n" +
            "from temp_ab ta left join \n" +
            "(select t1.TicketTypeId,\n" +
            "\t\tcount(t1.TicketId) as sum,\n" +
            "\t\tifnull(sum(case when tL.L is null then 1 else 0 end),0) as sumPay,\n" +
            "\t\tifnull(sum(case when tL.L is not null then 1 else 0 end),0) as sumRZDWork\n" +
            "from ticket t1 \n" +
            "\tjoin file t2 on t1.FileId=t2.FileId\n" +
            "\tleft join (select disc_id as L from discount_rzdwork) tL on t1.TicketTypeL=tL.L\n" +
            "where t1.TimeCalcReport >= :dBegin and t1.TimeCalcReport < :dEnd\n" +
            "\tand t2.PlaceTerm in (:stations)\n" +
            "\tand (:terminal is null or t2.TypeTerm = :terminal)\n" +
            "\tand t1.TicketTypeID in (select a_type from temp_ab)\n" +
            "group by t1.TicketTypeId\n" +
            ") t1 on ta.a_type=t1.TicketTypeId;";

    public Report3932(List<Map<String, Object>> incoms, List<Map<String, Object>> tickets, List<Map<String, Object>> abonements, Date dateReport, int segmentId) {
        this.incoms = incoms;
        this.tickets = tickets;
        this.abonements = abonements;
        this.dateReport = dateReport;
        this.segmentId = segmentId;
    }

    private List<Map<String, Object>> incoms;
    private List<Map<String, Object>> tickets;
    private List<Map<String, Object>> abonements;
    private Date dateReport;
    private int segmentId;

    static Report3932 buildReport(JdbcTemplate template, Date dateReport, Report.ReportSegment segment, int segmentId, Report.TerminalType terminalType) {
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
        if (segment == Report.ReportSegment.DIRECTION) {
            stationId = template.queryForList("select distinct t3.stat_id from direction t1 join sector t2 on t1.dir_id=t2.sect_dir_id join station_sector_cross t3 on t2.sect_id=t3.sect_id where dir_id = ?;",
                    Integer.class, segmentId);
        } else if (segment == Report.ReportSegment.SECTOR) {
            stationId = template.queryForList("select distinct t2.stat_id from sector t1 join station_sector_cross t2 on t1.sect_id=t2.sect_id where t1.sect_id = ?;",
                    Integer.class, segmentId);
        } else { // считаем что это станция
            stationId = Collections.nCopies(1, segmentId);
        }
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("dBegin", dateBegin);
        parameters.addValue("dEnd", dateEnd);
        parameters.addValue("stations", stationId.size() > 0 ? stationId : null);
        parameters.addValue("terminal", getTerminalName(terminalType));
        List<Map<String, Object>> listIncoms = namedParameterJdbcTemplate.queryForList(QUERY_MONEY, parameters);
        List<Map<String, Object>> listTickets = namedParameterJdbcTemplate.queryForList(QUERY_TICKETS, parameters);
        List<Map<String, Object>> listAbonements = namedParameterJdbcTemplate.queryForList(QUERY_ABONEMENTS, parameters);
        return new Report3932(listIncoms, listTickets, listAbonements, dateReport, segmentId);
    }

    static Collection<Report3932> buildNonAggregateReports(JdbcTemplate template, Date dateReport, Report.ReportSegment segment, int segmentId, Report.TerminalType terminalType) {
        List<Integer> stations;
        if (segment == Report.ReportSegment.DIRECTION) {
            stations = template.queryForList("select distinct t3.stat_id from direction t1 join sector t2 on t1.dir_id=t2.sect_dir_id join station_sector_cross t3 on t2.sect_id=t3.sect_id where dir_id = ?;",
                    Integer.class, segmentId);
        } else if (segment == Report.ReportSegment.SECTOR) {
            stations = template.queryForList("select distinct t2.stat_id from sector t1 join station_sector_cross t2 on t1.sect_id=t2.sect_id where t1.sect_id = ?;",
                    Integer.class, segmentId);
        } else { // считаем что это станция
            stations = Collections.nCopies(1, segmentId);
        }
        ArrayList<Report3932> reports = new ArrayList<Report3932>(stations.size());
        for(int stationId : stations) {
            reports.add(buildReport(template, dateReport, Report.ReportSegment.STATION, stationId, terminalType));
        }        
        return reports;
    }

    private static String getTerminalName(Report.TerminalType terminalType) {
        if (terminalType == Report.TerminalType.MKTK) return "МКТК";
        if (terminalType == Report.TerminalType.PKTK) return "PKTK";
        return null;
    }

    public List<Map<String, Object>> getIncoms() {
        return incoms;
    }

    public List<Map<String, Object>> getTickets() {
        return tickets;
    }

    public List<Map<String, Object>> getAbonements() {
        return abonements;
    }

    public Date getDateReport() {
        return dateReport;
    }

    public int getSegmentId() {
        return segmentId;
    }
}
