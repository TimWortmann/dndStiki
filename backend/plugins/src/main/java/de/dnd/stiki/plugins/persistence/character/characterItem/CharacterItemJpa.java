package de.dnd.stiki.plugins.persistence.character.characterItem;

import de.dnd.stiki.plugins.persistence.character.CharacterJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_ITEM", schema = "DND_STIKI")
public class CharacterItemJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID")
    private CharacterJpa character;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "ITEM_TYPE")
    private String type;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "ITEM_VALUE")
    private Double value;

    @Column(name = "PROPERTIES")
    private String properties;

    @Column(name = "DMG1")
    private String dmg1;

    @Column(name = "DMG2")
    private String dmg2;

    @Column(name = "DMG_TYPE")
    private String dmgType;

    @Column(name = "ITEM_RANGE")
    private String range;

    @Column(name = "AC")
    private Integer ac;

    @Column(name = "STEALTH")
    private Boolean stealth;

    @Column(name = "MAGIC")
    private Boolean magic;

    @Column(name = "STRENGTH")
    private Integer strength;

    @Column(name = "TEXT")
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CharacterJpa getCharacter() {
        return character;
    }

    public void setCharacter(CharacterJpa character) {
        this.character = character;
    }

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

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
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
