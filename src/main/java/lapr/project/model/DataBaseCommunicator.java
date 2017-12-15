package lapr.project.model;

import java.util.List;

/**
 * <p>
 * Handles database read and write operations
 * </p>
 */
public class DataBaseCommunicator {
    
    
    
    public List<Project> fetchProjectsList(){
        List<Project> listProjects = null;
        return listProjects;
    }

    public boolean storeProject(Project p) {
        return true;
    }
    
}
