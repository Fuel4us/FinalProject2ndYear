package lapr.project.model;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.utils.FileParser.Exportable;
import lapr.project.utils.Measurable;
import org.antlr.stringtemplate.StringTemplate;;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Defines general behaviour for different types of analysis
 * in order to better accommodate future requirement changes
 */
public class Analysis implements Exportable {

    private int id;
    private Project requestingInstance;
    private String algorithmName;
    private Collection<Section> bestPath;
    private Measurable expendedEnergy;
    private Measurable travelTime;
    private Measurable travelCost;

    /**
     * Creates an analysis with id, requesting instance, algorithm name, best path, expended energy,
     * travel time and travel cost
     * @param id this analysis' id
     * @param requestingInstance this analysis' requesting instance
     * @param algorithmName this analysis' algorithm name
     * @param bestPath this analysis' best path
     * @param expendedEnergy this analysis' expended energy
     * @param travelTime this analysis' travel time
     * @param travelCost this analysis' travel cost
     */
    public Analysis(int id, Project requestingInstance, String algorithmName, Collection<Section> bestPath,
                    Measurable expendedEnergy, Measurable travelTime, Measurable travelCost) {
        this.id = id;
        this.requestingInstance = requestingInstance;
        this.algorithmName = algorithmName;
        this.bestPath = bestPath;
        this.expendedEnergy = expendedEnergy;
        this.travelTime = travelTime;
        this.travelCost = travelCost;
    }

    /**
     *Creates an analysis with id, requesting instance, algorithm name and best path
     * @param id this analysis' id
     * @param requestingInstance this analysis' requesting instance
     * @param algorithmName this analysis' algorithm name
     * @param bestPath this analysis' best path
     */
    public Analysis(int id, Project requestingInstance, String algorithmName, Collection<Section> bestPath) {
        this.id = id;
        this.requestingInstance = requestingInstance;
        this.algorithmName = algorithmName;
        this.bestPath = bestPath;
    }

    /**
     * @return the entity that issued the analysis
     */
    public Project issueRequestingEntity() {
        return requestingInstance;
    }

    /**
     * Returns a value that should be unique for the instance
     * @return the id of this analysis
     */
    public int identify() {
        return id;
    }

    /**
     * Provides the results of an analysis,
     * encapsulating them in a Collection subclass
     * @return Such Results as aforementioned
     */
    public Collection<?> generateReport() {
        //ToDo Test only, replace by actual implementation
        List<String> test = new ArrayList<>();
        test.add("A");
        test.add("B");
        test.add("C");
        test.add("D");
        return test;
    };

    /**
     * Prints data from a given segment filling the information missing in a given file template
     * @param stringTemplate1 instance of {@link StringTemplate}
     * @param stringTemplate2 instance of {@link StringTemplate}
     * @param file FileWriter object
     */
    @Override
    public void printDataFromAnalysis(StringTemplate stringTemplate1, StringTemplate stringTemplate2, FileWriter file) throws IOException {
        String projectName = requestingInstance.getName();
        String analysisName = algorithmName;
//        String travelTimeStr = travelTime.toString;
//        String recordsNumber = String.valueOf(recordsNumber);
//        String energyConsumption;
//        String tollCost;

        stringTemplate1.setAttribute("projectName", projectName);
        stringTemplate1.setAttribute("sampleName", analysisName);
//        stringTemplate1.setAttribute("sampleTime", travelTimeStr);
//        stringTemplate1.setAttribute("sampleRecords", recordsNumber);
//        stringTemplate1.setAttribute("sampleEnergy", energyConsumption);
//        stringTemplate1.setAttribute("sampleCost", tollCost);

        file.write(stringTemplate1.toString());
        printPathRoads(file);
        file.write(stringTemplate2.toString());

        printPath(file);

        file.write("</body></html>");
        file.close();

    }

    /**
     * Prints roads that compose the best path
     */
    private void printPathRoads(FileWriter file) throws IOException {
        List<Road> roads = new ArrayList<>();
        file.write("<center>");
        for (Section section: bestPath) {
            Road road = section.getOwningRoad();
            if (!roads.contains(road)) {
                roads.add(road);
            }
        }
        for (Road road : roads) {
//            String roadName = road.getName();
            file.write(road.getName() + " | ");
        }
        file.write("</center>");
    }

    /**
     * Prints segment information for each section that composes the best path
     */
    private void printPath(FileWriter file) throws IOException {
        for (Section section: bestPath) {
            section.printSegmentsFromSection(file);
        }
    }

}
