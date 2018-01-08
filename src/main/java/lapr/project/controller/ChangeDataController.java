package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.Project;
import lapr.project.utils.FileParser.XMLImporterRoads;
import org.xml.sax.SAXException;



public class ChangeDataController {

    private Project project;
    
    public ChangeDataController(Project project){
        this.project=project;
    }
    /**
     * Getter for project name
     * 
     * @return Name of Project
     */
    public String getName(){
        return project.getName();
    }
    
    /**
     * Getter for project description
     * 
     * @return Description of project
     */
    public String getDescription(){
        return project.getDescription();
    }
    
    /**
     * Setter for project name
     * 
     * @param name 
     */
    public void setName(String name){
        project.setName(name);
    }

    /**
     * Setter for project description
     * 
     * @param description 
     */
    public void setDescription(String description){
        project.setDescription(description);
    }
    
    /**
     * Getter for project
     * 
     * @return project
     */
    public Project getProject(){
        return project;
    }
    
    public void addNewRoads(File roads) throws IOException, SAXException, ParserConfigurationException{
        XMLImporterRoads xmlImporter = new XMLImporterRoads(roads,project.getRoadNetwork());
            xmlImporter.importNetwork(false);
    }
            
}