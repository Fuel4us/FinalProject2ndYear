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
import lapr.project.utils.Measurable;
import lapr.project.utils.Unit;

/**
 * Import of vehicles from XML
 */
public class XMLImporterVehicles implements FileParser {

    public boolean importVehicles(Project object, String filename) {

        try {
            
            /**
             * Initiate Variables
             */
            
            String name = "Default";
            String description = "Default";

            VehicleType vehicleType = null;
            String newVehicleType = "Default";

            int newTollClass = 0;

            Motorization motorization = null;
            String newMotorization = "Default";

            Fuel fuel = null;
            String newFuel = "Default";

            Measurable mass = null;
            Measurable load = null;
            String massUnit;
            String loadUnit;
            int newMass = 0;
            int newLoad = 0;

            float dragCoefficient = 0;
            float newFrontalArea = 0;
            float newRRC = 0;
            float newWheel = 0;

            List<VelocityLimit> newVelocityLimitList = new ArrayList<VelocityLimit>();
            VelocityLimit newVelocityLimit = new VelocityLimit();
            String newSegmentType = "Default";
            int newLimit = 0;

            Energy newEnergy = null;
            int newMinRpm = 0;
            int newMaxRpm = 0;
            float newFinalDriveRatio = 0;
            Gears newGear = null;
            List<Gears> newGearList = new ArrayList<>();
            int newGearId = 0;
            float newRatio = 0;

            Regime newRegime = null;
            int newThrottleId = 0;
            int newTorque = 0;
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
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            // Get attributes
            NodeList vehicleList = doc.getElementsByTagName("vehicle");
            Node vehicleNode = vehicleList.item(0);
            Element nameElement = (Element) vehicleNode;

            // Name & Description
            name = nameElement.getAttribute("name");
            description = nameElement.getAttribute("description");

            // Get vehicle nodes
            for (int temp = 0; temp < vehicleList.getLength(); temp++) {
                Node vehicle = vehicleList.item(temp);

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
                                newVehicleType = attribute.getTextContent();
                                VehicleType[] typeOfVehicle = VehicleType.values();
                                for (VehicleType type : typeOfVehicle) {
                                    String typeStr = newVehicleType;
                                    if (typeStr.equals(type.toString())) {
                                        vehicleType = type;
                                    }
                                }
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
                                newMotorization = attribute.getTextContent();
                                if (newMotorization.equalsIgnoreCase("combustion")) {
                                    motorization = new CombustionMotor();
                                } else {
                                    motorization = new NonCombustionMotor();
                                }
                            }
                            
                            /**
                             * Fuel
                             */
                            if (attribute.getNodeName().equals("fuel")) {
                                newFuel = attribute.getTextContent();
                                Fuel[] fuelEnum = Fuel.values();
                                for (Fuel fuelType : fuelEnum) {
                                    String fuelStr = newFuel;
                                    if (fuelStr.equals(fuelType.toString())) {
                                        fuel = fuelType;
                                    }
                                }
                            }
                            
                            /**
                             * Mass from Measurable
                             */
                            if (attribute.getNodeName().equals("mass")) {
                                String x = attribute.getTextContent();
                                String[] splitX = x.split(" ");
                                newMass = Integer.parseInt(splitX[0]);
                                massUnit = splitX[1];
                                if(massUnit.equals("kg")){
                                    mass = new Measurable(newMass, Unit.KILOGRAM);
                                }else if(massUnit.equals("g")){
                                    mass = new Measurable(newMass, Unit.GRAM);
                                }
                                
                            }
                            
                            /**
                             * Load from Measurable
                             */
                            if (attribute.getNodeName().equals("load")) {
                                String y = attribute.getTextContent();
                                String[] splity = y.split(" ");
                                newLoad = Integer.parseInt(splity[0]);
                                loadUnit = splity[1];
                                if(loadUnit.equals("kg")){
                                    load = new Measurable(newLoad, Unit.KILOGRAM);
                                }else if(loadUnit.equals("g")){
                                    load = new Measurable(newLoad, Unit.GRAM
                                    );
                                }
                                
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
                                newFrontalArea = Float.parseFloat(attribute.getTextContent());
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
                            if (attribute.getNodeName().equals("whell_size")) {
                                newWheel = Float.parseFloat(attribute.getTextContent());
                            }
                            
                            /**
                             * VelocityLimitList
                             */
                            if (attribute.getNodeName().equals("velocity_limit_list")) {

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
                                                newLimit = Integer.parseInt(velocityLimitNode.getTextContent());
                                            }
                                        }
                                        newVelocityLimit.setSegmentType(newSegmentType);
                                        newVelocityLimit.setLimit(newLimit);
                                        newVelocityLimitList.add(newVelocityLimit);
                                    }
                                }
                            }
                        }
                        
                        /**
                         * Energy
                         */
                        if (attribute.getNodeName().equals("energy")) {

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
                                    /**
                                     * Throttle List
                                     */
                                    if (energyNode.getNodeName().equals("throttle_list")) {
                                        NodeList throttleList = energyNode.getChildNodes();
                                        for (int k = 0; k < throttleList.getLength(); k++) {
                                            Node throttleNode = throttleList.item(k);
                                            if (throttleNode.getNodeType() == Node.ELEMENT_NODE) {
                                                Element throttleElement = (Element) throttleNode;
                                                newThrottleId = Integer.parseInt(throttleElement.getAttribute("id"));
                                                NodeList regimeList = throttleNode.getChildNodes();
                                                for (int l = 0; l < regimeList.getLength(); l++) {
                                                    Node regimeNode = throttleList.item(l);
                                                    if (regimeNode.getNodeName().equals("torque")) {
                                                        newTorque = Integer.parseInt(energyNode.getTextContent());
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

                                                    newRegime = new Regime(newTorque, newRpmLow, newRpmHigh, newSfc);
                                                    newRegimeList.add(newRegime);

                                                }
                                            }
                                            newThrottle = new Throttle(newThrottleId, newRegimeList);
                                            newThrottleList.add(newThrottle);
                                        }

                                    }

                                }

                            }
                            newEnergy = new Energy(newMinRpm, newMaxRpm, newFinalDriveRatio, newGearList, newThrottleList);
                        }
                    }

                }
                /**
                 * Create Vehicle
                 */
                newVehicle = new Vehicle(name, description, vehicleType, newTollClass, motorization, fuel, mass, load, dragCoefficient, newFrontalArea, newRRC, newWheel, newVelocityLimitList, newEnergy);
                set.add(newVehicle);
            }

            object.setVehicles(set);
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            return false;
        }
        return true;
    }

}
