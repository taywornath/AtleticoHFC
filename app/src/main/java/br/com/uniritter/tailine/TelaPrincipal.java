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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.uniritter.tailine.models.TipoUsuario;
import br.com.uniritter.tailine.models.Usuario;
import br.com.uniritter.tailine.services.FirebaseServices;

public class TelaPrincipal extends AppCompatActivity {

    private Button logout, ranking, cadastrarMembro, eventos, perfil;
    private TextView username;

    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private final FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();

    private TipoUsuario tipoUsuario;
    private Usuario usuario = new Usuario(user.getDisplayName(), user.getEmail(), user);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        logout = (Button) findViewById(R.id.btnLogout);
        ranking = (Button) findViewById(R.id.btnRanking);
        cadastrarMembro = (Button) findViewById(R.id.btnCadastrarMembro);
        eventos = (Button) findViewById(R.id.btnEventos);
        perfil = (Button) findViewById(R.id.btnPerfil);
        username = findViewById(R.id.text_username);

        username.setText(usuario.getNome());


        // busca o tipo do usuario no firebase para validar se vai habilitar ou não os botões
        DocumentReference docRef = db.collection("tipoUsuario").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tipoUsuario = documentSnapshot.toObject(TipoUsuario.class);
            }
        });


        // busca o usuario do banco
//        DocumentReference docReference = db.collection("usuarios").document("taywornath@gmail.com");
//        docReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                usuario = documentSnapshot.toObject(Usuario.class);
//            }
//        });

        //se usuario não é admin, esconde os botões de cadastro de evento e membro
        if(tipoUsuario.admin != usuario.getTipoUsuario()) {
            eventos.setVisibility(View.GONE);
            cadastrarMembro.setVisibility(View.GONE);
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarNovoMembro();
            }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventos();
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { perfil(); }
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

    private void rankingJogadores() {
    }

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


    private void perfil(){
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }


}