package lapr.project.model.Vehicle;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Throttle {

    @XmlElement
    private int id;
    
    @XmlElementWrapper(name = "regime")
    private List<Regime> regimes;

    public Throttle(int id, List<Regime> regime) {
        this.id = id;
        this.regimes = regime;
    }
}
