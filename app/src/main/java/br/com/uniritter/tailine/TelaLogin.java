package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button botaoLogin;

    private String email = "taywornath@gmail.com";
    private String senha = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editTextEmail);
        editSenha = findViewById(R.id.editTextPassword);
        botaoLogin = findViewById(R.id.btnLogin);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Valida se os campos que são preenchidos estão vazios e se são iguais aos dados de login
                if (!TextUtils.isEmpty(editEmail.getText()) && !TextUtils.isEmpty(editSenha.getText())) {
                    if (TextUtils.equals(editEmail.getText(), email) && TextUtils.equals(editSenha.getText(), senha)) {
                        // Mostrar se login deu certo
                        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        fazLogin();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void fazLogin() {
        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }
}