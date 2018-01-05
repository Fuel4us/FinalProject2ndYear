package lapr.project.model.Vehicle;

import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

import javax.persistence.Embeddable;

/**
 * Type of fuel that the car uses
 */
@Embeddable
public enum Fuel {

    Diesel(new Measurable(48, Unit.KILOJOULE_PER_GRAM)), Gasoline(new Measurable(44.4, Unit.KILOJOULE_PER_GRAM)),
    Electric(new Measurable(0, null));

    private Measurable specificEnergy;

    /**
     * Full constructor
     * @param specificEnergy the specific energy
     */
    Fuel(Measurable specificEnergy) {

        this.specificEnergy = specificEnergy;
    }

    /**
     * @return specific energy
     */
    public Measurable getSpecificEnergy() {
        return specificEnergy;
    }

}
