package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class TelaPrincipal extends AppCompatActivity {


    private Button logout;
    private Button ranking;
    private Button cadastrarMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        logout = (Button) findViewById(R.id.btnLogout);
        ranking = (Button) findViewById(R.id.btnRanking);
        cadastrarMembro = (Button) findViewById(R.id.btnCadastrarMembro);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { logout(); }
        });

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cadastrarNovoMembro(); }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){ }
        });
    }

    private void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) { voltaLogin(); }
                });    }

    private void rankingJogadores() { }

    private void cadastrarNovoMembro() {
        Intent intent = new Intent(this, CadastrarMembro.class);
        startActivity(intent);
    }

    private void voltaLogin() {
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);
    }

}