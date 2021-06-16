package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.uniritter.tailine.models.TipoUsuario;
import br.com.uniritter.tailine.models.Usuario;
import br.com.uniritter.tailine.services.FirebaseServices;

public class TelaPrincipal extends AppCompatActivity {

    private String nome;
    private int tipoUsuario;

    private Button logout, ranking, cadastrarMembro, eventos, novoEvento, perfil;
    private TextView username;

    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private final FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();

    private static final String NOME = "nome";
    private static final String TIPO = "tipoUsuario";
    private static final String TAG = "TelaPrincipal";

    private DocumentReference docRef = db.collection("usuarios").document(user.getUid());

    private TipoUsuario tipoUsuarioObj = new TipoUsuario(1, 2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        logout = (Button) findViewById(R.id.btnLogout);
        ranking = (Button) findViewById(R.id.btnRanking);
        cadastrarMembro = (Button) findViewById(R.id.btnCadastrarMembro);
        eventos = (Button) findViewById(R.id.btnEventos);
        novoEvento = (Button) findViewById(R.id.btnCadastrarEvento);
        perfil = (Button) findViewById(R.id.btnPerfil);
        username = findViewById(R.id.text_username);

        getDocument();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { logout(); }
        });

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cadastrarNovoMembro(); }
        });

        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { eventos();  }
        });

        novoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cadastrarEvento();  }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { perfil(); }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { rankingJogadores(); }
        });
    }

    // validação e bloqueio dos botões de cadastros quando for membro
    private void defineView(int tipo) {
        if (tipo == tipoUsuarioObj.membro) {
            novoEvento.setVisibility(View.GONE);
            cadastrarMembro.setVisibility(View.GONE);
        }
    }

    // validação do tipo de usuário logado
    public void getDocument() {
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            nome = documentSnapshot.getString(NOME);
                            tipoUsuario = Integer.parseInt(documentSnapshot.get(TIPO).toString());
                            username.setText(nome);
                            defineView(tipoUsuario);
                        } else {
                            Toast.makeText(TelaPrincipal.this, "Usuario não cadastrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TelaPrincipal.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    private void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        voltaLogin();
                    }
                });
    }

    private void perfil(){
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    private void rankingJogadores() {
        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }

    private void cadastrarNovoMembro() {
        Intent intent = new Intent(this, CadastrarMembro.class);
        startActivity(intent);
    }

    private void voltaLogin(){
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);
    }

    private void eventos() {
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }

    private void cadastrarEvento(){
        Intent intent = new Intent(this, CadastroEventos.class);
        startActivity(intent);
    }



}