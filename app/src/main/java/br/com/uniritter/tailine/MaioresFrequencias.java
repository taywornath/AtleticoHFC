package br.com.uniritter.tailine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MaioresFrequencias extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maiores_frequencias);

        recyclerView = (RecyclerView) findViewById(R.id.recyViewMaiores);


    }
}