package lapr.project.utils.FileParser;
import lapr.project.model.Analysis;
import lapr.project.model.RoadNetwork.Segment;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

/**
 * Handles exportation of an analysis result to a HTML file
 */
public class ExportHTML implements Exporter{

    private Analysis analysis;

    //method that gets and analysis result
        //method that gets header data
            //method that prints header data
        //method that iterates each segment of the resulting path
            //method that prints data of each segment

    /**
     * Prints data from analysis filling the information missing in a given file template
     */
    @Override
    public void printDataFromAnalysis() {
        StringTemplateGroup groupSegment =  new StringTemplateGroup("myGroup", "C:\\Tutorials", DefaultTemplateLexer.class);
        StringTemplate stringTemplate = groupSegment.getInstanceOf("html_structure_main");
        analysis.printDataFromAnalysis(stringTemplate);
    }

}
