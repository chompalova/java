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

import xmlParser_DOM.XMLParser;
import xmlParser_DOM.ZWaveCmdClass;
 
public class CodeGenCodeModel {
 
  public static void main(String ... args) {
    File file = new File ("./resources/ZWave_cmd_classes.xml");
    //File file = new File ("./resources/test1.xml");
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
 
    //JDefinedClass will let you create a class in a specified package.    
    List <ZWaveCmdClass> cmdClasses = XMLParser.getCmdClasses(); 
    ZWaveCmdClass[] cmdClasses1 = cmdClasses.toArray(new ZWaveCmdClass[cmdClasses.size()]);        
    List <JDefinedClass> generatedCmdClasses = new ArrayList <JDefinedClass>();
    String className = null;
    for (int i = 0, j = (i + 1); j < cmdClasses1.length; i++, j++) {
      JDefinedClass cmdClass = null;
      String name1 = cmdClasses1[i].getName();
      String name2 = cmdClasses1[j].getName();      
      if (name1.equals(name2)) {
        String className1 = cmdClasses1[i].getName() + "_V" + cmdClasses1[i].getVersion();
        if (!className1.equals(className)) {
          cmdClass = codeModel._class(className1);
          //generatedCmdClasses.add(cmdClass);
          createFields(cmdClass, codeModel);
          className = className1; 
        }
        String className2 = cmdClasses1[j].getName() + "_V" + cmdClasses1[j].getVersion();
        if (!className2.equals(className)) {
          cmdClass = codeModel._class(className2);
          createFields(cmdClass, codeModel);
          //generatedCmdClasses.add(cmdClass);
          className = className2;
        }          
      } else {
          String className3 = cmdClasses1[j].getName();
          if (j != (cmdClasses1.length - 1) && !(className3.equals(cmdClasses1[j+1].getName()))) {             
            cmdClass = codeModel._class(className3);
            createFields(cmdClass, codeModel);
            //generatedCmdClasses.add(cmdClass);
          } else if (j == (cmdClasses1.length - 1)) {
            cmdClass = codeModel._class(className3);
            createFields(cmdClass, codeModel);
            //generatedCmdClasses.add(cmdClass);
          }
      }
    }
    writeToFile(codeModel);
  }
    /*for (JDefinedClass cmdClass : generatedCmdClasses) {
      JFieldVar field1 = cmdClass.field(JMod.PRIVATE, String.class, "name");
      JFieldVar field2 = cmdClass.field(JMod.PRIVATE, String.class, "version");
      JMethod field1GetterMethod = cmdClass.method(JMod.PUBLIC, field1.type(), "getName");
      JMethod field1SetterMethod = cmdClass.method(JMod.PUBLIC, codeModel.VOID, "setName");
      JMethod field2GetterMethod = cmdClass.method(JMod.PUBLIC, field2.type(), "getVersion");
      JMethod field2SetterMethod = cmdClass.method(JMod.PUBLIC, codeModel.VOID, "setVersion");
    }*/
    
  public static void createFields(JDefinedClass cmdClass, JCodeModel codeModel) {
    JFieldVar name = cmdClass.field(JMod.PRIVATE, String.class, "name");
    JFieldVar version = cmdClass.field(JMod.PRIVATE, String.class, "version");
    JMethod nameGetterMethod = cmdClass.method(JMod.PUBLIC, name.type(), "getName");
    JMethod nameSetterMethod = cmdClass.method(JMod.PUBLIC, codeModel.VOID, "setName");
    JMethod versionGetterMethod = cmdClass.method(JMod.PUBLIC, version.type(), "getVersion");
    JMethod versionSetterMethod = cmdClass.method(JMod.PUBLIC, codeModel.VOID, "setVersion");
  }
  
  public static void writeToFile (JCodeModel codeModel) throws IOException {
    File target = new File("./target/classes");
    if (!target.mkdirs()) {
      throw new IOException("Cannot make dir.");
    }
    codeModel.build(target);
  }
}
