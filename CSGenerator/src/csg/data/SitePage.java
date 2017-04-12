/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jackie
 */
public class SitePage {
    private final Boolean use;
    private final StringProperty title;
    private final StringProperty fileName;
    private final StringProperty script;
    
    public SitePage(Boolean initUse, String initTitle, String initFile, String initScript) {
        use = initUse;
        title = new SimpleStringProperty(initTitle);
        fileName = new SimpleStringProperty(initFile);
        script = new SimpleStringProperty(initScript);
    }
    
    
}
