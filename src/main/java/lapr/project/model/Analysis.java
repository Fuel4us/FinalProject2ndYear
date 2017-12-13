package lapr.project.model;

import java.util.List;

/**
 * Defines general behaviour for different types of analysis
 * in order to better accommodate future requirement changes
 */
public interface Analysis<E> {

    /**
     * Displays the results of an analysis
     * @return Such Results as aforementioned
     */
    List<E> showResults();

    /**
     * Returns value for the instance that should be unique
     * @return the id of this analysis
     */
    int exposeID();

}
