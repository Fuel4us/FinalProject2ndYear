package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.utils.FileParser.FileParser;
import lapr.project.utils.FileParser.XMLImporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class ChangeDataController {

    private Project project;

    public ChangeDataController(Project project) {
        this.project = project;
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
     * Setter for project name
     * @param name
     */
    public void setName(String name) {
        project.setName(name);
    }

    /**
     * Setter for project description
     * @param description
     */
    public void setDescription(String description) {
        project.setDescription(description);
    }

    /**
     * Getter for project
     * @return project
     */
    public Project getProject() {
        return project;
    }

    public void addNewRoads(File roads) throws Exception {
        FileParser importer = new XMLImporter(roads, project.getRoadNetwork());
        importer.importNetwork(false);
    }

}