package br.com.uniritter.tailine.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FrequenciaAdapter extends RecyclerView.Adapter<FrequenciaAdapter.FrequenciaViewHolder> {

    @NonNull
    @Override
    public FrequenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.id.)
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FrequenciaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FrequenciaViewHolder extends RecyclerView.ViewHolder{

        public View viewFrequencia;
        public FrequenciaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewFrequencia = itemView;
        }
    }


}
