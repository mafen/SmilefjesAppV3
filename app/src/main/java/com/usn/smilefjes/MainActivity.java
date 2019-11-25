package com.usn.smilefjes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.ActivityMainBinding;
import com.usn.smilefjes.ui.tilsyn.TilsynsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    MutableLiveData<List<Tilsyn>> listMutableLiveData;

    public MutableLiveData<Tilsyn> tilsyn = new MutableLiveData<>() ;


    private final List<Tilsyn> tilsynsListe = new ArrayList<>();

    public static final String PREFS_FILE_NAME = "usn.smilefjes.prefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // En form for erstattning av normale SetContentView
        // Denne finner elemeneter selv og setter dem ut
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigasjon_tilsyn)
                .build();





        if (!isOnline()) {
            {
                Toast internet = Toast.makeText(this, "Kan ikke koble til internet", Toast.LENGTH_SHORT);
                internet.show();
            }
        }


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.open_settings) {

            Intent intent = new Intent(this, SettingsActivity.class );
            startActivity(intent);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("WeakerAccess")
    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return (networkInfo != null && networkInfo.isConnected());
    }




    private void showSettings() {
        Toast open_settings = Toast.makeText(this, "Open settings", Toast.LENGTH_SHORT);
        open_settings.show();



    }



}
