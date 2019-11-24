package com.usn.smilefjes.ui.kravpunkt;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.data.entities.Kravpunkt;
import com.usn.smilefjes.databinding.KravItemBinding;

import java.util.List;


public class KravAdapter extends RecyclerView.Adapter<KravAdapter.KravViewHolder> {
    KravItemBinding itemBinding;
    private List<Kravpunkt> kravListe;


    public KravAdapter(List<Kravpunkt> kravListe) {
        this.kravListe = kravListe;

    }

    public void setKravListe(List<Kravpunkt> kravListe) {
        this.kravListe = kravListe;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public KravViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemBinding = KravItemBinding.inflate(layoutInflater, parent, false);
        return new KravViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull KravViewHolder holder, int position) {
        Kravpunkt kravpunkt = kravListe.get(position);
        holder.bind(kravpunkt);



    }

    @Override
    public int getItemCount() {
        return kravListe.size();
    }



    class KravViewHolder extends RecyclerView.ViewHolder{

        private KravItemBinding binding;


        public KravViewHolder(KravItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


        public void bind(Kravpunkt kravpunkt) {
            binding.setKravpunkt(kravpunkt);
            binding.executePendingBindings();

        }

    }


}





