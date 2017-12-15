package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;

public class Gear {

    @XmlElement
    private int id = 0;
    
    @XmlElement
    private double ratio = 0;

    public Gear(int id, double ratio) {
        this.id = id;
        this.ratio = ratio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
    
    
}
