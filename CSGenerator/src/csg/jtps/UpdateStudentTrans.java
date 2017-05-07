/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Student;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class UpdateStudentTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    String firstName;
    String lastName;
    String team;
    String role;
    Student student;
    
    public UpdateStudentTrans(CSGeneratorApp initApp, String firstName, String lastName, String team, String role, Student student) {
        app = initApp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.role = role;
        this.student = student;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.updateStudent(firstName,lastName);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoUpdateStudent(firstName,lastName,team,role,student);
    }
    
}
