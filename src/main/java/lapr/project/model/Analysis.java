package lapr.project.model;

import lapr.project.model.RoadNetwork.Section;
import lapr.project.model.RoadNetwork.Segment;
import lapr.project.utils.Measurable;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import java.util.Collection;

/**
 * Defines general behaviour for different types of analysis
 * in order to better accommodate future requirement changes
 */
public abstract class Analysis {

    private int id;
    private Project requestingInstance;
    private String algorithmName;
    private Measurable travelTime;
    private Collection<Section> bestPath;

    public Analysis(int id, Project requestingInstance) {
        this.id = id;
        this.requestingInstance = requestingInstance;
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
    public abstract Collection<?> generateReport();

    /**
     * Prints data from a given segment filling the information missing in a given file template
     * @param stringTemplate instance of {@link StringTemplate}
     */
    public void printDataFromAnalysis(StringTemplate stringTemplate) {
        String projectName = requestingInstance.getName();
        String analysisName = algorithmName;
//        String travelTimeStr = travelTime.toString;
//        String recordsNumber = String.valueOf(recordsNumber);

        stringTemplate.setAttribute("projectName", projectName);
        stringTemplate.setAttribute("sampleName", analysisName);
//        stringTemplate.setAttribute("sampleTime", travelTimeStr);
//        stringTemplate.setAttribute("sampleRecords", recordsNumber);

        printPath();

        System.out.println(stringTemplate.toString());

    }

    /**
     * Prints segment information for each section that composes the best path
     */
    private void printPath() {
//        for (Section section: bestPath) {
//            section.printSegmentsFromSection();
//        }
    }

    /**
     * Prints data from a given segment filling the information missing in a given file template
     * @param segment
     */
    public void printDataFromSegment(Segment segment) {
        StringTemplateGroup groupSegment =  new StringTemplateGroup("myGroup", "C:\\Tutorials", DefaultTemplateLexer.class);
        StringTemplate segmentTemplate = groupSegment.getInstanceOf("html_structure_segment");
        segment.printDataFromSegment(segmentTemplate);
    }
}
