package test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;

@XmlRootElement
public class TestReport3932 {
    @XmlAttribute
    public final static String type = "3932";
    @XmlElement
    Header[] header;

    public void setHeader(Header... header) {
        this.header = header;
    }

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        TestReport3932 report = new TestReport3932();
        Header header = new Header("MKTK", "Moscow3", "Moscow-East", "Moscow");
        report.setHeader(header);
        Element e = new Element("noway", "123.4", "123.4");
        header.setTickets(Collections.nCopies(5, e));
        e = new Element("1-month", "123.4", "123.4");
        header.setAbonements(Collections.nCopies(5, e));
        Marshaller marshaller = JAXBContext.newInstance(TestReport3932.class).createMarshaller();
        marshaller.marshal(new JAXBElement<TestReport3932>(new QName("form"), TestReport3932.class, report), System.out);
        marshaller.marshal(new JAXBElement<TestReport3932>(new QName("form"), TestReport3932.class, report), new FileOutputStream("d:\\Docs\\projects\\RZD_Piter\\out.xml"));
    }

}

