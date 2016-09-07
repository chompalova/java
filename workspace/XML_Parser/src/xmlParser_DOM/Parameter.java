package xmlParser_DOM;

import java.util.List;


enum ParamType {
  BYTE, WORD, DWORD, BIT_24, ARRAY, BITMASK, STRUCT_BYTE, ENUM, ENUM_ARRAY, MULTI_ARRAY, 
  CONST, VARIANT, MARKER }

public class Parameter {
  private String keyId;
  private String name;
  private ParamType type;
  private String variantKey;
  private String paramOffs;
  private String sizemask;
  private String sizeoffs;
  private List<ParamChild> paramChildren;    
  
  public Parameter() {}
  
  public Parameter(String keyId, String name, ParamType type, List<ParamChild> paramChildren) {
    super();
    this.keyId = keyId;
    this.name = name;
    this.type = type;
    this.paramChildren = paramChildren;
  }   

  public String setKeyId() {
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

  public ParamType getType() {
    return type;
  }

  public void setType(ParamType type) {
    this.type = type;
  }

  public String getVariantKey() {
    return variantKey;
  }

  public void setVariantKey(String variantKey) {
    this.variantKey = variantKey;
  }

  public String getParamOffs() {
    return paramOffs;
  }

  public void setParamOffs(String paramOffs) {
    this.paramOffs = paramOffs;
  }

  public String getSizemask() {
    return sizemask;
  }

  public void setSizemask(String sizemask) {
    this.sizemask = sizemask;
  }  
  
  public String getSizeoffs() {
    return sizeoffs;
  }

  public void setSizeoffs(String sizeoffs) {
    this.sizeoffs = sizeoffs;
  }

  public List<ParamChild> getParamChildren() {
    return paramChildren;
  }

  public void setParamChildren(List<ParamChild> paramChildren) {
    this.paramChildren = paramChildren;
  }

  @Override
  public String toString() {    
    return "Parameter [keyId=" + keyId + ", name=" + name + ", type=" + type + ", variantKey=" + variantKey
        + ", paramOffs=" + paramOffs + ", sizemask=" + sizemask + ", sizeoffs=" + sizeoffs + ", paramChildren="
        + paramChildren + "]";
  }  
}