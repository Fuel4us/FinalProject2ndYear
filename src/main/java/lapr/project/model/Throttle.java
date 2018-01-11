package lapr.project.model;

import java.util.List;

public class Throttle {

    private int id;
    private List<Regime> regimes;

    public Throttle(int id, List<Regime> regime) {
        this.id = id;
        this.regimes = regime;
    }

    /**
     * @return regimes
     */
    public List<Regime> getRegimes() {
        return regimes;
    }

    @Override
    public String toString() {
        return "Throttle "+ id;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }
}
