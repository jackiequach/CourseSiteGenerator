/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.jtps;

import csg.CSGeneratorApp;
import csg.data.CSGData;
import csg.data.Team;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jackie Quach
 */
public class DeleteTeamTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    Team team;
    
    public DeleteTeamTrans(CSGeneratorApp initApp, Team team) {
        app = initApp;
        this.team = team;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.removeTeam(team);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.addTeam(team.getName(),team.getColor(),team.getTextColor(),team.getLink());
    }
    
}
