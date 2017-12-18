package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "velocity_limit")
public class VelocityLimit {
    
    @XmlElement(name ="segment_type")
    private String TypeSegment;
    
    @XmlElement(name ="limit")
    private int limit;

    public VelocityLimit(String TypeSegment, int limit) {
        this.TypeSegment = TypeSegment;
        this.limit = limit;
    }

    public VelocityLimit() {
    }

    public String getTypeSegment() {
        return TypeSegment;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setTypeSegment(String TypeSegment) {
        this.TypeSegment = TypeSegment;
    }
    
    
    
}
