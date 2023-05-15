package com.example.opencsvdemo;

import javafx.beans.property.SimpleStringProperty;

public class ThesisRecord {

    private SimpleStringProperty title,
            group,
            term,
            sy,
            grp_number,
            members,
            adviser,
            chair_panel,
            panelist1,
            panelist2,
            status;

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getGroup() {
        return group.get();
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public String getTerm() {
        return term.get();
    }

    public SimpleStringProperty termProperty() {
        return term;
    }

    public String getSy() {
        return sy.get();
    }

    public SimpleStringProperty syProperty() {
        return sy;
    }

    public String getGrp_number() {
        return grp_number.get();
    }

    public SimpleStringProperty grp_numberProperty() {
        return grp_number;
    }

    public String getMembers() {
        return members.get();
    }

    public SimpleStringProperty membersProperty() {
        return members;
    }

    public String getAdviser() {
        return adviser.get();
    }

    public SimpleStringProperty adviserProperty() {
        return adviser;
    }

    public String getChair_panel() {
        return chair_panel.get();
    }

    public SimpleStringProperty chair_panelProperty() {
        return chair_panel;
    }

    public String getPanelist1() {
        return panelist1.get();
    }

    public SimpleStringProperty panelist1Property() {
        return panelist1;
    }

    public String getPanelist2() {
        return panelist2.get();
    }

    public SimpleStringProperty panelist2Property() {
        return panelist2;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    ThesisRecord(String title,
                 String group,
                 String term,
                 String sy,
                 String grp_number,
                 String members,
                 String adviser,
                 String chair_panel,
                 String panelist1,
                 String panelist2,
                 String status) {

        this.title = new SimpleStringProperty(title);
        this.group = new SimpleStringProperty(group);
        this.term = new SimpleStringProperty(term);
        this.sy = new SimpleStringProperty(sy);
        this.grp_number = new SimpleStringProperty(grp_number);
        this.members = new SimpleStringProperty(members);
        this.adviser = new SimpleStringProperty(adviser);
        this.chair_panel = new SimpleStringProperty(chair_panel);
        this.panelist1 = new SimpleStringProperty(panelist1);
        this.panelist2 = new SimpleStringProperty(panelist2);
        this.status = new SimpleStringProperty(status);
    }

    ThesisRecord(String [] record) {
        this.title = new SimpleStringProperty(record[0]);
        this.group = new SimpleStringProperty(record[1]);
        this.term = new SimpleStringProperty(record[2]);
        this.sy = new SimpleStringProperty(record[3]);
        this.grp_number = new SimpleStringProperty(record[4]);
        this.members = new SimpleStringProperty(record[5]);
        this.adviser = new SimpleStringProperty(record[6]);
        this.chair_panel = new SimpleStringProperty(record[7]);
        this.panelist1 = new SimpleStringProperty(record[8]);
        this.panelist2 = new SimpleStringProperty(record[9]);
        this.status = new SimpleStringProperty(record[10]);
    }
}