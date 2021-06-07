package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import br.com.uniritter.tailine.services.FirebaseServices;

public class TelaPrincipal extends AppCompatActivity {

    private Button logout, ranking, cadastrarMembro, eventos;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        logout = (Button) findViewById(R.id.btnLogout);
        ranking = (Button) findViewById(R.id.btnRanking);
        cadastrarMembro = (Button) findViewById(R.id.btnCadastrarMembro);
        eventos = (Button) findViewById(R.id.btnEventos);
        username = findViewById(R.id.username);

        username.setText(getUserName());

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

        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){ eventos(); }
        });
    }


    private void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) { voltaLogin(); }
                });
    }

    public String getUserName() {
        FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();
        String name = "";

        if (user != null) {
            name = user.getDisplayName();
        }
        return name;
    }

    private void rankingJogadores() { }

    private void cadastrarNovoMembro() {
        Intent intent = new Intent(this, CadastrarMembro.class);
        startActivity(intent);
    }

    private void voltaLogin() {
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);
    }

    private void eventos() {
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }



}