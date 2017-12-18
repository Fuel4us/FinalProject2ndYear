package lapr.project.model.RoadNetwork;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Segment {

    @XmlElement(name = "id")
    private int index;
    @XmlElement(name = "init_height")
    private double initialHeight;
    @XmlElement(name = "final_height")
    private double finalHeight;

    //private double slope;
    @XmlElement
    private double length;
    @XmlElement(name = "wind_direction")
    private double windAngle;
    @XmlElement(name = "wind_speed")
    private double windSpeed;
    @XmlElement(name = "max_velocity")
    private double maxVelocity;
    @XmlElement(name = "min_velocity")
    private double minVelocity;


    /**
     * Forbid default no-arg instantiation
     */
    private Segment() {}

    public Segment(int index, double initialHeight, double finalHeight, double length, double windAngle, double windSpeed, double maxVelocity, double minVelocity) {
        this.index = index;
        this.initialHeight = initialHeight;
        this.finalHeight = finalHeight;
        this.length = length;
        this.windAngle = windAngle;
        this.windSpeed = windSpeed;
        this.maxVelocity = maxVelocity;
        this.minVelocity = minVelocity;
    }

    public double getLength() {
        return length;
    }

    /**
     * Calculates the minimum time interval spent for the segment,
     * taking into account the velocity limit, its length and
     * the velocity limit of the vehicle
     * @param vehicleMaxVelocity the velocity limit of the vehicle
     * @return the minimum time interval
     */
    public double calculateMinimumTimeInterval(double vehicleMaxVelocity) {

        return length / Math.min(maxVelocity, vehicleMaxVelocity);

    }
}