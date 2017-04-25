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
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    private final StringProperty criteria;
    
    public ScheduleItem(String initType, String initDate, String initTitle, String initTopic) {
        this(initType,initDate,"",initTitle,initTopic,"","");
    }
    
    public ScheduleItem(String initType, String initDate, String initTime, String initTitle, String initTopic, String initLink, String initCriteria) {
        type = new SimpleStringProperty(initType);
        date = new SimpleStringProperty(initDate);
        time = new SimpleStringProperty(initTime);
        title = new SimpleStringProperty(initTitle);
        topic = new SimpleStringProperty(initTopic);
        link = new SimpleStringProperty(initLink);
        criteria = new SimpleStringProperty(initCriteria);
    }
    
    public String getType() {
        return type.get();
    }
    
    public String getDate() {
        return date.get();
    }
    
    public String getTime() {
        return time.get();
    }
    
    public String getTitle() {
        return title.get();
    }
    
    public String getTopic() {
        return topic.get();
    }
    
    public String getLink() {
        return link.get();
    }
    
    public String getCriteria() {
        return criteria.get();
    }
}
