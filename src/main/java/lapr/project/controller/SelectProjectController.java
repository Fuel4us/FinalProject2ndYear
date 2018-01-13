/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.ui.Main;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;

import java.util.List;

/**
 * Connects UI events to Model classes
 * @author Antelo
 */
public class SelectProjectController {

    private DataBaseCommunicator dbCom;

    public SelectProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
    }

    /**
     * @return the {@link List} of instances of {@link Project} stored in the data layer
     */
    public List<Project> fetchProjectsList() {
        return dbCom.fetchProjectList();
    }

    /**
     * Defines the current project as the project that corresponds to a given {@code projectName}
     * If no project exists in the database, the current project remains unchanged.
     * @param project The project to set as the current
     */
    public void setCurrentProject(Project project) {
            Main.setCurrentProject(project);
    }

}
