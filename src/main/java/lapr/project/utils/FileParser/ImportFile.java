/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.FileParser;

import lapr.project.model.Project;

/**
 *
 * @author goncalo
 */
public class ImportFile {
    
     public boolean importVehicles(FileParser input, Project object, String filename) {
        return input.importVehicles(object, filename);
    }
    
}
