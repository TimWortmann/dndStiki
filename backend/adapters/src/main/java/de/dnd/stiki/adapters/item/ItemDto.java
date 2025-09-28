package de.dnd.stiki.adapters.item;

import java.util.List;

public class ItemDto {

    private String name;

    private String detail;

    private String type;

    private Double weight;

    private Double value;

    private List<String> properties;

    private String dmg1;

    private String dmg2;

    private String dmgType;

    private String range;

    private Integer ac;

    private Boolean stealth;

    private Boolean magic;

    private Integer strength;

    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public String getDmg1() {
        return dmg1;
    }

    public void setDmg1(String dmg1) {
        this.dmg1 = dmg1;
    }

    public String getDmg2() {
        return dmg2;
    }

    public void setDmg2(String dmg2) {
        this.dmg2 = dmg2;
    }

    public String getDmgType() {
        return dmgType;
    }

    public void setDmgType(String dmgType) {
        this.dmgType = dmgType;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }

    public Boolean getStealth() {
        return stealth;
    }

    public void setStealth(Boolean stealth) {
        this.stealth = stealth;
    }

    public Boolean getMagic() {
        return magic;
    }

    public void setMagic(Boolean magic) {
        this.magic = magic;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
