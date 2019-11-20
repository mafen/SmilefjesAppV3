
package com.usn.smilefjes.ui.tilsyn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.*;

import java.util.ArrayList;
import java.util.List;


public class TilsynsFragment extends Fragment {

    FragmentTilsynBinding bindingTilsyn;

    int i = 0;

    private TilsynsViewModel tilsynsViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("Frag", "I was killed");

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        bindingTilsyn = FragmentTilsynBinding.inflate(inflater, container, false);

        View root = bindingTilsyn.getRoot();

        RecyclerView recyclerView = bindingTilsyn.recyclerViewTilsyn;

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final List<Tilsyn> tilsynsListe = new ArrayList<>();

        final TilsynAdapter tilsynAdapter = new TilsynAdapter(tilsynsListe);
        recyclerView.setAdapter(tilsynAdapter);
        recyclerView.setHasFixedSize(true);

        tilsynsViewModel = new ViewModelProvider(this).get(TilsynsViewModel.class);


        tilsynsViewModel.getTilsynListe().observe(this, new Observer<List<Tilsyn>>() {
            @Override
            public void onChanged(List<Tilsyn> tilsynsListeNett) {
                tilsynAdapter.setTilsynListe(tilsynsListeNett);

            }

        });


        return root;
    }


}
