package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.uniritter.tailine.services.FirebaseServices;


public class CadastrarMembro extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha;
    private int tipoUsuario;

    private Button btnCadastro;
    private RadioButton admin, membro;

    private static final String TAG = "CadastrarMembro";
    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        mAuth = FirebaseAuth.getInstance();

        editNome = findViewById(R.id.newMemberName);
        editEmail = findViewById(R.id.newMemberMail);
        editSenha = findViewById(R.id.editDefPassword);
        admin = findViewById(R.id.radAdmin);
        membro = findViewById(R.id.radMembro);
        btnCadastro = findViewById(R.id.btnRegister);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editNome.getText()) && !TextUtils.isEmpty(editEmail.getText()) && !TextUtils.isEmpty(editSenha.getText()) ) {
                    // verifica se o tipo de usuário foi selecionado
                    if (membro.isChecked()){
                        tipoUsuario = 1;
                    } else if (admin.isChecked()){
                        tipoUsuario = 2;
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Informe o tipo de usuário", Toast.LENGTH_LONG).show();
                    }

                    // chama a função para criar novo usuario no Authentication
                    createAccount(editEmail.getText().toString(), editSenha.getText().toString());

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

    public void cadastraNovoUsuario(String id, int tipo){
        //  salva os dados do novo usuario na collection usuarios
        Map<String, Object> user = new HashMap<>();
        user.put("UID", id);
        user.put("tipoUsuario", tipo);

        // Adiciona o novo usuário na collection usuarios
        db.collection("usuarios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Cadastro efetuado com sucesso! \n " + documentReference.getId());
                        reload();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Erro no cadastro. Tente novamente.", e);
                    }
                });
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            cadastraNovoUsuario(mAuth.getUid(), tipoUsuario);
                            Toast.makeText(CadastrarMembro.this, "Cadastro realizado com sucesso.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastrarMembro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void reload() { }

}