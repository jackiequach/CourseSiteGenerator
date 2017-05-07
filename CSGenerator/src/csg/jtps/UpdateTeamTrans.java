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
public class UpdateTeamTrans implements jTPS_Transaction {

    CSGeneratorApp app;
    String name;
    String color;
    String textColor;
    String link;
    Team team;
    
    public UpdateTeamTrans(CSGeneratorApp initApp, String name, String color, String textColor, String link, Team team) {
        app = initApp;
        this.name = name;
        this.color = color;
        this.textColor = textColor;
        this.link = link;
        this.team = team;
    }
    
    @Override
    public void doTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.updateTeam(name,color);
    }

    @Override
    public void undoTransaction() {
        CSGData data = (CSGData)app.getDataComponent();
        data.undoUpdateTeam(name,color,textColor,link,team);
    }
    
}
