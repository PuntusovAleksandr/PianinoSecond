package com.example.dev2.pianinisecond.play_sound;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import com.example.dev2.pianinisecond.statik_value.StaticValue;

/**
 * Created by Aleksandr on 26.05.2015.
 */
public class PlaySound {


    private final int duration = 3; // seconds
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private double freqOfTone = 440; // hz

    private final byte generatedSnd[] = new byte[2 * numSamples];

    private int note;

    public PlaySound(int note) {

        switch (note) {

            case 1:
                this.freqOfTone = 300;
                break;

            case 2:
                this.freqOfTone = 900;
                break;

            case 3:
                this.freqOfTone = 1500;
                break;

            case 4:
                this.freqOfTone = 3000;
                break;

            case 5:
                this.freqOfTone = 5000;
                break;

            case 6:
                this.freqOfTone = 7500;
                break;

            case 7:
                this.freqOfTone = 10000;
                break;

        }
        Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start first thread");
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start getTone");
                genTone();
                Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start second thread");
                new Runnable() {
                    @Override
                    public void run() {
                        Log.i(StaticValue.MY_LOG_FOR_SOUND, "Start playSound");
                        playSound();
                    }
                };
                Log.i(StaticValue.MY_LOG_FOR_SOUND, "Finish second thread");
            }
        });
        Log.i(StaticValue.MY_LOG_FOR_SOUND, "Finish first thread");
        thread.start();
    }

    void genTone(){
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

    void playSound(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }

}