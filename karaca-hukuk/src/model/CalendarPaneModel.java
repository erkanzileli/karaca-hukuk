package model;

import javafx.scene.layout.Pane;

import java.sql.Date;

public class CalendarPaneModel extends Pane {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
