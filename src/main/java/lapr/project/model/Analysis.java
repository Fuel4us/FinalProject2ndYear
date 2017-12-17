package lapr.project.model;

import java.util.Collection;

/**
 * Defines general behaviour for different types of analysis
 * in order to better accommodate future requirement changes
 */
public abstract class Analysis {

    private int id;
    private Project requestingInstance;

    /**
     * @return the entity that issued the analysis
     */
    public Project issueRequestingEntity() {
        return requestingInstance;
    }

    /**
     * Returns a value that should be unique for the instance
     * @return the id of this analysis
     */
    public int identify() {
        return id;
    }

    /**
     * Provides the results of an analysis,
     * encapsulating them in a Collection subclass
     * @return Such Results as aforementioned
     */
    public abstract Collection<?> generateReport();

}
