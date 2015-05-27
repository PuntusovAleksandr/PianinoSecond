package com.example.dev2.pianinisecond.read_write;

import android.util.Log;

import com.example.dev2.pianinisecond.edit.FileLog;
import com.example.dev2.pianinisecond.statik_value.StaticValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev2 on 27.05.15.
 */
public class ReadWrite {


    public List<FileLog> readFile(File file) {
        List<FileLog> fileList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream inObject=null;
        try {
            fis = new FileInputStream(file);
            inObject = new ObjectInputStream(fis);
            fileList = (List<FileLog>) inObject.readObject();
            inObject.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error FileInputStream " + e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 1 " + e.toString());
        } catch (OptionalDataException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 2 " + e.toString());
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 3 " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 4 " + e.toString());
        }
        return fileList;
    }

    public void write(File file, List<FileLog> fileList) {
        FileOutputStream fos = null;
        ObjectOutputStream outObject = null;

        try {
            fos = new FileOutputStream(file);
            outObject = new ObjectOutputStream(fos);
            outObject.writeObject(fileList);
            outObject.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error FileOutputStream " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error ObjectOutputStream " + e.toString());
        }
    }
}
