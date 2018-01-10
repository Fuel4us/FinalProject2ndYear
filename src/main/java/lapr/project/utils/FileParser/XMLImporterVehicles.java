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
import java.util.Iterator;
import java.util.List;

/**
 * Import of vehicles from XML
 */
public class XMLImporterVehicles implements FileParser {

    private static final String byDefault = "Default";
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

            List<VelocityLimit> newVelocityLimitList = new ArrayList<>();
            VelocityLimit newVelocityLimit = new VelocityLimit();

            Energy newEnergy = null;
            int newMinRpm = 0;
            int newMaxRpm = 0;
            float newFinalDriveRatio = 0;
            List<Gears> newGearList = new ArrayList<>();

            Regime newRegime = new Regime();
            int newThrottleId = 0;
            int newTorqueLow = 0;
            int newTorqueHigh = 0;
            int newRpmLow = 0;
            int newRpmHigh = 0;
            double newSfc = 0;

            List<Regime> newRegimeList = new ArrayList<>();
            List<Throttle> newThrottleList = new ArrayList<>();
            Vehicle newVehicle;

            // Get vehicleList
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
                Node vehicle = vehicleList.item(temp);
                Element nameElement = (Element) vehicle;
                // Name & Description
                name = addName(vehicles, nameElement.getAttribute("name"));
                description = nameElement.getAttribute("description");

                /*
                Get vehicle attributes
                 */
                if (vehicle.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList vehicleAttributes = vehicle.getChildNodes();

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
                                velocityLimitList(byDefault,
                                        newVelocityLimitList, newVelocityLimit,
                                        attribute);
                            }

