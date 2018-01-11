package lapr.project.model;

import lapr.project.utils.Measurable;
public class VelocityLimit {
    
    private String segmentType;
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
