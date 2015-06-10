package com.example.dev2.pianinisecond.edit;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dev2 on 25.05.15.
 */
public class FileLog implements Serializable {
    private String text;
    public FileLog(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text+"\n";
    }
}
