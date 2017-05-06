/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class AddRecTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    String section;
    String instructor;
    String day;
    String location;
    String taOne;
    String taTwo;
    
    public AddRecTrans(CSGeneratorApp initApp, String section, String instructor, String day, String location, String taOne, String taTwo) {
        app = initApp;
        this.section = section;
        this.instructor = instructor;
        this.day = day;
        this.location = location;
        this.taOne = taOne;
        this.taTwo = taTwo;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addRecitation(section, instructor, day, location, taOne, taTwo);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        Recitation recitation = new Recitation(section,instructor,day,location,taOne,taTwo);
        data.removeRecitation(recitation);
    }
    
}
