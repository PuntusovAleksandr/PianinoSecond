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
 *@author AleksandrP
 */
public class ReadWrite {

    /**
     *
     * @param file - name file in the internal memory
     * @return collection classes FileLog
     */
    public List<FileLog> readFile(File file) {
        // создаем коллекцию обьектов классов FileLog
        List<FileLog> fileList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream inObject=null;
        try {
            // созданиеи и инициализация потока FileInputStream
            fis = new FileInputStream(file);
            // создание и инициализация потока ObjectInputStream
            inObject = new ObjectInputStream(fis);
            // список FileLog
            fileList = (List<FileLog>) inObject.readObject();
            // закрытие потока FileInputStream
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

    /**
     *
     * @param file - name file in the internal memory
     * @param fileList - collection FileLog
     */
    public void write(File file, List<FileLog> fileList) {
        FileOutputStream fos = null;
        ObjectOutputStream outObject = null;

        try {
            // создание и инициализация потока вывода
            fos = new FileOutputStream(file);
            // создание и инициализация потока ObjectOutputStream
            outObject = new ObjectOutputStream(fos);
            // запись потока ObjectOutputStream
            outObject.writeObject(fileList);
            // закрытие потока ObjectOutputStream
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
