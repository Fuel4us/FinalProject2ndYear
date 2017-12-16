/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.controller;

import java.util.List;
import lapr.project.model.DataBaseCommunicator;
import lapr.project.model.Project;

/**
 *
 * @author Antelo
 */
public class SelectProjectController {
    
    List<Project> listProjects;
    DataBaseCommunicator dbc;
    
    
    public SelectProjectController(){
    
    }
    
    public List<Project> fetchProjectsList(){
        List<Project> listProjects = dbc.fetchProjectList();
        return listProjects;
    }
    
    public boolean storeProject(Project p){
        return dbc.storeProject(p);
    }
    
    public void activateProject(Project p){
        p.setActive();
    }

}
