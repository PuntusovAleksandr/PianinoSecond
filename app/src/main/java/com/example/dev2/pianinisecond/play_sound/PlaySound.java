package com.example.dev2.pianinisecond.play_sound;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.util.Log;
import com.example.dev2.pianinisecond.statik_value.StaticValue;

/**
 * Created by Aleksandr on 26.05.2015.
 */
public class PlaySound {


    private final int duration = 1;                                 // секунжы звучания
    private final int sampleRate = 44000;                           // количество точек
    private final int numSamples = duration * sampleRate;           // количество точек за время
    private final double sample[] = new double[numSamples];         // размер массива
    private double freqOfTone = 0;                                  // hz

    private final byte generatedSnd[] = new byte[2 * numSamples];   // размер байтового массива

    Handler handler = new Handler();

    public PlaySound(Handler handler) {
        this.handler = handler;
    }

    public PlaySound() {
    }

    public PlaySound(double note) {
        this.freqOfTone = note;
    }
    // можно сделать в отдельном методе
    // convert to 16 bit pcm sound array
    // assumes the sample buffer is normalized.



    public void genTone(){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    public void playSound(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }
























//        Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start first thread");
//
//            @Override
//            public void run() {
//                Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start getTone");
//                genTone();
//                Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start second thread");
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start playSound");
//                        playSound();
//                    }
//                };
//                Log.i(StaticValue.MY_LOG_FOR_SOUND, "Finish second thread");
//            }
//        });
//        Log.i(StaticValue.MY_LOG_FOR_SOUND, "Finish first thread");
//        thread.start();
//    }
//
//    void genTone(){
//        // fill out the array
//        for (int i = 0; i < numSamples; ++i) {
//            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
//        }
//
//        // convert to 16 bit pcm sound array
//        // assumes the sample buffer is normalised.
//        int idx = 0;
//        for (final double dVal : sample) {
//            // scale to maximum amplitude
//            final short val = (short) ((dVal * 32767));
//            // in 16 bit wav PCM, first byte is the low order byte
//            generatedSnd[idx++] = (byte) (val & 0x00ff);
//            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
//
//        }
//    }
//
//    void playSound(){
//        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
//                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
//                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
//                AudioTrack.MODE_STATIC);
//        audioTrack.write(generatedSnd, 0, generatedSnd.length);
//        audioTrack.play();
//    }

}