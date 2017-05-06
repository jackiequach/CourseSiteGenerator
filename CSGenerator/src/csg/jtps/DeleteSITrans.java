/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.ScheduleItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class DeleteSITrans implements jTPS_Transaction {
    
    CSGeneratorApp app;
    ScheduleItem scheduleItem;
    
    public DeleteSITrans(CSGeneratorApp initApp, ScheduleItem scheduleItem) {
        app = initApp;
        this.scheduleItem = scheduleItem;
    }

    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.removeScheduleItem(scheduleItem);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addScheduleItem(scheduleItem.getType(),scheduleItem.getDate(),scheduleItem.getTime(),scheduleItem.getTitle(),scheduleItem.getTopic(),scheduleItem.getLink(),scheduleItem.getCriteria());
    }
}
