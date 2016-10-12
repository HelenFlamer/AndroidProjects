package elena.ru.newscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
   /* public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);//создаем экземпляр класса,
        // указываем текущий класс и класс для перехода
        startActivity(intent);//запускаем новый экран
    }
    public void onClick2(View view){
        Intent intent2 = new Intent(MainActivity.this, AboutActivity2.class);//создаем экземпляр класса,
        // указываем текущий класс и класс для перехода
        startActivity(intent2);//запускаем новый экран
    }*/

    public void onClick(View view) {
        EditText userEditText = (EditText) findViewById(R.id.editTextUser);
        EditText giftEditText = (EditText) findViewById(R.id.editTextGift);
        EditText avtorEditText = (EditText) findViewById(R.id.editTextAvtor);

        Intent intent = new Intent(MainActivity.this, AboutActivity.class);

        // в ключ username пихаем текст из первого текстового поля
        intent.putExtra("username", userEditText.getText().toString());//передача данных в другую активность (ключ,значение)
        // в ключ gift пихаем текст из второго текстового поля
        intent.putExtra("gift", giftEditText.getText().toString());
        intent.putExtra("avtor", avtorEditText.getText().toString());
        startActivity(intent);
    }


}
