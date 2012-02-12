package test;


import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.bean.BeanElement;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sun.util.calendar.Gregorian;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestDB {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws ParseException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DataSource ds = context.getBean("mainDataSource", DataSource.class);
        SimpleDateFormat db = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date date2 = c.getTime();
        System.out.println(date2);
        String sDate = db.format(date);
        JdbcTemplate template = new JdbcTemplate(ds);
        List<Integer> file = template.queryForList("select FileId from file where TimeCalcReport < ?;", Integer.class, db.format(date));
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("names", file);
        List<Integer> ticket = namedParameterJdbcTemplate.queryForList("select ticketId from ticket where FileId in (:names)",parameters, Integer.class);
        System.out.println(ticket.size());


//        System.out.println(i);
        DOMDocument d = new DOMDocument();
        Element e = DOMDocumentFactory.getInstance().createElement("root");
        d.add(e);
        e.addAttribute("cccc", "1");
        e.addAttribute("aaaa", "2");
        e.addAttribute("bbbb", "3");
        e.addAttribute("A", null);
        BeanElement b = new BeanElement(new QName("el"), new A("123123"));
        e.add(b);
        System.out.println(b.attributes().size());
        XMLWriter w = new XMLWriter(System.out);
        w.write(d);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", "11111");
        map.put("2", "22222");
        map.put("3", "33333");
        TreeMap<String, String> tmap = new TreeMap<String, String>();
        tmap.putAll(map);
        for(String i : tmap.values()) {
            System.out.println(i);
        }
        System.out.println("_________________");
        map.remove("2");
        tmap.remove("1");
        for(String i : tmap.values()) {
            System.out.println(i);
        }
        System.out.println("_________________");
        for(String i : map.values()) {
            System.out.println(i);
        }


    }

    static class A {

        public String c = "asd";

        A(String c) {
            this.c = c;
        }

        public String getC() {
            return "asd";
        }

        public void setC(String c) {
            this.c = c;
        }
    }

}