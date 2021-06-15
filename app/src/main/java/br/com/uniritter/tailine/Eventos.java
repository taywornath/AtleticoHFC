package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.uniritter.tailine.services.FirebaseServices;

public class Eventos extends AppCompatActivity {

    private Button maiores, menores, share;
    private TextView frequentadores, eventos;
    private ImageView logo;

    private FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private final FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        frequentadores = findViewById(R.id.txt_frequentadores);
        eventos = findViewById(R.id.textWelcomeLogin);
        maiores = (Button) findViewById(R.id.btn_maiores);
        menores = (Button) findViewById(R.id.btn_menores);
        share = (Button) findViewById(R.id.btnShare);
        logo = (ImageView) findViewById(R.id.img_eventos);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){ calendario(); }
        });

        maiores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    confirmaPresenca();
            }
        });

        menores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retirarPresenca();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });
    }


    public void confirmaPresenca(){
        DocumentReference attendance = db.collection("frequencia").document("qRvJkrgw03Ug5YOg7bre");
        attendance.update("jogadores", FieldValue.arrayUnion(user.getUid()));
    }

    public void retirarPresenca(){
        DocumentReference attendance = db.collection("frequencia").document("qRvJkrgw03Ug5YOg7bre");
        attendance.update("jogadores", FieldValue.arrayRemove(user.getUid()));
    }


    private void calendario() {
        Intent intent = new Intent(this, Calendario.class);
        startActivity(intent);
    }

    private void shareContent(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Fica ligado nesse evento!");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}