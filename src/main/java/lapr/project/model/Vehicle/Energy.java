package lapr.project.model.Vehicle;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Energy {
    

    private int min_rpm;
    private int max_rpm;
    private float final_drive_ratio;
    private List<Gears> gears;
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

    public float getFinal_drive_ratio() {
        return final_drive_ratio;
    }

    public List<Gears> getGears() {
        return gears;
    }

    public int getMax_rpm() {
        return max_rpm;
    }

    public int getMin_rpm() {
        return min_rpm;
    }

    public List<Throttle> getThrottles() {
        return throttles;
    }

    @Override
    public String toString() {
        return "Energy têm de minRPM " +this.min_rpm+ " max RPM " +this.max_rpm+ " Ratio " +this.final_drive_ratio+ " Gears: " +this.gears+ " Throttle: " +this.throttles;
    }
    
    
}
