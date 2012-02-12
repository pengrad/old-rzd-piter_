package test;

import javax.xml.bind.annotation.XmlAttribute;

public class Element {

    public enum Type {
        All, out_a;


        @Override
        public String toString() {
            return "asd";
        }
    }

    @XmlAttribute
    private String sumService, sumRZDService, sumRZDWork, sumRZDPersonal, sumStudy, sumWar, sumRegion, sumFedNonSoc, sumFedSoc, sumPay, sum, subtype;
    @XmlAttribute
    public Type type;


    public Element() {
    }

    public Element(String type, String subtype, String sum, String sumPay, String sumFedSoc, String sumFedNonSoc, String sumRegion, String sumWar, String sumStudy, String sumRZDPersonal, String sumRZDWork, String sumRZDService, String sumService) {
       // this.type = type;
        this.subtype = subtype;
        this.sum = sum;
        this.sumPay = sumPay;
        this.sumFedSoc = sumFedSoc;
        this.sumFedNonSoc = sumFedNonSoc;
        this.sumRegion = sumRegion;
        this.sumWar = sumWar;
        this.sumStudy = sumStudy;
        this.sumRZDPersonal = sumRZDPersonal;
        this.sumRZDWork = sumRZDWork;
        this.sumRZDService = sumRZDService;
        this.sumService = sumService;
    }

    public static Element getForIncomAll(String type, String sum, String sumPay, String sumService) {
        Element e = new Element();
       // e.type = type;
        e.sum = sum;
        e.sumPay = sumPay;
        e.sumService = sumService;
        return e;
    }

    //Для билетов
    public Element(String type, String sum, String sumPay) {
        this.type = Type.out_a;
        this.sum = sum;
        this.sumPay = sumPay;
    }

    //Для aбонементов
    public Element(String type, String subtype, String sum, String sumPay, String sumRZDWork) {
   //     this.type = type;
        this.subtype = subtype;
        this.sum = sum;
        this.sumPay = sumPay;
        this.sumRZDWork = sumRZDWork;
    }
}
