package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.NavigableMap;

public class CadastrarMembro extends AppCompatActivity {

    private EditText nome, idade, email;
    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        nome = findViewById(R.id.newMemberName);
        idade = findViewById(R.id.newMemberAge);
        email = findViewById(R.id.newMemberMail);
        btnCadastro = findViewById(R.id.btnRegister);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(nome.getText()) && !TextUtils.isEmpty(idade.getText()) && !TextUtils.isEmpty(email.getText())) {
                        Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        voltaParaMenu();
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void voltaParaMenu() {
        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

}