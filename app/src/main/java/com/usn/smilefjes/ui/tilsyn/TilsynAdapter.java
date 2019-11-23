package com.usn.smilefjes.ui.tilsyn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.TilsynActivity;
import com.usn.smilefjes.data.entities.Kravpunkt;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.TilsynItemBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TilsynAdapter extends RecyclerView.Adapter<TilsynAdapter.TilsynViewHolder> implements Filterable {
    TilsynItemBinding tilsynItemBinding;
    private List<Tilsyn> tilsynListe;
    List<Tilsyn> fullTilsynsliste;

    private OnTilsynLytter onTilsynLytter;

    private final Filter tilsynsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            final List<Tilsyn> filtertListe = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filtertListe.addAll(fullTilsynsliste);

            } else {
                String filterMoenster = constraint.toString().toLowerCase().trim();
                for (Tilsyn tilsyn : fullTilsynsliste) {
                    if (tilsyn.getNavn().toLowerCase().contains(filterMoenster) || tilsyn.getPoststed().toLowerCase().contains(filterMoenster)) {
                        filtertListe.add(tilsyn);
                    }
                }

            }
            FilterResults reslutater = new FilterResults();

            reslutater.values = filtertListe;

            return reslutater;
        }

        //https://stackoverflow.com/questions/14642985/type-safety-unchecked-cast-from-object-to-listmyobject

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            tilsynListe.clear();
            if (results.values != null) {
                List<?> result = (List<?>) results.values;
                for (Object object : result) {
                    if (object instanceof Tilsyn) {
                        tilsynListe.add((Tilsyn) object); // <-- add to temp
                    }
                }

                Collections.sort(tilsynListe);
                Collections.reverse(tilsynListe);

                notifyDataSetChanged();


            }

        }
    };

    public TilsynAdapter(List<Tilsyn> tilsynList, OnTilsynLytter onTilsynLytter, List<Tilsyn> fullTilsynsliste) {
        this.tilsynListe = tilsynList;
        this.fullTilsynsliste = fullTilsynsliste;

        fullTilsynsliste.addAll(tilsynList);

        this.onTilsynLytter = onTilsynLytter;

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
        return new TilsynViewHolder(tilsynItemBinding, onTilsynLytter);
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

    @Override
    public Filter getFilter() {
        return tilsynsFilter;
    }

    public interface OnTilsynLytter {
        void onTilsynClick(int pos);
    }

    class TilsynViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnTilsynLytter onTilsynLytter;
        private TilsynItemBinding binding;


        public TilsynViewHolder(TilsynItemBinding binding, OnTilsynLytter onTilsynLytter) {
            super(binding.getRoot());
            this.binding = binding;
            this.onTilsynLytter = onTilsynLytter;
            itemView.setOnClickListener(this);

        }


        public void bind(Tilsyn tilsyn) {
            binding.setTilsyn(tilsyn);
            binding.executePendingBindings();

        }

        @Override
        public void onClick(View v) {

            onTilsynLytter.onTilsynClick(getAdapterPosition());
        }
    }

}





