package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "velocity_limit")
public class VelocityLimit {
    
    @XmlElement(name ="segment_type")
    private String TypeSegment;
    
    @XmlElement(name ="limit")
    private int velocityLimit;

    public VelocityLimit(String TypeSegment, int velocityLimit) {
        this.TypeSegment = TypeSegment;
        this.velocityLimit = velocityLimit;
    }

    public String getTypeSegment() {
        return TypeSegment;
    }

    public void setTypeSegment(String TypeSegment) {
        this.TypeSegment = TypeSegment;
    }

    public int getVelocityLimit() {
        return velocityLimit;
    }

    public void setVelocityLimit(int velocityLimit) {
        this.velocityLimit = velocityLimit;
    }
    
    
    
}
