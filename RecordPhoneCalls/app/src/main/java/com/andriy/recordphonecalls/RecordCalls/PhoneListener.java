package com.andriy.recordphonecalls.RecordCalls;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.andriy.recordphonecalls.DataBase.CallsDate;
import com.andriy.recordphonecalls.Stopwatch;

import java.io.File;
import java.io.IOException;

public class PhoneListener extends PhoneStateListener {
    private MediaRecorder mediaRecorder;
    private Context context;

    PhoneListener(Context context) {
        this.context = context;
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        RecordNotification recordNotification = new RecordNotification(context);

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                if (mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();

                    recordNotification.closeNotification();

                    Stopwatch.stop = true;

                    CallsDate date = new CallsDate();
                    date.addItem();
                }
                break;

            case TelephonyManager.CALL_STATE_OFFHOOK:
                recordNotification.showNotification();

                recordCall();

                Stopwatch.stop = false;
                Thread thread = new Thread(new Stopwatch(), "Stopwatch");
                thread.start();
                break;
        }
    }

    private void recordCall() {
        String fileName = Environment.getExternalStorageDirectory() + "/Record Call.mp3";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            outFile.delete();
        }

        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(fileName);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}