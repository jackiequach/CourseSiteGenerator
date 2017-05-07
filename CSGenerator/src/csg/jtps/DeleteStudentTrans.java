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
public class DeleteStudentTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    Student student;
    
    public DeleteStudentTrans(CSGeneratorApp initApp, Student student) {
        app = initApp;
        this.student = student;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.removeStudent(student);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addStudent(student.getFirstName(),student.getLastName(),student.getTeam(),student.getRole());
    }
    
}
