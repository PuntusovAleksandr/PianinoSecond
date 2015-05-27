package com.example.dev2.pianinisecond;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private TextView bigTextView;
    private LinearLayout ll_3;
    private LinearLayout ll_head;
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
        bigTextView = (TextView) findViewById(R.id.bif_text_yiew);
        ll_3 = (LinearLayout) findViewById(R.id.ll_3);
        ll_head = (LinearLayout) findViewById(R.id.ll_head);

        bigTextView.setMovementMethod(new ScrollingMovementMethod());   // включаем скролинг

        textView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override // "слушатель" организован в виде анонимного внутреннего класса
            public boolean onLongClick(View arg0) // действия на нажатие
            {
                bigTextView.setText(textView.getText().toString());
                ll_3.setVerticalGravity(1);
                bigTextView.setVisibility(View.VISIBLE);
                bigTextView.setBackgroundColor(Color.BLACK);
                bigTextView.setTextColor(Color.WHITE);

                return true;
            }
        });
        bigTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override // "слушатель" организован в виде анонимного внутреннего класса
            public boolean onLongClick(View arg0) // действия на нажатие
            {
                bigTextView.setText("");
                ll_3.setVerticalGravity(0);
                bigTextView.setVisibility(View.INVISIBLE);
                bigTextView.setBackgroundColor(Color.BLACK);
                bigTextView.setTextColor(Color.WHITE);
                return true;
            }
        });
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
        PlaySound playSound;
        switch (view.getId()) {
            case R.id.btDo:
                addInfoToFile("До");
                playSound = new PlaySound(261.63);
                playSound.playSound();
                break;
            case R.id.btRe:
                addInfoToFile("Ре");
                playSound = new PlaySound(293.66);
                playSound.playSound();
                break;
            case R.id.btMi:
                addInfoToFile("Ми");
                playSound = new PlaySound(329.63);
                playSound.playSound();
                break;
            case R.id.btFa:
                addInfoToFile("Фа");
                playSound = new PlaySound(349.23);
                playSound.playSound();
                break;
            case R.id.btSol:
                addInfoToFile("Соль");
                playSound = new PlaySound(392.00);
                playSound.playSound();
                break;
            case R.id.btLa:
                addInfoToFile("Ля");
                playSound = new PlaySound(440.00);
                playSound.playSound();
                break;
            case R.id.btSi:
                addInfoToFile("Си");
                playSound = new PlaySound(493.88);
                playSound.playSound();
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
