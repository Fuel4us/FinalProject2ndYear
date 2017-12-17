package lapr.project.model.Vehicle;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Energy {
    
    @XmlElement
    private int min_rpm;
    
    @XmlElement
    private int max_rpm;
    
    @XmlElement
    private float final_drive_ratio;
    
    @XmlElementWrapper(name = "gear_list")
    @XmlElement(name = "gear")
    private List<Gears> gears;
    
    @XmlElementWrapper(name = "throttle_list")
    @XmlElement(name = "throttle")
    private List<Throttle> throttles;

    /**
     * Constructor
     * @param min_rpm
     * @param max_rpm
     * @param final_drive_ratio
     * @param gears
     * @param throttles
     */
    public Energy(int min_rpm, int max_rpm, float final_drive_ratio, List<Gears> gears, List<Throttle> throttles) {
        this.min_rpm = min_rpm;
        this.max_rpm = max_rpm;
        this.final_drive_ratio = final_drive_ratio;
        this.gears = gears;
        this.throttles = throttles;
    }
}
