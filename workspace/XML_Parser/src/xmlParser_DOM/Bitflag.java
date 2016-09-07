package xmlParser_DOM;

public class Bitflag {
  private String keyId;
  private String param;
  private String paramdesc;
  private String flagname;
  private String flagmask;
  
  public Bitflag() {
  }
  
  public Bitflag(String keyId, String flagname, String flagmask, String param, String paramdesc) {
    super();
    this.keyId = keyId;
    this.flagname = flagname;
    this.flagmask = flagmask;
    this.param = param;
    this.paramdesc = paramdesc;
  }

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public String getParamdesc() {
    return paramdesc;
  }

  public void setParamdesc(String paramdesc) {
    this.paramdesc = paramdesc;
  }

  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
  }

  public String getFlagname() {
    return flagname;
  }

  public void setFlagname(String flagname) {
    this.flagname = flagname;
  }

  public String getFlagmask() {
    return flagmask;
  }

  public void setFlagmask(String flagmask) {
    this.flagmask = flagmask;
  }

  @Override
  public String toString() {
    return "Bitflag [keyId=" + keyId + ", param=" + param + ", paramdesc=" + paramdesc + ", flagname=" + flagname
        + ", flagmask=" + flagmask + "]";
  }  
}
