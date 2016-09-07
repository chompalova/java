package xmlParser_DOM;

import java.util.List;

public class CommandClass {
  private String Id;
  private String version;
  private String name;
  private String humanReadableName;
  private List<Command> commands;
  
  public CommandClass(String Id, String version, String name, String humanReadableName, List<Command> commands) {
    super();
    this.Id = Id;
    this.version = version;
    this.name = name;
    this.humanReadableName = humanReadableName;
    this.commands = commands;
  }

  public String getId() {
    return Id;
  }

  public void setID(String Id) {
    this.Id = Id;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
      
  public String getHumanReadableName() {
    return humanReadableName;
  }

  public void setHumanReadableName(String humanReadableName) {
    this.humanReadableName = humanReadableName;
  }

  public List<Command> getCommands() {
    return commands;
  }

  public void setCommands(List<Command> commands) {
    this.commands = commands;
  }
    
}
