package com.usn.smilefjes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.ActivityTilsynBinding;
import com.usn.smilefjes.ui.tilsyn.TilsynsFragment;
import com.usn.smilefjes.ui.tilsyn.TilsynsViewModel;

public class TilsynActivity extends AppCompatActivity {

    private ActivityTilsynBinding binding;

    private TilsynsViewModel tilsynsViewModel;

    String test2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_tilsyn);



        binding = DataBindingUtil.setContentView(this, R.layout.activity_tilsyn);

        binding.setLifecycleOwner(this);

        Intent intent = getIntent();

        tilsynsViewModel = new ViewModelProvider(this).get(TilsynsViewModel.class);

        test2 = intent.getStringExtra("test");


        if(test2.length() != 0){
            tilsynsViewModel.getTilsyn(test2).observe(this, new Observer<Tilsyn>() {
                @Override
                public void onChanged(Tilsyn tilsyn) {
                    binding.setTilsyn(tilsyn);
                }

            });
        }




        Log.d("HI", test2);

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
            tilsynsViewModel.getTilsyn(test2).removeObservers(this);
        }



        return  true;
    }

    }
