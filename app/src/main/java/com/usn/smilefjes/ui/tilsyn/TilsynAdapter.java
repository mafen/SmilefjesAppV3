package com.usn.smilefjes.ui.tilsyn;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.usn.smilefjes.databinding.TilsynItemBinding;
import com.usn.smilefjes.R;
import com.usn.smilefjes.data.entities.Tilsyn;

import java.util.ArrayList;
import java.util.List;


public class TilsynAdapter extends RecyclerView.Adapter<TilsynAdapter.TilsynViewHolder>{
    private List<Tilsyn> tilsynListe;
    //private Context ctx;

    TilsynItemBinding tilsynItemBinding;


    public TilsynAdapter(List<Tilsyn> tilsynList) {
        this.tilsynListe = tilsynList ;

    }

    public void setTilsynListe(List<Tilsyn> tilsynListe) {
        this.tilsynListe = tilsynListe;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TilsynViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        tilsynItemBinding = TilsynItemBinding.inflate(layoutInflater, parent, false);
        return new TilsynViewHolder(tilsynItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TilsynViewHolder holder, int position) {
        Tilsyn tilsyn = tilsynListe.get(position);
        holder.bind(tilsyn);

    }

    @Override
    public int getItemCount() {
        return tilsynListe.size();
    }


    class TilsynViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TilsynItemBinding binding;



        public TilsynViewHolder(TilsynItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);

        }


        public void bind(Tilsyn tilsyn){
            binding.setTilsyn(tilsyn);
            binding.executePendingBindings();

        }


        @Override
        public void onClick(View v) {
            String orgNr = binding.textViewOrgNr.getText().toString();
            Log.d("Tilsynid", "onClick: " + binding.getTilsyn().getTilsynid());
            tilsynListe.remove(binding.getTilsyn());
            notifyDataSetChanged();
            Log.d("Navn", "onClick: " + binding.getTilsyn().getNavn());
        }
    }


    }



