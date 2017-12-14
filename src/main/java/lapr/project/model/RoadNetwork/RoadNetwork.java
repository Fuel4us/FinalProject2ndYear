package lapr.project.model.RoadNetwork;

import lapr.project.utils.Graph.Graph;

/**
 * Graph view of a network of roads
 */
public class RoadNetwork<Road,Section> extends Graph<Road,Section> {


    Graph<Road, Section> roadNetwork;


    public RoadNetwork(boolean directed, Graph<Road, Section> roadNetwork) {
        super(directed);
        this.roadNetwork = roadNetwork;
    }

    public Graph<Road, Section> getRoadNetwork() {
        return roadNetwork;
    }

}
