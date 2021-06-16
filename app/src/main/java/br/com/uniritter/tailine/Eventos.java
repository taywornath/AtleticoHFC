package br.com.uniritter.tailine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import br.com.uniritter.tailine.services.FirebaseServices;

public class Eventos extends AppCompatActivity {

    private Button maiores, menores, share;
    private TextView frequentadores, eventos;
    private CalendarView logo;
    private CheckBox checkBox;

    private Boolean presenca;

    private FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();
    private final FirebaseUser user = FirebaseServices.getFirebaseAuthInstance().getCurrentUser();
    private static final String TAG = "CadastroEvento";
    private static final String TAG1 = "Calendario";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        frequentadores = findViewById(R.id.txt_frequentadores);
        eventos = findViewById(R.id.textWelcomeLogin);
        maiores = (Button) findViewById(R.id.btn_maiores);
        checkBox = findViewById(R.id.checkBox);
        share = (Button) findViewById(R.id.btnShare);
        logo = (CalendarView) findViewById(R.id.cv_eventos);

        //logo.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v){ calendario(); }
        //});

        logo.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;//da uma olhada se não precisa mais um.
                Log.d(TAG1, "onSelectDayChange: dd/mm/yyyy: " + date);




                Intent intent = new Intent(Eventos.this, Calendario.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
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
            case R.id.checkBox:
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

    public void proxEventos(View view) {
        db.collection("eventos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Erro ao abrir", task.getException());
                        }
                    }


                });


    }


}