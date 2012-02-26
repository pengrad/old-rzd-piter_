package managers;

import objects.File;
import objects.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 21.02.2012
 * Time: 0:21:20
 * To change this template use File | Settings | File Templates.
 */
public class FileManager {
    private DataSource dataSource;
    private JdbcTemplate db;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        db = new JdbcTemplate(dataSource);
    }

    public Collection<File> getFiles(Date date, int idStation) {
        String query = "select\n" +
                /*0 */"FileID,\n" +
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
                /*29*/"TimeCreate\n" +
                "from file where date(TimeCreate)=? and PlaceTerm=?;";
        return db.query(query, new RowMapper<File>() {
            public File mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                File file = new File();
                file.setFileId(rs.getInt("FileID"));
                file.setFileName(rs.getString("FileName"));
                file.setNumTerm(rs.getInt("NumTerm"));
                file.setSmenaNum(rs.getInt("SmenaNum"));
                file.setPlaceTerm(rs.getInt("PlaceTerm"));
                file.setMonth(rs.getInt("Month"));
                file.setTimeOpen(rs.getTimestamp("TimeOpen"));
                file.setFirstTicket(rs.getInt("FirstTicket"));
                file.setSum(rs.getDouble("Sum"));
                file.setTimeClose(rs.getTimestamp("TimeClose"));
                file.setNumTickets(rs.getInt("NumTickets"));
                file.setLenTape(rs.getDouble("LenTape"));
                file.setTypeTerm(rs.getString("TypeTerm"));
                file.setSoftVersion(rs.getString("SoftVersion"));
                file.setINN(rs.getString("INN"));
                file.setFIO(rs.getString("FIO"));
                file.setCardOut(rs.getDouble("CardOut"));
                file.setCardIn(rs.getDouble("CardIn"));
                file.setSup(rs.getDouble("Sup"));
                file.setCancel(rs.getDouble("Cancel"));
                file.setNumProc(rs.getInt("NumProc"));
                file.setSumProc(rs.getDouble("SumProc"));
                file.setSumEKLZ(rs.getDouble("SumEklz"));
                file.setSCol(rs.getDouble("SCol"));
                file.setSTax(rs.getDouble("STax"));
                file.setBlank(rs.getInt("Blank"));
                file.setSumRet(rs.getDouble("SumRet"));
                file.setSumServ(rs.getDouble("SumServ"));
                file.setNDSServ(rs.getDouble("NDSServ"));
                file.setTimeCreate(rs.getTimestamp("TimeCreate"));
                file.setTickets(getTicketsByFileId(file.getFileId()));
                return file;
            }
        }, date, idStation);
    }

    public File getFileById(int fileId) {
        String query = "select\n" +
                /*0 */"FileID,\n" +
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
                /*29*/"TimeCreate\n" +
                "from file where FileID=?;";
        return db.queryForObject(query, new RowMapper<File>() {
            public File mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                File file = new File();
                file.setFileId(rs.getInt("FileID"));
                file.setFileName(rs.getString("FileName"));
                file.setNumTerm(rs.getInt("NumTerm"));
                file.setSmenaNum(rs.getInt("SmenaNum"));
                file.setPlaceTerm(rs.getInt("PlaceTerm"));
                file.setMonth(rs.getInt("Month"));
                file.setTimeOpen(rs.getTimestamp("TimeOpen"));
                file.setFirstTicket(rs.getInt("FirstTicket"));
                file.setSum(rs.getDouble("Sum"));
                file.setTimeClose(rs.getTimestamp("TimeClose"));
                file.setNumTickets(rs.getInt("NumTickets"));
                file.setLenTape(rs.getDouble("LenTape"));
                file.setTypeTerm(rs.getString("TypeTerm"));
                file.setSoftVersion(rs.getString("SoftVersion"));
                file.setINN(rs.getString("INN"));
                file.setFIO(rs.getString("FIO"));
                file.setCardOut(rs.getDouble("CardOut"));
                file.setCardIn(rs.getDouble("CardIn"));
                file.setSup(rs.getDouble("Sup"));
                file.setCancel(rs.getDouble("Cancel"));
                file.setNumProc(rs.getInt("NumProc"));
                file.setSumProc(rs.getDouble("SumProc"));
                file.setSumEKLZ(rs.getDouble("SumEklz"));
                file.setSCol(rs.getDouble("SCol"));
                file.setSTax(rs.getDouble("STax"));
                file.setBlank(rs.getInt("Blank"));
                file.setSumRet(rs.getDouble("SumRet"));
                file.setSumServ(rs.getDouble("SumServ"));
                file.setNDSServ(rs.getDouble("NDSServ"));
                file.setTimeCreate(rs.getTimestamp("TimeCreate"));
                file.setTickets(getTicketsByFileId(file.getFileId()));
                return file;
            }
        }, fileId);
    }

    public Collection<Ticket> getTicketsByFileId(int fileId) {
        String query = "select\n" +
                /*0 */"TicketId,\n" +
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
                /*30*/"Bl,\n" +
                /*31*/"SN,\n" +
                /*32*/"TimeCalcReport\n" +
                "from ticket where FileId=?;";
        return db.query(query, new RowMapper<Ticket>() {
            public Ticket mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("TicketId"));
                ticket.setFileId(rs.getInt("fileId"));
                ticket.setPerevozGkey(rs.getInt("PerevozGkey"));
                ticket.setTrainCat(rs.getString("TrainCat"));
                ticket.setTPlanID(rs.getInt("TPlanID"));
                ticket.setFromStation(rs.getInt("FromStationE"));
                ticket.setToStation(rs.getInt("ToStationE"));
                ticket.setTicketTypeID(rs.getInt("TicketTypeID"));
                ticket.setTicketTypeL(rs.getInt("TicketTypeL"));
                ticket.setN(rs.getInt("N"));
                ticket.setA(rs.getInt("A"));
                ticket.setT(rs.getTimestamp("T"));
                ticket.setS(rs.getDouble("S"));
                ticket.setR(rs.getInt("R"));
                ticket.setP(rs.getTimestamp("P"));
                ticket.setU(rs.getDouble("U"));
                ticket.setV(rs.getInt("V"));
                ticket.setD(rs.getString("D"));
                ticket.setB(rs.getString("B"));
                ticket.setO(rs.getString("O"));
                ticket.setH(rs.getString("H"));
                ticket.setC(rs.getString("C"));
                ticket.setK(rs.getString("K"));
                ticket.setF(rs.getString("F"));
                ticket.setZ(rs.getString("Z"));
                ticket.setE(rs.getInt("E"));
                ticket.setDK(rs.getString("DK"));
                ticket.setCol(rs.getDouble("Col"));
                ticket.setTax(rs.getDouble("Tax"));
                ticket.setNDS(rs.getDouble("NDS"));
                ticket.setBl(rs.getInt("Bl"));
                ticket.setSN(rs.getString("SN"));
                ticket.setTimeCalcReport(rs.getTimestamp("TimeCalcReport"));
                return ticket;
            }
        }, fileId);
    }

    public Ticket getTicketById(int ticketId) {
        String query = "select\n" +
                /*0 */"TicketId,\n" +
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
                /*30*/"Bl,\n" +
                /*31*/"SN,\n" +
                /*32*/"TimeCalcReport\n" +
                "from ticket where TicketId=?;";
        return db.queryForObject(query, new RowMapper<Ticket>() {
            public Ticket mapRow(java.sql.ResultSet rs, int i) throws SQLException {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("TicketId"));
                ticket.setFileId(rs.getInt("fileId"));
                ticket.setPerevozGkey(rs.getInt("PerevozGkey"));
                ticket.setTrainCat(rs.getString("TrainCat"));
                ticket.setTPlanID(rs.getInt("TPlanID"));
                ticket.setFromStation(rs.getInt("FromStationE"));
                ticket.setToStation(rs.getInt("ToStationE"));
                ticket.setTicketTypeID(rs.getInt("TicketTypeID"));
                ticket.setTicketTypeL(rs.getInt("TicketTypeL"));
                ticket.setN(rs.getInt("N"));
                ticket.setA(rs.getInt("A"));
                ticket.setT(rs.getTimestamp("T"));
                ticket.setS(rs.getDouble("S"));
                ticket.setR(rs.getInt("R"));
                ticket.setP(rs.getTimestamp("P"));
                ticket.setU(rs.getDouble("U"));
                ticket.setV(rs.getInt("V"));
                ticket.setD(rs.getString("D"));
                ticket.setB(rs.getString("B"));
                ticket.setO(rs.getString("O"));
                ticket.setH(rs.getString("H"));
                ticket.setC(rs.getString("C"));
                ticket.setK(rs.getString("K"));
                ticket.setF(rs.getString("F"));
                ticket.setZ(rs.getString("Z"));
                ticket.setE(rs.getInt("E"));
                ticket.setDK(rs.getString("DK"));
                ticket.setCol(rs.getDouble("Col"));
                ticket.setTax(rs.getDouble("Tax"));
                ticket.setNDS(rs.getDouble("NDS"));
                ticket.setBl(rs.getInt("Bl"));
                ticket.setSN(rs.getString("SN"));
                ticket.setTimeCalcReport(rs.getTimestamp("TimeCalcReport"));
                return ticket;
            }
        }, ticketId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(int fileId) throws Exception {
        db.update("delete from ticket where fileId=?;", fileId);
        db.update("delete from file where fileId=?;", fileId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTicket(int ticketId) throws Exception {
        db.update("delete from ticket where TicketId=?;", ticketId);
    }


        @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void addFile(final File fileLoad) throws Exception {
        JdbcTemplate db = new JdbcTemplate(dataSource);
        String query = "";
        int count = db.queryForInt("select count(FileId) from file where FileName=?", fileLoad.getFileName());
        if (count > 0) {
            db.update("delete from ticket where FileId in (select FileId from file where FileName=?);", fileLoad.getFileName());
            db.update("delete from file where FileName=?;", fileLoad.getFileName());
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
                /*28*/"NDSServ\n" +
                ") values (\n" +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?\n" +
                ");";
        db.update(query,
                /*1 */fileLoad.getFileName(),
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
                /*14*/fileLoad.getINN(),
                /*15*/fileLoad.getFIO(),
                /*16*/fileLoad.getCardOut(),
                /*17*/fileLoad.getCardIn(),
                /*18*/fileLoad.getSup(),
                /*19*/fileLoad.getCancel(),
                /*20*/fileLoad.getNumProc(),
                /*21*/fileLoad.getSumProc(),
                /*22*/fileLoad.getSumEKLZ(),
                /*23*/fileLoad.getSCol(),
                /*24*/fileLoad.getSTax(),
                /*25*/fileLoad.getBlank(),
                /*26*/fileLoad.getSumRet(),
                /*27*/fileLoad.getSumServ(),
                /*28*/fileLoad.getNDSServ()
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
                /*30*/"Bl,\n" +
                /*31*/"SN,\n" +
                /*32*/"TimeCalcReport\n" +
                ") values (\n" +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?\n" +
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
                    /*26*/ticket.getDK(),
                    /*27*/ticket.getCol(),
                    /*28*/ticket.getTax(),
                    /*29*/ticket.getNDS(),
                    /*30*/ticket.getBl(),
                    /*31*/ticket.getSN(),
                    /*32*/ticket.getTimeCalcReport()
            );
        }


    }


}
