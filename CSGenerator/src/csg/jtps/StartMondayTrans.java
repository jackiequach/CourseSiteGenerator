/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import java.time.LocalDate;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class StartMondayTrans implements jTPS_Transaction {
    
    CSGeneratorApp app;
    LocalDate chosenDate, startMonday, endFriday;
    
    public StartMondayTrans(CSGeneratorApp app, LocalDate chosenDate, LocalDate startMonday, LocalDate endFriday) {
        this.app = app;
        this.chosenDate = chosenDate;
        this.startMonday = startMonday;
        this.endFriday = endFriday;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.changeStartMonday(chosenDate,startMonday,endFriday);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.setStartMonday(startMonday);
    }
    
}
