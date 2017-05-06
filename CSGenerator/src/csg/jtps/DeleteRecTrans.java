/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Recitation;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class DeleteRecTrans implements jTPS_Transaction {
    CSGeneratorApp app;
    Recitation recitation;
    
    public DeleteRecTrans(CSGeneratorApp initApp, Recitation recitation) {
        app = initApp;
        this.recitation = recitation;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.removeRecitation(recitation);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addRecitation(recitation.getSection(),recitation.getInstructor(),recitation.getDay(),recitation.getLocation(),recitation.getTAOne(),recitation.getTATwo());
    }
}
