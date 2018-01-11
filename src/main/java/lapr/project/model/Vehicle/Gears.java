package lapr.project.model.Vehicle;

import java.util.Objects;

public class Gears {

    private int id;
    private float ratio;
    
    public Gears() {
    }

    public Gears(int id, float ratio) {
        this.id = id;
        this.ratio = ratio;
    }

    /**
     * @return ratio
     */
    public float getRatio() {
        return ratio;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Equals for objects of the class Gears
     * @param o the other object
     * @return true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gears gears = (Gears) o;
        return id == gears.id &&
                Float.compare(gears.ratio, ratio) == 0;
    }

    /**
     * Hash code for instances of the class Gear
     * @return the hash code value
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, ratio);
    }
}
