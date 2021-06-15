package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ranking extends AppCompatActivity {

    private Button maiores, menores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        maiores = (Button) findViewById(R.id.btnMaisFrequentes);
        menores = (Button) findViewById(R.id.btnMenosFrequentes);

        maiores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMaiores();
            }
        });

        menores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenores();
            }
        });
    }

    public void gotoMaiores(){
        Intent intent = new Intent(this, MaioresFrequencias.class);
        startActivity(intent);
    }

    public void gotoMenores(){
        Intent intent = new Intent(this, MenoresFrequencias.class);
        startActivity(intent);
    }

}