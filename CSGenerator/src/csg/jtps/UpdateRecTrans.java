/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import csg.workspace.CSGController;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class UpdateRecTrans implements jTPS_Transaction {
    
    CSGeneratorApp app;
    String section;
    String instructor;
    String day;
    String location;
    String taOne;
    String taTwo;
    Recitation recitation;
    
    public UpdateRecTrans(CSGeneratorApp initApp, String section, String instructor, String day, String location, String taOne, String taTwo, Recitation recitation) {
        app = initApp;
        this.section = section;
        this.instructor = instructor;
        this.day = day;
        this.location = location;
        this.taOne = taOne;
        this.taTwo = taTwo;
        this.recitation = recitation;
    }
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.updateRecitation(section);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoUpdateRecitation(section,instructor,day,location,taOne,taTwo,recitation);
    }
    
}
