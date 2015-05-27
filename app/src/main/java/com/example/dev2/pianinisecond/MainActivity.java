package com.example.dev2.pianinisecond;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.dev2.pianinisecond.edit.FileLog;
import com.example.dev2.pianinisecond.play_sound.PlaySound;
import com.example.dev2.pianinisecond.read_write.ReadWrite;
import com.example.dev2.pianinisecond.statik_value.StaticValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Button btDo;
    private Button btRe;
    private Button btMi;
    private Button btFa;
    private Button btSol;
    private Button btLa;
    private Button btSi;
    private TextView textView;
    private PlaySound playSound;

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

    @Override
    protected void onResume() {
        super.onResume();

        List<FileLog> fileList = new ArrayList<>();
        File file = new File(getFilesDir(), StaticValue.FILE_NAME);
        ReadWrite readWrite = new ReadWrite();
        fileList = readWrite.readFile(file);
        for (FileLog fileLog : fileList) {
            textView.append(fileLog.toString());
        }

    }

    public void clicOnButton(View view) {
        switch (view.getId()) {
            case R.id.btDo:
                addInfoToFile("До");
                new PlaySound(261.63);
                break;
            case R.id.btRe:
                addInfoToFile("Ре");
                new PlaySound(293.66);
                break;
            case R.id.btMi:
                addInfoToFile("Ми");
                new PlaySound(329.63);
                break;
            case R.id.btFa:
                addInfoToFile("Фа");
                new PlaySound(349.23);
                break;
            case R.id.btSol:
                addInfoToFile("Соль");
                new PlaySound(392.00);
                break;
            case R.id.btLa:
                addInfoToFile("Ля");
                new PlaySound(440.00);
                break;
            case R.id.btSi:
                addInfoToFile("Си");
                new PlaySound(493.88);
                break;
        }
    }

    private void addInfoToFile(String text) {

        textView.setText("");
        List<FileLog> fileList = new ArrayList<>();
        File file = new File(getFilesDir(), StaticValue.FILE_NAME);

        ReadWrite readWrite = new ReadWrite();
        fileList = readWrite.readFile(file);

        fileList.add(new FileLog(text));
        if (fileList.size() >= 20) {
            fileList.remove(0);
        }

        readWrite.write(file, fileList);

        for (FileLog fileLog : fileList) {
            textView.append(fileLog.toString());
        }

    }
}


/**
 *  http://developer.alexanderklimov.ru/android/texteditor.php
 *  http://developer-android.unlimited-translate.org/training/basics/data-storage/files.html#GetWritePermission
 *  http://microsin.net/programming/android/saving-files.html
 *  http://androidimran.blogspot.ru/2012/06/pass-custom-object-using-serializable.html
 *
 */
