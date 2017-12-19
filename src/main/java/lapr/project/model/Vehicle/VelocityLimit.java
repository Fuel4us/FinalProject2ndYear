package lapr.project.model.Vehicle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "velocity_limit")
public class VelocityLimit {
    
    @XmlElement(name ="segment_type")
    private String segmentType;
    
    @XmlElement(name ="limit")
    private int limit;

    public VelocityLimit(String segmentType, int limit) {
        this.segmentType = segmentType;
        this.limit = limit;
    }

    public VelocityLimit() {
    }

    public String getSegmentType() {
        return segmentType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }
    
}
