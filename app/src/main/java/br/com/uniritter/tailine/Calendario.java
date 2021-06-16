package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import br.com.uniritter.tailine.services.FirebaseServices;


public class Calendario extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseServices.getFirebaseFirestoreInstance();

    private static final String TAG = "MainActivity";

    private TextView theDate;
    private TextView event;
    private Button btnGoCalendar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        theDate = (TextView) findViewById(R.id.date);
        event = (TextView) findViewById(R.id.date_event);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);



        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);




        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendario.this, Eventos.class);
                startActivity(intent);
            }
        });

    }
}