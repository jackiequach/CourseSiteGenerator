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
public class Student<E extends Comparable<E>> implements Comparable<E> {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty team;
    private final StringProperty role;
    
    public Student(String initfirstName, String initlastName, String initTeam, String initRole) {
        firstName = new SimpleStringProperty(initfirstName);
        lastName = new SimpleStringProperty(initlastName);
        team = new SimpleStringProperty(initTeam);
        role = new SimpleStringProperty(initRole);
    }
    
    public String getFirstName() {
        return firstName.get();
    }
    
    public String getLastName() {
        return lastName.get();
    }
    
    public String getTeam() {
        return team.get();
    }
    
    public String getRole() {
        return role.get();
    }

    @Override
    public int compareTo(E otherStudent) {
        if(getFirstName().equals(((Student)otherStudent).getFirstName()) && getLastName().equals(((Student)otherStudent).getLastName()))
            return 0;
        return getFirstName().compareTo(((Student)otherStudent).getFirstName());
    }
}
