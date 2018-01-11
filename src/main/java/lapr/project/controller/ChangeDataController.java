package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.utils.FileParser.FileParser;
import lapr.project.utils.FileParser.XMLImporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import lapr.project.utils.DataAccessLayer.DataBaseCommunicator;


public class ChangeDataController {

    private Project project;
    private DataBaseCommunicator dbCom;
    private String name;
    private String description;

    public ChangeDataController(Project project, DataBaseCommunicator dbCom) {
        this.project = project;
        this.dbCom = dbCom;
    }

    /**
     * Getter for project name
     * @return Name of Project
     */
    public String getName() {
        return project.getName();
    }

    /**
     * Getter for project description
     * @return Description of project
     */
    public String getDescription() {
        return project.getDescription();
    }

    /**
     * Getter for project
     * @return project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Setter of name
     * @param name project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter of description
     * @param description project description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param roads
     * @throws Exception
     */
    public void addNewRoads(File roads) throws Exception {
        FileParser importer = new XMLImporter(roads, project.getRoadNetwork());
        importer.importNetwork(false);
    }

    /**
     *
     * @param project
     * @param updatedName
     * @param updatedDescription
     */
    public void changeProjectData(Project project, String updatedName, String updatedDescription){
        dbCom.changeProjectData(project, updatedName, updatedDescription);
        project.setName(updatedName);
        project.setDescription(updatedDescription);
    }
}