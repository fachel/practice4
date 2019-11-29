package ru.mirea.belov.lablesson4;

import android.util.Log;

public class MyThread extends Thread {
    private static final String TAG = MyThread.class.getSimpleName();
    public void run(){
        Log.d(TAG, "МОЙ ПОТОК ЗАПУЩЕН...");
    }
}
