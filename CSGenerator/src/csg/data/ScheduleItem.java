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
public class ScheduleItem {
    private final StringProperty type;
    private final StringProperty data;
    private final StringProperty title;
    private final StringProperty topic;
    
    public ScheduleItem(String initType, String initData, String initTitle, String initTopic) {
        type = new SimpleStringProperty(initType);
        data = new SimpleStringProperty(initData);
        title = new SimpleStringProperty(initTitle);
        topic = new SimpleStringProperty(initTopic);
    }
}
