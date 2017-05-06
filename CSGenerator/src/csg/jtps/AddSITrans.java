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
public class AddSITrans implements jTPS_Transaction {
    
    CSGeneratorApp app;
    String type;
    String date;
    String time;
    String title;
    String topic;
    String link;
    String criteria;
    
    public AddSITrans(CSGeneratorApp initApp, String type, String date, String time, String title, String topic, String link, String criteria) {
        app = initApp;
        this.type = type;
        this.date = date;
        this.time = time;
        this.title = title;
        this.topic = topic;
        this.link = link;
        this.criteria = criteria;
    }

    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addScheduleItem(type, date, time, title, topic, link, criteria);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        ScheduleItem scheduleItem = new ScheduleItem(type, date, time, title, topic, link, criteria);
        data.removeScheduleItem(scheduleItem);
    }
}
