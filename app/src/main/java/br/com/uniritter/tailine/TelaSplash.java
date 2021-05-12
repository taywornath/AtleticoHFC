package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaSplash extends AppCompatActivity {

    // Inicializa o componente
    private Button botaoLoginSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        // Vincula o botão da tela ao botão da Classe
        botaoLoginSplash = (Button) findViewById(R.id.btnLoginSplash);

        // Atribui o evento de click ao botão e a sua ação após o clique
        botaoLoginSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irLogin();
            }
        });
    }


    private void irLogin() {
        // Cria a intenção de mudar da tela atual, para a tela de login
        Intent intent = new Intent(this, TelaLogin.class);
        // Executa a intenção
        startActivity(intent);
    }

}