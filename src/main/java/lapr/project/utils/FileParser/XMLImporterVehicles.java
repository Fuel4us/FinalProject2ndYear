package lapr.project.utils.FileParser;

import lapr.project.model.Project;
import lapr.project.model.RoadNetwork.RoadNetwork;
import lapr.project.model.Vehicle.*;
import lapr.project.model.Vehicle.Vehicle.MotorType;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Import of vehicles from XML
 */
public class XMLImporterVehicles implements FileParser {


    private int id = 1;

    @Override
    public boolean importVehicles(Project project, String filename) {

        try {

            //<editor-fold desc="Variable Initialization">
            String name;
            String description;

            VehicleType vehicleType = null;

            int newTollClass = 0;

            MotorType motorTypeValue = null;

            Fuel fuel = null;

            Measurable mass = null;
            Measurable load = null;

            float dragCoefficient = 0;
            Measurable newFrontalArea = new Measurable(0, Unit.METER_SQUARED);
            float newRRC = 0;
            Measurable newWheel = new Measurable(0, Unit.METER);

            List<VelocityLimit> velocityLimits = new ArrayList<>();

            Energy energy = null;

            Vehicle vehicle;

            List<Vehicle> vehicles = new ArrayList<>();
            //</editor-fold>

            // Initiate parser
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filename));

            doc.getDocumentElement().normalize();

            // Get attributes
            NodeList vehicleList = doc.getElementsByTagName("vehicle");

