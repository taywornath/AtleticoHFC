package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox checkBox;

    private Boolean presenca;

    private FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private final FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        frequentadores = findViewById(R.id.txt_frequentadores);
        eventos = findViewById(R.id.textWelcomeLogin);
        maiores = (Button) findViewById(R.id.btn_maiores);
        checkBox = findViewById(R.id.checkBox1);
        share = (Button) findViewById(R.id.btnShare);
        logo = (ImageView) findViewById(R.id.img_eventos);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){ calendario(); }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        DocumentReference attendance = db.collection("frequencia").document("qRvJkrgw03Ug5YOg7bre");

        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked) {
                    presenca = true;
                    attendance.update("jogadores", FieldValue.arrayUnion(user.getUid()));
                } else {
                    presenca = false;
                    attendance.update("jogadores", FieldValue.arrayRemove(user.getUid()));
                    break;
                }
        }
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