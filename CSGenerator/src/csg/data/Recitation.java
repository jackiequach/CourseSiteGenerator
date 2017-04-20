/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jackie
 */
public class Recitation {
    private final StringProperty section;
    private final StringProperty instructor;
    private final StringProperty day;
    private final StringProperty location;
    private final TeachingAssistant ta;
    
    public Recitation(String initSection, String initInstructor, String initDay, String initLocation, TeachingAssistant initTA) {
        section = new SimpleStringProperty(initSection);
        instructor = new SimpleStringProperty(initInstructor);
        day = new SimpleStringProperty(initDay);
        location = new SimpleStringProperty(initLocation);
        ta = initTA;
    }
    
    public String getSection() {
        return section.get();
    }
    
    public String getInstructor() {
        return instructor.get();
    }
    
    public String getDay() {
        return day.get();
    }
    
    public String getLocation() {
        return location.get();
    }
    
    public String getTA() {
        return ta.getName();
    }
}