            // Get vehicle nodes
            for (int temp = 0; temp < vehicleList.getLength(); temp++) {
                Node vehicleNode = vehicleList.item(temp);
                Element nameElement = (Element) vehicleNode;
                // Name & Description
                name = addName(vehicles, nameElement.getAttribute("name"));
                description = nameElement.getAttribute("description");

                /*
                Get vehicle attributes
                 */
                if (vehicleNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList vehicleAttributes = vehicleNode.getChildNodes();

                    for (int i = 0; i < vehicleAttributes.getLength(); i++) {
                        Node attribute = vehicleAttributes.item(i);

                        if (attribute.getNodeType() == Node.ELEMENT_NODE) {

                            /*
                             Type of vehicle
                             */
                            if (attribute.getNodeName().equalsIgnoreCase("type")) {
                                vehicleType = addVehicleType(attribute);
                            }

                            /*
                             Toll_class
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("toll_class")) {
                                newTollClass = Integer.parseInt(attribute.getTextContent());
                            }

                            /*
                             Motorization type ENUM
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("motorization")) {
                                motorTypeValue = addMotorization(attribute);
                            }

                            /*
                             Fuel
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("fuel")) {
                                fuel = addFuel(attribute);
                            }

                            /*
                             Mass from Measurable
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("mass")) {
                                mass = addMass(attribute);

                            }

                            /*
                             Load from Measurable
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("load")) {
                                load = addLoad(attribute);
                            }

                            /*
                             Drag
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("drag")) {
                                dragCoefficient = Float.parseFloat(attribute.getTextContent());
                            }

                            /*
                             Frontal Area
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("frontal_area")) {
                                newFrontalArea.setQuantity(Double.parseDouble(attribute.getTextContent()));
                            }
                            /*
                             RollingReleaseCoefficient
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("rrc")) {
                                newRRC = Float.parseFloat(attribute.getTextContent());
                            }

                            /*
                             Wheel size
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("wheel_size")) {
                                newWheel.setQuantity(Double.parseDouble(attribute.getTextContent()));
                            }

                            /*
                             VelocityLimitList
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("velocity_limit_list")) {
                                velocityLimits = readVelocityLimits(attribute);
                            }

                            /*
                             Energy
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("energy")) {
                                energy = addEnergy(attribute);
                            }

                        }
                    }

                }

                /*
                Create Vehicle
                 */
                vehicle = new Vehicle(name, description, vehicleType, newTollClass, motorTypeValue, fuel, mass, load, dragCoefficient, newFrontalArea, newRRC, newWheel, velocityLimits, energy);
                vehicles.add(vehicle);
            }

            project.addVehicles(vehicles);
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            return false;
        }
        return true;
    }

    @Override
    public RoadNetwork importNetwork(boolean newNetwork) throws Exception {
        return null;
    }

    private VehicleType addVehicleType(Node attribute) {
        String newVehicleType = attribute.getTextContent();
        VehicleType[] typeOfVehicle = VehicleType.values();
        for (VehicleType type : typeOfVehicle) {
            if (newVehicleType.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        return VehicleType.Car;
    }

    private MotorType addMotorization(Node attribute) {
        if (attribute.getTextContent().equalsIgnoreCase("combustion")) {
            return Vehicle.MotorType.COMBUSTION;
        } else if (attribute.getTextContent().equalsIgnoreCase("electric")) {
            return Vehicle.MotorType.NONCOMBUSTION;
        }
        return Vehicle.MotorType.COMBUSTION;
    }

    private Fuel addFuel(Node attribute) {
        String newFuel = attribute.getTextContent();
        Fuel[] fuelEnum = Fuel.values();
        for (Fuel fuelType : fuelEnum) {
            if (newFuel.equalsIgnoreCase(fuelType.toString())) {
                return fuelType;
            }
        }
        return Fuel.Gasoline;
    }

    private Measurable addMass(Node attribute) {
        String x = attribute.getTextContent();
        String[] splitX = x.split(" ");
        double newMass = Double.parseDouble(splitX[0]);
        String massUnit = splitX[1];
        if (massUnit.equalsIgnoreCase("kg")) {
            return new Measurable(newMass, Unit.KILOGRAM);
        } else if (massUnit.equalsIgnoreCase("g")) {
            return new Measurable(newMass, Unit.GRAM);
        }
        return new Measurable(newMass, Unit.KILOGRAM);
    }

    private Measurable addLoad(Node attribute) {
        String y = attribute.getTextContent();
        String[] splity = y.split(" ");
        double newLoad = Double.parseDouble(splity[0]);
        String loadUnit = splity[1];
        if (loadUnit.equalsIgnoreCase("kg")) {
            return new Measurable(newLoad, Unit.KILOGRAM);
        } else if (loadUnit.equalsIgnoreCase("g")) {
            return new Measurable(newLoad, Unit.GRAM);
        }
        return new Measurable(newLoad, Unit.KILOGRAM);
    }

    private List<VelocityLimit> readVelocityLimits(Node attribute) {

        String segmentType = "";

        List<VelocityLimit> velocityLimits = new ArrayList<>();

        NodeList velocityLimitList = attribute.getChildNodes();
        for (int i = 0; i < velocityLimitList.getLength(); i++) {
            Node velocityLimitListNode = velocityLimitList.item(i);

            NodeList velocityLimit = velocityLimitListNode.getChildNodes();
            for (int j = 0; j < velocityLimit.getLength(); j++) {
                Node velocityLimitNode = velocityLimit.item(j);

                /*
                Read Segment Type
                 */
                if (velocityLimitNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (velocityLimitNode.getNodeName().equalsIgnoreCase("segment_type")) {
                        segmentType = velocityLimitNode.getTextContent();
                    }

                    /*
                    Read Limit
                     */
                    else if (velocityLimitNode.getNodeName().equalsIgnoreCase("limit")) {

                        String string = velocityLimitNode.getTextContent().replaceAll("\\s+", "");
                        String[] stringSplit = string.split(" ");
                        double limit;

                        //Read Unit, if unit is present after the limit quantity
                        if (stringSplit.length == 2) {
                            limit = Double.parseDouble(stringSplit[0]);
                            String newVelocity = stringSplit[1];

                            if (newVelocity.equalsIgnoreCase("km/h")) {
                                Measurable newVelocityLimitValue = new Measurable(limit, Unit.KILOMETERS_PER_HOUR);
                                velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));

                            } else if (newVelocity.equalsIgnoreCase("mp/h")) {

                                Measurable newVelocityLimitValue = new Measurable(limit, Unit.MILES_PER_HOUR);
                                velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));

                            } else if (newVelocity.equalsIgnoreCase("m/s")) {

                                Measurable newVelocityLimitValue = new Measurable(limit, Unit.METERS_PER_SECOND);
                                velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));

                            }

                            //Assume limit is expressed in the file in km/h
                        } else if (stringSplit.length == 1) {
                            limit = Double.parseDouble(stringSplit[0]);

                            Measurable newVelocityLimitValue = new Measurable(limit, Unit.KILOMETERS_PER_HOUR);
                            velocityLimits.add(new VelocityLimit(segmentType, newVelocityLimitValue));
                        }
                    }
                }
            }
        }
        return velocityLimits;
    }

    private Energy addEnergy(Node attribute) {

        int minRpm = 0;
        int maxRpm = 0;
        float finalDriveRatio = 0f;
        List<Gears> gearBox = new ArrayList<>();
        List<Throttle> throttles = new ArrayList<>();

        NodeList energyList = attribute.getChildNodes();
        for (int j = 0; j < energyList.getLength(); j++) {
            Node energyNode = energyList.item(j);
            if (energyNode.getNodeType() == Node.ELEMENT_NODE) {

                /*
                Min RPM
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("min_rpm")) {
                    minRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /*
                Max RPM
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("max_rpm")) {
                    maxRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /*
                Final Drive ratio
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("final_drive_ratio")) {
                    finalDriveRatio = Float.parseFloat(energyNode.getTextContent());
                }
                /*
                Gear list
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("gear_list")) {
                    gearBox = addGearList(energyNode);
                }
                /*
                Throttle List
                 */
                else if (energyNode.getNodeName().equalsIgnoreCase("throttle_list")) {
                    throttles = addThrottleList(energyNode);
                    break;
                }

            }

        }
        return new Energy(minRpm, maxRpm, finalDriveRatio, gearBox, throttles);
    }

    private List<Gears> addGearList(Node energyNode) {

        List<Gears> gears = new ArrayList<>();

        NodeList gearListList = energyNode.getChildNodes();
        for (int k = 1; k < gearListList.getLength(); k++) {
            Node gearListNode = gearListList.item(k);

            /*
            ID
             */
            if (gearListNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gearElement = (Element) gearListNode;
                int newGearId = Integer.parseInt(gearElement.getAttribute("id"));

                NodeList gearList = gearListNode.getChildNodes();
                for (int l = 1; l < gearList.getLength(); l++) {
                    Node gearNode = gearList.item(l);

                    /*
                    Ratio
                     */
                    if (gearNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (gearNode.getNodeName().equalsIgnoreCase("ratio")) {
                            float newRatio = Float.parseFloat(gearNode.getTextContent());
                            Gears newGear = new Gears(newGearId, newRatio);
                            gears.add(newGear);
                        }
                    }
                }
            }

        }
        return gears;
    }

    private List<Throttle> addThrottleList(Node energyNode) {

        int throttleId, torqueLow = 0, torqueHigh = 0, rpmLow = 0, rpmHigh = 0;
        List<Throttle> throttles = new ArrayList<>();
        double sfc = 0;

        NodeList throttleList = energyNode.getChildNodes();
        for (int i = 0; i < throttleList.getLength(); i++) {

            Node throttleNode = throttleList.item(i);
            if (throttleNode.getNodeType() == Node.ELEMENT_NODE) {

                Element throttleElement = (Element) throttleNode;
                throttleId = Integer.parseInt(throttleElement.getAttribute("id"));
                NodeList regimeList = throttleNode.getChildNodes();

                List<Regime> regimes = new ArrayList<>();

                for (int j = 0; j < regimeList.getLength(); j++) {
                    Node regimeNode = regimeList.item(j);
                    NodeList regimeChildList = regimeNode.getChildNodes();


                    if (regimeChildList.getLength() != 0) {

                        //Read regime
                        for (int k = 0; k < regimeChildList.getLength(); k++) {
                            Node regimeChild = regimeChildList.item(k);

                            if (regimeChild.getNodeName().equalsIgnoreCase("torque_low")) {

                                torqueLow = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("torque_high")) {

                                torqueHigh = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("rpm_low")) {

                                rpmLow = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("rpm_high")) {

                                rpmHigh = Integer.parseInt(regimeChild.getTextContent());

                            } else if (regimeChild.getNodeName().equalsIgnoreCase("SFC")) {
                                sfc = Double.parseDouble(regimeChild.getTextContent());
                                break;
                            }

                        }

                        //Add regime
                        Regime newRegime;
                        if (sfc != 0) {
                            newRegime = new Regime(torqueLow, torqueHigh, rpmLow, rpmHigh, sfc);
                        } else {
                            newRegime = new Regime(torqueLow, torqueHigh, rpmLow, rpmHigh);
                        }
                        regimes.add(newRegime);
                    }

                }

                Throttle throttle = new Throttle(throttleId, regimes);
                throttles.add(throttle);

            }

        }

        return throttles;
    }

    /**
     * If we get the name search for that name. save the spot we started. start searching after that
     * @param list
     * @param name
     * @return
     */
    String addName(List<Vehicle> list, String name) {

        for (Vehicle v : list) {
            if (v.getName().equalsIgnoreCase(name)) {
                name += id;
                id++;
            }
        }

        return name;

    }

}
