package test;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import sun.reflect.generics.tree.Tree;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.jar.Attributes;

public class XMLBuilder {

    public static Document buildXML(Map<String, String> headerAttributes, Collection<Map<String, String>> incoms, Collection<Map<String, String>> tickets, Collection<Map<String, String>> abonemets) throws ParserConfigurationException {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element rootElement = doc.createElement("form");
        Element headerElement = doc.createElement("header");
        Element moneyElement = doc.createElement("money");
        Element ticketsElement = doc.createElement("tickets");
        Element abonementsElement = doc.createElement("abonemets");
        doc.appendChild(rootElement);
        rootElement.appendChild(headerElement);
        headerElement.appendChild(moneyElement);
        headerElement.appendChild(ticketsElement);
        headerElement.appendChild(abonementsElement);
        rootElement.setAttribute("type", "3932");
        setElementAttributes(headerElement, headerAttributes);
        for (Map<String, String> incomAttrs : incoms) {
            Element incom = doc.createElement("incom");
            setElementAttributes(incom, incomAttrs);
            moneyElement.appendChild(incom);
        }
        for (Map<String, String> ticketAttrs : tickets) {
            Element ticket = doc.createElement("ticket");
            setElementAttributes(ticket, ticketAttrs);
            ticketsElement.appendChild(ticket);
        }
        for (Map<String, String> abonementAttrs : abonemets) {
            Element abonement = doc.createElement("abonement");
            setElementAttributes(abonement, abonementAttrs);
            abonementsElement.appendChild(abonement);
            Attr a = doc.createAttribute("asd");
            a.setValue("ASD");
            Attr t = doc.createAttribute("TTT");
            t.setValue("asdf");
            Attr b = doc.createAttribute("BBB");
            b.setValue("asdf");
            abonement.setAttributeNode(a);
            abonement.setAttributeNode(t);
            abonement.setAttributeNode(b);
            NamedNodeMap s =  abonement.getAttributes();
            for(int i = 0; i < s.getLength(); i++) {
                System.out.println(s.item(i).toString());
            }

        }



        return doc;
    }

    private static void setElementAttributes(Element element, Map<String, String> attrs) {
        for (Map.Entry<String, String> attr : attrs.entrySet()) {
            element.setAttribute(attr.getKey(), attr.getValue());
        }
    }

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        Comparator<String> cp = new Comparator<String>() {
            HashMap<String, Integer> weights = new HashMap<String, Integer>();

            {
                weights.put("type", 0);
                weights.put("subtype", 10);
                weights.put("sum", 20);
                weights.put("sumPay", 30);
            }

            public int compare(String o1, String o2) {
                Integer i1 = weights.get(o1);
                Integer i2 = weights.get(o2);
                if (i1 == null && i2 == null) return o1.compareTo(o2);
                if (i1 == null) return 1;
                if (i2 == null) return -1;
                return i1 - i2;
            }
        };
        TreeMap<String, String> headerAttributes = new TreeMap<String, String>(cp);
        headerAttributes.put("direction", "123");
        headerAttributes.put("sector", "123");
        headerAttributes.put("typeTerminal", "MKTK");
        ArrayList<Map<String, String>> incoms = new ArrayList<Map<String, String>>();
        TreeMap<String, String> incom = new TreeMap<String, String>(cp);
        incom.put("type", "all");
        incom.put("sum", "135.5");
        incom.put("sumPay", "123.3");
        incom.put("sumService", "45.5");
        incoms.add(incom);
        incom = new TreeMap<String, String>(cp);
        incom.put("type", "pay");
        incom.put("sum", "135.5");
        incom.put("sumPay", "123.3");
        incom.put("sumService", "45.5");
        incoms.add(incom);
        incom = new TreeMap<String, String>(cp);
        incom.put("type", "outpay");
        incom.put("sum", "135.5");
        incom.put("sumPay", "123.3");
        incom.put("sumService", "45.5");
        incoms.add(incom);
        ArrayList<Map<String, String>> tickets = new ArrayList<Map<String, String>>();
        TreeMap<String, String> ticket = new TreeMap<String, String>(cp);
        ticket.put("type", "all");
        ticket.put("sum", "135.5");
        ticket.put("sumPay", "123.3");
        tickets.add(ticket);
        ticket = new TreeMap<String, String>(cp);
        ticket.put("type", "one-way");
        ticket.put("sum", "135.5");
        ticket.put("sumPay", "123.3");
        tickets.add(ticket);
        ticket = new TreeMap<String, String>(cp);
        ticket.put("type", "round-trip");
        ticket.put("sum", "135.5");
        ticket.put("sumPay", "123.3");
        tickets.add(ticket);
        ArrayList<Map<String, String>> abonements = new ArrayList<Map<String, String>>();
        TreeMap<String, String> abonement = new TreeMap<String, String>(cp);
        abonement.put("type", "all");
        abonement.put("sum", "135.5");
        abonement.put("sumRZDWork", "123.3");
        abonements.add(abonement);
        abonement = new TreeMap<String, String>(cp);
        abonement.put("type", "everyday");
        abonement.put("subtype", "day-5");
        abonement.put("sum", "135.5");
        abonement.put("sumRZDWork", "123.3");
        abonements.add(abonement);
        abonement = new TreeMap<String, String>(cp);
        abonement.put("type", "workday");
        abonement.put("subtype", "month-1");
        abonement.put("sum", "135.5");
        abonement.put("sumRZDWork", "123.3");
        abonements.add(abonement);
        abonement = new TreeMap<String, String>(cp);
        abonement.put("type", "trip");
        abonement.put("subtype", "60-month-2");
        abonement.put("sum", "135.5");
        abonement.put("sumRZDWork", "123.3");
        abonements.add(abonement);
        Document doc = buildXML(headerAttributes, incoms, tickets, abonements);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.out);
        StreamResult resultFile = new StreamResult(new File("d:\\Docs\\projects\\RZD_Piter\\out.xml"));
        transformer.transform(source, result);
        transformer.transform(source, resultFile);
    }

}

