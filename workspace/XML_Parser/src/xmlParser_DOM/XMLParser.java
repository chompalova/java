package xmlParser_DOM;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import xmlParser_DOM.Parameter;

public class XMLParser {
  
  private static Document doc;  
  private static List<ZWaveCmdClass> cmdClasses = new ArrayList<ZWaveCmdClass>();
    
  /*public static List<ZWaveCmdClass> getCmdClasses() {
    return cmdClasses;
  }*/

  public static void parse(File file) {
    try {      
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
      if (file != null) {
        doc = docBuilder.parse(file);            
      }
      doc.getDocumentElement().normalize();
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();        
    }
  }  
  
  public static String getNodeValue (NamedNodeMap attributes, String attribute) {
    Node node = null;
    String value = null;                     
    node = attributes.getNamedItem(attribute);
    if (node != null) {
      value = node.getNodeValue();
    }
    return value;
  }
  
  public static void createCmdClass() {    
    NodeList cmdClassNodes = doc.getElementsByTagName("cmd_class");//get command classes
    NamedNodeMap childAttributes = null;
    //int len = cmdClassNodes.getLength();    
    for (int i = 0; i < cmdClassNodes.getLength(); i++) {      
      ZWaveCmdClass cmdClass = new ZWaveCmdClass();
      Node nNode = cmdClassNodes.item(i);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        NamedNodeMap attributes = nNode.getAttributes();
        String value = getNodeValue(attributes, "key");
        cmdClass.setKeyId(value);
        value = getNodeValue(attributes, "version");
        cmdClass.setVersion(value);
        value = getNodeValue(attributes, "name");
        cmdClass.setName(value);
        value = getNodeValue(attributes, "help");
        cmdClass.setHumanReadableName(value);
        NodeList cmdNodes = nNode.getChildNodes();//get command nodes
        List<Command> cmdObjects = new ArrayList<Command>();//commands
        for (int j = 0; j < cmdNodes.getLength(); j++) {
          nNode = cmdNodes.item(j);
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            childAttributes = nNode.getAttributes();            
            Command cmd = createCmd(nNode, childAttributes);
            cmdObjects.add(cmd);            
          }
        }
        cmdClass.setCommands(cmdObjects);        
        cmdClasses.add(cmdClass);
      }      
    }
  }                 
    
  public static Command createCmd(Node nNode, NamedNodeMap attributes) {
    Command cmd = new Command();
    String value = getNodeValue(attributes, "key");
    cmd.setKeyId(value);    
    value = getNodeValue(attributes, "name");
    cmd.setName(value);
    value = getNodeValue(attributes, "help");
    cmd.setHumanReadableName(value);
    NodeList paramNodes = nNode.getChildNodes();//get param nodes
    List<Parameter> paramObjects = new ArrayList<Parameter>();//parameters
    for (int j = 0; j < paramNodes.getLength(); j++) {
      nNode = paramNodes.item(j);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        attributes = nNode.getAttributes();            
        Parameter param = createParam(nNode, attributes);
        paramObjects.add(param);            
      }
    }
    cmd.setParams(paramObjects); 
    return cmd;
  }  
 
  
  public static Parameter createParam(Node nNode, NamedNodeMap attributes) {
    Parameter param = new Parameter();
    Element el = (Element) nNode;
    String nodeName = el.getNodeName();
    if (nodeName.equals("param")) {
      String value = getNodeValue(attributes, "key");
      param.setKeyId(value);    
      value = getNodeValue(attributes, "name");
      param.setName(value);
      value = getNodeValue(attributes, "type");
      param.setType(ParamType.valueOf(value));
    } else if (nodeName.equals("variant_group")) {
        String value = getNodeValue(attributes, "key");
        param.setKeyId(value);    
        value = getNodeValue(attributes, "name");
        param.setName(value);
        value = getNodeValue(attributes, "variantKey");
        param.setVariantKey(value);
        value = getNodeValue(attributes, "paramOffs");
        param.setParamOffs(value);
        value = getNodeValue(attributes, "sizemask");
        param.setSizemask(value);
        value = getNodeValue(attributes, "sizeoffs");
        param.setSizeoffs(value);
        nNode = nNode.getFirstChild().getNextSibling();
        attributes = nNode.getAttributes();       
        createParam(nNode, attributes);
    }
    NodeList paramChildNodes = nNode.getChildNodes();//get param child nodes
    List<ParamChild> paramChildObjects = new ArrayList<ParamChild>();//param children
    for (int j = 0; j < paramChildNodes.getLength(); j++) {
      nNode = paramChildNodes.item(j);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        attributes = nNode.getAttributes();            
        ParamChild param_child = new ParamChild();
        ParamType type = param.getType();
        if (type != null) {
    	  param_child = createParamChild(type, nNode, attributes);
        }
        paramChildObjects.add(param_child);            
      }
    }
    param.setParamChildren(paramChildObjects); 
    return param;    
  }  
    
  public static ParamChild createParamChild(ParamType type, Node nNode, NamedNodeMap attributes) {
    ParamChild param_child = new ParamChild();                
    String value = null;    
    switch (type) {
      case BYTE: case CONST: case MARKER:                    
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
        value = getNodeValue(attributes, "flagname");   
        param_child.setFlagname(value);
        value = getNodeValue(attributes, "flagmask");   
        param_child.setFlagmask(value);                     
      break;
      case BITMASK: 
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
        value = getNodeValue(attributes, "flagname");   
        param_child.setFlagname(value);
        value = getNodeValue(attributes, "flagmask");   
        param_child.setFlagmask(value);
        value = getNodeValue(attributes, "paramoffs");
        param_child.setParamoffs(value);
        value = getNodeValue(attributes, "lenmask");
        param_child.setLenmask(value);
        value = getNodeValue(attributes, "lenoffs");
        param_child.setLenoffs(value);             
      break;
      case STRUCT_BYTE: 
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
        value = getNodeValue(attributes, "fieldname");
        param_child.setFieldname(value);
        value = getNodeValue(attributes, "fieldmask");
        param_child.setFieldmask(value);
        value = getNodeValue(attributes, "fieldenumvalue");//TODO        
        param_child.setFieldenumValue(value);
        value = getNodeValue(attributes, "flagname");   
        param_child.setFlagname(value);
        value = getNodeValue(attributes, "flagmask");   
        param_child.setFlagmask(value);
        value = getNodeValue(attributes, "shifter");
        param_child.setShifter(value);                
      break;      
      case MULTI_ARRAY: 
        nNode = nNode.getParentNode();
        Element el = (Element) nNode;
        NodeList list = el.getElementsByTagName("multi_array");
        List <Bitflag> bitflagObjects = new ArrayList<Bitflag>();
        for (int i = 0; i < list.getLength(); i++) {
          nNode = list.item(i);
          NodeList list1 = nNode.getChildNodes();
          for (int j = 0; j < list1.getLength(); j++) {
            nNode = list1.item(j);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Bitflag bitflag = new Bitflag();
              attributes = nNode.getAttributes();
              value = getNodeValue(attributes, "key");
              bitflag.setKeyId(value);
              value = getNodeValue(attributes, "param");
              bitflag.setParam(value);
              value = getNodeValue(attributes, "paramdesc");
              bitflag.setParamdesc(value);
              value = getNodeValue(attributes, "flagname");   
              bitflag.setFlagname(value);
              value = getNodeValue(attributes, "flagmask");   
              bitflag.setFlagmask(value);
              bitflagObjects.add(bitflag);
            }            
          }
          param_child.setChildren(bitflagObjects);
        }
      break;      
      case ENUM : case ENUM_ARRAY: //Not tested
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
        value = getNodeValue(attributes, "name");
        param_child.setName(value);
      break;
      case ARRAY:
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
        value = getNodeValue(attributes, "len");
        param_child.setLen(value);
      break;
      case VARIANT: 
        value = getNodeValue(attributes, "paramoffs");
        param_child.setParamoffs(value);
        value = getNodeValue(attributes, "sizemask");
        param_child.setSizemask(value);
        value = getNodeValue(attributes, "sizeoffs");
        param_child.setSizeoffs(value);        
      break;
      case WORD: case DWORD: case BIT_24:
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
      break;       
      default://If no type is specified
        value = getNodeValue(attributes, "key");
        param_child.setKeyId(value);
        value = getNodeValue(attributes, "name");
        param_child.setLen(value);
      break;
    }
    return param_child;
  }
  
  public static void printList() {
    File file = new File("./resources/log.txt");
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter(file));
      for (ZWaveCmdClass cmdClass : cmdClasses) {
        bw.write(cmdClass.toString());
        bw.newLine();
      }      
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
      try {
        bw.close();
      } catch (IOException e) {
          e.printStackTrace();        
      }      
    }    
  }

  public static List<ZWaveCmdClass> getCmdClasses() {
    // TODO Auto-generated method stub
    return cmdClasses;
  }
}