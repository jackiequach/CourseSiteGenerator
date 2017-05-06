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
public class Recitation<E extends Comparable<E>> implements Comparable<E>  {
    private final StringProperty section;
    private final StringProperty instructor;
    private final StringProperty day;
    private final StringProperty location;
    private final StringProperty taOne;
    private final StringProperty taTwo;
    
    public Recitation(String initSection, String initInstructor, String initDay, String initLocation, String initTAOne, String initTATwo) {
        section = new SimpleStringProperty(initSection);
        instructor = new SimpleStringProperty(initInstructor);
        day = new SimpleStringProperty(initDay);
        location = new SimpleStringProperty(initLocation);
        taOne = new SimpleStringProperty(initTAOne);
        taTwo = new SimpleStringProperty(initTATwo);
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
    
    public String getTAOne() {
        return taOne.get();
    }
    
    public String getTATwo() {
        return taTwo.get();
    }

    @Override
    public int compareTo(E otherRecitation) {
        return getSection().compareTo(((Recitation)otherRecitation).getSection());
    }
}
