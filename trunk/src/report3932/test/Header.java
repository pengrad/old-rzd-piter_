package test;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Collection;

public class Header {
    @XmlAttribute
    private String typeTerminal,station, sector,direction;

    public Header() {
    }

    public Header(String typeTerminal, String station, String sector, String direction) {
        this.typeTerminal = typeTerminal;
        this.station = station;
        this.sector = sector;
        this.direction = direction;
    }

    public void setIncoms(Collection<Element> incoms) {
        this.incoms = incoms;
    }

    public void setTickets(Collection<Element> tickets) {
        this.tickets = tickets;
    }

    public void setAbonements(Collection<Element> abonements) {
        this.abonements = abonements;
    }

    @XmlElementWrapper(name = "money")
    @XmlElement(name = "incom")
    Collection<Element> incoms;
    @XmlElementWrapper(name = "tickets")
    @XmlElement(name = "ticket")
    Collection<Element> tickets;
    @XmlElementWrapper(name = "abonements")
    @XmlElement(name = "abonement")
    Collection<Element> abonements;

}
