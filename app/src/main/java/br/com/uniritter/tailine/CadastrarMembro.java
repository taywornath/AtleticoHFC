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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.uniritter.tailine.services.FirebaseServices;


public class CadastrarMembro extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha;
    private int tipoUsuario = 2;

    private Button btnCadastro;
    private RadioButton admin, membro;

    private static final String TAG = "CadastrarUsuario";
    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        editNome = findViewById(R.id.newMemberName);
        editEmail = findViewById(R.id.newMemberMail);
        editSenha = findViewById(R.id.editDefPassword);
        admin = findViewById(R.id.radAdmin);
        membro = findViewById(R.id.radMembro);


        if (membro.isChecked()){
            tipoUsuario = 1;
        } else {
            tipoUsuario = 2;
        }

        btnCadastro = findViewById(R.id.btnRegister);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editNome.getText()) && !TextUtils.isEmpty(editEmail.getText()) && !TextUtils.isEmpty(editSenha.getText()) ) {

                    cadastraNovoUsuario();

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

    public void cadastraNovoUsuario(){
        Map<String, Object> user = new HashMap<>();
        user.put("email", editEmail.getText().toString());
        user.put("nome", editNome.getText().toString());
        user.put("senha", editSenha.getText().toString());
        user.put("tipoUsuario", tipoUsuario);

// Add a new document with a generated ID
        db.collection("usuarios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Cadastro efetuado com sucesso! \n " + documentReference.getId());
                        voltaParaMenu();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Erro no cadastro. Tente novamente.", e);
                    }
                });
    }
}