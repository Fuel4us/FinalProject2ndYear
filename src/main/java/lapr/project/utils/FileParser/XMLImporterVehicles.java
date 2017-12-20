package lapr.project.utils.FileParser;

import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.*;
import lapr.project.model.Vehicle.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import lapr.project.model.Vehicle.Vehicle.MotorType;
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

/**
 * Import of vehicles from XML
 */
public class XMLImporterVehicles implements FileParser {

    private int id = 1;

    @Override
    public boolean importVehicles(Project project, String filename) {

        try {

            /**
             * Initiate Variables
             */
            String name = "Default";
            String description = "Default";

            VehicleType vehicleType = null;
            String newVehicleType = "Default";

            int newTollClass = 0;

            String newMotorization = "Default";
            MotorType motorTypeValue = null;

            Fuel fuel = null;
            String newFuel = "Default";

            Measurable mass = null;
            Measurable load = null;
            String massUnit = "";
            String loadUnit = "";
            double newMass = 0;
            double newLoad = 0;

            float dragCoefficient = 0;
            Measurable newFrontalArea = new Measurable(0, Unit.METER_SQUARED);
            float newRRC = 0;
            Measurable newWheel = new Measurable(0, Unit.METER);

            List<VelocityLimit> newVelocityLimitList = new ArrayList<>();
            VelocityLimit newVelocityLimit = new VelocityLimit();
            String newSegmentType = "Default";
            String newVelocity = "Default";
            Measurable newVelocityLimitValue = null;
            double newLimit = 0;

            Energy newEnergy = null;
            int newMinRpm = 0;
            int newMaxRpm = 0;
            float newFinalDriveRatio = 0;
            Gears newGear = null;
            List<Gears> newGearList = new ArrayList<>();
            int newGearId = 0;
            float newRatio = 0;

            Regime newRegime = new Regime();
            int newThrottleId = 0;
            int newTorqueLow = 0;
            int newTorqueHigh = 0;
            int newRpmLow = 0;
            int newRpmHigh = 0;
            int newSfc = 0;

            List<Regime> newRegimeList = new ArrayList<>();
            Throttle newThrottle = null;
            List<Throttle> newThrottleList = new ArrayList<>();
            Vehicle newVehicle = null;

            // Get vehicleList
            List<Vehicle> set = new ArrayList<>();

            // Initiate parser
            File file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            // Get attributes
            NodeList vehicleList = doc.getElementsByTagName("vehicle");
            Node vehicleNode = vehicleList.item(0);
            Element nameElement = (Element) vehicleNode;

            // Get vehicle nodes
            for (int temp = 0; temp < vehicleList.getLength(); temp++) {
                Node vehicle = vehicleList.item(temp);

                // Name & Description
                name = addName(set, nameElement.getAttribute("name"));
                description = nameElement.getAttribute("description");

                /**
                 * Get vehicle attributes
                 */
                if (vehicle.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList vehicleAttributes = vehicle.getChildNodes();

                    for (int i = 0; i < vehicleAttributes.getLength(); i++) {
                        Node attribute = vehicleAttributes.item(i);

                        if (attribute.getNodeType() == Node.ELEMENT_NODE) {

                            /**
                             * Type of vehicle
                             */
                            if (attribute.getNodeName().equals("type")) {
                                vehicleType = addVehicleType(newVehicleType, attribute);
                            }

                            /**
                             * Toll_class
                             */
                            if (attribute.getNodeName().equals("toll_class")) {
                                newTollClass = Integer.parseInt(attribute.getTextContent());
                            }

                            /**
                             * Motorization type ENUM
                             */
                            if (attribute.getNodeName().equals("motorization")) {
                                motorTypeValue = addMotorization(newMotorization, attribute);
                            }

                            /**
                             * Fuel
                             */
                            if (attribute.getNodeName().equals("fuel")) {
                                fuel = addFuel(newFuel, attribute);
                            }

                            /**
                             * Mass from Measurable
                             */
                            if (attribute.getNodeName().equals("mass")) {
                                mass = addMass(newMass, massUnit, attribute);

                            }

                            /**
                             * Load from Measurable
                             */
                            if (attribute.getNodeName().equals("load")) {
                                load = addLoad(newLoad, loadUnit, attribute);
                            }

                            /**
                             * Drag
                             */
                            if (attribute.getNodeName().equals("drag")) {
                                dragCoefficient = Float.parseFloat(attribute.getTextContent());
                            }

                            /**
                             * Frontal Area
                             */
                            if (attribute.getNodeName().equals("frontal_area")) {
                                newFrontalArea.setQuantity(Double.parseDouble(attribute.getTextContent()));
                            }
                            /**
                             * RollingReleaseCoefficient
                             */
                            if (attribute.getNodeName().equals("rrc")) {
                                newRRC = Float.parseFloat(attribute.getTextContent());
                            }

                            /**
                             * Wheel size
                             */
                            if (attribute.getNodeName().equals("wheel_size")) {
                                newWheel.setQuantity(Double.parseDouble(attribute.getTextContent()));
                            }

                            /**
                             * VelocityLimitList
                             */
                            if (attribute.getNodeName().equals("velocity_limit_list")) {
                                velocityLimitList(newLimit, newSegmentType,
                                        newVelocity, newVelocityLimitValue,
                                        newVelocityLimitList, newVelocityLimit,
                                        attribute);
                            }
                        }

                        /**
                         * Energy
                         */
                        if (attribute.getNodeName().equals("energy")) {
                            newEnergy = addEnergy(newMinRpm, newMaxRpm, newFinalDriveRatio,
                                    newGearId, newRatio, newGear, newGearList,
                                    newThrottleId, newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh, newSfc,
                                    newRegime, newRegimeList, newThrottle,
                                    newThrottleList, attribute);
                        }
                    }

                }
                /**
                 * Create Vehicle
                 */
                newVehicle = new Vehicle(name, description, vehicleType, newTollClass, motorTypeValue, fuel, mass, load, dragCoefficient, newFrontalArea, newRRC, newWheel, newVelocityLimitList, newEnergy);
                set.add(newVehicle);
            }

            project.setVehicles(set);
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            return false;
        }
        return true;
    }

