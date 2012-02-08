package managers;

//http://code.google.com/p/rzd-piter/source/browse/trunk/src/db/rzd.sql

import objects.FileLoad;
import objects.Service;
import objects.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.02.2012
 * Time: 14:50:07
 * To change this template use File | Settings | File Templates.
 */

public class UploadManager {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void upload(InputStream is, Date timeCalcReport) throws Exception {
        JdbcTemplate db = new JdbcTemplate(dataSource);
        FileLoad fileLoad = parseXML(is);
        String query = "";
        int count = db.queryForInt("select count(FileId) from file where FileName=?", fileLoad.getNameXML());
        if (count > 0) {
            db.update("delete from ticket where FileId in (select FileId from file where FileName=?);", fileLoad.getNameXML());
            db.update("delete from file where FileName=?;", fileLoad.getNameXML());
        }
        query = "insert into file (\n" +
                /*1 */"FileName,\n" +
                /*2 */"NumTerm,\n" +
                /*3 */"SmenaNum,\n" +
                /*4 */"PlaceTerm,\n" +
                /*5 */"Month,\n" +
                /*6 */"TimeOpen,\n" +
                /*7 */"FirstTicket,\n" +
                /*8 */"Sum,\n" +
                /*9 */"TimeClose,\n" +
                /*10*/"NumTickets,\n" +
                /*11*/"LenTape,\n" +
                /*12*/"TypeTerm,\n" +
                /*13*/"SoftVersion,\n" +
                /*14*/"INN,\n" +
                /*15*/"FIO,\n" +
                /*16*/"CardOut,\n" +
                /*17*/"CardIn,\n" +
                /*18*/"Sup,\n" +
                /*19*/"Cancel,\n" +
                /*20*/"NumProc,\n" +
                /*21*/"SumProc,\n" +
                /*22*/"SumEKLZ,\n" +
                /*23*/"SCol,\n" +
                /*24*/"STax,\n" +
                /*25*/"Blank,\n" +
                /*26*/"SumRet,\n" +
                /*27*/"SumServ,\n" +
                /*28*/"NDSServ,\n" +
                /*29*/"TimeCalcReport\n" +
                ") values (\n" +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?\n" +
                ");";
        db.update(query,
                /*1 */fileLoad.getNameXML(),
                /*2 */fileLoad.getNumTerm(),
                /*3 */fileLoad.getSmenaNum(),
                /*4 */fileLoad.getPlaceTerm(),
                /*5 */fileLoad.getMonth(),
                /*6 */fileLoad.getTimeOpen(),
                /*7 */fileLoad.getFirstTicket(),
                /*8 */fileLoad.getSum(),
                /*9 */fileLoad.getTimeClose(),
                /*10*/fileLoad.getNumTickets(),
                /*11*/fileLoad.getLenTape(),
                /*12*/fileLoad.getTypeTerm(),
                /*13*/fileLoad.getSoftVersion(),
                /*14*/fileLoad.getInn(),
                /*15*/fileLoad.getFio(),
                /*16*/fileLoad.getCardOut(),
                /*17*/fileLoad.getCardIn(),
                /*18*/fileLoad.getSup(),
                /*19*/fileLoad.getCancel(),
                /*20*/fileLoad.getNumProc(),
                /*21*/fileLoad.getSumProc(),
                /*22*/fileLoad.getSumEklz(),
                /*23*/fileLoad.getSCol(),
                /*24*/fileLoad.getSTax(),
                /*25*/fileLoad.getBlank(),
                /*26*/fileLoad.getSumRet(),
                /*27*/fileLoad.getSumServ(),
                /*28*/fileLoad.getNDSServ(),
                /*29*/timeCalcReport
        );

