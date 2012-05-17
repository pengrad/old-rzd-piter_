package objects;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import utils.Helper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.02.2012
 * Time: 14:51:05
 * To change this template use File | Settings | File Templates.
 */
public class File {
    private int fileId;      // 'ID',
//    @NotNull(message = "Фамилия должна быть задана")
//    @Size(min = 3)
    @NotEmpty(message = "Значение должно быть задано")
    @Size(min = 10, message = "Длина не должна быть меньше 10")
    private String fileName;     // 'имя XML документа',
//    @NotEmpty(message = "Значение должно быть задано")
    @Range(min = 10, max = 1000, message = "Должно быть....")
    private int numTerm;        // 'номер терминала',
    private int smenaNum;       // 'номер смены',
    private int placeTerm;      // 'экспресс код терминала',
    private int month;          // 'номер месяца',
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timeOpen;      // 'дата и время открытия смены',
    private int firstTicket;    // 'номер первой операйии',
    private double sum;         // 'сумма смены',
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timeClose;     // 'дата и время закрытия смены',
    private int numTickets;     // 'количество операций',
    private double lenTape;        // 'длина ленты',
    private String typeTerm;    // 'тип терминала',
    private String softVersion; // 'Версия ПО терминал'
    @Range(min = 10, max = 1000, message = "Должно быть....")
    private String INN;         // 'ИНН',
    private String FIO;         // 'ФИО',
    private int cashierId;   // 'Код кассира',
    private double cardOut;     // 'Сумма принятого залог',
    private double cardIn;      // 'Сумма возвращенного залог',
    private double sup;         // 'Сумма доплаты льготных билетов',
    private double cancel;      // 'Сумма анулированных билетов',
    private int numProc;        // 'количество билетов для p97',
    private double sumProc;     // 'сумма билетов для p97',
    private double sumEklz;     // 'сумма в ЭКЛЗ',
    private double sCol;        // 'сумма сборов за оформление ППД',
    private double sTax;        // 'НДС за оформление ППД',
    private int blank;          // 'расход бланков',
    private double sumRet;      // 'сумма возврвта по аворийной квитанции',
    private double sumServ;     // 'сумма услуги',
    private double NDSServ;     // 'НДС услуги',
    private Date timeCreate;
    private Collection<Service> services = new ArrayList<Service>(0);
    private Collection<Ticket> tickets = new ArrayList<Ticket>(0);

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNumTerm() {
        return numTerm;
    }

    public void setNumTerm(int numTerm) {
        this.numTerm = numTerm;
    }

    public int getSmenaNum() {
        return smenaNum;
    }

    public void setSmenaNum(int smenaNum) {
        this.smenaNum = smenaNum;
    }

    public int getPlaceTerm() {
        return placeTerm;
    }

    public void setPlaceTerm(int placeTerm) {
        this.placeTerm = placeTerm;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    public int getFirstTicket() {
        return firstTicket;
    }

    public void setFirstTicket(int firstTicket) {
        this.firstTicket = firstTicket;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public double getLenTape() {
        return lenTape;
    }

    public void setLenTape(double lenTape) {
        this.lenTape = lenTape;
    }

    public String getTypeTerm() {
        return typeTerm;
    }

    public void setTypeTerm(String typeTerm) {
        this.typeTerm = typeTerm;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String inn) {
        this.INN = inn;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String fio) {
        this.FIO = fio;
    }

    public double getCardOut() {
        return cardOut;
    }

    public void setCardOut(double cardOut) {
        this.cardOut = cardOut;
    }

    public double getCardIn() {
        return cardIn;
    }

    public void setCardIn(double cardIn) {
        this.cardIn = cardIn;
    }

    public double getSup() {
        return sup;
    }

    public void setSup(double sup) {
        this.sup = sup;
    }

    public double getCancel() {
        return cancel;
    }

    public void setCancel(double cancel) {
        this.cancel = cancel;
    }

    public int getNumProc() {
        return numProc;
    }

    public void setNumProc(int numProc) {
        this.numProc = numProc;
    }

    public double getSumProc() {
        return sumProc;
    }

    public void setSumProc(double sumProc) {
        this.sumProc = sumProc;
    }

    public double getSumEKLZ() {
        return sumEklz;
    }

    public void setSumEKLZ(double sumEklz) {
        this.sumEklz = sumEklz;
    }

    public double getSCol() {
        return sCol;
    }

    public void setSCol(double sCol) {
        this.sCol = sCol;
    }

    public double getSTax() {
        return sTax;
    }

    public void setSTax(double sTax) {
        this.sTax = sTax;
    }

    public int getBlank() {
        return blank;
    }

    public void setBlank(int blank) {
        this.blank = blank;
    }

    public double getSumRet() {
        return sumRet;
    }

    public void setSumRet(double sumRet) {
        this.sumRet = sumRet;
    }

    public double getSumServ() {
        return sumServ;
    }

    public void setSumServ(double sumServ) {
        this.sumServ = sumServ;
    }

    public double getNDSServ() {
        return NDSServ;
    }

    public void setNDSServ(double NDSServ) {
        this.NDSServ = NDSServ;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public Helper.typeTerm getTypeTerminal() {
        if (typeTerm.equals("МКТК"))
            return Helper.typeTerm.MKTK;
        else if (typeTerm.equals("PKTK"))
            return Helper.typeTerm.PKTK;
        else return Helper.typeTerm.UNDEFINED;
    }
}
