package parser;

import objects.File;
import objects.Ticket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 12.02.2012
 * Time: 12:11:17
 * To change this template use File | Settings | File Templates.
 */
public class ParserKFileXML {
    public File parse(InputStream is) throws Exception {
        File fileLoad = new File();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse(is);
        Element root = dom.getDocumentElement();
        fileLoad.setFileName(root.getAttribute("Name"));
        Element eHead = (Element) root.getElementsByTagName("Head").item(0);
        fileLoad.setNumTerm(parseInt(eHead.getAttribute("NumTerm")));
        fileLoad.setSmenaNum(parseInt(eHead.getAttribute("SmenaNum")));
        fileLoad.setPlaceTerm(parseInt(eHead.getAttribute("PlaceTerm")));
        fileLoad.setMonth(parseInt(eHead.getAttribute("Month")));
        fileLoad.setTimeOpen(parseDate(eHead.getAttribute("TimeOpen")));
        fileLoad.setFirstTicket(parseInt(eHead.getAttribute("FirstTicket")));
        fileLoad.setSum(parseDouble(eHead.getAttribute("Sum")));
        fileLoad.setTimeClose(parseDate(eHead.getAttribute("TimeClose")));
        fileLoad.setNumTickets(parseInt(eHead.getAttribute("NumTickets")));
        fileLoad.setLenTape(parseDouble(eHead.getAttribute("LenTape")));
        fileLoad.setTypeTerm(eHead.getAttribute("TypeTerm"));
        fileLoad.setSoftVersion(eHead.getAttribute("SoftVersion"));
        fileLoad.setINN(eHead.getAttribute("INN"));
        String fio = eHead.getAttribute("FIO").trim();
        int cashId = 0;
        try {
            cashId = Integer.parseInt(fio.substring(0, fio.indexOf(" ")));
            fio = fio.substring(fio.indexOf(" ") + 1, fio.length());
        } catch (Exception e) {

        }
        fileLoad.setFIO(fio);
        fileLoad.setCashierId(cashId);
        fileLoad.setCardOut(parseDouble(eHead.getAttribute("CardOut")));
        fileLoad.setCardIn(parseDouble(eHead.getAttribute("CardIn")));
        fileLoad.setSum(parseDouble(eHead.getAttribute("Sup")));
        fileLoad.setCancel(parseDouble(eHead.getAttribute("Cancel")));
        fileLoad.setNumProc(parseInt(eHead.getAttribute("NumProc")));
        fileLoad.setSumProc(parseDouble(eHead.getAttribute("SumProc")));
        fileLoad.setSumEKLZ(parseDouble(eHead.getAttribute("SumEKLZ")));
        fileLoad.setSCol(parseDouble(eHead.getAttribute("SCol")));
        fileLoad.setSTax(parseDouble(eHead.getAttribute("STax")));
        fileLoad.setBlank(parseInt(eHead.getAttribute("Blank")));
        fileLoad.setSumRet(parseDouble(eHead.getAttribute("SumRet")));
        fileLoad.setSumServ(parseDouble(eHead.getAttribute("SumServ")));
        fileLoad.setNDSServ(parseDouble(eHead.getAttribute("NDSServ")));
        if (root.getElementsByTagName("Perevoz") != null)
            for (int i = 0; i < root.getElementsByTagName("Perevoz").getLength(); i++) {
                Element ePerevoz = (Element) root.getElementsByTagName("Perevoz").item(i);
//                if (ePerevoz.getElementsByTagName("TrainCat") != null)
                for (int j = 0; j < ePerevoz.getElementsByTagName("TrainCat").getLength(); j++) {
                    Element eTrainCat = (Element) ePerevoz.getElementsByTagName("TrainCat").item(j);
//                        if (eTrainCat.getElementsByTagName("TPlan") != null)
                    for (int k = 0; k < eTrainCat.getElementsByTagName("TPlan").getLength(); k++) {
                        Element eTPlan = (Element) eTrainCat.getElementsByTagName("TPlan").item(k);
//                                if (eTPlan.getElementsByTagName("FromStation") != null)
                        for (int l = 0; l < eTPlan.getElementsByTagName("FromStation").getLength(); l++) {
                            Element eFromStation = (Element) eTPlan.getElementsByTagName("FromStation").item(l);
//                                        if (eFromStation.getElementsByTagName("ToStation") != null)
                            for (int z = 0; z < eFromStation.getElementsByTagName("ToStation").getLength(); z++) {
                                Element eToStation = (Element) eFromStation.getElementsByTagName("ToStation").item(z);
//                                                if (eToStation.getElementsByTagName("TicketType") != null)
                                for (int x = 0; x < eToStation.getElementsByTagName("TicketType").getLength(); x++) {
                                    Element eTicketType = (Element) eToStation.getElementsByTagName("TicketType").item(x);
//                                                        if (eTicketType.getElementsByTagName("Ticket") != null)
                                    for (int c = 0; c < eTicketType.getElementsByTagName("Ticket").getLength(); c++) {
                                        Element eTicket = (Element) eTicketType.getElementsByTagName("Ticket").item(c);
                                        Ticket ticket = new Ticket();
                                        ticket.setPerevozGkey(parseInt(ePerevoz.getAttribute("GKey")));
                                        ticket.setTrainCat(eTrainCat.getAttribute("Cat"));
                                        ticket.setTPlanID(parseInt(eTPlan.getAttribute("ID")));
                                        ticket.setFromStation(parseInt(eFromStation.getAttribute("E")));
                                        ticket.setToStation(parseInt(eToStation.getAttribute("E")));
                                        ticket.setTicketTypeID(parseInt(eTicketType.getAttribute("ID")));
                                        ticket.setTicketTypeL(parseInt(eTicketType.getAttribute("L")));
                                        ticket.setN(parseInt(eTicket.getAttribute("N")));
                                        ticket.setA(parseInt(eTicket.getAttribute("A")));
                                        ticket.setT(parseDate(eTicket.getAttribute("T")));
                                        ticket.setS(parseDouble(eTicket.getAttribute("S")));
                                        ticket.setR(parseInt(eTicket.getAttribute("R")));
                                        ticket.setP((eTicket.getAttribute("P") != null && eTicket.getAttribute("P").length() > 0 ? new SimpleDateFormat("dd.MM.yyyy").parse((eTicket.getAttribute("P"))) : null));
                                        ticket.setU(parseDouble(eTicket.getAttribute("U")));
                                        ticket.setV(parseInt(eTicket.getAttribute("V")));
                                        ticket.setD(eTicket.getAttribute("D"));
                                        ticket.setB(eTicket.getAttribute("B"));
                                        ticket.setO(eTicket.getAttribute("O"));
                                        ticket.setH(eTicket.getAttribute("H"));
                                        ticket.setC(eTicket.getAttribute("C"));
                                        ticket.setK(eTicket.getAttribute("K"));
                                        ticket.setF(eTicket.getAttribute("F"));
                                        ticket.setZ(eTicket.getAttribute("Z"));
                                        ticket.setE(parseInt(eTicket.getAttribute("E")));
                                        ticket.setDK(eTicket.getAttribute("DK"));
                                        ticket.setCol(parseDouble(eTicket.getAttribute("Col")));
                                        ticket.setTax(parseDouble(eTicket.getAttribute("Tax")));
                                        ticket.setNDS(parseDouble(eTicket.getAttribute("NDS")));
                                        ticket.setBl(parseInt(eTicket.getAttribute("BI")));
                                        ticket.setSN(eTicket.getAttribute("SN"));
                                        tickets.add(ticket);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        fileLoad.setTickets(tickets);
        return fileLoad;
    }

    private int parseInt(String s) {
        if (s != null && s.length() > 0) {
            return Integer.parseInt(s);
        } else {
            return 0;
        }
    }

    private double parseDouble(String s) {
        if (s != null && s.length() > 0) {
            return Double.parseDouble(s);
        } else {
            return 0;
        }
    }

    public Date parseDate(String s) throws Exception {
        try {
            return new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").parse(s);
        } catch (Exception e) {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        }
    }

}
