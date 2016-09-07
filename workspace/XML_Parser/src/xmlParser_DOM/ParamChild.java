package xmlParser_DOM;

import java.util.List;

public class ParamChild {
  private String keyId;//All but VARIANT
  //BITMASK, VARIANT
  private String paramoffs;
  //BITMASK
  private String lenmask;
  private String lenoffs;
  //BITMASK, CONST, BYTE, STRUCT_BYTE
  private String flagname;
  private String flagmask;
  //STRUCT_BYTE
  private String fieldname;
  private String fieldmask;
  private String fieldenumValue;
  private String shifter;
  //ENUM, ENUM_ARRAY
  private String name;
  //ARRAY
  private String len;
  //MULTI_ARRAY
  private String param;
  private String paramdesc;
  //VARIANT
  private String sizemask;
  private String sizeoffs;
  private List <Bitflag> children;
  
  public String getKeyId() {
    return keyId;
  }
  public void setKeyId(String keyId) {
    this.keyId = keyId;
  }
  public String getParamoffs() {
    return paramoffs;
  }
  
  public void setParamoffs(String paramoffs) {
    this.paramoffs = paramoffs;
  }
  public String getLenmask() {
    return lenmask;
  }
  public void setLenmask(String lenmask) {
    this.lenmask = lenmask;
  }
  public String getLenoffs() {
    return lenoffs;
  }
  public void setLenoffs(String lenoffs) {
    this.lenoffs = lenoffs;
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
  public String getFieldname() {
    return fieldname;
  }
  public void setFieldname(String fieldname) {
    this.fieldname = fieldname;
  }
  public String getFieldmask() {
    return fieldmask;
  }
  public void setFieldmask(String fieldmask) {
    this.fieldmask = fieldmask;
  }
  public String getFieldenumValue() {
    return fieldenumValue;
  }
  public void setFieldenumValue(String fieldenumValue) {
    this.fieldenumValue = fieldenumValue;
  }
  public String getShifter() {
    return shifter;
  }
  public void setShifter(String shifter) {
    this.shifter = shifter;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLen() {
    return len;
  }
  public void setLen(String len) {
    this.len = len;
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
  
  public List <Bitflag> getChildren() {
    return children;
  }
  public void setChildren(List <Bitflag> children) {
    this.children = children;
  }
  @Override
  public String toString() {
    return "ParamChild [keyId=" + keyId + ", paramoffs=" + paramoffs + ", lenmask=" + lenmask + ", lenoffs=" + lenoffs
        + ", flagname=" + flagname + ", flagmask=" + flagmask + ", fieldname=" + fieldname + ", fieldmask=" + fieldmask
        + ", fieldenumValue=" + fieldenumValue + ", shifter=" + shifter + ", name=" + name + ", len=" + len + ", param="
        + param + ", paramdesc=" + paramdesc + ", sizemask=" + sizemask + ", sizeoffs=" + sizeoffs + ", children="
        + children + "]";
  }  
}