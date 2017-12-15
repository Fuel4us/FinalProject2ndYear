package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;


public class VelocityLimitList {
    
    @XmlElement(name ="segment_type")
    private String TypeSegment;
    
    @XmlElement(name ="limit")
    private int velocityLimit;

    public VelocityLimitList(String TypeSegment, int velocityLimit) {
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
