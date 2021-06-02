package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class TelaSplash extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { menu(); }
        }, AUTO_HIDE_DELAY_MILLIS);

    }

    public void menu(){
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);
    }


}

