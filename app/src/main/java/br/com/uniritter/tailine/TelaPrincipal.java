package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPrincipal extends AppCompatActivity {

    private Button voltarLogin;
    private Button voltarSplash;
    private Button cadastrarMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        voltarLogin = (Button) findViewById(R.id.btnVoltarLogin);
        voltarSplash = (Button) findViewById(R.id.btnVoltaSplash);
        cadastrarMembro = (Button) findViewById(R.id.btnCadastrarMembro);

        voltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltaParaTelaDeLogin();
            }
        });

        voltarSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                voltaParaTelaSplash();
            }
        });

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cadastrarNovoMembro(); }
        });
    }

    private void voltaParaTelaDeLogin() {
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);
    }

    private void voltaParaTelaSplash() {
        Intent intent = new Intent(this, TelaSplash.class);
        startActivity(intent);
    }

    private void cadastrarNovoMembro() {
        Intent intent = new Intent(this, CadastrarMembro.class);
        startActivity(intent);
    }


}