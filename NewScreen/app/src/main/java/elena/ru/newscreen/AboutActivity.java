package elena.ru.newscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Dmitry on 01.06.2016.
 */

public class AboutActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        String user;
        String gift;
        String avtor;
        user = getIntent().getStringExtra("username");//принимающая активность вызывает подходящий метод.
        gift = getIntent().getStringExtra("gift");
        avtor = getIntent().getStringExtra("avtor");

        TextView infoTextView = (TextView)findViewById(R.id.textViewInfo);
        infoTextView.setText(user + " , вам передали "+ gift + " ." + avtor);
    }

}
