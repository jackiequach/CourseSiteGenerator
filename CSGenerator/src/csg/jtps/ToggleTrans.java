/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import javafx.beans.property.StringProperty;
import jtps.jTPS_Transaction;
import csg.CSGeneratorApp;
import csg.data.CSGData;

/**
 *
 * @author Jackie Quach
 */
public class ToggleTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    String cellKey;
    StringProperty cellProp;
    String cellText;
    String name;
    
    public ToggleTrans(CSGeneratorApp initApp, String cellKey, StringProperty cellProp, String cellText, String name) {
        app = initApp;
        this.cellKey = cellKey;
        this.cellProp = cellProp;
        this.cellText = cellText;
        this.name = name;
    }
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.toggleTAOfficeHours(cellKey, name);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoToggleTA(cellProp, cellText);
    }
    
}
