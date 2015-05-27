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

//    Handler handler = new Handler();
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // Use a new tread as this can take a while
//        final Thread thread = new Thread(new Runnable() {
//            public void run() {
//                new PlaySound(handler).genTone();
//                handler.post(new Runnable() {
//
//                    public void run() {
//                        new PlaySound(handler).playSound();
//                    }
//                });
//            }
//        });
//        thread.start();
//    }

    private void addInfoToFile(String text) {

        textView.setText("");
        List<FileLog> fileList = new ArrayList<>();
        File file = new File(getFilesDir(), StaticValue.FILE_NAME);

//        FileInputStream fis = null;
//        ObjectInputStream inObject=null;

        ReadWrite readWrite = new ReadWrite();
        fileList = readWrite.readFile(file);


//        try {
//            fis = new FileInputStream(file);
//            inObject = new ObjectInputStream(fis);
//            fileList = (List<FileLog>) inObject.readObject();
//            inObject.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error FileInputStream " + e.toString());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 1 " + e.toString());
//        } catch (OptionalDataException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 2 " + e.toString());
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 3 " + e.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error ObjectInputStream 4 " + e.toString());
//        }

        fileList.add(new FileLog(text));
        if (fileList.size() >= 20) {
            fileList.remove(0);
        }


        readWrite.write(file, fileList);


//        FileOutputStream fos = null;
//        ObjectOutputStream outObject = null;
//
//        try {
//            fos = new FileOutputStream(file);
//            outObject = new ObjectOutputStream(fos);
//            outObject.writeObject(fileList);
//            outObject.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error FileOutputStream " + e.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error ObjectOutputStream " + e.toString());
//        }

        for (FileLog fileLog : fileList) {
            textView.append(fileLog.toString());
        }



//        //проверочные действия работы
//        Date date = new Date();
//        String dateString = new SimpleDateFormat("dd.MM.yyyy : hh.mm.ss").format(date);
//        textView.append( dateString + " " + " : "+text+"\n");

//        fileLogArrayList = new ArrayList<FileLog>();
//        fileLog = new FileLog(text);
//        fileLogArrayList.add(fileLog);
//
//        Log.i(StaticValue.MY_LOG, "Size 1 _________" + fileLogArrayList.size());
//
//        if (fileLogArrayList.size() >= 5) fileLogArrayList.remove(0);
//        Log.i(StaticValue.MY_LOG, "Select button " + text);
//
//        Log.i(StaticValue.MY_LOG, "Size " + fileLogArrayList.size());
//
//        try {
//            outputStream = openFileOutput(StaticValue.FILE_NAME, 0);
//            objectOutputStreamt = new ObjectOutputStream(outputStream);
//            objectOutputStreamt.writeObject(fileLogArrayList);
//            Log.i(StaticValue.MY_LOG, "File created and saved ");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error openFileOutput " + e.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Error ObjectOutputStream " + e.toString());
//        }
//
//
//        try {
//            inputStream = openFileInput(StaticValue.FILE_NAME);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            fileLogArrayList = new ObjectInputStream(reader.readLine())
//            textView.append(reader.readLine());
////            objectInputStream =
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Eror open file  ");
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i(StaticValue.MY_LOG, "Eror ObjectOutputStream  ");
//        }
//
//
////        for (FileLog fl : fileLogArrayList) {
////            textView.append( fl.toString());
        }
}


/**
 *  http://developer.alexanderklimov.ru/android/texteditor.php
 *  http://developer-android.unlimited-translate.org/training/basics/data-storage/files.html#GetWritePermission
 *  http://microsin.net/programming/android/saving-files.html
 *  http://androidimran.blogspot.ru/2012/06/pass-custom-object-using-serializable.html
 *
 */
