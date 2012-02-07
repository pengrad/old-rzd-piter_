package objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.02.2012
 * Time: 14:51:05
 * To change this template use File | Settings | File Templates.
 */
public class FileLoad {
    private String nameXML;     // 'имя XML документа',
    private int numTerm;        // 'номер терминала',
    private int smenaNum;       // 'номер смены',
    private int placeTerm;      // 'экспресс код терминала',
    private int month;          // 'номер месяца',
    private Date timeOpen;      // 'дата и время открытия смены',
    private int firstTicket;    // 'номер первой операйии',
    private double sum;         // 'сумма смены',
    private Date timeClose;     // 'дата и время закрытия смены',
    private int numTickets;     // 'количество операций',
    private double lenTape;        // 'длина ленты',
    private String typeTerm;    // 'тип терминала',
    private String softVersion; // 'Версия ПО терминал'
    private String inn;         // 'ИНН',
    private String fio;         // 'ФИО',
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
    private ArrayList<Service> services=new ArrayList<Service>(0);
    private ArrayList<Ticket> tickets=new ArrayList<Ticket>(0);

    public String getNameXML() {
        return nameXML;
    }

    public void setNameXML(String nameXML) {
        this.nameXML = nameXML;
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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

    public double getSumEklz() {
        return sumEklz;
    }

    public void setSumEklz(double sumEklz) {
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

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
