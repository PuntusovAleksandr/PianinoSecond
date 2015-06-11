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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.dev2.pianinisecond.edit.FileLog;
import com.example.dev2.pianinisecond.play_sound.PlaySound;
import com.example.dev2.pianinisecond.read_write.ReadWrite;
import com.example.dev2.pianinisecond.statik_value.StaticValue;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author AleksandrP
 */
public class MainActivity extends Activity implements StaticValue {

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

    /**
     * Один из циклов
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /**
         * @value инициализация всех View элементов на текущем активити
         */
        btDo = (Button) findViewById(R.id.btDo);
        btRe = (Button) findViewById(R.id.btRe);
        btMi = (Button) findViewById(R.id.btMi);
        btFa = (Button) findViewById(R.id.btFa);
        btSol =(Button) findViewById(R.id.btSol);
        btLa = (Button) findViewById(R.id.btLa);
        btSi = (Button) findViewById(R.id.btSi);
        textView = (TextView) findViewById(R.id.textView);
        bigTextView = (TextView) findViewById(R.id.bif_text_yiew);
        ll_3 = (LinearLayout) findViewById(R.id.ll_3);
        ll_head = (LinearLayout) findViewById(R.id.ll_head);

        bigTextView.setMovementMethod(new ScrollingMovementMethod());   // включаем скролинг
        textView.setMovementMethod(new ScrollingMovementMethod());   // включаем скролинг

        /**
         * меттод слушатель, который позволяет открвъыть окно логов на полный экран
         */
        textView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override // "слушатель" организован в виде анонимного внутреннего класса
            public boolean onLongClick(View arg0) // действия на нажатие
            {
                bigTextView.setText(textView.getText().toString()); // присваивается текст из имеющегося на экране
                ll_3.setVerticalGravity(1); // устновка размеа на максимальную высоту
                bigTextView.setVisibility(View.VISIBLE); // установка визуализации
                bigTextView.setBackgroundColor(Color.BLACK);    // присвоение черного фона
                bigTextView.setTextColor(Color.WHITE);  // присвоение белых кнопок

                LinearLayout.LayoutParams llParamd = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ll_3.setLayoutParams(llParamd);

                return true;
            }
        });
        /**
         * меттод слушатель, который возвращает окно в нормальный вид
         */
        bigTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override // "слушатель" организован в виде анонимного внутреннего класса
            public boolean onLongClick(View arg0) // действия на нажатие
            {
                bigTextView.setText("");    // установка пустого текста в bigTextView
                ll_3.setVerticalGravity(0); // установка вертикального размера в нуль
                bigTextView.setVisibility(View.INVISIBLE);  // гасим показ
                bigTextView.setBackgroundColor(Color.BLACK);    // установа белого фона bigTextView
                bigTextView.setTextColor(Color.WHITE);  // установка текса в белый цвет

                LinearLayout.LayoutParams llParamd = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ll_3.setLayoutParams(llParamd);
                return true;
            }
        });

        if (savedInstanceState == null) {


            addInfoToFile("\n" + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

        }


    }

    /**
     * меетод, который в жизненном цикле выполняется всегда после Started
     * (or metod Paused)
     */
    @Override
    protected void onResume() {
        super.onResume();

        List<FileLog> fileList = new ArrayList<>();     // создание списка FileLog
        ReadWrite readWrite = new ReadWrite();  // создание объекта класса ReadWrite
        fileList = readWrite.readFile();    // получение списка fileList для отображения его на экране при запуске приложения
        for (FileLog fileLog : fileList) {
            textView.append(fileLog.toString());    // добавление элементов на экран в textView
        }
//        textView.append();
//        new SimpleDateFormat("dd.MM.yyyy : hh.mm.ss").format(date)
    }

    /**
     * Метод, который слушает нажатие кнопок
     * @param view - текущий view
     */
    public void clicOnButton(View view) {
        // создание объекта класса PlaySound
        PlaySound playSound;
        switch (view.getId()) {
            // Кнопка "До"
            case R.id.btDo:
                addInfoToFile("До");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(261.63);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
            // Кнопка "Ре"
            case R.id.btRe:
                addInfoToFile("Ре");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(293.66);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
            // Кнопка "Ми"
            case R.id.btMi:
                addInfoToFile("Ми");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(329.63);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
            // Кнопка "Фа"
            case R.id.btFa:
                addInfoToFile("Фа");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(349.23);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
            // Кнопка "Соль"
            case R.id.btSol:
                addInfoToFile("Соль");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(392.00);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
            // Кнопка "Ля"
            case R.id.btLa:
                addInfoToFile("Ля");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(440.00);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
            // Кнопка "Си"
            case R.id.btSi:
                addInfoToFile("Си");
                // передаем в конструктор PlaySound значение частоты ноты
                playSound = new PlaySound(493.88);
                // запуск потока воспроизведения ноты при нажатии на кнопку
                playSound.playSound();
                break;
        }
    }

    /**
     * Метод для записи данных в файл
     * @param text file - name file in the internal memory
     */
    private void addInfoToFile(String text) {
        // установка textView в пустую строку (сбрасывание предыдущего вида)
        textView.setText("");
        List<FileLog> fileList = new ArrayList<>(); // создание списка FileLog

        ReadWrite readWrite = new ReadWrite();  // создание объекта класса ReadWrite
        fileList = readWrite.readFile();// получение списка fileList для отображения его на экране при запуске приложения

        String textToFile = "::: " + new SimpleDateFormat("hh.mm.ss").format(new Date()) + "\t"+ text;


        // проверка количества содержимого и удаление не нужного
        fileList.add(new FileLog(textToFile));
        if (fileList.size() >= 200) {
            fileList.remove(0);
        }

        // запись данных в файл
        readWrite.write(fileList);
        // чтение и визуализация данных на экране
        for (FileLog fileLog : fileList) {
            textView.append(fileLog.toString());
        }
    }
}


/**
 * Источники:
 *  http://developer.alexanderklimov.ru/android/texteditor.php
 *  http://developer-android.unlimited-translate.org/training/basics/data-storage/files.html#GetWritePermission
 *  http://microsin.net/programming/android/saving-files.html
 *  http://androidimran.blogspot.ru/2012/06/pass-custom-object-using-serializable.html
 *
 */
