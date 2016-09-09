package CodeGen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JEnumConstant;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

import xmlParser_DOM.Command;
import xmlParser_DOM.XMLParser;
import xmlParser_DOM.ZWaveCmdClass;
 
public class CodeGenCodeModel {
 
  public static void main(String ... args) {
    File file = new File ("./resources/ZWave_cmd_classes.xml");
    if (file != null) {
      XMLParser.parse(file);
    }    
    XMLParser.createCmdClass();
    //XMLParser.printList();
    try {
      CodeGenCodeModel.generateSource();
    } catch (JClassAlreadyExistsException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
  }
 
  public static void generateSource() throws JClassAlreadyExistsException, IOException {
    //Instantiate an instance of the JCodeModel class
    JCodeModel codeModel = new JCodeModel();
    List <ZWaveCmdClass> cmdClasses = XMLParser.getCmdClasses(); 
    //List <JDefinedClass> generatedCmdClasses = new ArrayList <JDefinedClass>();
    String className = null;
    for (int i = 0, j = (i + 1); j < cmdClasses.size(); i++, j++) {
      JDefinedClass generatedClass = null;
      ZWaveCmdClass previousCmdClass = cmdClasses.get(i);
      ZWaveCmdClass currentCmdClass = cmdClasses.get(j);
      String name1 = previousCmdClass.getName();
      String name2 = currentCmdClass.getName();      
      if (name1.equals(name2)) {
        String className1 = previousCmdClass.getName() + "_V" + previousCmdClass.getVersion();
        if (!className1.equals(className)) {
          generatedClass = codeModel._class(className1);
          //generatedCmdClasses.add(generatedClass);
          className = className1;
          if (generatedClass != null) {
            setFields(previousCmdClass, generatedClass, codeModel);
          }
        }
        String className2 = currentCmdClass.getName() + "_V" + currentCmdClass.getVersion();
        if (!className2.equals(className)) {
          generatedClass = codeModel._class(className2);
          //generatedCmdClasses.add(generatedClass);
          className = className2;
          if (generatedClass != null) {
            setFields(currentCmdClass, generatedClass, codeModel);
          }
        }          
      } else {
          String className3 = cmdClasses.get(j).getName();
          if (j != (cmdClasses.size() - 1)) {                       
            if (!className3.equals(cmdClasses.get(j+1).getName())) {
              generatedClass = codeModel._class(className3);
              //generatedCmdClasses.add(generatedClass);
              if (generatedClass != null) {
                setFields(currentCmdClass, generatedClass, codeModel);
              }
            }                 
          } else { //last element
              generatedClass = codeModel._class(className3);
              if (generatedClass != null) {
                setFields(currentCmdClass, generatedClass, codeModel);
              }
          }
      }      
    }
    generateClasses(codeModel);
  }
  
  public static void setFields(ZWaveCmdClass cmdClass, JDefinedClass generatedClass, JCodeModel codeModel) {        
    JFieldVar name = generatedClass.field(JMod.PRIVATE|JMod.FINAL, String.class, "name");
    JFieldVar version = generatedClass.field(JMod.PRIVATE|JMod.FINAL, codeModel.INT, "version");
    JMethod nameGetterMethod = generatedClass.method(JMod.PUBLIC, name.type(), "getName");
    JMethod nameSetterMethod = generatedClass.method(JMod.PUBLIC, codeModel.VOID, "setName");
    JMethod versionGetterMethod = generatedClass.method(JMod.PUBLIC, version.type(), "getVersion");
    JMethod versionSetterMethod = generatedClass.method(JMod.PUBLIC, codeModel.VOID, "setVersion");        
    setCommandField(cmdClass, generatedClass, codeModel);
  }
  
  public static void setCommandField(ZWaveCmdClass cmdClass, JDefinedClass generatedClass, JCodeModel codeModel) {   
    List<Command> commands = cmdClass.getCommands();
    for (Command command : commands) {
      JFieldVar cmd = generatedClass.field(JMod.PUBLIC|JMod.STATIC|JMod.FINAL, codeModel.BYTE, command.getName());
      //byte []keyId = command.getKeyId().getBytes();
      cmd.init(JExpr.lit(command.getKeyId()));
    }
  }

  public static void generateClasses (JCodeModel codeModel)throws IOException {
    File target = new File("./target/classes");
    if (!target.mkdirs()) {
      throw new IOException("Cannot create classes.");
    }
    codeModel.build(target);
  }        
}
