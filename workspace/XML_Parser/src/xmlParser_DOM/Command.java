package xmlParser_DOM;

import java.util.List;

public class Command {
  private String keyId;
  private String name;
  private String humanReadableName;
  private List<Parameter> params;
  
  public Command(String keyId, String name, String humanReadableName, List<Parameter> params) {
    super();
    this.keyId = keyId;
    this.name = name;
    this.humanReadableName = humanReadableName;
    this.params = params;
  }

  public Command() {
    // TODO Auto-generated constructor stub
  }

  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
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

  public List<Parameter> getParams() {
    return params;
  }

  public void setParams(List<Parameter> params) {
    this.params = params;
  }

  @Override
  public String toString() {
    return "Command [keyId=" + keyId + ", name=" + name + ", humanReadableName=" + humanReadableName + ", params="
        + params + "]";
  }  
}