                            /*
                             Energy
                             */
                            else if (attribute.getNodeName().equalsIgnoreCase("energy")) {
                                newEnergy = addEnergy(newMinRpm, newMaxRpm, newFinalDriveRatio,
                                        newGearList,
                                        newThrottleId, newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh, newSfc,
                                        newRegimeList,
                                        newThrottleList, attribute, motorTypeValue);
                            }

                        }
                    }

                }

                /*
                Create Vehicle
                 */
                newVehicle = new Vehicle(name, description, vehicleType, newTollClass, motorTypeValue, fuel, mass, load, dragCoefficient, newFrontalArea, newRRC, newWheel, newVelocityLimitList, newEnergy);
                vehicles.add(newVehicle);
            }

            project.setVehicles(vehicles);
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

    private void velocityLimitList(String newSegmentType, List<VelocityLimit> newVelocityLimitList, VelocityLimit newVelocityLimit, Node attribute) {

        NodeList velocityLimitList = attribute.getChildNodes();
        for (int i = 0; i < velocityLimitList.getLength(); i++) {
            Node velocityLimitListNode = velocityLimitList.item(i);

            NodeList velocityLimit = velocityLimitListNode.getChildNodes();
            for (int j = 0; j < velocityLimit.getLength(); j++) {
                Node velocityLimitNode = velocityLimit.item(j);

                /*
                Segment Type
                 */
                if (velocityLimitNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (velocityLimitNode.getNodeName().equalsIgnoreCase("segment_type")) {
                        newSegmentType = velocityLimitNode.getTextContent();
                    }
                    /**
                     * Velocity Limit
                     */
                    if (velocityLimitNode.getNodeName().equalsIgnoreCase("limit")) {
                        String string = velocityLimitNode.getTextContent().replaceAll("\\s+", "");
                        String[] stringSplit = string.split(" ");
                        double newLimit;
                        Measurable newVelocityLimitValue;
                        if (stringSplit.length == 2) {
                            newLimit = Double.parseDouble(stringSplit[0]);
                            String newVelocity = stringSplit[1];
                            if (newVelocity.equalsIgnoreCase("km/h")) {
                                newVelocityLimitValue = new Measurable(newLimit, Unit.KILOMETERS_PER_HOUR);
                                newVelocityLimit.setSegmentType(newSegmentType);
                                newVelocityLimit.setLimit(newVelocityLimitValue);
                                newVelocityLimitList.add(newVelocityLimit);
                            } else if (newVelocity.equalsIgnoreCase("mp/h")) {
                                newVelocityLimitValue = new Measurable(newLimit, Unit.MILES_PER_HOUR);
                                newVelocityLimit.setSegmentType(newSegmentType);
                                newVelocityLimit.setLimit(newVelocityLimitValue);
                                newVelocityLimitList.add(newVelocityLimit);
                            } else if (newVelocity.equalsIgnoreCase("m/s")) {
                                newVelocityLimitValue = new Measurable(newLimit, Unit.METERS_PER_SECOND);
                                newVelocityLimit.setSegmentType(newSegmentType);
                                newVelocityLimit.setLimit(newVelocityLimitValue);
                                newVelocityLimitList.add(newVelocityLimit);
                            }
                        } else if (stringSplit.length == 1) {
                            newLimit = Double.parseDouble(stringSplit[0]);
                            newVelocityLimitValue = new Measurable(newLimit, Unit.KILOMETERS_PER_HOUR);
                            newVelocityLimit.setSegmentType(newSegmentType);
                            newVelocityLimit.setLimit(newVelocityLimitValue);
                            newVelocityLimitList.add(newVelocityLimit);
                        }
                    }
                }
            }
        }
    }

    private Energy addEnergy(int newMinRpm, int newMaxRpm, float newFinalDriveRatio, List<Gears> newGearList,
                             int newThrottleId, int newTorqueLow, int newTorqueHigh, int newRpmLow, int newRpmHigh, double newSfc,
                             List<Regime> newRegimeList, List<Throttle> newThrottleList, Node attribute, MotorType motorTypeValue) {
        NodeList energyList = attribute.getChildNodes();
        for (int j = 0; j < energyList.getLength(); j++) {
            Node energyNode = energyList.item(j);
            if (energyNode.getNodeType() == Node.ELEMENT_NODE) {
                /**
                 * Min RPM
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("min_rpm")) {
                    newMinRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /**
                 * Max RPM
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("max_rpm")) {
                    newMaxRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /**
                 * Final Drive ratio
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("final_drive_ratio")) {
                    newFinalDriveRatio = Float.parseFloat(energyNode.getTextContent());
                }
                /**
                 * Gear list
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("gear_list")) {
                    addGearList(energyNode, newGearList);
                }
                /**
                 * Throttle List
                 */
                if (energyNode.getNodeName().equalsIgnoreCase("throttle_list")) {
                    addThrottleList(energyNode, newThrottleId,
                            newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh,
                            newSfc, newRegimeList,
                            newThrottleList, motorTypeValue);
                    break;
                }

            }

        }
        return new Energy(newMinRpm, newMaxRpm, newFinalDriveRatio, newGearList, newThrottleList);
    }

    private void addGearList(Node energyNode, List<Gears> newGearList) {
        NodeList gearListList = energyNode.getChildNodes();
        newGearList.clear();
        for (int k = 1; k < gearListList.getLength(); k++) {
            Node gearListNode = gearListList.item(k);
            /**
             * ID
             */
            if (gearListNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gearElement = (Element) gearListNode;
                int newGearId = Integer.parseInt(gearElement.getAttribute("id"));

                NodeList gearList = gearListNode.getChildNodes();
                for (int l = 1; l < gearList.getLength(); l++) {
                    Node gearNode = gearList.item(l);
                    /**
                     * Ratio
                     */
                    if (gearNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (gearNode.getNodeName().equalsIgnoreCase("ratio")) {
                            float newRatio = Float.parseFloat(gearNode.getTextContent());
                            Gears newGear = new Gears(newGearId, newRatio);
                            newGearList.add(newGear);
                        }
                    }
                }
            }

        }
    }

    private void addThrottleList(Node energyNode, int newThrottleId,
                                 int newTorqueLow, int newTorqueHigh, int newRpmLow, int newRpmHigh,
                                 double newSfc, List<Regime> newRegimeList,
                                 List<Throttle> newThrottleList, MotorType motorTypeValue) {
        NodeList throttleList = energyNode.getChildNodes();
        for (int k = 0; k < throttleList.getLength(); k++) {
            Node throttleNode = throttleList.item(k);
            if (throttleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element throttleElement = (Element) throttleNode;
                newThrottleId = Integer.parseInt(throttleElement.getAttribute("id"));
                NodeList regimeList = throttleNode.getChildNodes();
                for (int line = 0; line < regimeList.getLength(); line++) {
                    Node regimeNode = regimeList.item(line);
                    NodeList regimeChildList = regimeNode.getChildNodes();
                    for (int i = 0; i < regimeChildList.getLength(); i++) {
                        Node regimeChild = regimeChildList.item(i);
                        if (regimeChild.getNodeName().equalsIgnoreCase("torque_low")) {
                            newTorqueLow = Integer.parseInt(regimeChild.getTextContent());
                        }
                        if (regimeChild.getNodeName().equalsIgnoreCase("torque_high")) {
                            newTorqueHigh = Integer.parseInt(regimeChild.getTextContent());
                        }

                        if (regimeChild.getNodeName().equalsIgnoreCase("rpm_low")) {
                            newRpmLow = Integer.parseInt(regimeChild.getTextContent());
                        }
                        if (regimeChild.getNodeName().equalsIgnoreCase("rpm_high")) {
                            newRpmHigh = Integer.parseInt(regimeChild.getTextContent());
                            if (motorTypeValue.equals(MotorType.NONCOMBUSTION)) {
                                break;
                            }
                        }
                        if (regimeChild.getNodeName().equalsIgnoreCase("SFC")) {
                            newSfc = Double.parseDouble(regimeChild.getTextContent());
                            break;
                        }
                    }
                    Regime newRegime;
                    if (newSfc == 0) {
                        newRegime = new Regime(newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh);
                    } else {
                        newRegime = new Regime(newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh, newSfc);
                    }
                    newRegimeList.add(newRegime);

                }
            }
            Throttle newThrottle = new Throttle(newThrottleId, newRegimeList);
            newThrottleList.add(newThrottle);
        }
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
