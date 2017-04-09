package csg.jtps;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jtps.jTPS_Transaction;
import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.TeachingAssistant;

/**
 *
 * @author Jackie Quach
 */
public class AddTrans implements jTPS_Transaction{

    CSGeneratorApp app;
    String name;
    String email;
    
    public AddTrans(CSGeneratorApp initApp, String name, String email) {
        app = initApp;
        this.name = name;
        this.email = email;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addTA(name, email);
    }
    
    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        TeachingAssistant ta = new TeachingAssistant(name,email);
        data.removeTA(ta);
    }
    
}
