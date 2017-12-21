package lapr.project.model.Vehicle;

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

}
