package com.usn.smilefjes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.data.entities.Kravpunkt;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.ActivityTilsynBinding;
import com.usn.smilefjes.ui.kravpunkt.KravAdapter;
import com.usn.smilefjes.ui.kravpunkt.KravViewModel;
import com.usn.smilefjes.ui.tilsyn.TilsynsViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TilsynActivity extends AppCompatActivity {

    private ActivityTilsynBinding binding;

    private KravViewModel kravViewModel;

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


        kravViewModel = new ViewModelProvider(this).get(KravViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        //Deklarasjon av recyclerview og dens adapter

        recyclerView = binding.recyclerViewKrav;
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(kravAdapter);
        recyclerView.setHasFixedSize(true);

        binding.setTilsyn((Tilsyn)intent.getSerializableExtra("test"));



        kravViewModel.getKravListe(((Tilsyn) intent.getSerializableExtra("test")).getTilsynid()).observe(this, new Observer<List<Kravpunkt>>() {
            @Override
            public void onChanged(List<Kravpunkt> kravpunktListeNett) {

                kravListe.addAll(kravpunktListeNett);
                Collections.sort(kravListe, Kravpunkt::compareTo);
                kravAdapter.setKravListe(kravListe);

            }
        });


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
