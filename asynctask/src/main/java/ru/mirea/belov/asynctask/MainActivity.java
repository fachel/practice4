package ru.mirea.belov.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView mInfoTextView;
    private ProgressBar mProgressBar;
    private Button mStartButton;
    private ProgressBar mHorizontalProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInfoTextView = findViewById(R.id.text_info);
        mStartButton = findViewById(R.id.button_start);
        mHorizontalProgressBar = findViewById(R.id.progress_horizontal);
    }

    public void onClick(View view) {
        CatTask catTask = new CatTask();
        catTask.execute();
    }

    class CatTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mInfoTextView.setText("Поехали");
            mStartButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mInfoTextView.setText("Киллометр: " + values[0]);
            mHorizontalProgressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                int counter = 0;

                for(int i = 0; i < 14; i++){
                    getFloor();
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mInfoTextView.setText("Приехали");
            mStartButton.setVisibility(View.VISIBLE);
            mHorizontalProgressBar.setProgress(0);
        }

        private void getFloor() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}