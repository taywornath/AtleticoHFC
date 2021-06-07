package br.com.uniritter.tailine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Eventos extends AppCompatActivity {

    private Button maiores, menores;
    private TextView frequentadores, eventos;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        frequentadores = findViewById(R.id.txt_frequentadores);
        eventos =findViewById(R.id.txt_eventos);
        maiores = findViewById(R.id.btn_maiores);
        menores = findViewById(R.id.btn_menores);
        logo = findViewById(R.id.img_eventos);


    }

    public void mostraRankingMaiores(){

    }
}