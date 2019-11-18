
package com.usn.smilefjes.ui.tilsyn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.*;

import java.util.ArrayList;

public class TilsynsFragment extends Fragment {

    FragmentTilsynBinding bindingTilsyn;

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
        //recyclerView.setHasFixedSize(true);

        ArrayList<Tilsyn> tilsynsListe = new ArrayList<>();

       Tilsyn test = new Tilsyn("Baker Brun Telegrafen",997046722 );
       test.setPostNr(5014);
       test.setTotalKarakter(5);
       test.setAdresse("Starvhusgaen 4");
       test.setPostSted("Bergen");
       //test.setOrgNr(997046722);

       int ii = 0;
        tilsynsListe.add(test);
        for (int i = 0; i < 50 ; i++) {
            tilsynsListe.add(new Tilsyn("Baker Brun Telegrafen",997046722 ));
        }



        final TilsynAdapter tilsynAdapter = new TilsynAdapter(tilsynsListe);
        recyclerView.setAdapter(tilsynAdapter);

        tilsynsViewModel = new ViewModelProvider(this).get(TilsynsViewModel.class);

        //Log.d("Size", ""+ tilsynsViewModel.getTilsynListe().getValue().isEmpty());


        return root;
    }


}
