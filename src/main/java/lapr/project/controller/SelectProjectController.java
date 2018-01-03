/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.controller;

import java.util.List;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;
import lapr.project.model.Project;

/**
 *
 * @author Antelo
 */
public class SelectProjectController {
    
    List<Project> listProjects;
    private DataBaseCommunicator dbCom;
    
    
    public SelectProjectController(DataBaseCommunicator dbCom){
        this.dbCom = dbCom;    
    }
    
    public List<Project> fetchProjectsList(){
        List<Project> list = dbCom.fetchProjectList();
        return list;
    }
    
}
