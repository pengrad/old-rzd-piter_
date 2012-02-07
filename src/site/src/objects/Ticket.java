package objects;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 05.02.2012
 * Time: 14:51:30
 * To change this template use File | Settings | File Templates.
 */
public class Ticket {
    private int perevozGkey;         // 'код в АСОКУПЭ-Л',
    private String trainCat;       // 'категория поезда',
    private int tPlanID;      // 'ID в БД АСОКУПЭ-Л',
    private int fromStation;  // 'Экспресс код',
    private int toStation;    // 'Экспресс код',
    private int ticketTypeID; // 'ID типа билета',
    private int ticketTypeL;            // 'Код льготы',
    private int n;            // 'Номер билета / квитанции /операции',
    private int a;            // 'Признак аннулирования =1',
    private Date t;           // 'Время операции',
    private double s;         // 'Сумма оплаты',
    private int r;            // 'Признак туда и обратно',
    private Date p;           // 'Дата предварительного проезда',
    private double u;         // 'Сумма доплаты',
    private int v;            // 'Тип документа на льготу',
    private String d;         // 'Номер льготного документа / ЭТТ с БСК',
    private String b;         // 'Код билетного бюро',
    private String o;         // 'Код организации',
    private String h;         // 'Категория пассажира',
    private String c;         // 'Номер ТК / СК / ЭТТ',
    private String k;         // 'Кристалл ТК / СК / ЭТТ',
    private String f;         // 'Кристалл ТК / СК / ЭТТ',
    private String z;         // 'Номер аннулированного документа',
    private int e;            // 'Номер ошибки',
    private String dk;        // 'Внутренний номер по ЭТТс БСК',
    private double col;       // 'Стоимость услуги оформления ППД на ПКТК',
    private double tax;       // 'НДС услуги оформленияППД на ПКТК',
    private double nds;       // 'НДС по ручной клади',
    private int bi;           // 'Номер бланка',
    private String sn;        // 'СНИЛС',


    public int getPerevozGkey() {
        return perevozGkey;
    }

    public void setPerevozGkey(int perevozGkey) {
        this.perevozGkey = perevozGkey;
    }

    public String getTrainCat() {
        return trainCat;
    }

    public void setTrainCat(String trainCat) {
        this.trainCat = trainCat;
    }

    public int getTPlanID() {
        return tPlanID;
    }

    public void setTPlanID(int tPlanID) {
        this.tPlanID = tPlanID;
    }

    public int getFromStation() {
        return fromStation;
    }

    public void setFromStation(int fromStation) {
        this.fromStation = fromStation;
    }

    public int getToStation() {
        return toStation;
    }

    public void setToStation(int toStation) {
        this.toStation = toStation;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public int getTicketTypeL() {
        return ticketTypeL;
    }

    public void setTicketTypeL(int ticketTypeL) {
        this.ticketTypeL = ticketTypeL;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Date getT() {
        return t;
    }

    public void setT(Date t) {
        this.t = t;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Date getP() {
        return p;
    }

    public void setP(Date p) {
        this.p = p;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public String getDk() {
        return dk;
    }

    public void setDk(String dk) {
        this.dk = dk;
    }

    public double getCol() {
        return col;
    }

    public void setCol(double col) {
        this.col = col;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getNds() {
        return nds;
    }

    public void setNds(double nds) {
        this.nds = nds;
    }

    public int getBi() {
        return bi;
    }

    public void setBi(int bi) {
        this.bi = bi;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