    public String addName(List<Vehicle> list, String name) {

        for (Vehicle v : list) {
            if (v.getName().equalsIgnoreCase(name)) {
                name += id;
                id++;
            }
        }

        return name;

    }

    public VehicleType addVehicleType(String newVehicleType, Node attribute) {
        newVehicleType = attribute.getTextContent();
        VehicleType[] typeOfVehicle = VehicleType.values();
        for (VehicleType type : typeOfVehicle) {
            String typeStr = newVehicleType;
            if (typeStr.equals(type.toString())) {
                return type;
            }
        }
        return VehicleType.Car;
    }

    public MotorType addMotorization(String newMotorization, Node attribute) {
        newMotorization = attribute.getTextContent();
        String newMoto = newMotorization;
        if (newMoto.equalsIgnoreCase("combustion")) {
            return Vehicle.MotorType.COMBUSTION;
        } else if (newMoto.equalsIgnoreCase("electric")) {
            return Vehicle.MotorType.NONCOMBUSTION;
        }
        System.out.println("Mass unit not correct the value is now the default (combustion)");
        return Vehicle.MotorType.COMBUSTION;
    }

    public Fuel addFuel(String newFuel, Node attribute) {
        newFuel = attribute.getTextContent();
        Fuel[] fuelEnum = Fuel.values();
        for (Fuel fuelType : fuelEnum) {
            String fuelStr = newFuel;
            if (fuelStr.equals(fuelType.toString())) {
                return fuelType;
            }
        }
        System.out.println("Fuel unit not correct the value is now the default (gasoline)");
        return Fuel.Gasoline;
    }

