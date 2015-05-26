package com.example.dev2.pianinisecond.edit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dev2 on 25.05.15.
 */
public class FileLog implements Serializable {
    private String text;
    private Date date;

    public FileLog(String text) {
        date = new Date();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "::: " + new SimpleDateFormat("dd.MM.yyyy : hh.mm.ss").format(date) + " note is: " + text+"\n";
    }
}
