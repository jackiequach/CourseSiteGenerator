/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import jtps.jTPS_Transaction;
import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.TeachingAssistant;
import csg.workspace.CSGController;

/**
 *
 * @author Jackie Quach
 */
public class UpdateTrans implements jTPS_Transaction {
    
    CSGeneratorApp app;
    CSGController.EmailValidator emailValidator;
    String name;
    String email;
    TeachingAssistant ta;
    
    public UpdateTrans(CSGeneratorApp initApp,CSGController.EmailValidator emailValidator, String name, String email, TeachingAssistant ta) {
        app = initApp;
        this.emailValidator = emailValidator;
        this.name = name;
        this.email = email;
        this.ta = ta;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.updateTA(emailValidator,name);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoUpdateTA(name, email, ta, emailValidator);
    }
    
}
