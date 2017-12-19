package lapr.project.model.RoadNetwork;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

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
     * Fills and prints data of segment in a file
     * @param segmentTemplate instance of {@link StringTemplate}
     */
    public void printDataFromSegment(StringTemplate segmentTemplate) {
        String segmentId = String.valueOf(index);
        String segmentIniHeight = String.valueOf(initialHeight);
        String segmentFinHeight = String.valueOf(finalHeight);
        String segmentLength = String.valueOf(length);
        String segmentWindAngle = String.valueOf(windAngle);
        String segmentWindSpeed = String.valueOf(windSpeed);
        String segmentMaxVelocity = String.valueOf(maxVelocity);
        String segmentMinVelocity = String.valueOf(minVelocity);

        segmentTemplate.setAttribute("sampleId", segmentId);
        segmentTemplate.setAttribute("sampleIniHeight", segmentIniHeight);
        segmentTemplate.setAttribute("sampleFimHeight", segmentFinHeight);
        segmentTemplate.setAttribute("sampleLength", segmentLength);
        segmentTemplate.setAttribute("sampleWindAngle", segmentWindAngle);
        segmentTemplate.setAttribute("sampleWindSpeed", segmentWindSpeed);
        segmentTemplate.setAttribute("sampleMaxVel", segmentMaxVelocity);
        segmentTemplate.setAttribute("sampleMinVel", segmentMinVelocity);

        System.out.println(segmentTemplate.toString());
    }

}