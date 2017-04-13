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
public class Student {
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
}
