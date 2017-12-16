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

    
}
