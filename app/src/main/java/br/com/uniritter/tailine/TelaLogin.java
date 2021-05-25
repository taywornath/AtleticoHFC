package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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


        try {
            SQLiteDatabase db = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar tabela
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario VARCHAR, senha VARCHAR, tipoUsuario INT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS tipoUsuario (id INTEGER PRIMARY KEY, tipo VARCHAR)");
            db.execSQL("INSERT INTO tipoUsuario(id, tipo) VALUES (1, 'Administrador')");
            db.execSQL("INSERT INTO tipoUsuario(id, tipo) VALUES (2, 'Membro')");

        } catch (Exception e) {
            e.printStackTrace();
        }


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Valida se os campos que são preenchidos estão vazios e se são iguais aos dados de login
                if (!TextUtils.isEmpty(editEmail.getText()) && !TextUtils.isEmpty(editSenha.getText())) {
                    if (TextUtils.equals(editEmail.getText(), email) && TextUtils.equals(editSenha.getText(), senha)) {
                        // Mostrar se login deu certo
                        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();

                        //Abrir o banco
                        SQLiteDatabase db = openOrCreateDatabase("app", MODE_PRIVATE, null);

                        //Pegar dados do input do usuario
                        db.execSQL("INSERT INTO usuarios(usuario, senha, tipo) VALUES ("+ editEmail.getText() +", " +editSenha.getText() +", 1);");

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