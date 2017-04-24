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
    Boolean undergrad;
    String name;
    String email;
    
    public AddTrans(CSGeneratorApp initApp, Boolean undergrad, String name, String email) {
        app = initApp;
        this.undergrad = undergrad;
        this.name = name;
        this.email = email;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addTA(undergrad, name, email);
    }
    
    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        TeachingAssistant ta = new TeachingAssistant(undergrad,name,email);
        data.removeTA(ta);
    }
    
}
