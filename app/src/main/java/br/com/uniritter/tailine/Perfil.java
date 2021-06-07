package br.com.uniritter.tailine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity {

    private Button alterarEmail, alterarSenha;
    private TextView txtEmail, txtNome;

    private String email, nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        alterarEmail = findViewById(R.id.btn_alterarEmail);
        alterarSenha = findViewById(R.id.btn_alterarSenha);
        txtEmail = findViewById(R.id.txt_emailPerfil);
        txtNome = findViewById(R.id.txt_nomePerfil);



    }


}