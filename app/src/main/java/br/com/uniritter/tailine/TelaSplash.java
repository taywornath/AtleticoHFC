package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TelaSplash extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 1000;

    // Componentes do Android
    private ConstraintLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Habilita as transições de Activity personalizadas
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_tela_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { menu(); }
        }, AUTO_HIDE_DELAY_MILLIS);

        // Localiza os componentes da tela
        layout = (ConstraintLayout) findViewById(R.id.tela_splash);



        // Aplica a animação definida no Layout
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setFillAfter(true);
        animation.setDuration(4000);
        layout.startAnimation(animation);

    }


    public void menu(){
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);
    }

}

