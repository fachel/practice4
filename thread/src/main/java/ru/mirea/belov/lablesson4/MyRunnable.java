package ru.mirea.belov.lablesson4;

import android.util.Log;
import android.widget.TextView;

public class MyRunnable implements Runnable {
    public static final String TAG = MyRunnable.class.getSimpleName();
    public String goal;
    public String sred;
    public MyRunnable(String goal){
        this.goal = goal;
    }

    @Override
    public void run(){
        Log.d(TAG, "МОЙ ПОТОК ЗАПУЩЕН:" + goal);
        double lessons = 59;
        double days = 26;
        double temp = lessons / days;
        sred = String.valueOf(temp);
        Log.d(TAG, "СРЕДНЕЕ КОЛ-ВО ПАР В НЕДЕЛЮ: " + sred);
    }
}
