package lapr.project.model.Vehicle;

public class Gears {

    private int id;
    private float ratio;

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
}
