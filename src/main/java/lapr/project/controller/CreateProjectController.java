package lapr.project.controller;

import lapr.project.model.DataBaseCommunicator;

/**
 * Class that represents the Controller for the UC2 - Create Project
 */
public class CreateProjectController {

    private DataBaseCommunicator dbCom;

    /**
     * Full constructor for the class CreateProjectController
     */
    public CreateProjectController(DataBaseCommunicator dbCom) {
        this.dbCom = dbCom;
    }
}
