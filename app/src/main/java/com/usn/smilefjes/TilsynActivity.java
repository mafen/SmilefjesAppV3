package com.usn.smilefjes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Lifecycling;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.data.entities.Kravpunkt;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.ActivityTilsynBinding;
import com.usn.smilefjes.ui.tilsyn.KravAdapter;
import com.usn.smilefjes.ui.tilsyn.TilsynsViewModel;

import java.util.ArrayList;
import java.util.List;

public class TilsynActivity extends AppCompatActivity {

    private ActivityTilsynBinding binding;

    private TilsynsViewModel tilsynsViewModel;

    final List<Kravpunkt> kravListe = new ArrayList<>();

    RecyclerView recyclerView;

    KravAdapter kravAdapter = new KravAdapter(kravListe);

    String test2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_tilsyn);
        binding.executePendingBindings();


        Intent intent = getIntent();


        //tilsynsViewModel = new ViewModelProvider(this).get(TilsynsViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        //Deklarasjon av recyclerview og dens adapter

        recyclerView = binding.recyclerViewKrav;
        recyclerView.setLayoutManager(layoutManager);


        Kravpunkt kravpunkt = new Kravpunkt("Ansvaret til driftsansvarlige");

        kravListe.add(kravpunkt);
        kravListe.add(new Kravpunkt("Opplæring og kompetanse"));
        kravListe.add(new Kravpunkt("Internkontroll"));
        kravListe.add(new Kravpunkt("Håndvask"));




        recyclerView.setAdapter(kravAdapter);
        recyclerView.setHasFixedSize(true);

        binding.setTilsyn((Tilsyn)intent.getSerializableExtra("test"));



/*
        tilsynsViewModel.getTilsynListe().observe(this, new Observer<List<Tilsyn>>() {
            @Override
            public void onChanged(List<Tilsyn> tilsynsListeNett) {
                tilsynsListe.addAll(tilsynsListeNett);
                full.addAll(tilsynsListeNett);
                Collections.sort(tilsynsListe);
                Collections.reverse(tilsynsListe);

                tilsynAdapter.setTilsynListe(tilsynsListe);

            }

        });*/


     /*   if(test2.length() != 0){
            tilsynsViewModel.getTilsyn(test2).observe(this, new Observer<Tilsyn>() {
                @Override
                public void onChanged(Tilsyn tilsyn) {
                }

            });
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.close_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.close){
            finish();
            //binding.unbind();
            //recyclerView.invalidate();
            //tilsynsViewModel.getTilsyn(test2).removeObservers(this);
        }


        return  true;
    }


}
