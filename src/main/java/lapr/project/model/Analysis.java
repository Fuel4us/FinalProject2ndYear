package lapr.project.model;

import lapr.project.model.RoadNetwork.Road;
import lapr.project.model.RoadNetwork.Section;
import lapr.project.utils.FileParser.ExportableCSV;
import lapr.project.utils.FileParser.ExportableHTML;
import org.antlr.stringtemplate.StringTemplate;
import lapr.project.utils.Measurable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

;

/**
 * <p>
 * Stores information about expended energy,
 * travel time and travel cost for a vehicle in a list of sections.
 * </p>
 */
public class Analysis implements ExportableHTML, ExportableCSV {

    private int id;
    private Project requestingInstance;
    private String algorithmName;
    private Collection<Section> bestPath;
    private Measurable expendedEnergy;
    private Measurable travelTime;
    private Measurable travelCost;

    private static int analysisCounter = 0;

    /**
     * Creates an analysis with id, requesting instance, algorithm name, best path, expended energy,
     * travel time and travel cost
     * @param requestingInstance this analysis' requesting instance
     * @param algorithmName this analysis' algorithm name
     * @param bestPath this analysis' best path
     * @param expendedEnergy this analysis' expended energy
     * @param travelTime this analysis' travel time
     * @param travelCost this analysis' travel cost
     */
    public Analysis(Project requestingInstance, String algorithmName, Collection<Section> bestPath,
                    Measurable expendedEnergy, Measurable travelTime, Measurable travelCost) {
        id = ++analysisCounter;
        this.requestingInstance = requestingInstance;
        this.algorithmName = algorithmName;
        this.bestPath = bestPath;
        this.expendedEnergy = expendedEnergy;
        this.travelTime = travelTime;
        this.travelCost = travelCost;
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
     * Provides the results of an analysis in string form.
     * Results include
     * @return Such Results as aforementioned
     */
    public String generateReport() {

        String sectionsHeader = "Travelled sections";
        String energyHeader = "Expended Energy during travel";
        String timeHeader = "Total Travel time";
        String costHeader = "Total toll costs";

        String sectionContext = bestPath.toString();
        String energyContext = expendedEnergy.toString();
        String timeContext = travelTime.toString();
        String costContext = travelCost.toString();

        return String.format("%s:%s%n%n %s:%s%n%n %s:%s%n%n %s:%s%n%n",
                sectionsHeader, sectionContext,
                energyHeader, energyContext,
                timeHeader, timeContext,
                costHeader, costContext);
    }

    /**
     * Exports data from analysis to an html file according to given templates
     * @param stringTemplate1 instance of {@link StringTemplate}
     * @param stringTemplate2 instance of {@link StringTemplate}
     * @param file FileWriter object
     */
    @Override
    public void exportDataHTML(StringTemplate stringTemplate1, StringTemplate stringTemplate2, FileWriter file) throws IOException {
        exportAnalysisData(stringTemplate1, file);
        printPathRoadsHTML(file);
        file.write(stringTemplate2.toString());
        printPathHTML(file);
        file.write("</body></html>");

        file.close();
    }

    /**
     * Exports data from analysis to an csv file according to given templates
     * @param stringTemplate1 instance of {@link StringTemplate}
     * @param stringTemplate2 instance of {@link StringTemplate}
     * @param file FileWriter object
     */
    @Override
    public void exportDataCSV(StringTemplate stringTemplate1, StringTemplate stringTemplate2, FileWriter file) throws IOException {
        exportAnalysisData(stringTemplate1, file);
        printPathRoadsCSV(file);
        file.write(stringTemplate2.toString());
        printPathCSV(file);

        file.close();
    }

    /**
     * Exports data from analysis excluding path information
     * @param stringTemplate1
     * @param file
     * @throws IOException
     */
    private void exportAnalysisData(StringTemplate stringTemplate1, FileWriter file) throws IOException {
        String projectName = requestingInstance.getName();
        String analysisName = algorithmName;
        String travelTimeStr = travelTime.toString();
        String energyConsumption = expendedEnergy.toString();
        String tollCost = travelCost.toString();

        stringTemplate1.setAttribute("projectName", projectName);
        stringTemplate1.setAttribute("sampleName", analysisName);
        stringTemplate1.setAttribute("sampleTime", travelTimeStr);
        stringTemplate1.setAttribute("sampleEnergy", energyConsumption);
        stringTemplate1.setAttribute("sampleCost", tollCost);

        file.write(stringTemplate1.toString());
    }

    /**
     * Exports roads in html that compose the best path
     */
    private void printPathRoadsHTML(FileWriter file) throws IOException {
        List<Road> roads = getPathRoads();
        file.write("<center>");
        for (Road road : roads) {
            file.write(road.getName() + " | ");
        }
        file.write("</center>");
    }

    /**
     * Exports roads in csv that compose the best path
     */
    private void printPathRoadsCSV(FileWriter file) throws IOException {
        List<Road> roads = getPathRoads();
        file.write("\n");
        file.write("Roads:,");
        int i = roads.size();
        for (Road road : roads) {
            if (i > 1) {
                file.write(road.getName() + ",");
            } else {
                file.write(road.getName());
            }
            i--;
        }
        file.write("\n");
    }

    /**
     * ToDo
     * @return list of instances of {@link Road}
     */
    private List<Road> getPathRoads() {
        List<Road> roads = new ArrayList<>();
        for (Section section: bestPath) {
            Road road = section.getOwningRoad();
            if (!roads.contains(road)) {
                roads.add(road);
            }
        }
        return roads;
    }

    /**
     * Exports to an html file the segment information for each section that composes the best path
     */
    private void printPathHTML(FileWriter file) throws IOException {
        for (Section section : bestPath) {
            section.printSegmentsFromSectionHTML(file);
        }
    }

    /**
     * Exports to a csv file the segment information for each section that composes the best path
     */
    private void printPathCSV(FileWriter file) throws IOException {
        for (Section section : bestPath) {
            section.printSegmentsFromSectionCSV(file);
        }
    }

    /**
     * @return the RequestingInstance
     */
    public Project getRequestingInstance() {
        return requestingInstance;
    }

    /**
     * @return the AlgorithmName
     */
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * @return the BestPath
     */
    public Collection<Section> getBestPath() {
        return bestPath;
    }

    /**
     * @return the ExpendedEnergy
     */
    public Measurable getExpendedEnergy() {
        return expendedEnergy;
    }

    /**
     * @return the TravelTime
     */
    public Measurable getTravelTime() {
        return travelTime;
    }

    /**
     * @return the TravelCost
     */
    public Measurable getTravelCost() {
        return travelCost;
    }

}
