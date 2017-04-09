/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import jtps.jTPS_Transaction;
import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.file.TimeSlot;

/**
 *
 * @author Jackie Quach
 */
public class StartTimeTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    int chosenHour, startHour;
    ArrayList<TimeSlot> officeHours;
    String selectedTime;
    HashMap<String, StringProperty> officeHoursP;
    
    public StartTimeTrans(CSGeneratorApp app, int chosenHour, int startHour, ArrayList<TimeSlot> officeHours, String selectedTime, HashMap<String, StringProperty> officeHoursP) {
        this.app = app;
        this.chosenHour = chosenHour;
        this.startHour = startHour;
        this.officeHours = officeHours;
        this.selectedTime = selectedTime;
        this.officeHoursP = officeHoursP;
    }
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.changeStartHour(chosenHour, startHour, officeHours, selectedTime, officeHoursP);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoChangeStartHour(startHour, officeHours);
    }
    
}
