package ru.mirea.belov.looper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLooper myLooper = new MyLooper();
        //myLooper.start();

        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "mirea");
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
        }

        myLooper.start();

    }
}
