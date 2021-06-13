package br.com.uniritter.tailine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import br.com.uniritter.tailine.services.FirebaseServices;

public class CadastroEventos extends AppCompatActivity {

    private EditText nome, data, local, horario;

    private Button btnCadastro;

    private static final String NAME_KEY = "nome";
    private static final String DATE_KEY = "data";
    private static final String LOC_KEY = "local";
    private static final String HOUR_KEY = "horario";
    private static final String TAG = "CadastroEvento";


    private final FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_eventos);


        nome = findViewById(R.id.evento_nome);
        data = findViewById(R.id.evento_data);
        local = findViewById(R.id.evento_local);
        horario = findViewById(R.id.evento_horario);
        btnCadastro = findViewById(R.id.btnRegister);


    }

    private void voltaParaMenu() {
        Intent intent = new Intent(this, TelaPrincipal.class);
        startActivity(intent);
    }

    public void novoEvento(View view){
        String nomeEvento = nome.getText().toString();
        String dataEvento = data.getText().toString();
        String localEvento = local.getText().toString();
        String horaEvento = horario.getText().toString();

        if(nomeEvento.isEmpty() || dataEvento.isEmpty() || localEvento.isEmpty() || horaEvento.isEmpty()) {
            return;
        }
        Map<String, Object> event = new HashMap<String, Object>();
        event.put(NAME_KEY, nomeEvento);
        event.put(DATE_KEY, dataEvento);
        event.put(LOC_KEY, localEvento);
        event.put(HOUR_KEY, horaEvento);

        db.collection("eventos")
                .add(event)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Cadastro efetuado com sucesso! \n " + documentReference.getId());
                reload();
                voltaParaMenu();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Erro no cadastro. Tente novamente.", e);
                    }
                });
        };

    public void reload() { }
    }