        query = "insert into ticket(\n" +
                /*1 */"FileId,\n" +
                /*2 */"PerevozGkey,\n" +
                /*3 */"TrainCat,\n" +
                /*4 */"TPlanID,\n" +
                /*5 */"FromStationE,\n" +
                /*6 */"ToStationE,\n" +
                /*7 */"TicketTypeID,\n" +
                /*8 */"TicketTypeL,\n" +
                /*9 */"N,\n" +
                /*10*/"A,\n" +
                /*11*/"T,\n" +
                /*12*/"S,\n" +
                /*13*/"R,\n" +
                /*14*/"P,\n" +
                /*15*/"U,\n" +
                /*16*/"V,\n" +
                /*17*/"D,\n" +
                /*18*/"B,\n" +
                /*19*/"O,\n" +
                /*20*/"H,\n" +
                /*21*/"C,\n" +
                /*22*/"K,\n" +
                /*23*/"F,\n" +
                /*24*/"Z,\n" +
                /*25*/"E,\n" +
                /*26*/"DK,\n" +
                /*27*/"Col,\n" +
                /*28*/"Tax,\n" +
                /*29*/"NDS,\n" +
                /*30*/"Bl\n" +
                ") values (\n" +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?\n" +
                ");";
        int id = db.queryForInt("select LAST_INSERT_ID()");
        for (Ticket ticket : fileLoad.getTickets()) {
            db.update(query,
                    /*1 */id,
                    /*2 */ticket.getPerevozGkey(),
                    /*3 */ticket.getTrainCat(),
                    /*4 */ticket.getTPlanID(),
                    /*5 */ticket.getFromStation(),
                    /*6 */ticket.getToStation(),
                    /*7 */ticket.getTicketTypeID(),
                    /*8 */ticket.getTicketTypeL(),
                    /*9 */ticket.getN(),
                    /*10 */ticket.getA(),
                    /*11 */ticket.getT(),
                    /*12*/ticket.getS(),
                    /*13*/ticket.getR(),
                    /*14*/ticket.getP(),
                    /*15*/ticket.getU(),
                    /*16*/ticket.getV(),
                    /*17*/ticket.getD(),
                    /*18*/ticket.getB(),
                    /*19*/ticket.getO(),
                    /*20*/ticket.getH(),
                    /*21*/ticket.getC(),
                    /*22*/ticket.getK(),
                    /*23*/ticket.getF(),
                    /*24*/ticket.getZ(),
                    /*25*/ticket.getE(),
                    /*26*/ticket.getDk(),
                    /*27*/ticket.getCol(),
                    /*28*/ticket.getTax(),
                    /*29*/ticket.getNds(),
                    /*30*/ticket.getBi()
            );
        }

    }

    private FileLoad parseXML(InputStream is) throws Exception {
        FileLoad fileLoad = new FileLoad();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse(is);

        Element root = dom.getDocumentElement();
        fileLoad.setNameXML(root.getAttribute("Name"));
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
        fileLoad.setInn(eHead.getAttribute("INN"));
        fileLoad.setFio(eHead.getAttribute("FIO"));
        fileLoad.setCardOut(parseDouble(eHead.getAttribute("CardOut")));
        fileLoad.setCardIn(parseDouble(eHead.getAttribute("CardIn")));
        fileLoad.setSum(parseDouble(eHead.getAttribute("Sup")));
        fileLoad.setCancel(parseDouble(eHead.getAttribute("Cancel")));
        fileLoad.setNumProc(parseInt(eHead.getAttribute("NumProc")));
        fileLoad.setSumProc(parseDouble(eHead.getAttribute("SumProc")));
        fileLoad.setSumEklz(parseDouble(eHead.getAttribute("SumEKLZ")));
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
                                        ticket.setDk(eTicket.getAttribute("DK"));
                                        ticket.setCol(parseDouble(eTicket.getAttribute("Col")));
                                        ticket.setTax(parseDouble(eTicket.getAttribute("Tax")));
                                        ticket.setNds(parseDouble(eTicket.getAttribute("NDS")));
                                        ticket.setBi(parseInt(eTicket.getAttribute("BI")));
                                        ticket.setSn(eTicket.getAttribute("SN"));
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

    private Date parseDate(String s) throws Exception {
        try {
            return new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").parse(s);
        } catch (Exception e) {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        }
    }


    public static void main(String[] args) {
        try {
            File f = new File("G:\\проект РЖД\\2010-11\\2010-11");
            for (String fName : f.list()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(f.getAbsolutePath() + "\\" + fName);
                    new UploadManager().parseXML(fis);
                } catch (Exception e) {
                    System.out.println(f.getAbsolutePath() + "\\" + fName);
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        fis.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
