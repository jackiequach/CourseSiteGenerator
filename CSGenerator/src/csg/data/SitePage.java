/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jackie
 */
public class SitePage {
    private final BooleanProperty use;
    private final StringProperty title;
    private final StringProperty fileName;
    private final StringProperty script;
    
    public SitePage(Boolean initUse, String initTitle, String initFile, String initScript) {
        use = new SimpleBooleanProperty(initUse);
        title = new SimpleStringProperty(initTitle);
        fileName = new SimpleStringProperty(initFile);
        script = new SimpleStringProperty(initScript);
    }
    
    public BooleanProperty useProperty() {
        return use;
    }
    
    public Boolean getUse() {
        return this.use.get();
    }
    
    public void setUse(Boolean use) {
        this.use.set(use);
    }
    
    public String getNavbar() {
        return title.get();
    }
    
    public String getFile() {
        return fileName.get();
    }
    
    public String getScript() {
        return script.get();
    }
}
