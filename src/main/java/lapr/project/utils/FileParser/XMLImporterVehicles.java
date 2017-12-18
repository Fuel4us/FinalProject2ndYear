package lapr.project.utils.FileParser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.*;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import lapr.project.model.Vehicle.*;


/**
 * 
 * @author goncalo
 */
public class XMLImporterVehicles implements FileParser {

    
    public boolean importVehicles(Project object, String filename) {
        try {
            // Initialize all variables
            String newName = "N/A";
            String newDescription = "N/A";
            String newType = "N/A";
            int newTollClass = 0;
            String newMotorization = "N/A";
            String newFuel = "N/A";
            int newMass = 0;
            int newLoad = 0;
            float newDrag = 0F;
            float newFrontalArea = 0F;
            float newRrc = 0F;
            float newWheelSize = 0F;
            List<VelocityLimit> newVelocityLimitList = new ArrayList<VelocityLimit>();
            VelocityLimit newVelocityLimit = null;
            String newSegmentType = "N/A";
            int newLimit = 0;
            int newMinRpm = 0;
            int newMaxRpm = 0;
            float newFinalDriveRatio = 0F;
            Gears newGear = null;
            int newGearId = 0;
            float newRatio = 0F;
            List<Gears> newGearList = new ArrayList<>();
            Energy newEnergy = null;
            int newThrottleId = 0;
            int newTorque = 0;
            int newRpmLow = 0;
            int newRpmHigh = 0;
            int newSfc = 0;
            Regime newRegime = null;
            List<Regime> newRegimeList = new ArrayList<>();
            Throttle newThrottle = null;
            List<Throttle> newThrottleList = new ArrayList<>();
            Vehicle newVehicle = null;
            
            // Get vehicleList
            List<Vehicle> set = new ArrayList<>();

            // Start Parser
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            // Get vehicle attributes
            NodeList vehicleList = doc.getElementsByTagName("vehicle");

            Node vehicleNode = vehicleList.item(0);
            Element nameElement = (Element) vehicleNode;

            newName = nameElement.getAttribute("name");
            newDescription = nameElement.getAttribute("description");

            // Get vehicle nodes
            for (int temp = 0; temp < vehicleList.getLength(); temp++) {
                Node vehicle = vehicleList.item(temp);

                if (vehicle.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList vehicleAttributes = vehicle.getChildNodes();

                    for (int i = 0; i < vehicleAttributes.getLength(); i++) {
                        Node attribute = vehicleAttributes.item(i);

                        if (attribute.getNodeType() == Node.ELEMENT_NODE) {

                            if (attribute.getNodeName().equals("type")) {
                                newType = attribute.getTextContent();
                                
                            }
                            if (attribute.getNodeName().equals("toll_class")) {
                                newTollClass = Integer.parseInt(attribute.getTextContent());
                            }
                            if (attribute.getNodeName().equals("motorization")) {
                                newMotorization = attribute.getTextContent();
                            }
                            if (attribute.getNodeName().equals("fuel")) {
                                newFuel = attribute.getTextContent();
                            }
                            if (attribute.getNodeName().equals("mass")) {
                                String x = attribute.getTextContent();
                                String[] splitX = x.split(" ");
                                newMass = Integer.parseInt(splitX[0]);

                            }
                            if (attribute.getNodeName().equals("load")) {
                                String y = attribute.getTextContent();
                                String[] splity = y.split(" ");
                                newLoad = Integer.parseInt(splity[0]);
                            }
                            if (attribute.getNodeName().equals("drag")) {
                                newDrag = Float.parseFloat(attribute.getTextContent());
                            }
                            if (attribute.getNodeName().equals("frontal_area")) {
                                newFrontalArea = Float.parseFloat(attribute.getTextContent());
                            }
                            if (attribute.getNodeName().equals("rrc")) {
                                newRrc = Float.parseFloat(attribute.getTextContent());
                            }
                            if (attribute.getNodeName().equals("whell_size")) {
                                newWheelSize = Float.parseFloat(attribute.getTextContent());
                            }
                            if (attribute.getNodeName().equals("velocity_limit_list")) {

                            }
                            NodeList velocityLimitList = attribute.getChildNodes();
                            for (int we = 0; we < velocityLimitList.getLength(); we++) {
                                Node velocityLimitListNode = velocityLimitList.item(we);

                                NodeList velocityLimit = velocityLimitListNode.getChildNodes();
                                for (int xu = 0; xu < velocityLimit.getLength(); xu++) {
                                    Node velocityLimitNode = velocityLimit.item(xu);

                                    if (velocityLimitNode.getNodeType() == Node.ELEMENT_NODE) {
                                        if (velocityLimitNode.getNodeName().equals("segment_type")) {
                                            newSegmentType = velocityLimitNode.getTextContent();
                                        }
                                        if (velocityLimitNode.getNodeName().equals("limit")) {
                                            newLimit = Integer.parseInt(velocityLimitNode.getTextContent());
                                        }
                                    }
                                    newVelocityLimit.setTypeSegment(newSegmentType);
                                    newVelocityLimit.setLimit(newLimit);
                                    newVelocityLimitList.add(newVelocityLimit);
                                }
                            }
                        }
                        if (attribute.getNodeName().equals("energy")) {

                            NodeList energyList = attribute.getChildNodes();
                            for (int j = 0; j < energyList.getLength(); j++) {
                                Node energyNode = energyList.item(j);
                                if (energyNode.getNodeType() == Node.ELEMENT_NODE) {
                                    if (energyNode.getNodeName().equals("min_rpm")) {
                                        newMinRpm = Integer.parseInt(energyNode.getTextContent());
                                    }
                                    if (energyNode.getNodeName().equals("max_rpm")) {
                                        newMaxRpm = Integer.parseInt(energyNode.getTextContent());
                                    }
                                    if (energyNode.getNodeName().equals("final_drive_ratio")) {
                                        newFinalDriveRatio = Float.parseFloat(energyNode.getTextContent());
                                    }
                                    if (energyNode.getNodeName().equals("gear_list")) {
                                        NodeList gearListList = energyNode.getChildNodes();
                                        for (int k = 0; k < gearListList.getLength(); k++) {
                                            Node gearListNode = gearListList.item(k);
                                            if (gearListNode.getNodeType() == Node.ELEMENT_NODE) {
                                                Element gearElement = (Element) gearListNode;
                                                newGearId = Integer.parseInt(gearElement.getAttribute("id"));

                                                NodeList gearList = gearListNode.getChildNodes();
                                                for (int l = 0; l < gearList.getLength(); l++) {
                                                    Node gearNode = gearList.item(l);
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
                                            newThrottle = new Throttle(newThrottleId,newRegimeList);
                                            newThrottleList.add(newThrottle);
                                        }

                                    }

                                }

                            }
                            newEnergy = new Energy(newMinRpm, newMaxRpm, newFinalDriveRatio, newGearList, newThrottleList);
                        }
                    }

                }
                newVehicle = new Vehicle(newName, newDescription, newType, newTollClass, newMotorization, newFuel, newMass, newLoad, newDrag, newFrontalArea, newRrc, newWheelSize, newVelocityLimitList, newEnergy);
                set.add(newVehicle);
            }

            object.setVehicles(set);
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            return false;
        }
        return true;
    }
    
}

