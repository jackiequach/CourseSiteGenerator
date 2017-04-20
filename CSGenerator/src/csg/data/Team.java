/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Hyperlink;

/**
 *
 * @author Jackie
 */
public class Team {
    private final StringProperty name;
    private final StringProperty color;
    private final StringProperty textColor;
    private final StringProperty link;
    
    public Team(String initName, String initColor, String initTextColor, StringProperty initLink) {
        name = new SimpleStringProperty(initName);
        color = new SimpleStringProperty(initColor);
        textColor = new SimpleStringProperty(initTextColor);
        link = initLink;
    }
    
    public String getName() {
        return name.get();
    }
    
    public String getColor() {
        return color.get();
    }
    
    public String getTextColor() {
        return textColor.get();
    }
    
    public String getLink() {
        return link.get();
    }
}
