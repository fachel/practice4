package ru.mirea.belov.loader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    public final String TAG = this.getClass().getSimpleName();
    private TextView mResultTextView;
    public static final int LOADER_ID = 1;
    private Loader<String> mLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultTextView = (TextView) findViewById(R.id.resultTxt);

        Bundle bundle = new Bundle();
        bundle.putString(RandomLoader.ARG_WORD, "test");
        // Инициализируем загрузчик с идентификатором
        // Если загрузчик не существует, то он будет создан,
        // иначе он будет перезапущен.
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);
    }

    // Будет вызван, если до этого не существовал
    // Это значит, что при повороте не будет вызываться
    // так как предыдущий загрузчик с данным ID уже был создан ранее
    // Будет также вызван при рестарте через метод LoaderManager.restartLoader()
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        // Создаем новый CursorLoader с нужными параметрами
        Loader<String> mLoader = null;
        // условие можно убрать, если вы используете только один загрузчик
        if (id == LOADER_ID) {
            mLoader = new RandomLoader(this, args);
            Log.d(TAG, "onCreateLoader");
        }
        return mLoader;
    }

    // Вызовется, когда загрузчик закончит свою работу. Вызывается в основном потоке
    // Может вызываться несколько раз при изменении данных
    // Также вызывается при поворотах
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished");
        mResultTextView.setText(data);

        // Если используется несколько загрузчиков, то удобнее через оператор switch-case
//        switch (loader.getId()) {
//            case LOADER_ID:
//                // Данные загружены и готовы к использованию
//
//                break;
//        }
        // список теперь содержит данные на экране
    }

    // Вызовется при уничтожении активности
    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    public void onClick(View view) {
        Log.d(TAG, "startLoad");
        mLoader.onContentChanged();
    }
}

