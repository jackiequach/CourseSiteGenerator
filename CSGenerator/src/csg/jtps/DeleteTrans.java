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
import csg.data.TeachingAssistant;
import csg.file.TimeSlot;
import csg.workspace.CSGWorkspace;

/**
 *
 * @author Jackie Quach
 */
public class DeleteTrans implements jTPS_Transaction {
    CSGeneratorApp app;
    TeachingAssistant ta;
    ArrayList<TimeSlot> officeHours;
    HashMap<String, StringProperty> officeHoursH;
    
    public DeleteTrans(CSGeneratorApp initApp, TeachingAssistant ta, ArrayList<TimeSlot> officeHours, HashMap<String, StringProperty> officeHoursH) {
        app = initApp;
        this.ta = ta;
        this.officeHours = officeHours;
        this.officeHoursH = officeHoursH;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.removeTA(ta);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        CSGWorkspace workspace = (CSGWorkspace)app.getWorkspaceComponent();
        data.addTA(ta.getName(),ta.getEmail());
        workspace.reloadWorkspace(data);
        for (TimeSlot ts : officeHours) {
            data.addOfficeHoursReservation(ts.getDay(), ts.getTime(), ts.getName());
            for (StringProperty p : officeHoursH.values()) {
                String selected = data.getCellKey(ts.getDay(),ts.getTime());
                int col = Integer.parseInt(selected.split("_")[0]);
                int row = Integer.parseInt(selected.split("_")[1]);
                data.setCellProperty(col, row, data.getCellTextProperty(col, row));
            }
        }
    }  
}
