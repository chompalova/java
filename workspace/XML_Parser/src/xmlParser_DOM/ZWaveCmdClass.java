package xmlParser_DOM;

import java.util.List;

public class ZWaveCmdClass {
  private String keyId;
  private String version;
  private String name;
  private String humanReadableName;
  private List<Command> commands;
  
  public ZWaveCmdClass(String keyId, String version, String name, String humanReadableName, List<Command> commands) {
    super();
    this.keyId = keyId;
    this.version = version;
    this.name = name;
    this.humanReadableName = humanReadableName;
    this.commands = commands;
  }

  public ZWaveCmdClass() {
    // TODO Auto-generated constructor stub
  }

  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
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

  @Override
  public String toString() {
    return "ZWaveCmdClass [keyId=" + keyId + ", version=" + version + ", name=" + name + ", humanReadableName="
        + humanReadableName + ", commands=" + commands + "]";
  }
    
}