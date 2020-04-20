
package com.usn.smilefjes.ui.tilsyn;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.MainActivity;
import com.usn.smilefjes.R;
import com.usn.smilefjes.TilsynActivity;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.FragmentTilsynBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class TilsynsFragment extends Fragment implements TilsynAdapter.OnTilsynLytter {

    private final  static int MIN_LOKALIASJON = 1;
    private final List<Tilsyn> tilsynsListe = new ArrayList<>();
    private final List<Tilsyn> full = new ArrayList<>();
    private final List<Tilsyn> kopi = new ArrayList<>();
    RecyclerView recyclerView;
    String postkode= "";
    private LocationManager locationMananger;
    private String locationGiver = locationMananger.GPS_PROVIDER;
    private Location MinLokal;

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            MinLokal = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    private TilsynAdapter tilsynAdapter = new TilsynAdapter(tilsynsListe, this, full);
    ItemTouchHelper touchHelper = new ItemTouchHelper(
            new SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                      @NonNull RecyclerView.ViewHolder target) {
                    int fraPos = viewHolder.getAdapterPosition();
                    int tilPos = target.getAdapterPosition();


                    Collections.swap(tilsynsListe, fraPos, tilPos);
                    tilsynAdapter.notifyItemMoved(fraPos, tilPos);
                    return true;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    Log.d("Removed", tilsynsListe.get(viewHolder.getAdapterPosition()).getNavn());

                    tilsynsListe.remove(viewHolder.getAdapterPosition());
                    tilsynAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());


                }
            });
    private FragmentTilsynBinding bindingTilsyn;
    private TilsynsViewModel tilsynsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MIN_LOKALIASJON){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    public void finnPostkode(){

        if(MinLokal != null){

            Geocoder geocoder =  new Geocoder(getContext());
            List<Address> geoAdress = null;
            try {
                if (geocoder.isPresent()){
                    geoAdress = geocoder.getFromLocation(MinLokal.getLatitude(), MinLokal.getLongitude(), 1);
                    if(geoAdress != null && geoAdress.size() > 0){
                        Address address = geoAdress.get(0);
                        postkode =  address.getPostalCode();
                    }

                }
            }catch (Exception e){

            }
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        if (locationMananger.isProviderEnabled(locationGiver) && locationListener!=null) {
            try {
                locationMananger.requestLocationUpdates(locationGiver, 1000, 0, locationListener);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(getContext(), "Aktiver "+locationGiver+" under Location i Settings", Toast.LENGTH_LONG).show();



    }

    // Dette er working in prgress
    // Men ble ikke ferdig med det
    // Denne skulle lese fra SharedPreferences
    private void letAar() {

        SharedPreferences myPreferences  = PreferenceManager.getDefaultSharedPreferences(getContext());
        lesTilsynMedAar(myPreferences.getString("aar", "2020"));


    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        FragmentActivity activity = this.getActivity();
        boolean isChangingConfigurations = activity != null && activity.isChangingConfigurations();
        this.getViewModelStore();
        if (!isChangingConfigurations) {
            this.getViewModelStore().clear();
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        bindingTilsyn = FragmentTilsynBinding.inflate(inflater, container, false);

        tilsynsViewModel = new ViewModelProvider(this).get(TilsynsViewModel.class);



        View root = bindingTilsyn.getRoot();

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());


        //Deklarasjon av recyclerview og dens adapter

        recyclerView = bindingTilsyn.recyclerViewTilsyn;
        recyclerView.setLayoutManager(layoutManager);


        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(tilsynAdapter);

        //letAar();

        locationMananger = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if(! locationMananger.isProviderEnabled(locationGiver)){

        }
        else {
            int tilgangsSjekk = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);

            if(tilgangsSjekk != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MIN_LOKALIASJON);
            }
            else {

            }
        }

        getListe();
        letAar();

        return root;
    }

    private void getListe() {
        tilsynsViewModel.getTilsynListe().observe(this, new Observer<List<Tilsyn>>() {
            @Override
            public void onChanged(List<Tilsyn> tilsynsListeNett) {
                tilsynsListe.addAll(tilsynsListeNett);
                full.addAll(tilsynsListeNett);

                Collections.sort(tilsynsListe);
                Collections.reverse(tilsynsListe);
                kopi.addAll(tilsynsListe);

                tilsynAdapter.setTilsynListe(tilsynsListe);

            }

        });
    }

    /**
     * @param menu
     * @param menuInflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, @NotNull MenuInflater menuInflater) {

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tilsynAdapter.getFilter().filter(newText);
                tilsynAdapter.notifyDataSetChanged();
                return false;
            }
        });




    }

    public void lesTilsynMedAar(String aar){
        tilsynsListe.clear();
        tilsynsViewModel.getTilsynMedAar(aar).observe(this, new Observer<List<Tilsyn>>() {

            @Override
            public void onChanged(List<Tilsyn> tilsynList) {

                full.addAll(tilsynList);

                tilsynAdapter.setTilsynListe(tilsynsListe);


            }

        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(item.getItemId() == R.id.app_bar_gps){


            finnPostkode();

            tilsynsListe.clear();
            tilsynsViewModel.getTilsyn1(postkode).observe(this, new Observer<List<Tilsyn>>() {

                @Override
                public void onChanged(List<Tilsyn> tilsynList) {

                    full.addAll(tilsynList);

                    tilsynAdapter.setTilsynListe(tilsynsListe);


                }

            });





        }

        if(item.getItemId() == R.id.app_bar_reset){
           tilsynsListe.clear();
            tilsynsListe.addAll(kopi);

            tilsynAdapter.setTilsynListe(tilsynsListe);

           //letAar();


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTilsynClick(int pos) {


        // Denne tar inn pos fra hvor enn brukeren er in listen og sender det videre slik at man kan lage et intent
        // og vise detaljer på kravpunket

        Intent intent = new Intent(getContext(), TilsynActivity.class);
        if (tilsynsListe.get(pos).getTilsynid() != null && isOnline()) {
            intent.putExtra("tilsyn", tilsynsListe.get(pos));
            FragmentActivity activity = this.getActivity();

            if (activity != null)
                activity.startActivityFromFragment(this, intent, 1);
        }
        else {
            Toast internet = Toast.makeText(getContext(), "Kan ikke koble til internett", Toast.LENGTH_SHORT);
            internet.show();
        }


    }

    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) (getActivity())
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }


}