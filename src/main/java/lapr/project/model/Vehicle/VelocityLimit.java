package lapr.project.model.Vehicle;

import lapr.project.utils.Measurable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "velocity_limit")
public class VelocityLimit {
    
    @XmlElement(name ="segment_type")
    private String segmentType;
    
    @XmlElement(name ="limit")
    private Measurable limit;

    public VelocityLimit(String segmentType, Measurable limit) {
        this.segmentType = segmentType;
        this.limit = limit;
    }

    public VelocityLimit() {
    }

    public String getSegmentType() {
        return segmentType;
    }

    public Measurable getLimit() {
        return limit;
    }

    public void setLimit(Measurable limit) {
        this.limit = limit;
    }

    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }
    
}
