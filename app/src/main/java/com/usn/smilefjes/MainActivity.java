package com.usn.smilefjes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.ActivityMainBinding;
import com.usn.smilefjes.ui.tilsyn.TilsynAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    /*    // AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigasjon_tilsyn)
                .build();*/
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /*binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setUpRecyclerView();
            }
        });*/


        //setUpRecyclerView();

    }

    /*private void setUpRecyclerView(){
        RecyclerView recyclerView = binding.recyclerViewTilsyn;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);

        List<Tilsyn> tilsynsListe = new ArrayList<>();

        tilsynsListe.add(new Tilsyn("Name"));
        tilsynsListe.add(new Tilsyn("HI"));

        TilsynAdapter tilsynAdapter = new TilsynAdapter(tilsynsListe);
        recyclerView.setAdapter(tilsynAdapter);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.open_settings:
                showSettings();
                return true;

            default: return super.onOptionsItemSelected(item);

        }
    }

    private void showSettings() {
        Toast.makeText(this, "Open settings", Toast.LENGTH_SHORT);
    }



}
