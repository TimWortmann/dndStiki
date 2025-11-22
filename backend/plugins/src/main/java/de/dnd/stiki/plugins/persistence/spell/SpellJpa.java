package de.dnd.stiki.plugins.persistence.spell;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Spell", schema = "DND_STIKI")
public class SpellJpa {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "SCHOOL")
    private String school;

    @Column(name = "TIME")
    private String time;

    @Column(name = "RANGE")
    private String range;

    @Column(name = "COMPONENTS")
    private String components;

    @Column(name = "DURATION")
    private String duration;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "ROLL")
    private String roll;

    @Column(name="CLASSES")
    private String classes;

    @Column(name = "RITUAL")
    private boolean ritual;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public boolean isRitual() {
        return ritual;
    }

    public void setRitual(boolean ritual) {
        this.ritual = ritual;
    }
}
