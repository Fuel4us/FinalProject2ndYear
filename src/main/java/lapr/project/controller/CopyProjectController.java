/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.controller;

import java.sql.SQLException;
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
     */
    public void cloneProject() throws CloneNotSupportedException {
        project = project.cloneProject();
        dbCom.addProject(project);
    }
    
    /**
     * Save the project and its data in the database 
     * @throws SQLException
     */
    public void saveProject() throws SQLException{
       
    }
    
}
