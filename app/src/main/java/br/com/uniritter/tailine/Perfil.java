package br.com.uniritter.tailine;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class Perfil extends AppCompatActivity {

    private String nome;
    private Button alterarSenha, alterarNome;
    private TextView txtEmail, txtNome;

    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();

    private static final String NOME = "nome";
    private static final String TAG = "Perfil";

    private CollectionReference colRef = db.collection("usuarios");
    private DocumentReference docRef = db.collection("usuarios").document(user.getUid());

    private final Usuario usuario = new Usuario();
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        alterarSenha = (Button) findViewById(R.id.btn_alterarSenha);
        alterarNome = (Button) findViewById(R.id.btn_alterarNome);
        txtEmail = findViewById(R.id.txt_emailPerfil);
        txtNome = findViewById(R.id.txt_nomePerfil);

        getDocument();

        txtEmail.setText(user.getEmail());

        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.resetaSenha(user);
                showAlteraSenha();
            }
        });

        alterarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlterarNome(v);
            }
        });

    }

    public void getDocument() {
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            nome = documentSnapshot.getString(NOME);
                            txtNome.setText(nome);
                        } else {
                            Toast.makeText(Perfil.this, "Usuario não cadastrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Perfil.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    public void showAlterarNome(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nome");

        final View customLayout = getLayoutInflater().inflate(R.layout.layout_altera_dados, null);
        builder.setView(customLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which) {
                EditText editText = customLayout.findViewById(R.id.editText);
                usuario.setNome(editText.getText().toString());
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showAlteraSenha() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alterar Senha");

        final View customLayout = getLayoutInflater().inflate(R.layout.layout_altera_senha, null);
        builder.setView(customLayout);
        builder.setMessage("O link de redefinição de senha foi enviado para " + user.getEmail());
        builder.setPositiveButton("Entendi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which) {
                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    }




}

