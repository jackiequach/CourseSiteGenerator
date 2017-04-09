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
public class EndTimeTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    int chosenHour, endHour;
    ArrayList<TimeSlot> officeHours;
    String selectedTime;
    HashMap<String, StringProperty> officeHoursP;
    
    public EndTimeTrans(CSGeneratorApp app, int chosenHour, int endHour, ArrayList<TimeSlot> officeHours, String selectedTime, HashMap<String, StringProperty> officeHoursP) {
        this.app = app;
        this.chosenHour = chosenHour;
        this.endHour = endHour;
        this.officeHours = officeHours;
        this.selectedTime = selectedTime;
        this.officeHoursP = officeHoursP;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.changeEndHour(chosenHour, endHour, officeHours, selectedTime, officeHoursP);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoChangeEndHour(endHour, officeHours);
    }
    
}
