package com.example.dev2.pianinisecond;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dev2.pianinisecond.edit.FileLog;
import com.example.dev2.pianinisecond.statik_value.StaticValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class MainActivity extends Activity {



    private Button btDo;
    private Button btRe;
    private Button btMi;
    private Button btFa;
    private Button btSol;
    private Button btLa;
    private Button btSi;
    private TextView textView;
    private File file;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStreamt;
    private ArrayList<FileLog> fileLogArrayList;
    private FileLog fileLog=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btDo = (Button) findViewById(R.id.btDo);
        btRe = (Button) findViewById(R.id.btRe);
        btMi = (Button) findViewById(R.id.btMi);
        btFa = (Button) findViewById(R.id.btFa);
        btSol = (Button) findViewById(R.id.btSol);
        btLa = (Button) findViewById(R.id.btLa);
        btSi = (Button) findViewById(R.id.btSi);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void clicOnButton(View view) {
        switch (view.getId()) {
            case R.id.btDo:
                openFile("До");
                break;
            case R.id.btRe:
                openFile("Ре");
                break;
            case R.id.btMi:
                openFile("Ми");
                break;
            case R.id.btFa:
                openFile("Фа");
                break;
            case R.id.btSol:
                openFile("Соль");
                break;
            case R.id.btLa:
                openFile("Ля");
                break;
            case R.id.btSi:
                openFile("Си");
                break;
        }
    }
    // Метод для открытия файла
    private void openFile(String text) {

//        //проверочные действия работы
//        Date date = new Date();
//        String dateString = new SimpleDateFormat("dd.MM.yyyy : hh.mm.ss").format(date);
//        textView.append( dateString + " " + " : "+text+"\n");

        fileLogArrayList = new ArrayList<FileLog>();
        fileLog = new FileLog(text);
        fileLogArrayList.add(fileLog);

        Log.i(StaticValue.MY_LOG, "Size 1 _________" + fileLogArrayList.size());

        if (fileLogArrayList.size() >= 5) fileLogArrayList.remove(0);
        Log.i(StaticValue.MY_LOG, "Select button " + text);

        Log.i(StaticValue.MY_LOG, "Size " + fileLogArrayList.size());

        try {
            outputStream = openFileOutput(StaticValue.FILE_NAME, 0);
            objectOutputStreamt = new ObjectOutputStream(outputStream);
            objectOutputStreamt.writeObject(fileLogArrayList);
            Log.i(StaticValue.MY_LOG, "File created and saved ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error openFileOutput " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Error ObjectOutputStream " + e.toString());
        }


        try {
            inputStream = openFileInput(StaticValue.FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            objectInputStream = new ObjectInputStream(new InputStreamReader(inputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Eror open file  ");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(StaticValue.MY_LOG, "Eror ObjectOutputStream  ");
        }


        for (FileLog fl : fileLogArrayList) {
            textView.append( fl.toString());
        }
}


/**
 *  http://developer.alexanderklimov.ru/android/texteditor.php
 *  http://developer-android.unlimited-translate.org/training/basics/data-storage/files.html#GetWritePermission
 *  http://microsin.net/programming/android/saving-files.html
 *  http://androidimran.blogspot.ru/2012/06/pass-custom-object-using-serializable.html
 *
 */
