/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;

/**
 *
 * @author Antelo
 */
public class CopyProjectController {

    private DataBaseCommunicator dbCom;
    private Project project;
    
    /**
     * Constructs an instance of CopyProjectController
     * @param dbCom
     */
    public CopyProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
        project = new Project();
    }
    
    /**
     * Create new project from the active project  
     * @param projectToClone
     * @return 
     * @throws java.lang.CloneNotSupportedException
     */
    public boolean cloneProject(Project projectToClone) throws CloneNotSupportedException {
        project = projectToClone.cloneProject();
        return dbCom.addProject(project);
    }    
}