    public Measurable addMass(double newMass, String massUnit, Node attribute) {
        String x = attribute.getTextContent();
        String[] splitX = x.split(" ");
        newMass = Double.parseDouble(splitX[0]);
        massUnit = splitX[1];
        if (massUnit.equals("kg")) {
            return new Measurable(newMass, Unit.KILOGRAM);
        } else if (massUnit.equals("g")) {
            return new Measurable(newMass, Unit.GRAM);
        }
        System.out.println("Mass unit not correct the value is now the default (kg)");
        return new Measurable(newMass, Unit.KILOGRAM);
    }

    public Measurable addLoad(double newLoad, String loadUnit, Node attribute) {
        String y = attribute.getTextContent();
        String[] splity = y.split(" ");
        newLoad = Double.parseDouble(splity[0]);
        loadUnit = splity[1];
        if (loadUnit.equals("kg")) {
            return new Measurable(newLoad, Unit.KILOGRAM);
        } else if (loadUnit.equals("g")) {
            return new Measurable(newLoad, Unit.GRAM);
        }
        System.out.println("Load unit not correct the value is now the default (kg)");
        return new Measurable(newLoad, Unit.KILOGRAM);
    }

    public void velocityLimitList(double newLimit, String newSegmentType,
            String newVelocity, Measurable newVelocityLimitValue,
            List<VelocityLimit> newVelocityLimitList, VelocityLimit newVelocityLimit,
            Node attribute) {
        NodeList velocityLimitList = attribute.getChildNodes();
        for (int we = 0; we < velocityLimitList.getLength(); we++) {
            Node velocityLimitListNode = velocityLimitList.item(we);

            NodeList velocityLimit = velocityLimitListNode.getChildNodes();
            for (int xu = 0; xu < velocityLimit.getLength(); xu++) {
                Node velocityLimitNode = velocityLimit.item(xu);
                /*
                                        *Segment Type
                 */
                if (velocityLimitNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (velocityLimitNode.getNodeName().equals("segment_type")) {
                        newSegmentType = velocityLimitNode.getTextContent();
                    }
                    /**
                     * Velocity Limit
                     */
                    if (velocityLimitNode.getNodeName().equals("limit")) {
                        String string = attribute.getTextContent();
                        String[] stringSplit = string.split(" ");
                        if (stringSplit.length == 2) {
                            newLimit = Double.parseDouble(stringSplit[0]);
                            newVelocity = stringSplit[1];
                            if (newVelocity.equalsIgnoreCase("km/h")) {
                                newVelocityLimitValue = new Measurable(newLimit, Unit.KILOMETERS_PER_HOUR);
                            } else if (newVelocity.equalsIgnoreCase("mp/h")) {
                                newVelocityLimitValue = new Measurable(newLimit, Unit.MILES_PER_HOUR);
                            } else if (newVelocity.equalsIgnoreCase("m/s")) {
                                newVelocityLimitValue = new Measurable(newLimit, Unit.METERS_PER_SECOND);
                            }
                        }else{
                            newVelocityLimitValue = new Measurable(newLimit, Unit.KILOMETERS_PER_HOUR);
                        }
                    }
                }
                newVelocityLimit.setSegmentType(newSegmentType);
                newVelocityLimit.setLimit(newVelocityLimitValue);
                newVelocityLimitList.add(newVelocityLimit);
            }
        }
    }

