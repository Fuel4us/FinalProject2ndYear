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
     * @param min_rpm min_rpm
     * @param max_rpm max_rpm
     * @param final_drive_ratio final_drive_ratio
     * @param gears gears
     * @param throttles throttles
     */
    public Energy(int min_rpm, int max_rpm, float final_drive_ratio, List<Gears> gears, List<Throttle> throttles) {
        this.min_rpm = min_rpm;
        this.max_rpm = max_rpm;
        this.final_drive_ratio = final_drive_ratio;
        this.gears = gears;
        this.throttles = throttles;
    }

    public Energy(Energy energy) {
        new Energy(energy.min_rpm, energy.max_rpm, energy.final_drive_ratio, energy.gears, energy.throttles);
    }

    @Override
    public String toString() {
        return "Energy{" +
                "min_rpm=" + min_rpm +
                ", max_rpm=" + max_rpm +
                ", final_drive_ratio=" + final_drive_ratio +
                ", gears=" + gears +
                ", throttles=" + throttles +
                '}';
    }
}
