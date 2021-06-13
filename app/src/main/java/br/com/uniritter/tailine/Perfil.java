package br.com.uniritter.tailine;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private Button alterarEmail, alterarSenha;
    private TextView txtEmail, txtNome;

    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();

    private static final String NOME = "nome";
    private static final String TAG = "Perfil";

    private CollectionReference colRef = db.collection("usuarios");
    private DocumentReference docRef = db.collection("usuarios").document(user.getUid());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        alterarEmail = findViewById(R.id.btn_alterarEmail);
        alterarSenha = findViewById(R.id.btn_alterarSenha);
        txtEmail = findViewById(R.id.txt_emailPerfil);
        txtNome = findViewById(R.id.txt_nomePerfil);

        getDocument();

        txtEmail.setText(user.getEmail());

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

}