    public Energy addEnergy(int newMinRpm, int newMaxRpm, float newFinalDriveRatio,
            int newGearId, float newRatio, Gears newGear, List<Gears> newGearList,
            int newThrottleId, int newTorqueLow, int newTorqueHigh, int newRpmLow, int newRpmHigh, int newSfc,
            Regime newRegime, List<Regime> newRegimeList, Throttle newThrottle,
            List<Throttle> newThrottleList, Node attribute) {
        NodeList energyList = attribute.getChildNodes();
        for (int j = 0; j < energyList.getLength(); j++) {
            Node energyNode = energyList.item(j);
            if (energyNode.getNodeType() == Node.ELEMENT_NODE) {
                /**
                 * Min RPM
                 */
                if (energyNode.getNodeName().equals("min_rpm")) {
                    newMinRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /**
                 * Max RPM
                 */
                if (energyNode.getNodeName().equals("max_rpm")) {
                    newMaxRpm = Integer.parseInt(energyNode.getTextContent());
                }
                /**
                 * Final Drive ratio
                 */
                if (energyNode.getNodeName().equals("final_drive_ratio")) {
                    newFinalDriveRatio = Float.parseFloat(energyNode.getTextContent());
                }
                /**
                 * Gear list
                 */
                if (energyNode.getNodeName().equals("gear_list")) {
                    addGearList(energyNode, newGearId, newRatio, newGear, newGearList);
                }
                /**
                 * Throttle List
                 */
                if (energyNode.getNodeName().equals("throttle_list")) {
                    addThrottleList(energyNode, newThrottleId,
                            newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh,
                             newSfc, newRegime, newRegimeList,
                             newThrottle, newThrottleList);
                }

            }

        }
        return new Energy(newMinRpm, newMaxRpm, newFinalDriveRatio, newGearList, newThrottleList);
    }

    public void addGearList(Node energyNode, int newGearId, float newRatio, Gears newGear, List<Gears> newGearList) {
        NodeList gearListList = energyNode.getChildNodes();
        for (int k = 0; k < gearListList.getLength(); k++) {
            Node gearListNode = gearListList.item(k);
            /**
             * ID
             */
            if (gearListNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gearElement = (Element) gearListNode;
                newGearId = Integer.parseInt(gearElement.getAttribute("id"));

                NodeList gearList = gearListNode.getChildNodes();
                for (int l = 0; l < gearList.getLength(); l++) {
                    Node gearNode = gearList.item(l);
                    /**
                     * Ratio
                     */
                    if (gearNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (energyNode.getNodeName().equals("ratio")) {
                            newRatio = Float.parseFloat(gearNode.getTextContent());
                        }
                    }
                    newGear = new Gears(newGearId, newRatio);
                    newGearList.add(newGear);

                }
            }

        }
    }

    public void addThrottleList(Node energyNode, int newThrottleId,
            int newTorqueLow, int newTorqueHigh, int newRpmLow, int newRpmHigh,
             int newSfc, Regime newRegime, List<Regime> newRegimeList,
             Throttle newThrottle, List<Throttle> newThrottleList) {
        NodeList throttleList = energyNode.getChildNodes();
        for (int k = 0; k < throttleList.getLength(); k++) {
            Node throttleNode = throttleList.item(k);
            if (throttleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element throttleElement = (Element) throttleNode;
                newThrottleId = Integer.parseInt(throttleElement.getAttribute("id"));
                NodeList regimeList = throttleNode.getChildNodes();
                for (int line = 0; line < regimeList.getLength(); line++) {
                    Node regimeNode = regimeList.item(line);
                    if (regimeNode.getNodeName().equals("torque_low")) {
                        newTorqueLow = Integer.parseInt(energyNode.getTextContent());
                    }
                    if (regimeNode.getNodeName().equals("torque_high")) {
                        newTorqueHigh = Integer.parseInt(energyNode.getTextContent());
                    }

                    if (regimeNode.getNodeName().equals("rpm_low")) {
                        newRpmLow = Integer.parseInt(energyNode.getTextContent());
                    }
                    if (regimeNode.getNodeName().equals("rpm_high")) {
                        newRpmHigh = Integer.parseInt(energyNode.getTextContent());
                    }
                    if (regimeNode.getNodeName().equals("SFC")) {
                        newSfc = Integer.parseInt(energyNode.getTextContent());
                    }

                    newRegime = new Regime(newTorqueLow, newTorqueHigh, newRpmLow, newRpmHigh, newSfc);
                    newRegimeList.add(newRegime);

                }
            }
            newThrottle = new Throttle(newThrottleId, newRegimeList);
            newThrottleList.add(newThrottle);
        }
    }
}
